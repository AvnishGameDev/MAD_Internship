# WeatherApp 🌤️

A weather information application that fetches real-time weather data from external APIs and displays current weather conditions for user-specified cities.

## 📱 Features

- **City Weather Search**: Search weather information for any city worldwide
- **Real-time Data**: Fetch current weather conditions from weather APIs
- **Weather Details**: Temperature, conditions, humidity, and more
- **Weather Icons**: Visual weather condition representations
- **Loading Indicators**: User feedback during data fetching
- **Error Handling**: Graceful handling of network and API errors
- **Clean Interface**: User-friendly weather display layout
- **Internet Connectivity**: Network-based weather data retrieval

## 🛠️ Technologies Used

- **Language**: Java
- **Networking**: HTTP requests for API communication
- **JSON Parsing**: Parse weather API response data
- **UI**: EditText, Button, TextView, ImageView for weather display
- **Threading**: Background network operations
- **Internet**: Network connectivity for API calls
- **Min SDK**: API 21+

## 📁 Project Structure

```
WeatherApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/avnishgamedev/weatherapp/
│   │   │   ├── MainActivity.java
│   │   │   ├── WeatherAPI.java
│   │   │   └── WeatherData.java (model)
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   └── activity_main.xml
│   │   │   ├── drawable/ (weather icons)
│   │   │   └── values/
│   │   └── AndroidManifest.xml
│   └── build.gradle
└── README.md
```

## 🔧 Implementation Details

### Core Components

#### MainActivity.java
- **City Input**: EditText for city name input
- **Weather Search**: Button to trigger weather data fetching
- **Data Display**: TextViews and ImageView for weather information
- **Loading Management**: Show/hide loading indicators during API calls
- **Error Handling**: Display user-friendly error messages

```java
public class MainActivity extends AppCompatActivity {
    EditText etCityName;
    Button btnSeeWeather;
    TextView tvWeather;
    ImageView ivWeather;
    RelativeLayout rlLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializeViews();
        setupClickListeners();
    }
    
    private void searchWeatherData(String cityName) {
        rlLoading.setVisibility(View.VISIBLE);
        // Fetch weather data in background thread
        new Thread(() -> {
            // API call implementation
        }).start();
    }
}
```

### Weather API Integration
```java
private void fetchWeatherData(String cityName) {
    try {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" 
                      + cityName + "&appid=" + API_KEY + "&units=metric";
        
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        
        parseWeatherResponse(response.toString());
        
    } catch (Exception e) {
        handleError(e);
    }
}
```

### JSON Response Parsing
```java
private void parseWeatherResponse(String jsonResponse) {
    try {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        
        // Extract weather information
        JSONObject main = jsonObject.getJSONObject("main");
        double temperature = main.getDouble("temp");
        int humidity = main.getInt("humidity");
        
        JSONArray weatherArray = jsonObject.getJSONArray("weather");
        JSONObject weather = weatherArray.getJSONObject(0);
        String description = weather.getString("description");
        String icon = weather.getString("icon");
        
        updateUI(temperature, humidity, description, icon);
        
    } catch (JSONException e) {
        handleParsingError(e);
    }
}
```

## 🎯 Learning Objectives

- REST API integration and HTTP requests
- JSON data parsing and manipulation
- Background threading for network operations
- Error handling for network operations
- User interface updates from background threads
- Weather data interpretation and display
- Internet permission and connectivity handling

## 🚀 Getting Started

### API Setup
1. Register for a weather API key (OpenWeatherMap, WeatherAPI, etc.)
2. Add your API key to the application
3. Configure API endpoints and parameters

### Running the App
1. Open the project in Android Studio
2. Add your weather API key to the code
3. Ensure device has internet connection
4. Run on emulator or physical device
5. Enter city name and tap "See Weather"
6. View weather information display

## 📋 Required Permissions

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

## 🔄 App Flow

1. **Launch**: Open weather app with city input field
2. **City Input**: Enter desired city name
3. **Search**: Tap "See Weather" button
4. **Loading**: Display loading indicator while fetching data
5. **API Call**: Make HTTP request to weather service
6. **Data Parse**: Parse JSON response for weather information
7. **Display**: Show weather data with appropriate icons and text
8. **Error Handling**: Display error message if request fails

