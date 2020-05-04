package com.opus_bd.lostandfound.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.widget.TextView;
import android.widget.Toast;

import com.opus_bd.lostandfound.Model.Dashboard.GDInformation;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitAPIInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HardwareInformationActivity extends AppCompatActivity {
    @BindView(R.id.IMEINumber)
    TextView IMEINumber;
    @BindView(R.id.SubscriberID)
    TextView SubscriberID;
    @BindView(R.id.SIMNumber)
    TextView SIMNumber;
    @BindView(R.id.IPAddress)
    TextView IPAddress;
    @BindView(R.id.WiFiMACAddress)
    TextView WiFiMACAddress;    @BindView(R.id.OperatorName)
    TextView OperatorName; @BindView(R.id.CountryCode)
    TextView CountryCode;
    TelephonyManager telephonyManager;
    private String url = "https://api.ipify.org";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardware_information);
        ButterKnife.bind(this);
        //getDataFromUrl();
        //getAllInfo();
        try{
            WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            assert wm != null;
            Utilities.showLogcatMessage("ip"+wm.getConnectionInfo().getIpAddress());
            Utilities.showLogcatMessage("wifi"+wm.getConnectionInfo().getMacAddress());
         /*   String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
            String wifi = Formatter.formatIpAddress(Integer.parseInt(wm.getConnectionInfo().getMacAddress()));*/
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getNetworkId());


            WiFiMACAddress.setText(String.valueOf(wm.getConnectionInfo().getMacAddress()));
            IPAddress.setText(String.valueOf(Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress())));
        }
        catch (Exception e){
           Utilities.showLogcatMessage("wifi"+e.toString());
        }

        telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        Ids();



        //	getDataFromUrl(); // Connect url from the main thread for get data this will throw NetworkOnMainThreadExcection

       // new GetJSONTask().execute(url); //execute asynctask object this will resolve NetworkOnMainThreadExcection
    }

    private void getDataFromUrl() {
        try {
            IPAddress.setText(Utilities.downloadDataFromUrl(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.btnNext1)
    public void btnNext1(){
        Intent intent=new Intent(HardwareInformationActivity.this,LoginActivity.class);
        startActivity(intent);
    }
    // Uses AsyncTask to create a task away from the main UI thread(For Avoid
    // NetworkOnMainThreadException). This task takes a
    // URL string and uses it to create an HttpUrlConnection. Once the
    // connection
    // has been established, the AsyncTask downloads the contents of the data as
    // an InputStream. Than, the InputStream is converted into a string, which
    // is
    // displayed in the TextView by the AsyncTask's onPostExecute method. Which
    // called after doInBackgroud Complete
    private class GetJSONTask extends AsyncTask {
        private ProgressDialog pd;


        @Override
        protected Object doInBackground(Object[] objects) {
            return null;
        }

        // onPreExecute called before the doInBackgroud start for display
        // progress dialog.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show(HardwareInformationActivity.this, "", "Loading", true,
                    false); // Create and show Progress dialog
        }

    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    public void Ids() {

        if (ContextCompat.checkSelfPermission(HardwareInformationActivity.this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Ask for permision
            ActivityCompat.requestPermissions(this,new String[] { Manifest.permission.READ_PHONE_STATE}, 1);
        }
        else {
            SubscriberID.setText(telephonyManager.getSubscriberId());
            IMEINumber.setText(telephonyManager.getDeviceId(0));
            SIMNumber.setText(telephonyManager.getSimSerialNumber());
            OperatorName.setText(telephonyManager.getSimOperatorName());
            String locale = this.getResources().getConfiguration().locale.getDisplayCountry();
            CountryCode.setText(telephonyManager.getNetworkCountryIso());

            Utilities.showLogcatMessage(" " + telephonyManager.getDeviceId());
// Permission has already been granted
        }
    /*    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,new String[] { Manifest.permission.READ_PHONE_STATE}, 1);
            SubscriberID.setText(telephonyManager.getSubscriberId());
            IMEINumber.setText(telephonyManager.getDeviceSoftwareVersion());
            SIMNumber.setText(telephonyManager.getSimSerialNumber());
            Utilities.showLogcatMessage(" " + telephonyManager.getDeviceId());
        }
        else {
            Utilities.showLogcatMessage(" Not Permited");
        }*/



    }


    public void getAllInfo() {

      /*  try (java.util.Scanner s = new java.util.Scanner(new java.net.URL("https://api.ipify.org").openStream(), "UTF-8").useDelimiter("\\A")) {
            //System.out.println("My current IP address is " + s.next());
            IPAddress.setText(s.next());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }*/
       /* RetrofitService retrofitService = RetrofitAPIInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> listCall = retrofitService.;
        listCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.body() != null) {

                    IPAddress.setText(response.body());


                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(HardwareInformationActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });*/

    }
}
