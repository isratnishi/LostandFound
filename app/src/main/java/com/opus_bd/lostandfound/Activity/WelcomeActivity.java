package com.opus_bd.lostandfound.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Utilities;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    private Location mylocation;

    int c = 0;
    private GoogleApiClient googleApiClient;
    private final static int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private final static int REQUEST_ID_MULTIPLE_PERMISSIONS = 0x2;
    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.textView1)
    TextView textView1;

    @BindView(R.id.ivappLogo)
    ImageView ivappLogo;
    AlertDialog.Builder builder;
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Animation rightEnter = AnimationUtils.loadAnimation(this, R.anim.right_enter);
        Animation leftEnter = AnimationUtils.loadAnimation(this, R.anim.left_enter);
        textView.startAnimation(rightEnter);
        Animation zoomOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);
        ivappLogo.startAnimation(zoomOutAnimation);
        builder = new AlertDialog.Builder(this);
        //  .startAnimation(rightEnter);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
               // LocationCheck();
                setUpGClient();


            }
        }, SPLASH_TIME_OUT);
    }

public void LocationCheck(){
    LocationManager lm = (LocationManager)
            getSystemService(Context. LOCATION_SERVICE ) ;
    boolean gps_enabled = false;
    boolean network_enabled = false;
    try {
        gps_enabled = lm.isProviderEnabled(LocationManager. GPS_PROVIDER ) ;
        Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(i);

        // close this activity
        finish();
    } catch (Exception e) {
        e.printStackTrace() ;
    }
    try {
        network_enabled = lm.isProviderEnabled(LocationManager. NETWORK_PROVIDER ) ;
        Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(i);

        // close this activity
        finish();
    } catch (Exception e) {
        e.printStackTrace() ;
    }
    if (!gps_enabled && !network_enabled) {
        new AlertDialog.Builder(WelcomeActivity. this )
                .setMessage( "GPS Enable" )
                .setPositiveButton( "Settings" , new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick (DialogInterface paramDialogInterface , int paramInt) {
                                startActivity( new Intent(Settings. ACTION_LOCATION_SOURCE_SETTINGS )) ;
                            }
                        })
                .setNegativeButton( "Cancel" , new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick (DialogInterface paramDialogInterface , int paramInt) {
                                finish();
                               // startActivity( new Intent(Settings. ACTION_LOCATION_SOURCE_SETTINGS )) ;
                            }
                        } )
                .show() ;
    }
}
    /*@OnClick(R.id.rootLayout)
    public void lorootLayoutgin() {
        Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }*/

// location cHeck

    public void showDialog() {

        //Uncomment the below code to Set the message and title from the strings.xml file
        //Setting message manually and performing action on button click
        builder.setMessage("Please open your mobile location(GPS) for continue \n \n" +
                "চালিয়ে যাওয়ার জন্য দয়া করে আপনার মোবাইল লোকেশন(GPS) খুলুন")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        getMyLocation();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                        finish();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Location Request");
        alert.show();
    }

    private synchronized void setUpGClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0, WelcomeActivity.this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }


    @Override
    public void onLocationChanged(Location location) {
        mylocation = location;
        if(mylocation != null){
            Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(i);

            // close this activity
            finish();
        }
        else {
            Utilities.showLogcatMessage(" No Thanks 2");
           // showDialog();
            finish();
        }
       /* if (mylocation != null) {
            Double latitude = mylocation.getLatitude();
            Double longitude = mylocation.getLongitude();
            //Or Do whatever you want with your location


        }*/
    }


    @Override
    public void onConnected(Bundle bundle) {
        checkPermissions();
    }

    @Override
    public void onConnectionSuspended(int i) {
        //Do whatever you need
        //You can display a message here
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        //You can display a message here
    }

    private void getMyLocation() {
        if (googleApiClient != null) {
            if (googleApiClient.isConnected()) {
                int permissionLocation = ContextCompat.checkSelfPermission(WelcomeActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                    mylocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                    @SuppressLint("RestrictedApi") LocationRequest locationRequest = new LocationRequest();
                    locationRequest.setInterval(3000);
                    locationRequest.setFastestInterval(3000);
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                            .addLocationRequest(locationRequest);
                    builder.setAlwaysShow(true);
                    LocationServices.FusedLocationApi
                            .requestLocationUpdates(googleApiClient, locationRequest, (com.google.android.gms.location.LocationListener) WelcomeActivity.this);
                    PendingResult<LocationSettingsResult> result =
                            LocationServices.SettingsApi
                                    .checkLocationSettings(googleApiClient, builder.build());
                    result.setResultCallback(new ResultCallback<LocationSettingsResult>() {

                        @Override
                        public void onResult(LocationSettingsResult result) {
                            final Status status = result.getStatus();
                            switch (status.getStatusCode()) {
                                case LocationSettingsStatusCodes.SUCCESS:
                                    // All location settings are satisfied.
                                    // You can initialize location requests here.
                                    int permissionLocation = ContextCompat
                                            .checkSelfPermission(WelcomeActivity.this,
                                                    Manifest.permission.ACCESS_FINE_LOCATION);
                                    if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                                        mylocation = LocationServices.FusedLocationApi
                                                .getLastLocation(googleApiClient);
                                    }
                                    break;
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                    // Location settings are not satisfied.
                                    // But could be fixed by showing the user a dialog.
                                    try {
                                        // Show the dialog by calling startResolutionForResult(),
                                        // and check the result in onActivityResult().
                                        // Ask to turn on GPS automatically
                                        status.startResolutionForResult(WelcomeActivity.this,
                                                REQUEST_CHECK_SETTINGS_GPS);
                                    } catch (IntentSender.SendIntentException e) {
                                        // Ignore the error.
                                    }
                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    // Location settings are not satisfied.
                                    // However, we have no way
                                    // to fix the
                                    // settings so we won't show the dialog.
                                    // finish();
                                    break;
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS_GPS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        getMyLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        Utilities.showLogcatMessage(" No Thanks 1");
                        //finish();
                        showDialog();
                        break;
                }
                break;
        }
    }

    private void checkPermissions() {
        int permissionLocation = ContextCompat.checkSelfPermission(WelcomeActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            }
        } else {
            getMyLocation();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        int permissionLocation = ContextCompat.checkSelfPermission(WelcomeActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
            getMyLocation();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
