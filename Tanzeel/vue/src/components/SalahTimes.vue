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
            {{ console.log(prayer, time) }}
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

import prayerTimeService from '@/services/PrayerTimeService'

export default {
  data() {
    return {
      prayerTimes: {}, // You can set your prayer times data here
    };
  },
  async mounted() {
    try {
      const position = await this.getGeolocation();
      const response = await prayerTimeService(
        position.coords.latitude,
        position.coords.longitude,
        3 // Method value
      );
      this.prayerTimes = response.data.timings;
    } catch (error) {
      console.error('Error fetching prayer times:', error);
    }
  },
  methods: {
    getGeolocation() {
      return new Promise((resolve, reject) => {
        navigator.geolocation.getCurrentPosition(resolve, reject);
      });
    },
  },
};
</script>

<style scoped>
/* Styling here */
</style>
