package com.github.ccmagic.progressview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private ProgressView1 progressView1;
    private ProgressView2 progressView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressView1 = findViewById(R.id.progressView1);
        progressView2 = findViewById(R.id.progressView2);
    }

    @Override
    protected void onDestroy() {
        progressView1.cancellation();
        progressView2.cancellation();
        super.onDestroy();


    }
}
