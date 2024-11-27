package com.example.lab;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab.R;

public class RecyclerDescriptionActivity extends AppCompatActivity {

    ImageView productImageView;
    TextView productNameTextView, productDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_description);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        productImageView = findViewById(R.id.product_image_view);
        productNameTextView = findViewById(R.id.product_name_text_view);
        productDescriptionTextView = findViewById(R.id.product_description_text_view);

        productImageView.setImageResource(getIntent().getIntExtra("productImage", 0));
        productNameTextView.setText(getIntent().getStringExtra("productName"));
        productDescriptionTextView.setText(getIntent().getStringExtra("productDescription"));
    }
}
