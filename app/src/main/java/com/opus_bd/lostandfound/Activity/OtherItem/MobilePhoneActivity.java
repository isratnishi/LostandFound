package com.opus_bd.lostandfound.Activity.OtherItem;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.opus_bd.lostandfound.R;

import butterknife.ButterKnife;

public class MobilePhoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_phone);
        ButterKnife.bind(this);
    }
}
