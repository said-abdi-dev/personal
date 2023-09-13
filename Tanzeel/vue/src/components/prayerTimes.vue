<template>
  <div class="prayer-times-container">
    <h1 class="city-name">{{ cityName }}</h1>

    <div class="location-form">
      <label for="city">City:</label>
      <vue-autosuggest id="city" v-model="location.city" :suggestions="citySuggestions" @input="onCityInput"
        placeholder="Enter city"></vue-autosuggest>

      <vue-autosuggest id="country" v-model="location.country" :suggestions="countrySuggestions" @input="onCountryInput"
        placeholder="Enter country"></vue-autosuggest>

      <button @click="fetchPrayerTimes">Get Prayer Times</button>
    </div>

    <div class="prayer-time-card">
      <div class="prayer-time-heading">Fajr</div>
      <div class="prayer-time">
        {{ formatTimeTo12HourClock(prayerTimes.Fajr) }}
      </div>
    </div>
    <div class="prayer-time-card">
      <div class="prayer-time-heading">Dhuhr</div>
      <div class="prayer-time">
        {{ formatTimeTo12HourClock(prayerTimes.Dhuhr) }}
      </div>
    </div>
    <div class="prayer-time-card">
      <div class="prayer-time-heading">Asr</div>
      <div class="prayer-time">
        {{ formatTimeTo12HourClock(prayerTimes.Asr) }}
      </div>
    </div>
    <div class="prayer-time-card">
      <div class="prayer-time-heading">Maghrib</div>
      <div class="prayer-time">
        {{ formatTimeTo12HourClock(prayerTimes.Maghrib) }}
      </div>
    </div>
    <div class="prayer-time-card">
      <div class="prayer-time-heading">Isha</div>
      <div class="prayer-time">
        {{ formatTimeTo12HourClock(prayerTimes.Isha) }}
      </div>
    </div>

    <section class="footer" id="footer">
      <div class="creator-info">
        <p>Created by: Said Abdi</p>
        <p>
          LinkedIn:
          <a href="https://www.linkedin.com/in/said-abdi-dev/" target="_blank"></a>
        </p>
        <p>
          GitHub:
          <a href="https://github.com/said-abdi-dev" target="_blank"></a>
        </p>
      </div>
    </section>
  </div>
</template>

<script>
//import VueAutoSuggest from 'vue-autosuggest';
import PrayerTimeService from '@/services/PrayerTimeService';
import GoogleGeocodingService from '@/services/GoogleGeocodingService';
import AdhanTimeService from '../services/AdhanTimeService';

export default {
  props: {
    cityName: String,
  },
  data() {
    return {
      prayerTimes: {},
      location: {
        city: '',
        country: '',
        citySuggestion: [],
        countrySuggestion: [],
      },
    };
  },
  async mounted() {
    try {
      await this.getUserLocation();
    } catch (error) {
      console.error('Error during initialization:', error);
    }
  },
  // components: {
  //   VueAutoSuggest
  // },
  methods: {
    async getUserLocation() {
      if ('geolocation' in navigator) {
        try {
          const position = await this.getPosition();
          const { city, country } = await this.getCityAndCountryFromPosition(
            position
          );
          const currentDate = this.getCurrentDate();
          const timings = await PrayerTimeService.getPrayerTimesByDate(
            currentDate,
            city,
            country,
            3
          );
          this.prayerTimes = {
            Fajr: timings.Fajr,
            Sunrise: timings.Sunrise,
            Dhuhr: timings.Dhuhr,
            Asr: timings.Asr,
            Maghrib: timings.Maghrib,
            Isha: timings.Isha,
          };
        } catch (error) {
          console.error('Error fetching location or prayer times:', error);
        }
      } else {
        console.error('Geolocation is not available');
      }
    },
    getPosition() {
      return new Promise((resolve, reject) => {
        navigator.geolocation.getCurrentPosition(resolve, reject);
      });
    },
    async getCityAndCountryFromPosition(position) {
      const latitude = position.coords.latitude;
      const longitude = position.coords.longitude;
      const { city, country } =
        await GoogleGeocodingService.getCityAndCountryFromCoordinates(
          latitude,
          longitude
        );
      this.location.city = city;
      this.location.country = country;
      return { city, country };
    },
    getCurrentDate() {
      const now = new Date();
      return now.toISOString().slice(0, 10);
    },
    formatTimeTo12HourClock(time24) {
      const [hour24, minute] = time24.split(':');
      const hour12 = parseInt(hour24, 10) % 12 || 12;
      const ampm = parseInt(hour24, 10) < 12 ? 'AM' : 'PM';
      return `${hour12}:${minute} ${ampm}`;
    },
    async fetchPrayerTimes() {
      const { city, country } = this.location;

      const currentDate = new Date();
      const year = currentDate.getFullYear();
      const month = currentDate.getMonth() + 1;

      try {
        const todayPrayerTime = await AdhanTimeService(
          city,
          country,
          year,
          month
        );

        this.prayerTimes = todayPrayerTime;
      } catch (error) {
        console.error('Error fetching prayer times:', error);
      }
    },
    onCityInput(input) {
      this.location.citySuggestions = this.location.citySuggestions.filter(
        (city) => city.toLowerCase().includes(input.toLowerCase())
      );
    },
    onCountryInput(input) {
      this.location.countrySuggestions =
        this.location.countrySuggestions.filter((country) =>
          country.toLowerCase().includes(input.toLowerCase())
        );
    },
  },
};
</script>

<style scoped>
.prayer-times-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  background-color: #5c8374;
  border-radius: 8px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
  text-align: center;
  margin-bottom: 100px;
}

.city-name {
  font-size: 24px;
  margin-bottom: 10px;
  color: #333;
}

.prayer-time-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 10px 0;
  padding: 10px;
  background-color: #f4f4f4;
  border-radius: 5px;
  width: 200px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
}

.prayer-time-heading {
  font-size: 18px;
  font-weight: bold;
  color: #333;
}

.prayer-time {
  font-size: 20px;
  color: #555;
}

.location-selection {
  text-align: center;
  margin-top: 20px;
}

.location-form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

label {
  margin-bottom: 5px;
}

input {
  padding: 5px;
  margin-bottom: 10px;
}

button {
  background-color: #183d3d;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #5c8374;
}

.footer {
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  background-color: #5c8374;
  color: white;
  text-align: center;
  z-index: 3;
}
</style>
