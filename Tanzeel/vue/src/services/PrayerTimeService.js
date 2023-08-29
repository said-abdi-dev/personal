import axios from 'axios';

const API_KEY = '0fe371429dmsh4efb3bc2fedf3bbp1ac14ejsn292a99d6a1b8';
const API_HOST = 'aladhan.p.rapidapi.com';

export async function getPrayerTimes(date) {
  const options = {
    method: 'GET',
    url: `http://api.aladhan.com/v1/timingsByCity/${date}`,
    params: {
      city: 'auto', // Automatically fill the city based on location
    },
    headers: {
      'X-RapidAPI-Key': API_KEY,
      'X-RapidAPI-Host': API_HOST,
    },
  };

const response = await axios.request(options);
  return response.data.timings;
}