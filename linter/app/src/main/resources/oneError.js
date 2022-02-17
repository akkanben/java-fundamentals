const axios = require('axios');
const Forecast = require('./Forecast');
const cache = require('./cache')

const handleWeatherRequest = async (request, response) => {
  const {lat, lon} = request.query;
  const url = `https://api.weatherbit.io/v2.0/forecast/daily?lat=${lat}&lon=${lon}&key=${process.env.WEATHER_API_KEY}&days=5`;
  const key = `weather#${lat}#${lon}`;
  if (cache[key] && (Date.now() - cache[key].timestamp < 600000)) {
    response.status(200).send(cache[key].forecastData);
  } else {
    cache[key] = {};
    cache[key].timestamp = Date.now();
    try {
      const apiResults = await axios.get(url);
      cache[key].forecastData = apiResults.data.data.map(element => new Forecast(element, cache[key].timestamp));
      response.status(200).send(cache[key].forecastData);
    } catch (event) {
      response.status(404).send('Nothing good');
    }
  }
}
