package com.opus_bd.lostandfound;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {
    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.textView1)
    TextView textView1;

    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Animation rightEnter = AnimationUtils.loadAnimation(this, R.anim.right_enter);
        Animation leftEnter = AnimationUtils.loadAnimation(this, R.anim.left_enter);
        textView.startAnimation(rightEnter);
        textView1.startAnimation(rightEnter);

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
                Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(i);

                // close this activity
                finish();
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


}
