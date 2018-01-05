package com.example.hooney.thread_tutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Exam1Activity extends AppCompatActivity {

    private TextView viewCount;
    private Button start;
    private Button stop;
    private Button reset;

    private int count;
    private boolean isStart;
    private Thread Timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam1);

        init();
        event();
    }

    private void init(){
        isStart = false;
        count = 0;

        viewCount = (TextView) findViewById(R.id.txtTime);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        reset = (Button) findViewById(R.id.reset);

        Timer = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isStart){
                    try {
                        Thread.sleep(1000);
                        count +=1;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                viewCount.setText(""+count);
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void event(){
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isStart) {
                    startTimer();
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isStart) {
                    stopTimer();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
                count = 0;
                viewCount.setText(""+count);
            }
        });
    }

    private void startTimer(){
        isStart = true;
        Timer.start();
    }

    private void stopTimer(){
        isStart = false;
        Timer.interrupt();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTimer();
    }
}
