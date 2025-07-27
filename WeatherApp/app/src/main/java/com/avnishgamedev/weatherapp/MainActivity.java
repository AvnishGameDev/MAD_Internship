package com.avnishgamedev.weatherapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

        etCityName = findViewById(R.id.etCityName);
        btnSeeWeather = findViewById(R.id.btnSeeWeather);
        tvWeather = findViewById(R.id.tvWeather);
        ivWeather = findViewById(R.id.ivWeather);
        rlLoading = findViewById(R.id.rlLoading);

        btnSeeWeather.setOnClickListener(v -> {
            rlLoading.setVisibility(View.VISIBLE);
            searchWeatherDate(etCityName.getText().toString());
        });

    }

    void searchWeatherDate(String city) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String apiKey = "bb739a085f08994a5fc77f60958594a7";
                String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    reader.close();

                    parseWeatherResponse(result.toString());
                } catch (Exception e) {
                    runOnUiThread(() -> rlLoading.setVisibility(View.GONE));
                    runOnUiThread(() ->
                    Toast.makeText(MainActivity.this, "Error with URL", Toast.LENGTH_SHORT).show());
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void parseWeatherResponse(String result) {
        try {
            StringBuilder textBuilder = new StringBuilder();
            JSONObject jsonObject = new JSONObject(result);
            JSONObject main = jsonObject.getJSONObject("main");
            final double temp = main.getDouble("temp");
            textBuilder.append("Temperature: ").append(temp).append("°C");

            JSONObject weatherObject = jsonObject.getJSONArray("weather").getJSONObject(0);
            final String description = weatherObject.getString("description");
            textBuilder.append("\nDescription: ").append(description);

            JSONObject sysObject = jsonObject.getJSONObject("sys");
            final String country = sysObject.getString("country");
            textBuilder.append("\nCountry: ").append(country);

            JSONObject windObject = jsonObject.getJSONObject("wind");
            final double speed = windObject.getDouble("speed");
            textBuilder.append("\nWind Speed: ").append(speed).append(" m/s");

            runOnUiThread(() -> tvWeather.setText(textBuilder.toString()));
            runOnUiThread(() -> rlLoading.setVisibility(View.GONE));

            // Load Weather Icon
            String iconcode = weatherObject.getString("icon");
            URL imageUrl = new URL("https://openweathermap.org/img/wn/" + iconcode + "@2x.png");
            Bitmap bmp = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());
            runOnUiThread(() ->ivWeather.setImageBitmap(bmp));
        } catch (Exception e) {
            runOnUiThread(() -> rlLoading.setVisibility(View.GONE));
            e.printStackTrace();
        }
    }
}