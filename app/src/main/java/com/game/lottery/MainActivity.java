package com.game.lottery;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final int THRESHOLD = 100;
    private SensorManager sensorManager;
    private Sensor sensorAccelerometr;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorAccelerometr = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensorAccelerometr, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorAccelerometr, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float runner = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 1000;

                if (runner > THRESHOLD) {
                    getRandomNumber();
                }

                last_z = z;
                last_x = x;
                last_y = y;
            }
        }

    }


    public void getRandomNumber(){
        ArrayList numberGenerate = new ArrayList();

        for (int i = 0; i < 9; i++){
            Random randomNumber = new Random();
            int iNumber = randomNumber.nextInt(48) + 1;

            if(!numberGenerate.contains(iNumber)){
                numberGenerate.add(iNumber);
            }else{
                i--;
            }
        }

        TextView text = (TextView)findViewById(R.id.number_1);
        text.setText(""+numberGenerate.get(0));

        text = (TextView)findViewById(R.id.number_2);
        text.setText(""+numberGenerate.get(1));

        text = (TextView)findViewById(R.id.number_3);
        text.setText(""+numberGenerate.get(2));

        text = (TextView)findViewById(R.id.number_4);
        text.setText(""+numberGenerate.get(3));

        text = (TextView)findViewById(R.id.number_5);
        text.setText(""+numberGenerate.get(4));

        text = (TextView)findViewById(R.id.number_6);
        text.setText(""+numberGenerate.get(5));

        text = (TextView)findViewById(R.id.number_7);
        text.setText(""+numberGenerate.get(6));

        text = (TextView)findViewById(R.id.number_8);
        text.setText(""+numberGenerate.get(7));

        text = (TextView)findViewById(R.id.number_9);
        text.setText(""+numberGenerate.get(8));

        FrameLayout ball1 = (FrameLayout) findViewById(R.id.ball_1);
        ball1.setVisibility(View.INVISIBLE);

        FrameLayout ball2 = (FrameLayout) findViewById(R.id.ball_2);
        ball2.setVisibility(View.INVISIBLE);

        FrameLayout ball3 = (FrameLayout) findViewById(R.id.ball_3);
        ball3.setVisibility(View.INVISIBLE);

        FrameLayout ball4 = (FrameLayout) findViewById(R.id.ball_4);
        ball4.setVisibility(View.INVISIBLE);

        FrameLayout ball5 = (FrameLayout) findViewById(R.id.ball_5);
        ball5.setVisibility(View.INVISIBLE);

        FrameLayout ball6 = (FrameLayout) findViewById(R.id.ball_6);
        ball6.setVisibility(View.INVISIBLE);

        FrameLayout ball7 = (FrameLayout) findViewById(R.id.ball_7);
        ball7.setVisibility(View.INVISIBLE);

        FrameLayout ball8 = (FrameLayout) findViewById(R.id.ball_8);
        ball8.setVisibility(View.INVISIBLE);

        FrameLayout ball9 = (FrameLayout) findViewById(R.id.ball_9);
        ball9.setVisibility(View.INVISIBLE);

        Animation a = AnimationUtils.loadAnimation(this, R.anim.move_down_ball_first);

        ball9.setVisibility(View.VISIBLE);
        ball9.clearAnimation();
        ball9.startAnimation(a);

        ball8.setVisibility(View.VISIBLE);
        ball8.clearAnimation();
        ball8.startAnimation(a);

        ball7.setVisibility(View.VISIBLE);
        ball7.clearAnimation();
        ball7.startAnimation(a);

        ball6.setVisibility(View.VISIBLE);
        ball6.clearAnimation();
        ball6.startAnimation(a);

        ball5.setVisibility(View.VISIBLE);
        ball5.clearAnimation();
        ball5.startAnimation(a);

        ball4.setVisibility(View.VISIBLE);
        ball4.clearAnimation();
        ball4.startAnimation(a);

        ball3.setVisibility(View.VISIBLE);
        ball3.clearAnimation();
        ball3.startAnimation(a);

        ball2.setVisibility(View.VISIBLE);
        ball2.clearAnimation();
        ball2.startAnimation(a);

        ball1.setVisibility(View.VISIBLE);
        ball1.clearAnimation();
        ball1.startAnimation(a);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}