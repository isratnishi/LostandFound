package com.opus_bd.lostandfound.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InformationEntryActivity extends AppCompatActivity {
    @BindView(R.id.llVOwn)
    LinearLayout llVOwn;

    @BindView(R.id.llVOthers)
    LinearLayout llVOthers;

    @BindView(R.id.LLInputForOthers)
    LinearLayout LLInputForOthers;

    @BindView(R.id.LLItems)
    LinearLayout LLItems;
    @BindView(R.id.llInput)
    LinearLayout llInput;


    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv4)
    ImageView iv4;

    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.v3)
    View v3;


    @BindView(R.id.cvItems)
    CardView cvItems;

    @BindView(R.id.cvInput)

    CardView cvInput;
    @BindView(R.id.fabOwn)
    FloatingActionButton fabOwn;
    @BindView(R.id.fabVihecal)
    FloatingActionButton fabVihecal;

    @BindView(R.id.fabOther)
    FloatingActionButton fabOther;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_entry);
        ButterKnife.bind(this);
        LLItems.setVisibility(View.GONE);
        LLInputForOthers.setVisibility(View.GONE);
        iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
    }
    protected void attachBaseContext(Context base) {
        SharedPreferences tprefs = base.getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME, MODE_PRIVATE);
        boolean language = tprefs.getBoolean(SharedPrefManager.KEY_State, true);
        if (language)
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.ENGLISH));
        else
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.BANGLA));
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.llVOwn, R.id.fabOwn})
    public void LlVOwn() {
        fabOwn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorPrimaryDark));
        fabOther.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.grey_40));
        LLInputForOthers.setVisibility(View.GONE);
        cvInput.setVisibility(View.GONE);
        LLItems.setVisibility(View.VISIBLE);
        cvItems.setVisibility(View.VISIBLE);
        iv4.setVisibility(View.GONE);
        v3.setVisibility(View.GONE);
        iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.llVOthers, R.id.fabOther})
    public void llVOthers() {
        fabOwn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.grey_40));
        fabOther.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorPrimaryDark));
        LLInputForOthers.setVisibility(View.VISIBLE);
        cvInput.setVisibility(View.GONE);
        LLItems.setVisibility(View.GONE);
        cvItems.setVisibility(View.GONE);
        iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.btnOthersInput)
    public void btnOthersInput() {
        LLInputForOthers.setVisibility(View.GONE);
        // llEntry.setVisibility(View.GONE);
        cvItems.setVisibility(View.VISIBLE);
        LLItems.setVisibility(View.VISIBLE);
        iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.llVVihecal, R.id.fabVihecal})
    public void llVVihecal() {

        fabVihecal.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorPrimaryDark));
        LLInputForOthers.setVisibility(View.GONE);
        llInput.setVisibility(View.GONE);
        LLItems.setVisibility(View.VISIBLE);
        cvItems.setVisibility(View.GONE);
        iv4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        Intent intent = new Intent(InformationEntryActivity.this, VehicleEntryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.llVVivid, R.id.fabVivid})
    public void llVVivid() {

        fabVihecal.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorPrimaryDark));
        LLInputForOthers.setVisibility(View.GONE);
        llInput.setVisibility(View.GONE);
        LLItems.setVisibility(View.VISIBLE);
        cvItems.setVisibility(View.GONE);
        iv4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        Intent intent = new Intent(InformationEntryActivity.this, OthersItemEntryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }


    @OnClick(R.id.fab)
    public void fab() {
        Intent intent = new Intent(InformationEntryActivity.this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
