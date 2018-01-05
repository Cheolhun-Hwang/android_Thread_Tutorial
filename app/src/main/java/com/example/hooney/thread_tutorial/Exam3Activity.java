package com.example.hooney.thread_tutorial;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Exam3Activity extends AppCompatActivity {

    private final static String TAG = "Exam3Activity";

    private TextView viewCount;
    private Button start;
    private Button stop;
    private Button reset;

    private int count;
    private boolean isStart;
    private Thread Timer;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam3);

        init();
        event();
    }

    private void init(){
        isStart = false;
        count = 0;
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                switch (msg.what){
                    case 1001:
                        int cnt = (int) msg.obj;
                        viewCount.setText(""+cnt);
                        break;
                    default:
                        Log.e(TAG, "Handler Messages Failed...");
                        break;
                }

                return true;
            }
        });

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

                        Message msg = handler.obtainMessage();
                        msg.what = 1001;

                        //실제로는 필요없는 과정입니다
                        //이미 count 변수는 생명주기 상에서 handler 자체에서 호출가능합니다
                        //하지만 배우는 과정으로서 값을 전달하는 것까지 해보겠습니다
                        msg.obj = count;
                        handler.sendMessage(msg);

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
