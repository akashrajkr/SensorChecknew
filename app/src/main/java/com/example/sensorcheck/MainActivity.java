package com.example.sensorcheck;

//UDP client imports
import java.net.*;
import java.io.*;
import java.text.DecimalFormat;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    private Sensor accelerometer, mGyro, mGrav, magnetic;


    TextView xVal,yVal,zVal,xGyroVal,yGyroVal,zGyroVal,xGravVal,yGravVal,zGravVal,xMagVal,yMagVal,zMagVal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xVal = (TextView) findViewById(R.id.xView);
        yVal = (TextView) findViewById(R.id.yView);
        zVal = (TextView) findViewById(R.id.zView);
        xGyroVal = (TextView) findViewById(R.id.xGyroView);
        yGyroVal = (TextView) findViewById(R.id.yGyroView);
        zGyroVal = (TextView) findViewById(R.id.zGyroView);
        xGravVal = (TextView) findViewById(R.id.xGravView);
        yGravVal = (TextView) findViewById(R.id.yGravView);
        zGravVal = (TextView) findViewById(R.id.zGravView);
        xMagVal = (TextView) findViewById(R.id.xMagView);
        yMagVal = (TextView) findViewById(R.id.yMagView);
        zMagVal = (TextView) findViewById(R.id.zMagView);

        Log.d(TAG, "onCreate: Initializing Sensor services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered accelerometer listener");
        }
        else{
            xVal.setText("Accelerometer not supported.");
        }
        mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (mGyro != null) {
            sensorManager.registerListener(this, mGyro , SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Gyroscope listener");
        }
        else{
            xVal.setText("Gyroscope not supported.");
            yVal.setText("Gyroscope not  supported");
            zVal.setText("Gyroscope not  supported");

        }
        mGrav = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        if (mGrav != null) {
            sensorManager.registerListener(this, mGrav , SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Gravity listener");
        }
        else{
            xVal.setText("Gravity sensor not supported.");
        }
        magnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magnetic != null) {
            sensorManager.registerListener(this, magnetic , SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Magnetometer listener");
        }
        else{
            xVal.setText("Magnetometer not supported.");
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if (sensor.getType() == sensor.TYPE_ACCELEROMETER) {
            Log.d(TAG, "onSensorChanged: X: " + event.values[0] + " Y: " + event.values[1] + " Z: " + event.values[2]);

            xVal.setText("X axis: " + event.values[0]);
            yVal.setText("Y axis: " + event.values[1]);
            zVal.setText("Z axis: " + event.values[2]);
        }
        else if(sensor.getType() ==  sensor.TYPE_GYROSCOPE) {
            if (mGyro == null) {
                xVal.setText("Gyroscope not supported.");
                yVal.setText("Gyroscope not  supported");
                zVal.setText("Gyroscope not  supported");

            } else {
                xGyroVal.setText("X axis: " + event.values[0]);
                yGyroVal.setText("Y axis: " + event.values[1]);
                zGyroVal.setText("Z axis: " + event.values[2]);
            }
        }
        else if(sensor.getType() == sensor.TYPE_GRAVITY){
            xGravVal.setText("X axis: " + event.values[0]);
            yGravVal.setText("Y axis: " + event.values[1]);
            zGravVal.setText("Z axis: " + event.values[2]);
        }
        else if(sensor.getType() == sensor.TYPE_MAGNETIC_FIELD){
            xMagVal.setText("X axis: " + event.values[0]);
            yMagVal.setText("Y axis: " + event.values[1]);
            zMagVal.setText("Z axis: " + event.values[2]);
        }
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
