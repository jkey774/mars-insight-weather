# mars-insight-weather
This is a Java Spring Boot web application deployed to Heroku that fetches and displays Mars weather data returned from NASA's Mars Weather Service API.

This was just an attempt to recreate this: https://mars.nasa.gov/insight/weather/

This app is running on a free dyno so it can be slow to load if the application is unused for a while since it gets unloaded from the server memory. On the first hit it gets loaded and then stays loaded until some time passes without anyone accessing it. Heroku does this to conserve resources and potentially as a business model to encourage paying for better responsiveness.
