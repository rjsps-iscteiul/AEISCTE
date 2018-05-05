package com.example.ruijs.aeiscte;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class InfoActivity extends AppCompatActivity implements OnMapReadyCallback{
    private GoogleMap map;
    private final LatLng ISCTE= new LatLng(38.748753,-9.153692);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // init map view

        MapView mapView = (MapView) findViewById(R.id.mapView);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getApplicationContext());
        map = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.addMarker(new MarkerOptions().position(ISCTE).title("ISCTE-IUL"));

        CameraPosition toIscte = CameraPosition.builder().target(ISCTE).zoom(16).bearing(0).tilt(45).build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(toIscte));

    }


    // it was to be used to show path to ISCTE but map already have this.
    // do not erase
    public  void DrawLine() {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.add(new LatLng(ISCTE.latitude-50, ISCTE.longitude-80))
                .add(new LatLng(ISCTE.latitude-10, ISCTE.longitude-50))
                .add(ISCTE);
        map.addPolyline(polylineOptions);
    }

    }
