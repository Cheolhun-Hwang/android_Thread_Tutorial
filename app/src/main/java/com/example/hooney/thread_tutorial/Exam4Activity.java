package com.example.hooney.thread_tutorial;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Exam4Activity extends AppCompatActivity {

    private final static String TAG = "Exam4Activity";

    private TextView viewCount;
    private Button start;
    private Button stop;
    private Button reset;

    private int count;
    private boolean isStart;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam4);

        init();
        event();
    }

    private void init(){
        isStart = false;
        count = 0;
        timer = new Timer();

        viewCount = (TextView) findViewById(R.id.txtTime);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        reset = (Button) findViewById(R.id.reset);
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
        Log.d(TAG, "Status : " + timer.getStatus());
        if(timer.getStatus() == AsyncTask.Status.FINISHED){
            timer.execute(100);
        }else{
            timer.execute(100);
        }

    }

    private void stopTimer(){
        isStart = false;
        timer.cancel(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTimer();
    }

    private class Timer extends AsyncTask<Integer, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(Integer... params) {
            while (isStart){
                if(params[0] == count){
                    Toast.makeText(getApplicationContext(), params[0]+"이 넘었어요!!", Toast.LENGTH_SHORT).show();
                }

                try {
                    Thread.sleep(1000);
                    count+=1;

                    publishProgress(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){
                Log.d(TAG, "AsyncTask is Running well...");
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            viewCount.setText(""+count);
        }

        @Override
        protected void onCancelled(Boolean aBoolean) {
            super.onCancelled(aBoolean);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
