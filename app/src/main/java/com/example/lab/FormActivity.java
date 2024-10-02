package com.example.lab;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Objects;
import java.util.regex.Pattern;

public class FormActivity extends AppCompatActivity {
    private EditText nameEditText, idEditText, emailEditText, phoneEditText;
    private TextView dobTextView;
    private Spinner deptSpinner;
    private String name, id, email, phone, dept;
    private Button submit, clearButton, editButton;
    private Pattern namePattern = Pattern.compile("[a-z A-Z._]+");
    LinearLayout inputLayout, outputLayout;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameEditText = findViewById(R.id.name);
        idEditText = findViewById(R.id.sId);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.num);
        dobTextView = findViewById(R.id.dateOfBirthText);
        deptSpinner = findViewById(R.id.spinner);
        submit = findViewById(R.id.submit);
        clearButton = findViewById(R.id.clearButton);
        editButton = findViewById(R.id.editButton);
        inputLayout = findViewById(R.id.inputLayout);
        outputLayout = findViewById(R.id.outputLayout);
        outputText = findViewById(R.id.outputText);


        String[] items = new String[]{"Select Department", "CSE", "EEE", "ARCH", "CE", "BuA", "ENG", "LAW", "IS", "BNG", "THM", "PH"};
        deptSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items));
        deptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dept = deptSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        submit.setOnClickListener(v -> {
            name = nameEditText.getText().toString();
            id = idEditText.getText().toString();
            email = emailEditText.getText().toString();
            phone = phoneEditText.getText().toString();

            if (name.isEmpty()) {
                nameEditText.setError("Empty!!");
                nameEditText.requestFocus();
            } else if (!namePattern.matcher(name).matches()) {
                nameEditText.setError("Name can be only Alphabet");
                nameEditText.requestFocus();
            } else if (id.isEmpty()) {
                idEditText.setError("Empty!!");
                idEditText.requestFocus();
            } else if (email.isEmpty()) {
                emailEditText.setError("Empty!!");
                emailEditText.requestFocus();
            } else if (phone.isEmpty()) {
                phoneEditText.setError("Empty!!");
                phoneEditText.requestFocus();
            } else if (Objects.equals(dept, "Select Department")) {
                Toast.makeText(getApplicationContext(), "Please Select Department", Toast.LENGTH_SHORT).show();
            } else {

                inputLayout.setVisibility(View.GONE);
                outputLayout.setVisibility(View.VISIBLE);
                String s = "Name: " + name + "\nS Id: " + id + "\nEmail: " + email + "\nMobile Number: " + phone + "\nDepartment: " + dept + "\nDate of Birth: " + dobTextView.getText();
                outputText.setText(s);
            }
        });

        // Clear button action
        clearButton.setOnClickListener(v -> {
            // Clear input fields
            nameEditText.setText("");
            idEditText.setText("");
            emailEditText.setText("");
            phoneEditText.setText("");
            deptSpinner.setSelection(0);
            dobTextView.setText("Date of Birth");
            inputLayout.setVisibility(View.VISIBLE);
            outputLayout.setVisibility(View.GONE);
        });

        editButton.setOnClickListener(v -> {

            outputLayout.setVisibility(View.GONE);
            inputLayout.setVisibility(View.VISIBLE);
        });

        dobTextView.setOnClickListener(v -> {

            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(FormActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        dobTextView.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
                    }, year, month, day);
            datePickerDialog.show();
        });
    }
}
