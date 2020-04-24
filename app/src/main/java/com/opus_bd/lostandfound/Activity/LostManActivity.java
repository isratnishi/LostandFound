package com.opus_bd.lostandfound.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView;

import com.opus_bd.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LostManActivity extends AppCompatActivity {
    @BindView(R.id.tvModel)
    TextView tvModel;

    @BindView(R.id.tvRegNoName)
    TextView tvRegNoName;

    @BindView(R.id.tvEngineNo)
    TextView etEngineNo;

    @BindView(R.id.etChesisNo)
    TextView etChesisNo;

    @BindView(R.id.etCCNo)
    TextView etCCNo;

    @BindView(R.id.etMadeIn)
    TextView etMadeIn;

    @BindView(R.id.etMadeDate)
    TextView etMadeDate;

    @BindView(R.id.etIdentitySign)
    TextView etIdentitySign;

    @BindView(R.id.etAddressDetails)
    TextView etAddressDetails;

    @BindView(R.id.etVehicleDate)
    TextView etVehicleDate;

    @BindView(R.id.etVehicleTime)
    TextView etVehicleTime;

    boolean isllVehicleEntryChecked = true;
    @BindView(R.id.llVehicleInfromation)
    LinearLayout llVehicleInfromation;

    @BindView(R.id.ivVehicleInformation)
    ImageView ivVehicleInformation;

    boolean isllVehicleIdentificationChecked = true;
    @BindView(R.id.llVIdentityInfo)
    LinearLayout llVIdentityInfo;

    @BindView(R.id.ivVIdentityInfo)
    ImageView ivVIdentityInfo;

    boolean isllVPATChecked = true;
    @BindView(R.id.llVPATInfo)
    LinearLayout llVPATInfo;

    @BindView(R.id.ivVPATInfo)
    ImageView ivVPATInfo;

    boolean isllVehicleAttachment = true;
    @BindView(R.id.llVehicleAttachment)
    LinearLayout llVehicleAttachment;

    @BindView(R.id.ivVehicleAttachment)
    ImageView ivVehicleAttachment;

    //TextView

    @BindView(R.id.spnSPDivision)
    TextView spnSPDivision;

    @BindView(R.id.spnSPDistrict)
    TextView spnSPDistrict;

    @BindView(R.id.spnSPThana)
    TextView spnSPThana;

    @BindView(R.id.spnDocumentType)
    TextView spnDocumentType;

    @BindView(R.id.spnVehicleType)
    TextView spnVehicleType;

    @BindView(R.id.spnMadeBy)
    TextView spnMadeBy;

    @BindView(R.id.spnRegNoName1)
    TextView spnRegNoName1;

    @BindView(R.id.spnRegNoName2)
    TextView spnRegNoName2;

    @BindView(R.id.spnColor)
    TextView spnColor;


    @BindView(R.id.etBlueBook)
    TextView etBlueBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_man);
        ButterKnife.bind(this);
    }
    //InformationEntryActivity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, InformationEntryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        super.onBackPressed();
    }
    //DashBoard

    @OnClick(R.id.fab)
    public void fab() {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
