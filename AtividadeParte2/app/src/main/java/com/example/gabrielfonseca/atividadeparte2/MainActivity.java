package com.example.gabrielfonseca.atividadeparte2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor light;
    private Sensor gyroscope;

    private TextView tvLight;
    private TextView tvGyroscope;
    private TextView tvLat, tvLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLight = (TextView)findViewById(R.id.tvLight);
        tvGyroscope = (TextView)findViewById(R.id.tvGyroscope);

        tvLat = (TextView)findViewById(R.id.tvLat);
        tvLon = (TextView)findViewById(R.id.tvLon);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


        if(light != null){
            sensorManager.registerListener(MainActivity.this, light, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(gyroscope != null){
            sensorManager.registerListener(MainActivity.this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        GPSTracker g = new GPSTracker(getApplicationContext());
        Location l = g.getLocation();
        if(l!=null)
        {
            double lat = l.getLatitude();
            double longi = l.getLongitude();
            tvLat.setText("Latitude: " + String.valueOf(lat));
            tvLon.setText("Longitude: " + String.valueOf(longi));
        }
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if(sensor.getType() == Sensor.TYPE_LIGHT){
            tvLight.setText("Intensidade da luz: " + event.values[0]);
        }
        if(sensor.getType() == Sensor.TYPE_GYROSCOPE){
            tvGyroscope.setText("Giroscópio: " + event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
