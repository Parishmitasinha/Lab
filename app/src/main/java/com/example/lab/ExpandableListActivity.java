package com.example.lab;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<String> groupData;
    HashMap<String, List<String>> childData;
    HashMap<String, String> productDescriptions;
    int lastExpandedGroup = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);

        // Handling window insets for edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        expandableListView = findViewById(R.id.expandableListView);
        setupData();  // Initialize data
        ExpandableListAdapter adapter = new ExpandableListAdapter(this, groupData, childData);
        expandableListView.setAdapter(adapter);

        // Handling group clicks
        expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            String groupText = groupData.get(groupPosition);
            Toast.makeText(getApplicationContext(), groupText, Toast.LENGTH_SHORT).show();
            return false;
        });

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            String group = groupData.get(groupPosition);
            String child = childData.get(group).get(childPosition);
            String description = productDescriptions.get(child);
            Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
            return false;
        });
        expandableListView.setOnGroupExpandListener(groupPosition -> {
            if (lastExpandedGroup != -1 && lastExpandedGroup != groupPosition) {
                expandableListView.collapseGroup(lastExpandedGroup);
            }
            lastExpandedGroup = groupPosition;
        });
    }

    private void setupData() {
        groupData = new ArrayList<>();
        childData = new HashMap<>();
        productDescriptions = new HashMap<>();

        String[] categories = getResources().getStringArray(R.array.product_categories);
        String[] smartDevices = getResources().getStringArray(R.array.smart_devices);
        String[] entertainment = getResources().getStringArray(R.array.entertainment);
        String[] descriptions = getResources().getStringArray(R.array.product_descriptions);


        groupData.add(categories[0]);
        groupData.add(categories[1]);

        List<String> smartDeviceList = new ArrayList<>();
        List<String> entertainmentList = new ArrayList<>();


        smartDeviceList.add(smartDevices[0]);
        smartDeviceList.add(smartDevices[1]);
        smartDeviceList.add(smartDevices[2]);
        smartDeviceList.add(smartDevices[3]);


        entertainmentList.add(entertainment[0]);


        childData.put(groupData.get(0), smartDeviceList);
        childData.put(groupData.get(1), entertainmentList);

        productDescriptions.put(smartDevices[0], descriptions[0]);
        productDescriptions.put(smartDevices[1], descriptions[1]);
        productDescriptions.put(smartDevices[2], descriptions[2]);
        productDescriptions.put(smartDevices[3], descriptions[3]);
        productDescriptions.put(entertainment[0], descriptions[4]);
    }
}
