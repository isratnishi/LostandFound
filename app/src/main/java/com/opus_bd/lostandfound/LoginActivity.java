package com.opus_bd.lostandfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.opus_bd.lostandfound.GeneralPeople.Main2Activity;
import com.opus_bd.lostandfound.Model.User.UserAuthModel;
import com.opus_bd.lostandfound.Model.User.UserLoginModel;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager.KEY_State;
import static com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager.SHARED_PREF_NAME;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    private Location mylocation;
    boolean isChecked = true;
    boolean isPassChecked = true;

    @BindView(R.id.tvLangugeName)
    Button tvLangugeName;
    @BindView(R.id.etPassword)
    EditText etPassword;      @BindView(R.id.etUserName)
    EditText etUserName;    @BindView(R.id.ivpassShow)
    ImageView ivpassShow;
    int c = 0;
    private GoogleApiClient googleApiClient;
    private final static int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private final static int REQUEST_ID_MULTIPLE_PERMISSIONS = 0x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //LocationCheck();
        setUpGClient();

        Boolean languageStatus = getSharedPrefValue();

        if (languageStatus) {
            tvLangugeName.setText(R.string.bangla);
        } else {
            tvLangugeName.setText(R.string.english);
        }

        //Animation rightEnter = AnimationUtils.loadAnimation(this, R.animator.fragment_slide_right_enter);
        Animation leftEnter = AnimationUtils.loadAnimation(this, R.anim.left_enter);

        //tvappname.startAnimation(leftEnter);


    }

    @Override
    protected void attachBaseContext(Context base) {
        SharedPreferences tprefs = base.getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME, MODE_PRIVATE);
        boolean language = tprefs.getBoolean(SharedPrefManager.KEY_State, true);
        if (language)
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.ENGLISH));
        else
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.BANGLA));
    }

    private void changeLanguage(String language) {
        LocaleHelper.setLocale(LoginActivity.this, language);
        recreate();
    }


    /*public void LocationCheck(){
        LocationManager lm = (LocationManager)
                getSystemService(Context. LOCATION_SERVICE ) ;
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager. GPS_PROVIDER ) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager. NETWORK_PROVIDER ) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
        if (!gps_enabled && !network_enabled) {
            new AlertDialog.Builder(LoginActivity.this )
                    .setMessage( "For Better Experience Turn on Location " )
                    .setPositiveButton( "Ok" , new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick (DialogInterface paramDialogInterface , int paramInt) {
                                    startActivity( new Intent(Settings. ACTION_LOCATION_SOURCE_SETTINGS )) ;
                                }
                            })
                    .setNegativeButton( "Cancel" ,  new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick (DialogInterface paramDialogInterface , int paramInt) {
                                    finish();
                                }
                            } )
                    .show() ;
        }
        else {
           // finish();
        }
    }*/
    private boolean getSharedPrefValue() {
        SharedPreferences tprefs = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return tprefs.getBoolean(KEY_State, true);
    }

    private void saveIntoSharedPref(boolean isLanguageEnglish) {
        SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(KEY_State, isLanguageEnglish);
        editor.apply();
    }

    @OnClick(R.id.tvLangugeName)
    public void button2() {
        isChecked = getSharedPrefValue();
        if (isChecked == true) {

            isChecked = false;

            saveIntoSharedPref(false);

            changeLanguage(Constants.BANGLA);
            tvLangugeName.setText(R.string.english);
        } else {
            isChecked = true;

            saveIntoSharedPref(true);

            changeLanguage(Constants.ENGLISH);
            tvLangugeName.setText(R.string.bangla);
        }
    }

    @OnClick(R.id.btnLogIn)
    public void btnLogIn() {
        if (!validatedForm())
            return;
        submitToServer();
    }




    private boolean validatedForm() {
        if (TextUtils.isEmpty(etUserName.getText().toString())) {
            etUserName.setError(getResources().getString(R.string.field_null_error));
            Toast.makeText(this, "Contact field can not be empty!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError(getResources().getString(R.string.field_null_error));
            Toast.makeText(this, "Password field can not be empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    @OnClick(R.id.tvResigtration)
    public void tvResigtration() {
        Intent intent = new Intent(LoginActivity.this, RegistrationProcessActivity.class);

        startActivity(intent);
    }

    @OnClick(R.id.ivpassShow)
    public void Passwordshow() {

        if (isPassChecked) {
            // show password
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            Glide.with(this).load(R.drawable.ic_visibility_off).into(ivpassShow);
            isPassChecked=false;
        } else {
            // hide password
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            Glide.with(this).load(R.drawable.ic_view).into(ivpassShow);
            isPassChecked=true;
        }
    }

    // location cHeck


    private synchronized void setUpGClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0, LoginActivity.this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }


    @Override
    public void onLocationChanged(Location location) {
        mylocation = location;
        if (mylocation != null) {
            Double latitude = mylocation.getLatitude();
            Double longitude = mylocation.getLongitude();
            //Or Do whatever you want with your location
        }
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
                int permissionLocation = ContextCompat.checkSelfPermission(LoginActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                    mylocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                    LocationRequest locationRequest = new LocationRequest();
                    locationRequest.setInterval(3000);
                    locationRequest.setFastestInterval(3000);
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                            .addLocationRequest(locationRequest);
                    builder.setAlwaysShow(true);
                    LocationServices.FusedLocationApi
                            .requestLocationUpdates(googleApiClient, locationRequest, (com.google.android.gms.location.LocationListener) LoginActivity.this);
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
                                            .checkSelfPermission(LoginActivity.this,
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
                                        status.startResolutionForResult(LoginActivity.this,
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
                        finish();
                        break;
                }
                break;
        }
    }

    private void checkPermissions() {
        int permissionLocation = ContextCompat.checkSelfPermission(LoginActivity.this,
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
        int permissionLocation = ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
            getMyLocation();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    private void submitToServer() {
//        Utilities.showProgress(this);
        SharedPrefManager.getInstance(LoginActivity.this).clearToken();
        final UserLoginModel userModel = new UserLoginModel(etUserName.getText().toString(), etPassword.getText().toString());

        //SharedPrefManager.getInstance(this).saveUserName(etUserName.getText().toString());
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<UserAuthModel> registrationRequest = retrofitService.login(userModel);
        registrationRequest.enqueue(new Callback<UserAuthModel>() {
            @Override
            public void onResponse(Call<UserAuthModel> call, Response<UserAuthModel> response) {
                //              Utilities.hideProgress(LoginActivity.this);
                try {
                    if (response.body() != null) {
                        SharedPrefManager.getInstance(LoginActivity.this).saveToken(response.body().getAuthToken());
                        Toast.makeText(LoginActivity.this, "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, Main2Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<UserAuthModel> call, Throwable t) {
               // Utilities.hideProgress(LoginActivity.this);
                Toast.makeText(LoginActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

