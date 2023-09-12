import axios from 'axios';

const API_BASE_URL = 'http://api.aladhan.com/v1';

class AdhanTimeService {
  static async getPrayerTimes(city, country, year, month) {
    try {
      const response = await axios.get(
        `${API_BASE_URL}/calendarByCity/${year}/${month}?city=${city}&country=${country}`
      );
      
      const { data } = response;
      
      const todayPrayerTimes = data.data[0].timings;
      
      return todayPrayerTimes;
    } catch (error) {
      throw new Error('Error fetching prayer times: ' + error.message);
    }
  }
}

export default AdhanTimeService;
