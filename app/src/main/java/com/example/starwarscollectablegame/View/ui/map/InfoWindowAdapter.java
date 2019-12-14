package com.example.starwarscollectablegame.View.ui.map;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.starwarscollectablegame.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private Activity context;

    public InfoWindowAdapter(Activity context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = context.getLayoutInflater().inflate(R.layout.custom_info, null);

        TextView tvTitle = (TextView) view.findViewById(R.id.infowindow_title);
        TextView tvSubTitle = (TextView) view.findViewById(R.id.infowindow_subtitle);

        tvTitle.setText(marker.getTitle());
        tvSubTitle.setText(marker.getSnippet());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "EWA", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


}
