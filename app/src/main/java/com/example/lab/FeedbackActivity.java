package com.example.lab;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FeedbackActivity extends AppCompatActivity {

    private CheckBox popcorn, candy, drinks;
    private TextView quantityTextView, ratingText;
    private Button increment, decrement, submitFeedback;
    private EditText commentsEditText;
    private RatingBar ratingBar;

    private int quantity = 0;
    private ArrayList<String> selectedSnacks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback); // Ensure this matches your XML layout name

        // Initialize UI components
        popcorn = findViewById(R.id.popcorn);
        candy = findViewById(R.id.candy);
        drinks = findViewById(R.id.drinks);
        quantityTextView = findViewById(R.id.quantityTextView);
        commentsEditText = findViewById(R.id.commentsEditText);
        ratingBar = findViewById(R.id.ratingBar);
        ratingText = findViewById(R.id.rating);
        increment = findViewById(R.id.increment);
        decrement = findViewById(R.id.decrement);
        submitFeedback = findViewById(R.id.submitFeedbackButton);
        increment.setOnClickListener(v -> {
            quantity++;
            updateQuantityDisplay();
        });

        decrement.setOnClickListener(v -> {
            if (quantity > 0) {
                quantity--;
                updateQuantityDisplay();
            }
        });

        popcorn.setOnCheckedChangeListener((buttonView, isChecked) -> updateSelectedSnacks(buttonView, isChecked));
        candy.setOnCheckedChangeListener((buttonView, isChecked) -> updateSelectedSnacks(buttonView, isChecked));
        drinks.setOnCheckedChangeListener((buttonView, isChecked) -> updateSelectedSnacks(buttonView, isChecked));

        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            ratingText.setText("Rating: " + rating);
        });

        // Submit feedback
        submitFeedback.setOnClickListener(v -> submitFeedback());
    }

    private void updateQuantityDisplay() {
        quantityTextView.setText(String.valueOf(quantity));
    }

    private void updateSelectedSnacks(View buttonView, boolean isChecked) {
        String snackName = ((CheckBox) buttonView).getText().toString();
        if (isChecked) {
            selectedSnacks.add(snackName);
        } else {
            selectedSnacks.remove(snackName);
        }
    }

    private void submitFeedback() {
        StringBuilder feedbackSummary = new StringBuilder();
        feedbackSummary.append("Selected Snacks: ").append(selectedSnacks.toString()).append("\n");
        feedbackSummary.append("Quantity: ").append(quantity).append("\n");
        feedbackSummary.append("Comments: ").append(commentsEditText.getText().toString()).append("\n");
        feedbackSummary.append("Rating: ").append(ratingBar.getRating()).append("\n");

        Toast.makeText(getApplicationContext(), feedbackSummary.toString(), Toast.LENGTH_LONG).show();

        resetFields();
    }

    private void resetFields() {
        quantity = 0;
        updateQuantityDisplay();
        commentsEditText.setText("");
        ratingBar.setRating(0);
        selectedSnacks.clear();
        popcorn.setChecked(false);
        candy.setChecked(false);
        drinks.setChecked(false);
    }
}
