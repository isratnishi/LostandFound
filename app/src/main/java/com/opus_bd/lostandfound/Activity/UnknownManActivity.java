package com.opus_bd.lostandfound.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.opus_bd.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UnknownManActivity extends AppCompatActivity {

    boolean isllPersonInfromationChecked = true;
    @BindView(R.id.llPersonInfromation)
    LinearLayout llPersonInfromation;

    @BindView(R.id.ivTPersonInfromation)
    ImageView ivTPersonInfromation;


    boolean isllPersonIdentificationChecked = true;
    @BindView(R.id.llPersonIdentification)
    LinearLayout llPersonIdentification;

    @BindView(R.id.ivTPersonIdentification)
    ImageView ivTPersonIdentification;

    boolean isllPersonPhysicalChecked = true;
    @BindView(R.id.llPersonPhysical)
    LinearLayout llPersonPhysical;

    @BindView(R.id.ivTPersonPhysical)
    ImageView ivTPersonPhysical;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unknown_man);
        ButterKnife.bind(this);
        llPersonInfromation.setVisibility(View.GONE);
        llPersonIdentification.setVisibility(View.GONE);
        llPersonPhysical.setVisibility(View.GONE);

    }
    @OnClick(R.id.ivTPersonInfromation)
    public void ivTPersonInfromation(){
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


    @OnClick({R.id.ivTPersonIdentification,R.id.btnNext1})
    public void ivTPersonIdentification(){
        if (isllPersonIdentificationChecked) {
            // show password
            llPersonInfromation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonInfromation);
            isllPersonInfromationChecked = true;

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



    @OnClick({R.id.ivTPersonPhysical,R.id.btnNext2})
    public void ivTPersonPhysical(){
        if (isllPersonPhysicalChecked) {
            // show password
            llPersonIdentification.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonIdentification);
            isllPersonIdentificationChecked = true;

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
      customDialog();
    }
}
