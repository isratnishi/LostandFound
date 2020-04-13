package com.opus_bd.lostandfound.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardActivity extends AppCompatActivity {
/*@BindView(R.id.llSeemore1)
    LinearLayout llSeemore1;@BindView(R.id.ll2)
    LinearLayout ll2;*/

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        BottomAppBar bar = (BottomAppBar) findViewById(R.id.bar);
        setSupportActionBar(bar);
        setSupportActionBar(toolbar);
      /*  if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Main Page");
        }*/
        toolbar.setSubtitle("Test Subtitle");
        toolbar.inflateMenu(R.menu.menu);
        /*bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Home Clicked",Toast.LENGTH_LONG).show();
            }
        });

        bar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ihelp:
                        Toast.makeText(getApplicationContext(),"Help Clicked",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.ilogout:


                        Intent intent=new Intent(DashboardActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.contact:
                        Toast.makeText(getApplicationContext(),"Contact Clicked",Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });*/
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
  /*  @OnClick({R.id.llSeemore,R.id.fabSeemore,R.id.tbSeemore})
    public void llSeemore() {
       ll2.setVisibility(View.VISIBLE);
        llSeemore1.setVisibility(View.VISIBLE);
       llSeemore.setVisibility(View.GONE);
    }


    @OnClick({R.id.llHidemore,R.id.fabHidemore,R.id.tvHidemore})
    public void llHidemore() {
       ll2.setVisibility(View.GONE);
        llSeemore1.setVisibility(View.GONE);
       llSeemore.setVisibility(View.VISIBLE);
    }*/


    @OnClick({R.id.llTheft,R.id.fabTheft})
    public void llTheft() {
        Intent intent = new Intent(DashboardActivity.this, InformationEntryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    /*@OnClick(R.id.button1)
    public void button1() {
        Intent intent = new Intent(Main2Activity.this, InformationEntryActivity.class);

        startActivity(intent);
    } @OnClick(R.id.button2)
    public void button2() {
        Intent intent = new Intent(Main2Activity.this, InformationSearchActivity.class);

        startActivity(intent);
    }*/


}
