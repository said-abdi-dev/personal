<template>
  <div class="home">
    <section class="greetings" id="greetings">
      <h1 class="arabic-greeting">السلام عليكم ورحمة الله وبركاته</h1>
      <h1>Welcome to Tanzeel</h1>
    </section>
  
    <section class="prayer-times" id="prayer-times">
      <div class="salah-time">
        <h1>Prayer Times</h1>
        <ul>
          <li v-for="(time, prayer) in prayerTimes" :key="prayer">
            {{ prayer }}: {{ time }}
          </li>
        </ul>
      </div>
    </section>
    
    <section class="footer" id="footer">
      <div class="creator-info">
        <p>Created by: Said Abdi</p>
        <p>LinkedIn: <a href="https://www.linkedin.com/in/said-abdi-dev/" target="_blank">Said Abdi</a></p>
        <p>GitHub: <a href="https://github.com/said-abdi-dev" target="_blank">said-abdi-dev</a></p>
      </div>
    </section>
  </div>
</template>

<script>
import PrayerTimeService from '@/services/PrayerTimeService';
import GoogleGeocodingService from '@/services/GoogleGeocodingService';

export default {
  data() {
    return {
      prayerTimes: {},
      location: {
        city: '',
        country: '',
      },
      salahTimes: {
        fajr: '',
        sunrise: '',
        Dhuhr: '',
        Asr: '',
        Maghrib: '',
        Isha: '',
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
  methods: {
    async getUserLocation() {
      if ('geolocation' in navigator) {
        try {
          const position = await this.getPosition();
          const { city, country } = await this.getCityAndCountryFromPosition(position);
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
      const { city, country } = await GoogleGeocodingService.getCityAndCountryFromCoordinates(
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
  },
  
};
</script>

<style scoped>
.home {
  background-color: #040D12;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 0;
  padding: 0;
}

h1 {
  color: #5C8374;
}

p {
  color: #93B1A6;
}

ul li {
  color: #5C8374;
}

.footer {
  background-color: #24a3e7;
  padding: 20px 0;
}

.creator-info p {
  color: #93B1A6;
}

.creator-info a {
  color: #183D3D;
}

.creator-info a:hover {
  color: #5C8374;
}

.arabic-greeting {
  animation: slideIn 1s ease-in-out;
}

@keyframes slideIn {
  0% {
    transform: translateX(-100%);
    opacity: 0;
  }
  100% {
    transform: translateX(0);
    opacity: 1;
  }
}

html {
  scroll-behavior: smooth;
}

.navigation {
  display: none;
}
</style>
