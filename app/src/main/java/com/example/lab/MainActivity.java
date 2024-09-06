package com.example.lab;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonShowToast = findViewById(R.id.buttonShowToast);
        Button buttonShowCustomToast = findViewById(R.id.buttonShowCustomToast);
        Button showRTheChange = findViewById(R.id.showRTheChange);

        buttonShowToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Left button is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        buttonShowCustomToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Right button is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        showRTheChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Changing successful", Toast.LENGTH_SHORT).show();

                LayoutInflater inflater = getLayoutInflater();
                View customToastView = inflater.inflate(R.layout.custom_toast, null);

                TextView toastTextView = customToastView.findViewById(R.id.customToastTextView);
                toastTextView.setText("HELLO");

                Toast customToast = new Toast(MainActivity.this);
                customToast.setView(customToastView);
                customToast.setGravity(Gravity.CENTER, 0, 0);
                customToast.setDuration(Toast.LENGTH_SHORT);
                customToast.show();
            }
        });
    }
}
