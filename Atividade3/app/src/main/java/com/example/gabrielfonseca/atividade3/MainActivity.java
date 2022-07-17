package com.example.gabrielfonseca.atividade3;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private EditText etX, etY, etZ;
    private Sensor mAccelerometer;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etX = (EditText)findViewById(R.id.editTextX);
        etY = (EditText)findViewById(R.id.editTextY);
        etZ = (EditText)findViewById(R.id.editTextZ);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = ((SensorManager) mSensorManager).getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {

        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float sensorX = event.values[0];
            float sensorY = event.values[1];
            float sensorZ = event.values[2];

            etX.setText("X: " + String.valueOf(sensorX));
            etY.setText("Y: " + String.valueOf(sensorY));
            etZ.setText("Z: " + String.valueOf(sensorZ));

            Intent i = new Intent(this, Main2Activity.class);
            if(Math.abs(sensorX) > 10.0f){
                i.putExtra("data","Sensor X moved");
                startActivity(i);
            } else if(Math.abs(sensorY) > 10.0f){
                i.putExtra("data","Sensor Y moved");
                startActivity(i);
            }else if(Math.abs(sensorZ) > 10.0f){
                i.putExtra("data","Sensor Z moved");
                startActivity(i);
            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
