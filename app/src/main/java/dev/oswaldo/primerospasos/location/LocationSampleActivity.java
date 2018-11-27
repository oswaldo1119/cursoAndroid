package dev.oswaldo.primerospasos.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.sql.Connection;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.util.KeysConstants;

public class LocationSampleActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    @BindView(R.id.textViewLocation)
    TextView mTextViewLocation;

    @BindView(R.id.progressBarLocation)
    ProgressBar mProgressBarLocation;

    FusedLocationProviderClient mFusedLocationProviderClient;

    GoogleApiClient mGoogleApiClient;

    LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_sample);
        ButterKnife.bind(this);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        buildGoogleApiClient();
    }

    private void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.reconnect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int response = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(LocationSampleActivity.this);
        if(response != ConnectionResult.SUCCESS) {
            // Si no tiene los servicios de google play
            Log.d("MainActivity", "Google Play Service Not Available");
            GoogleApiAvailability.getInstance().getErrorDialog(LocationSampleActivity.this,
                    response, 1).show();
        }else{
            if(mGoogleApiClient != null && mFusedLocationProviderClient != null){
                requestLocationUpdates();
            } else{
                buildGoogleApiClient();
            }
            Log.d("MainActivity", "Google play service available");
        }
    }

    private void requestLocationUpdates() {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(2000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest,
                    mLocationCallback,
                    Looper.myLooper());
        }else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    KeysConstants.LOCATION_PERMISSION_ID);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mFusedLocationProviderClient != null) {
            mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    /*LocationCallback mLocationCallBack = new LocationCallback(){
        @SuppressLint("SetTextI18n")
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            for(Location location : locationResult.getLocations()){
                Log.w("MainActivity", "|xXx| Location: " + location.getLongitude()
                        + " " + location.getLongitude());
                mTextViewLocation.setText(getEmojiByUnicode(0x1F680)
                        + getEmojiByUnicode(0x1F680)+"Location: "
                        + location.getLatitude() + location.getLongitude());
                mProgressBarLocation.setVisibility(View.GONE);
            }
        }
    };*/
    LocationCallback mLocationCallback = new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for (Location location : locationResult.getLocations()) {
                Log.w("MainActivity", "🌮🌮🌮🌮Location: " + location.getLatitude() + " " + location.getLongitude());
                mTextViewLocation.setText(getEmojiByUnicode(0x1F680)+getEmojiByUnicode(0x1F680)+"Location: " + location.getLatitude()  + location.getLongitude());
                mProgressBarLocation.setVisibility(View.GONE);

            }
        }
    };

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.w("MainActivity", "|xXx| onConnected ");
        requestLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.w("MainActivity", "|xXx| onConnectionSuspended ");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.w("MainActivity", "|xXx| onConnectionFailed ");
    }

    private String getEmojiByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case KeysConstants.LOCATION_PERMISSION_ID:{
                // If request is cancelled, the result arrays are empty.
                if(grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay!
                    if(ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED){
                        onResume();
                    } else{
                        showPopUp(LocationSampleActivity.this);
                    }
                }
                return;
            }
        }
    }

    public void showPopUp(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Permisos")
                .setMessage("\nNo le has dado permisos")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}
