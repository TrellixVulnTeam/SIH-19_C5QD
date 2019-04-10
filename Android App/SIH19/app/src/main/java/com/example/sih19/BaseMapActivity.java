package com.example.sih19;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Paul on 9/7/15.
 */
public abstract class BaseMapActivity extends AppCompatActivity {

    protected LatLng mCenterLocation = new LatLng( 25.3176, 82.9739 );

    protected GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( getMapLayoutId() );
        initMapIfNecessary();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initMapIfNecessary();
    }

    protected void initMapIfNecessary() {
        if( mGoogleMap != null ) {
            return;
        }

        mGoogleMap = ( (MapFragment) getFragmentManager().findFragmentById( R.id.map ) ).getMap();

        initMapSettings();
        initCamera();
    }

    protected void initCamera() {
        CameraPosition position = CameraPosition.builder()
                .target( mCenterLocation )
                .zoom( getInitialMapZoomLevel() )
                .build();

        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), null);
    }

    protected int getMapLayoutId() {
        return R.layout.activity_base_map;
    }

    protected float getInitialMapZoomLevel() {
        return 12.0f;
    }

    protected abstract void initMapSettings();
}
