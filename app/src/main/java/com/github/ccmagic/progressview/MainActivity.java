package com.github.ccmagic.progressview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ccmagic.progressviewlib.ProgressDialog;

/**
 * @author kxmc
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showProgressDialog(null);
    }

    public void click(View view) {
        startActivity(new Intent(this, MoreActivity.class));
    }

    public void showProgressDialog(View view) {
        ProgressDialog.newInstance().show(getSupportFragmentManager(), "progressDialog");
    }
}
