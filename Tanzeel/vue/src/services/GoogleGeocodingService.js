// src/services/GoogleGeocodingService.js
import axios from 'axios';

const API_BASE_URL = 'https://maps.googleapis.com/maps/api/geocode/json';
const API_KEY = 'AIzaSyDTXUQoRMJhv22Df787QSrYXuA2MixP74E'; 
export default {
  async getCityAndCountryFromCoordinates(latitude, longitude) {
    const response = await axios.get(`${API_BASE_URL}?latlng=${latitude},${longitude}&key=${API_KEY}`);

    const results = response.data.results;
    let city = '';
    let country = '';

    if (results && results.length > 0) {
      for (const addressComponent of results[0].address_components) {
        if (addressComponent.types.includes('locality')) {
          city = addressComponent.long_name;
        }
        if (addressComponent.types.includes('country')) {
          country = addressComponent.long_name;
        }
      }
    }

    return { city, country };
  },
};
