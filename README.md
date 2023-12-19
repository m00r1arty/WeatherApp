# WeatherApp

## Project Description

This project is a mobile application to display the current weather and forecast for the next 5 days. The app provides a user-friendly and visually appealing user interface that allows users to easily access weather information.

## Code Optimization
- Optimized code for improved performance and efficiency.
- Adhered to best practices for Android development, including the use of `Clean Architecture` with `MVVM` design pattern, `LiveData` for `Databinding`, and Kotlin `coroutines` for asynchronous tasks.

![structure](https://github.com/m00r1arty/WeatherApp/assets/70763902/a43bfef6-6620-4e1c-bd4b-c9e69e036c11)


## Interface

![preview](https://github.com/m00r1arty/WeatherApp/assets/70763902/9810e8c2-6b5e-40da-a0f0-282f73686d79)

### Displaying Current Weather

- A simple and user-friendly interface is implemented.
- UI elements include `TextViews`, `ImageViews`, `RecyclerView`, and `CardView`.
- Displays current temperature, weather description, and weather icon.
- Implemented a swipe-to-refresh feature for manually updating weather data.

![structure_layout](https://github.com/m00r1arty/WeatherApp/assets/70763902/25802f9b-23b0-4736-8fdb-8f282e8a524b)


### Displaying Weather Forecast

- Utilizes a `RecyclerView` to show a 5-day weather forecast.
- Each RecyclerView item includes date, weather description, minimum and maximum temperature, and a weather icon for each day.

## Location-Based Weather Search
- Implements a feature allowing users to search for weather by location, either by entering a city name or using the device's current location.

![image](https://github.com/m00r1arty/WeatherApp/assets/70763902/9eb259a1-4351-4213-b55e-e2520479597b)  ![image](https://github.com/m00r1arty/WeatherApp/assets/70763902/130cb906-84b6-4aae-94ee-271d7248a544)

- Utilized `FusedLocationProviderClient` for obtaining the current device location.

```kotlin
private fun isLocationPermissionGranted() =
    ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

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
## Unit Conversion
- Added a feature to switch between different temperature units (e.g., Celsius and Fahrenheit) based on user preferences.
- Introduced corresponding UI element button for convenient unit switching.

![image](https://github.com/m00r1arty/WeatherApp/assets/70763902/f1a5bd82-a002-4ef6-9da9-a5671d9ceef6)


## Retrieving Weather Data
**Using Weather API**

- Integrated a weather API, such as the **Weather API**, to retrieve weather data for the current location or user-selected location.
- Implemented asynchronous network calls using Retrofit to fetch data from the API in the background.

## Testing
- Develops modular and/or instrumental tests to verify various application functions, including fetching weather data from the API, updating the UI with weather data, and unit conversion.
- Utilizes testing tools like `JUnit`, `Mockito`, and `Espresso`.
 
**Example test**:
```kotlin
class WeatherApiTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var mockCall: Call<WeatherResponse>

    @Mock
    private lateinit var weatherApi: WeatherApi


    @Test
    fun getWeatherForecastSuccessTest() {

        whenever(weatherApi.getWeatherForecast(API_KEY, CITY, DAYS, AQI, ALERTS)).thenReturn(mockCall)
        whenever(mockCall.execute()).thenReturn(Response.success(WeatherResponse()))

        val response = weatherApi.getWeatherForecast(API_KEY, CITY, DAYS, AQI, ALERTS).execute()

        assert(response.isSuccessful)
    }

    @Test
    fun getWeatherForecastErrorTest() {
        whenever(weatherApi.getWeatherForecast(API_KEY, CITY, DAYS, AQI, ALERTS)).thenReturn(mockCall)
        whenever(mockCall.execute()).thenReturn(Response.error(404, "".toResponseBody(null)))

        val response = weatherApi.getWeatherForecast(API_KEY, CITY, DAYS, AQI, ALERTS).execute()

        assert(!response.isSuccessful)
    }

    companion object {
        private const val API_KEY = "6183a5484fc64341bce122716231312"
        private const val CITY = "London"
        private const val DAYS = 3
        private const val AQI = "no"
        private const val ALERTS = "no"
    }

}
```
