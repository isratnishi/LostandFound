package com.opus_bd.lostandfound.Activity.OtherItem;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.opus_bd.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.opus_bd.lostandfound.R;

public class OtherItemDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_item_details);
        ButterKnife.bind(this);
    }
}
