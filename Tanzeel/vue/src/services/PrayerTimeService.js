// src/services/PrayerTimeService.js
import axios from 'axios';

const API_BASE_URL = 'http://api.aladhan.com/v1';

export default {
  getPrayerTimesByDate: async (date, city, country, method) => {
    const response = await axios.get(`${API_BASE_URL}/timingsByCity/${date}`, {
      params: {
        city,
        country,
        method,
      },
    });
    return response.data.data.timings;
  },
};
