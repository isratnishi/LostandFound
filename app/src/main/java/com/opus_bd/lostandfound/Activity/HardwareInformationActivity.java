package com.opus_bd.lostandfound.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.app.Service;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.TextView;

import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HardwareInformationActivity extends AppCompatActivity {

    private static int sLastCpuCoreCount = -1;
    @BindView(R.id.IMEINumber)
    TextView IMEINumber;
    @BindView(R.id.SubscriberID)
    TextView SubscriberID;
    @BindView(R.id.SIMNumber)
    TextView SIMNumber;
    @BindView(R.id.IPAddress)
    TextView IPAddress;
    @BindView(R.id.WiFiMACAddress)
    TextView WiFiMACAddress;
    @BindView(R.id.OperatorName)
    TextView OperatorName;
    @BindView(R.id.CountryCode)
    TextView CountryCode;
    @BindView(R.id.DNSAddress)
    TextView DNSAddress;
    @BindView(R.id.MotherBoard)
    TextView MotherBoard;
    @BindView(R.id.Processor)
    TextView Processor;
    TelephonyManager telephonyManager;
    private String url = "https://api.ipify.org";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardware_information);
        ButterKnife.bind(this);
        MotherBoard.setText(Build.BOARD);


        try {
            WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            //  WifiInfo wifiInfo=wm.getConnectionInfo().getSSID(.;
            assert wm != null;
            Utilities.showLogcatMessage("ip" + wm.getConnectionInfo().getIpAddress());
            Utilities.showLogcatMessage("wifi" + wm.getConnectionInfo().getMacAddress());
         /*   String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
            String wifi = Formatter.formatIpAddress(Integer.parseInt(wm.getConnectionInfo().getMacAddress()));*/

            String ipAddress = Formatter.formatIpAddress(wm.getConnectionInfo().getNetworkId());


            //  WiFiMACAddress.setText(String.valueOf(wm.getConnectionInfo().getMacAddress()));
            IPAddress.setText(String.valueOf(Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress())));
            DhcpInfo info = wm.getDhcpInfo();
        } catch (Exception e) {
            Utilities.showLogcatMessage("wifi" + e.toString());
        }

        telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        Ids();

        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Service.CONNECTIVITY_SERVICE);

        /* you can print your active network via using below */
        Log.i("myNetworkType: ", connectivityManager.getActiveNetworkInfo().getTypeName());
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(getApplicationContext().WIFI_SERVICE);


       /* Log.i("routes ", connectivityManager.getLinkProperties(connectivityManager.getActiveNetwork()).getRoutes().toString());
        Log.i("domains ", connectivityManager.getLinkProperties(connectivityManager.getActiveNetwork()).getDomains().toString());
        Log.i("ip address ", connectivityManager.getLinkProperties(connectivityManager.getActiveNetwork()).getLinkAddresses().toString());
        Log.i("dns address ", connectivityManager.getLinkProperties(connectivityManager.getActiveNetwork()).getDnsServers().toString());
     */
        DNSAddress.setText(connectivityManager.getLinkProperties(connectivityManager.getActiveNetwork()).getDnsServers().toString());


        if (connectivityManager.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI) {
            DNSAddress.setText(connectivityManager.getLinkProperties(connectivityManager.getActiveNetwork()).getDnsServers().toString());

            Log.i("myType ", "wifi");
            DhcpInfo d = wifiManager.getDhcpInfo();
            Log.i("info", d.toString() + "");
        } else if (connectivityManager.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_ETHERNET) {
            /* there is no EthernetManager class, there is only WifiManager. so, I used this below trick to get my IP range, dns, gateway address etc */
            DNSAddress.setText(connectivityManager.getLinkProperties(connectivityManager.getActiveNetwork()).getDnsServers().toString());

            Log.i("myType ", "Ethernet");
            Log.i("routes ", connectivityManager.getLinkProperties(connectivityManager.getActiveNetwork()).getRoutes().toString());
            Log.i("domains ", connectivityManager.getLinkProperties(connectivityManager.getActiveNetwork()).getDomains().toString());
            Log.i("ip address ", connectivityManager.getLinkProperties(connectivityManager.getActiveNetwork()).getLinkAddresses().toString());
            Log.i("dns address ", connectivityManager.getLinkProperties(connectivityManager.getActiveNetwork()).getDnsServers().toString());

        } else {

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            List<SubscriptionInfo> subscription = SubscriptionManager.from(getApplicationContext()).getActiveSubscriptionInfoList();
            for (int i = 0; i < subscription.size(); i++) {
                SubscriptionInfo info = subscription.get(i);
                Utilities.showLogcatMessage("number " + info.getSubscriptionId());
                Utilities.showLogcatMessage("network name : " + info.getCarrierName());
                Utilities.showLogcatMessage("country iso " + info.getCountryIso());
            }
        }

        //	getDataFromUrl(); // Connect url from the main thread for get data this will throw NetworkOnMainThreadExcection

        // new GetJSONTask().execute(url); //execute asynctask object this will resolve NetworkOnMainThreadExcection
        Processor.setText(Build.CPU_ABI);
       /* for (int i = 0; i < calcCpuCoreCount(); i++) {
            Processor.append(takeCurrentCpuFreq(i) +"\n");
        }*/
        DeviceAdminReceiver admin = new DeviceAdminReceiver();
        DevicePolicyManager devicepolicymanager = admin.getManager(getApplicationContext());
        ComponentName name1 = admin.getWho(getApplicationContext());
        if (devicepolicymanager.isAdminActive(name1)) {
            String mac_address = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                mac_address = devicepolicymanager.getWifiMacAddress(name1);
                WiFiMACAddress.setText(mac_address);
            }


        } else {
            WiFiMACAddress.setText(getMacAddr());
        }

    }
    private static int readIntegerFile(String filePath) {

        try {
            final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filePath)), 1000);
            final String line = reader.readLine();
            reader.close();

            return Integer.parseInt(line);
        } catch (Exception e) {
            return 0;
        }
    }

    private static int takeCurrentCpuFreq(int coreIndex) {
        return readIntegerFile("/sys/devices/system/cpu/cpu" + coreIndex + "/cpufreq/scaling_cur_freq");
    }

    public static int calcCpuCoreCount() {

        if (sLastCpuCoreCount >= 1) {
            // キャッシュさせる
            return sLastCpuCoreCount;
        }

        try {
            // Get directory containing CPU info
            final File dir = new File("/sys/devices/system/cpu/");
            // Filter to only list the devices we care about
            final File[] files = dir.listFiles(new FileFilter() {

                public boolean accept(File pathname) {
                    //Check if filename is "cpu", followed by a single digit number
                    if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                        return true;
                    }
                    return false;
                }
            });

            // Return the number of cores (virtual CPU devices)
            sLastCpuCoreCount = files.length;

        } catch(Exception e) {
            sLastCpuCoreCount = Runtime.getRuntime().availableProcessors();
        }

        return sLastCpuCoreCount;
    }
    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    // res1.append(Integer.toHexString(b & 0xFF) + ":");
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            //handle exception
        }
        return "";
    }


    public static Map<String, String> getCPUInfo() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("/proc/cpuinfo"));

        String str;

        Map<String, String> output = new HashMap<>();

        while ((str = br.readLine()) != null) {

            String[] data = str.split(":");

            if (data.length > 1) {

                String key = data[0].trim().replace(" ", "_");
                if (key.equals("model_name")) key = "cpu_model";
                {
                    output.put(key, data[1].trim());

                }


            }

        }

        br.close();

        return output;

    }

    private String getInfo() {
        StringBuffer sb = new StringBuffer();
         sb.append(Build.CPU_ABI);/*.append("\n");
        if (new File("/proc/cpuinfo").exists()) {
            try {
                BufferedReader br = new BufferedReader(
                        new FileReader(new File("/proc/cpuinfo")));
                String aLine;
                while ((aLine = br.readLine()) != null) {
                    String[] data = aLine.split(":");

                    if (data.length > 1) {

                        String key = data[0].trim().replace(" ", "_");
                        if (key.equals("model_name")) key = "cpu_model";

                        {
                            sb.append(data[1].trim());

                        }

                    }
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        return sb.toString();
    }


    private void getDataFromUrl() {
        try {
            IPAddress.setText(Utilities.downloadDataFromUrl(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.btnNext1)
    public void btnNext1() {
        Intent intent = new Intent(HardwareInformationActivity.this, LoginActivity.class);
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
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        } else {
            SubscriberID.setText(telephonyManager.getSubscriberId());
            IMEINumber.setText(telephonyManager.getDeviceId(0) + "\n" + telephonyManager.getDeviceId(1));
            SIMNumber.setText("" + telephonyManager.getSimSerialNumber());
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
