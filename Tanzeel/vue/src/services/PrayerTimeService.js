import axios from 'axios';

const options = {
  method: 'GET',
  url: 'https://aladhan.p.rapidapi.com/timingsByCoordinates',
  params: {
    latitude: null, 
    longitude: null, 
    method: 3, 
  },
  headers: {
    'X-RapidAPI-Key': '0fe371429dmsh4efb3bc2fedf3bbp1ac14ejsn292a99d6a1b8',
    'X-RapidAPI-Host': 'aladhan.p.rapidapi.com',
  },
};

// Get user's geolocation
navigator.geolocation.getCurrentPosition(
  async (position) => {
    options.params.latitude = position.coords.latitude;
    options.params.longitude = position.coords.longitude;

    try {
      const response = await axios.request(options);
      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
  },
  (error) => {
    console.error('Error getting geolocation:', error);
  }
);
