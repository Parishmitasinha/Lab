package com.example.lab;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_view);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String[] productNames = getResources().getStringArray(R.array.product_categories);
        String[] productDescriptions = getResources().getStringArray(R.array.product_descriptions);
        Integer[] productImages = {
                R.drawable.smartphone, R.drawable.laptop, R.drawable.smartwatch,
                R.drawable.bluetooth_speaker, R.drawable.tablet, R.drawable.smart_tv
        };

        ArrayList<ProductModel> products = new ArrayList<>();

        for (int i = 0; i < productNames.length; i++) {
            ProductModel product = new ProductModel();
            product.setProductName(productNames[i]);
            product.setProductDescription(productDescriptions[i]);
            product.setProductImageId(productImages[i]);
            products.add(product);
        }

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(products, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
}
