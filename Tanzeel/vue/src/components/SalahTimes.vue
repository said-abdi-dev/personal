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
import { getPrayerTimes } from '@/services/PrayerTimeService';

export default {
  data() {
    return {
      prayerTimes: {}, // You can set your prayer times data here
    };
  },
  async mounted() {
    try {
      const currentDate = this.getCurrentDate(); // Get current date in YYYY-MM-DD format
      const timings = await getPrayerTimes(currentDate);
      this.prayerTimes = timings;
    } catch (error) {
      console.error('Error fetching prayer times:', error);
    }
  },
  methods: {
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
