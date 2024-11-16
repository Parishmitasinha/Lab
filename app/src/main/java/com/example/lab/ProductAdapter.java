package com.example.lab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductAdapter extends BaseAdapter {
    private final String[] productNames;
    private final String[] productDescriptions;
    private final Integer[] productImages;
    private final Context context;

    public ProductAdapter(Context context, String[] productNames, String[] productDescriptions, Integer[] productImages) {
        this.context = context;
        this.productNames = productNames;
        this.productDescriptions = productDescriptions;
        this.productImages = productImages;
    }

    @Override
    public int getCount() {
        return productNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sample_view, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.title);
        TextView subtitleTextView = convertView.findViewById(R.id.subtitle);
        ImageView productImageView = convertView.findViewById(R.id.imageView);

        titleTextView.setText(productNames[position]);
        subtitleTextView.setText(productDescriptions[position]);
        productImageView.setImageResource(productImages[position]);

        return convertView;
    }
}
