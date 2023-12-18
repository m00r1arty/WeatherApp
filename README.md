# WeatherApp

## Project Description

This project is a mobile application to display the current weather and forecast for the next 5 days. The app provides a user-friendly and visually appealing user interface that allows users to easily access weather information.

## Code Optimization
- Optimized code for improved performance and efficiency.
- Adhered to best practices for Android development, including the use of `Clean Architecture` with MVVM design pattern, `LiveData` for `Databinding`, and Kotlin `coroutines` for asynchronous tasks.

![image](https://github.com/m00r1arty/WeatherApp/assets/70763902/a43bfef6-6620-4e1c-bd4b-c9e69e036c11)


## Interface

![preview](https://github.com/m00r1arty/WeatherApp/assets/70763902/a5f52eb0-e164-4d9b-8655-f35d0211960e)

### Displaying Current Weather

- A simple and user-friendly interface is implemented.
- UI elements include `TextViews`, `ImageViews`, `RecyclerView`, and `CardView`.
- Displays current temperature, weather description, and weather icon.
- Implemented a swipe-to-refresh feature for manually updating weather data.

![structure](https://github.com/m00r1arty/WeatherApp/assets/70763902/fae71e5a-3e73-4ce9-9baf-a166a58257ae) 

### Displaying Weather Forecast

- Utilizes a `RecyclerView` to show a 5-day weather forecast.
- Each RecyclerView item includes date, weather description, minimum and maximum temperature, and a weather icon for each day.

### Location-Based Weather Search
- Implements a feature allowing users to search for weather by location, either by entering a city name or using the device's current location.
- Utilized FusedLocationProviderClient for obtaining the current device location.

```kotlin
private fun requireLocation(onComplete: (Location?) -> Unit = {}) {
        val cancelToken = CancellationTokenSource()
        if (isLocationPermissionGranted()) {
            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                cancelToken.token
            ).addOnCompleteListener {
                onComplete(it.result)
            }
        }
    }
```
### Unit Conversion
- Added a feature to switch between different temperature units (e.g., Celsius and Fahrenheit) based on user preferences.
- Introduced corresponding UI element button for convenient unit switching.

### Retrieving Weather Data
**Using Weather API**

- Integrated a weather API, such as the Weather API, to retrieve weather data for the current location or user-selected location.
- Implemented asynchronous network calls using Retrofit to fetch data from the API in the background.

### Data Processing
Analyzes the JSON response from the API to extract relevant weather information, such as temperature, description, and weather icon.
