const axios = require('axios');
const Restaurant = require('./Restaurant');
const cache = require('./cache');

const handleYelpRequest = async (request, response) => {
  const {city} = request.query
  const url = `https://api.yelp.com/v3/businesses/search?categories=restaurants&limit=20&location=${city}`;
  const key = `yelp#${city}`
  if (cache[key] && (Date.now() - cache[key].timestamp < 3600000)) { // New data if data older than 1 hour
    response.status(200).send(cache[key].yelpData);
  } else {
    cache[key] = {};
    cache[key].timestamp = Date.now()
    try {
      const apiResults = await axios.get(url, {headers: {'Authorization': `Bearer ${process.env.YELP_API_KEY}`}});
      cache[key].yelpData = apiResults.data.businesses.map(element => new Restaurant(element, cache[key].timestamp));
      response.status(200).send(cache[key].yelpData);
    } catch (event) {
      response.status(404).send('Nothing good');
    }
  }
}
