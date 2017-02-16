package com.vikaskumar.vsk.threads;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;

public class MainActivity extends AppCompatActivity {

    // Used for update user Interface
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            TextView buckysText = (TextView) findViewById(R.id.buckysText);
            buckysText.setText("Good job Hoss!");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBuckysButton(View view) {

        //Used for codes that run for long time and makes it run in the background.
        // It is not used for UI update, its bad practice.
        Runnable r = new Runnable() {
            @Override
            public void run() {
                long futureTime = System.currentTimeMillis() + 10000;
                while (System.currentTimeMillis() < futureTime) {
                    synchronized (this) {
                        try {
                            wait(futureTime - System.currentTimeMillis());
                        } catch (Exception e) {
                        }
                    }
                }
                handler.sendEmptyMessage(0);
            }
        };
        Thread buckysThread = new Thread(r);
        buckysThread.start();

    }
}
