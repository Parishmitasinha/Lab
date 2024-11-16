package com.example.lab;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class ProductActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        listView = findViewById(R.id.listView);

        String[] productNames = getResources().getStringArray(R.array.product_categories);
        String[] productDescriptions = getResources().getStringArray(R.array.product_descriptions);

        Integer[] productImages = {
                R.drawable.smartphone, R.drawable.laptop, R.drawable.smartwatch,
                R.drawable.bluetooth_speaker, R.drawable.tablet, R.drawable.smart_tv
        };

        ProductAdapter adapter = new ProductAdapter(this, productNames, productDescriptions, productImages);
        listView.setAdapter(adapter);
    }
}