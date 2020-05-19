package com.opus_bd.lostandfound.Activity.OtherItem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryListActivity extends AppCompatActivity {
    @BindView(R.id.ivItemLogo)
    ImageView ivItemLogo;
    @BindView(R.id.ivItemLogo2)
    ImageView ivItemLogo2;
    @BindView(R.id.ivItemLogo3)
    ImageView ivItemLogo3;
    @BindView(R.id.ivItemLogo4)
    ImageView ivItemLogo4;
    @BindView(R.id.ivItemLogo5)
    ImageView ivItemLogo5;
    @BindView(R.id.ivItemLogo1)
    ImageView ivItemLogo1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        ButterKnife.bind(this);
        this.setTitle(getResources().getText(R.string.computer_information_entry));
        Glide.with(this).load(R.drawable.desktop).into(ivItemLogo);
        Glide.with(this).load(R.drawable.laptop).into(ivItemLogo1);
        Glide.with(this).load(R.drawable.hybride).into(ivItemLogo2);
        Glide.with(this).load(R.drawable.computer_exesories).into(ivItemLogo3);
        Glide.with(this).load(R.drawable.notebook).into(ivItemLogo4);
        Glide.with(this).load(R.drawable.omnicomputer).into(ivItemLogo5);
    }

    protected void attachBaseContext(Context base) {
        SharedPreferences tprefs = base.getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME, MODE_PRIVATE);
        boolean language = tprefs.getBoolean(SharedPrefManager.KEY_State, true);
        if (language)
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.ENGLISH));
        else
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.BANGLA));
    }

    @OnClick({R.id.llRoot, R.id.llRoot1,R.id.llRoot2,R.id.llRoot4,R.id.llRoot5})
    public void llRoot(View v) {
        switch (v.getId()){
            case R.id.llRoot:
                Constants.COMPUTER_TYPE_NAME=getResources().getText(R.string.desktop).toString();
                break;
            case  R.id.llRoot1:
                Constants.COMPUTER_TYPE_NAME=getResources().getText(R.string.laptop).toString();
                break;
            case  R.id.llRoot2:
                Constants.COMPUTER_TYPE_NAME=getResources().getText(R.string.hybrid).toString();
                break;
            case  R.id.llRoot4:
                Constants.COMPUTER_TYPE_NAME=getResources().getText(R.string.note_book).toString();
                break;
            case  R.id.llRoot5:
                Constants.COMPUTER_TYPE_NAME=getResources().getText(R.string.omni).toString();
                break;

        }

        Constants.COMPUTER_TYPE_ID=Constants.COMOthers;
        Intent intent = new Intent(this, ComputerActivity.class);
        startActivity(intent);
    }@OnClick(R.id.llRoot3)
    public void llRoot3() {
        Constants.COMPUTER_TYPE_ID=Constants.COMACCESORIES;
        Constants.COMPUTER_TYPE_NAME=getResources().getText(R.string.accessories).toString();
        Intent intent = new Intent(this, ComputerActivity.class);
        startActivity(intent);
    }
}
