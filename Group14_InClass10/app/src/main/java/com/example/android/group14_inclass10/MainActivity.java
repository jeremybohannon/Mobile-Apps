package com.example.android.group14_inclass10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

//InClass10_Group14
//Jeremy Bohannon Elizabeth Thompson
//MainActivity.java
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap _googleMap) {
        final GoogleMap googleMap = _googleMap;

        Gson gson = new Gson();

        InputStream raw =  getResources().openRawResource(R.raw.trip);
        Reader rd = new BufferedReader(new InputStreamReader(raw));

        final Points points = gson.fromJson(rd, Points.class);

        final PolylineOptions options = new PolylineOptions();
        final LatLngBounds.Builder bound = new LatLngBounds.Builder();

        final LatLng first = new LatLng(points.getPoints().get(0).getLatitude(),points.getPoints().get(0).getLongitude() );
        final LatLng last = new LatLng(points.getPoints().get(points.getPoints().size() -1).getLatitude(),points.getPoints().get(points.getPoints().size()-1).getLongitude() );

        for(Point p : points.getPoints()){
            LatLng l = new LatLng(p.getLatitude(), p.getLongitude());
            options.add(l);
            bound.include(l);
        }

        googleMap.addPolyline(options);

        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bound.build(), 50));
                googleMap.addMarker(new MarkerOptions().position(first).title("Start"));
                googleMap.addMarker(new MarkerOptions().position(last).title("End"));
            }
        });
    }


}
