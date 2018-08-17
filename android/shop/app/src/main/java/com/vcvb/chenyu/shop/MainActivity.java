package com.vcvb.chenyu.shop;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        Toast.makeText(MainActivity.this, view.getId()+"", Toast.LENGTH_LONG).show();
        TextView hellotv = findViewById(R.id.textView2);
        hellotv.setText(R.string.app_name);
    }
}
