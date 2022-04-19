package com.example.amalmidterm2;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
//import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {
    private TextView reservation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        reservation=(TextView) findViewById(R.id.txtDate);
        Button button= (TableRow) findViewById(R.id.bttnDate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity2.this,d,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
    Calendar c= Calendar.getInstance();
    DateFormat fmtDate= DateFormat.getDateInstance();
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            reservation.setText("Your reservation is set for "+fmtDate.format(c.getTime()));
        }};


    // public boolean onCreateOptionsMenu(Menu menu){

    // getMenuInflater().inflate(R.menu.menu_main,menu);
    // return true;

    // }






    // we"ll make HTTP request to this URL to retrieve weather conditions
    String url = "https://api.openweathermap.org/data/2.5/weather?lat=21.48584&lon=39.1925&appid=5b34c288d82869579f2ee0de90e43141";
    ImageView weatherBackground;
    // Textview to show temperature and description
    TextView temperature, description, humidity, feels_like, sunrise_t, sunset_t;
    Spinner city;
    Button btnChange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // JSON object that contains weather information

        //link graphical items to variables
        temperature = findViewById(R.id.temperature);

        humidity = findViewById(R.id.humidity);
        sunrise_t = findViewById(R.id.Sunrise);
        sunset_t = findViewById(R.id.sunset);
        city = findViewById(R.id.cities);
        btnChange = findViewById(R.id.button);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String clicked;
                clicked = city.getSelectedItem().toString();

                switch (clicked) {
                    case "London":
                        url = "https://api.openweathermap.org/data/2.5/weather?lat=51.5072&lon=N, 0.1276&appid=5b34c288d82869579f2ee0de90e43141&units=metric";
                        weather(url);
                        break;
                    case "Jeddah":
                        url = "https://api.openweathermap.org/data/2.5/weather?lat=21.4858&lon=39.1925&appid=5b34c288d82869579f2ee0de90e43141&units=metric";
                        weather(url);
                        break;
                    case "Dammam":
                        url = "https://api.openweathermap.org/data/2.5/weather?lat=26.4207&lon=50.0888&appid=5b34c288d82869579f2ee0de90e43141&units=metric";
                        weather(url);
                        break;

                }
            }
        });

        weather(url);
        weatherBackground = findViewById(R.id.weatherbackground);

    }


    public void weather(String url){
        @SuppressLint("SetTextI18n") JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            Log.d("Amal", "Response Received");
            Log.d("Amal", response.toString());
            try {
                JSONObject jsonMain = response.getJSONObject("main");
                JSONObject jsonSys = response.getJSONObject("sys");

                double temp = jsonMain.getDouble("temp");
                Log.d("Amal","temp=" + temp);
                temperature.setText(String.valueOf(temp)+"°C");

                double feels = jsonMain.getDouble("feels_like");
                Log.d("Amal","feels_like=" + feels);
                feels_like.setText("Feels Like: "+String.valueOf(feels)+"°C");

                double humid = jsonMain.getDouble("humidity");
                Log.d("Amal","humidity=" + feels);
                humidity.setText("Humidity: "+String.valueOf(humid));

                String townResponse = response.getString("name");
                description.setText(townResponse);

                long sunrise = jsonSys.getLong("sunrise");
                long sunset = jsonSys.getLong("sunset");

                Date sunriseDate = new Date(1000*sunrise);
                String sunriseTime = new SimpleDateFormat("H:mm").format(sunriseDate);
                Date sunsetDate = new Date(1000*sunset);
                String sunsetTime = new SimpleDateFormat("H:mm").format(sunsetDate);


                Log.d("Amal", "sunrise= "+sunrise);
                Log.d("Amal", "sunset= "+sunset);
                sunrise_t.setText("Sunrise: "+ String.valueOf(sunriseTime));
                sunset_t.setText("Sunset: "+ String.valueOf(sunsetTime));


                JSONArray jsonArray = response.getJSONArray("weather");
                for (int i=0; i<jsonArray.length();i++){
                    Log.d("Amal-array",jsonArray.getString(i));
                    JSONObject oneObject = jsonArray.getJSONObject(i);
                    String weather =
                            oneObject.getString("main");
                    Log.d("Amal-w",weather);

                    switch (weather) {
                        case "Clear":
                            Glide.with(MainActivity2.this)
                                    .load("https://torange.biz/photo/20/HD/sky-blue-clear-20213.jpg")
                                    .into(weatherBackground);
                            break;
                        case "Clouds":
                            Glide.with(MainActivity2.this)
                                    .load("https://media.istockphoto.com/photos/blue-sky-with-white-clouds-nature-background-picture-id1125295327?k=20&m=1125295327&s=612x612&w=0&h=KAs_-jMfnsUQ1W4P1Uus8wX1WV53FFb12-iCFV6EcBY=")
                                    .into(weatherBackground);
                            break;
                        case "Rain":
                            Glide.with(MainActivity2.this)
                                    .load("https://travelerofcharleston.com/wp-content/uploads/2013/03/rainy-day.png")
                                    .into(weatherBackground);
                            break;
                    }

                }
            }
            catch (JSONException e){
                e.printStackTrace();
                Log.e("Receive Error", e.toString());
            }
        }, error -> Log.d("Amal", "Error Retrieving URL"));
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);
    }
}