## 🌤️ Weather Information

### Displayed Data
- **Temperature**: Current temperature in Celsius/Fahrenheit
- **Weather Condition**: Sunny, cloudy, rainy, etc.
- **Humidity**: Relative humidity percentage
- **Weather Description**: Detailed weather description
- **Weather Icon**: Visual representation of conditions
- **City Name**: Confirmation of searched location

### Weather Icons
- **Sunny**: ☀️ Clear sky conditions
- **Cloudy**: ☁️ Overcast conditions
- **Rainy**: 🌧️ Precipitation conditions
- **Snowy**: ❄️ Snow conditions
- **Stormy**: ⛈️ Thunderstorm conditions
- **Foggy**: 🌫️ Misty conditions

## 🎨 UI Components

- **City Input Field**: EditText for city name entry
- **Search Button**: Trigger weather data retrieval
- **Weather Display**: TextView showing temperature and conditions
- **Weather Icon**: ImageView displaying condition-appropriate icon
- **Loading Indicator**: Progress indicator during API calls
- **Error Messages**: Toast or TextView for error communication

## 🌐 API Integration

### Weather API Services
- **OpenWeatherMap**: Comprehensive weather data service
- **WeatherAPI**: Alternative weather data provider
- **AccuWeather**: Professional weather forecasting
- **Dark Sky**: (Deprecated) Previously popular weather API

### API Response Structure
```json
{
  "weather": [
    {
      "main": "Clear",
      "description": "clear sky",
      "icon": "01d"
    }
  ],
  "main": {
    "temp": 25.3,
    "humidity": 60,
    "pressure": 1013
  },
  "name": "London"
}
```

## 🔄 Future Enhancements

- **5-Day Forecast**: Extended weather forecasting
- **Current Location**: GPS-based automatic location detection
- **Multiple Cities**: Save and track multiple cities
- **Weather Alerts**: Push notifications for severe weather
- **Detailed Metrics**: Wind speed, visibility, UV index
- **Historical Data**: Past weather information
- **Weather Maps**: Interactive weather maps
- **Units Toggle**: Switch between metric and imperial units
- **Offline Mode**: Cache recent weather data for offline viewing
- **Widgets**: Home screen weather widgets
- **Voice Search**: Voice input for city names
- **Favorites**: Quick access to frequently checked cities
- **Weather Trends**: Charts showing weather patterns
- **Social Sharing**: Share weather conditions with others

## 🐛 Common Issues

- **API Key**: Ensure valid API key is configured
- **Network Connectivity**: Check internet connection
- **City Names**: Handle invalid or misspelled city names
- **API Limits**: Manage API request rate limits
- **JSON Parsing**: Handle malformed API responses
- **Threading**: Ensure UI updates on main thread

## 📱 Network Handling

### Error Scenarios
- **No Internet**: Display offline message
- **API Errors**: Handle 404, 500, and other HTTP errors
- **Timeout**: Handle request timeout situations
- **Invalid City**: Handle city not found responses
- **Rate Limiting**: Handle API rate limit exceeded

### Network Best Practices
```java
// Check network connectivity
private boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager = 
        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    return networkInfo != null && networkInfo.isConnected();
}
```

## 🎯 Use Cases

- **Travel Planning**: Check weather before traveling
- **Daily Planning**: Plan daily activities based on weather
- **Agriculture**: Weather conditions for farming decisions
- **Outdoor Events**: Event planning with weather considerations
- **Sports**: Weather impact on outdoor sports and activities
- **Emergency Preparedness**: Severe weather awareness

## 🔒 Privacy & Data

- **Location Privacy**: No automatic location tracking (user enters city)
- **API Security**: Secure API key management
- **Data Caching**: Minimal local data storage
- **No Personal Data**: No collection of personal information

## 📊 Weather Data Sources

### Professional APIs
- **Accuracy**: Professional meteorological data
- **Real-time**: Current weather conditions
- **Global Coverage**: Worldwide city coverage
- **Reliability**: Dependable data sources with uptime guarantees

---

**Part of MAD Internship - Android Development Portfolio**

**Note**: This app requires an internet connection and a valid weather API key to function. Please obtain an API key from a weather service provider before running the application.
