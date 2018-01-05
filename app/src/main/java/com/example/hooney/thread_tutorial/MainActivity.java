package com.example.hooney.thread_tutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        event();
    }

    private void init(){
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
    }

    private void event(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Exam1Activity.class));
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Exam2Activity.class));
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Exam3Activity.class));
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Exam4Activity.class));
            }
        });
    }

}
