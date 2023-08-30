<template>
  <div class="home">
    <section class="greetings">
      <h1>السلام عليكم ورحمة الله وبركاته</h1>
      <h1>Welcome to Tanzeel</h1>
    </section>
  
    <section class="prayer-times">
      <div class="salah-time">
        <h1>Prayer Times</h1>
        <ul>
          <li v-for="(time, prayer) in prayerTimes" :key="prayer">
            {{ prayer }}: {{ time }}
          </li>
        </ul>
      </div>
    </section>
    
    <section class="footer">
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
          this.prayerTimes = timings;
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
/* Styling here */
</style>
