package com.opus_bd.lostandfound.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UnknownManActivity extends AppCompatActivity {
    @BindView(R.id.llInput)
    LinearLayout llInput;
    @BindView(R.id.llReport)
    LinearLayout llReport;
    boolean isllPersonInfromationChecked = true;
    @BindView(R.id.llPersonInfromation)
    LinearLayout llPersonInfromation;




    @BindView(R.id.ivTPersonInfromation)
    ImageView ivTPersonInfromation;


    boolean isllPersonIdentificationChecked = true;
    @BindView(R.id.mcvPersonIdendityInformation)
    MaterialCardView mcvPersonIdendityInformation;

    @BindView(R.id.llPersonIdentification)
    LinearLayout llPersonIdentification;

    @BindView(R.id.ivTPersonIdentification)
    ImageView ivTPersonIdentification;

    boolean isllPersonPhysicalChecked = true;
    @BindView(R.id.mcvPersonPhysical)
    MaterialCardView mcvPersonPhysical;

    @BindView(R.id.llPersonPhysical)
    LinearLayout llPersonPhysical;

    @BindView(R.id.ivTPersonPhysical)
    ImageView ivTPersonPhysical;


    //input field
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etFathersName)
    EditText etFathersName;
    @BindView(R.id.etSpouseName)
    EditText etSpouseName;
    @BindView(R.id.etNidNum)
    EditText etNidNum;
    @BindView(R.id.spnReligion)
    Spinner spnReligion;
    @BindView(R.id.spnGender)
    Spinner spnGender;
    @BindView(R.id.spnBloodGroup)
    Spinner spnBloodGroup;
    @BindView(R.id.spnOcupation)
    Spinner spnOcupation;


    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvFathersName)
    TextView tvFathersName;
    @BindView(R.id.tvSpouseName)
    TextView tvSpouseName;
    @BindView(R.id.tvNidNum)
    TextView tvNidNum;
    @BindView(R.id.tvReligion)
    TextView tvReligion;
    @BindView(R.id.tvGender)
    TextView tvGender;
/*
    @BindView(R.id.tvBloodGroup)
    TextView tvBloodGroup;
    @BindView(R.id.tvOcupation)
    TextView tvOcupation;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unknown_man);
        ButterKnife.bind(this);
        llReport.setVisibility(View.GONE);
        llPersonInfromation.setVisibility(View.VISIBLE);
        Glide.with(this).load(R.drawable.ic_drop_up).into(ivTPersonInfromation);
        mcvPersonIdendityInformation.setVisibility(View.GONE);
        mcvPersonPhysical.setVisibility(View.GONE);

    }

    protected void attachBaseContext(Context base) {
        SharedPreferences tprefs = base.getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME, MODE_PRIVATE);
        boolean language = tprefs.getBoolean(SharedPrefManager.KEY_State, true);
        if (language)
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.ENGLISH));
        else
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.BANGLA));
    }

    @OnClick(R.id.ivTPersonInfromation)
    public void ivTPersonInfromation() {
        if (isllPersonInfromationChecked) {
            // show password
            llPersonInfromation.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivTPersonInfromation);
            isllPersonInfromationChecked = false;
        } else {
            // hide password
            llPersonInfromation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonInfromation);
            isllPersonInfromationChecked = true;
        }

    }


    @OnClick({R.id.ivTPersonIdentification, R.id.btnNext1})
    public void ivTPersonIdentification() {
        if (isllPersonIdentificationChecked) {
            // show password
            llPersonInfromation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonInfromation);
            isllPersonInfromationChecked = true;

            mcvPersonIdendityInformation.setVisibility(View.VISIBLE);
            llPersonIdentification.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivTPersonIdentification);
            isllPersonIdentificationChecked = false;

        } else {
            // hide password
            llPersonIdentification.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonIdentification);
            isllPersonIdentificationChecked = true;
        }

    }


    @OnClick({R.id.ivTPersonPhysical, R.id.btnNext2})
    public void ivTPersonPhysical() {
        if (isllPersonPhysicalChecked) {
            // show password
            llPersonIdentification.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonIdentification);
            isllPersonIdentificationChecked = true;

            mcvPersonPhysical.setVisibility(View.VISIBLE);
            llPersonPhysical.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivTPersonPhysical);
            isllPersonPhysicalChecked = false;

        } else {
            // hide password
            llPersonPhysical.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonPhysical);
            isllPersonPhysicalChecked = true;
        }

    }

    public void customDialog() {
        final Dialog dialog = new Dialog(UnknownManActivity.this);
        dialog.setContentView(R.layout.dialog_custom_code_generator);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        // set the custom dialog components - text, image and button
        Button btnThanks = (Button) dialog.findViewById(R.id.btnThanks);


        btnThanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    Intent intent = new Intent(UnknownManActivity.this, DashboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Toast.makeText(dialog, "Please install browser to continue", Toast.LENGTH_SHORT).show();
                }
            }
        });


        dialog.show();
    }

    @OnClick(R.id.btnNext3)
    public void btnNext2() {
        llInput.setVisibility(View.GONE);
        llReport.setVisibility(View.VISIBLE);
        tvName.setText(etName.getText().toString());
        tvFathersName.setText(etFathersName.getText().toString());
        tvSpouseName.setText(etSpouseName.getText().toString());
        tvNidNum.setText(etNidNum.getText().toString());
        tvReligion.setText(spnReligion.getSelectedItem().toString());
        tvGender.setText(spnGender.getSelectedItem().toString());


       /* Intent intent = new Intent(UnknownManActivity.this, CodeGenerateActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }  @OnClick(R.id.Edit)
    public void Edit() {
        llInput.setVisibility(View.VISIBLE);
        llReport.setVisibility(View.GONE);
       /* tvName.setText(etName.getText().toString());
        tvFathersName.setText(etFathersName.getText().toString());
        tvSpouseName.setText(etSpouseName.getText().toString());
        tvNidNum.setText(etNidNum.getText().toString());
        tvReligion.setText(spnReligion.getSelectedItem().toString());*/


       /* Intent intent = new Intent(UnknownManActivity.this, CodeGenerateActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }

    @OnClick(R.id.Submit)
    public void Submit() {
        Intent intent = new Intent(UnknownManActivity.this, CodeGenerateActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}