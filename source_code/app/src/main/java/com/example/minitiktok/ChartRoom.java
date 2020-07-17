package com.example.minitiktok;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minitiktok.R;


public class ChartRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        String message = getIntent().getStringExtra("message");

        TextView textView = findViewById(R.id.tv_content_info);
        textView.setText(message);
    }

}
