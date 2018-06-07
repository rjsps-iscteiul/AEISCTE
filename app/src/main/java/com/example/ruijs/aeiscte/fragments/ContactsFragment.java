package com.example.ruijs.aeiscte.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ruijs.aeiscte.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class ContactsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap map;
    private final LatLng ISCTE = new LatLng(38.748753, -9.153692);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // init map view
        MapView mapView = (MapView) view.findViewById(R.id.mapView);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        map = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.addMarker(new MarkerOptions().position(ISCTE).title(this.getString(R.string.contacts_university)));

        CameraPosition toIscte = CameraPosition.builder().target(ISCTE).zoom(16).bearing(0).tilt(45).build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(toIscte));
    }

    // do not erase can be useful in future
    public  void DrawLine() {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.add(new LatLng(ISCTE.latitude-50, ISCTE.longitude-80))
                .add(new LatLng(ISCTE.latitude-10, ISCTE.longitude-50))
                .add(ISCTE);
        map.addPolyline(polylineOptions);
    }

    /*

        **********************************
        CODE WILL BE HERE AND ON THE ONCREATED METHODS IF NECESSARY
        **********************************

     */
}
