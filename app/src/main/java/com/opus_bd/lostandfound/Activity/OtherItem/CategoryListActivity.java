package com.opus_bd.lostandfound.Activity.OtherItem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.opus_bd.lostandfound.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.llRoot,R.id.llRoot1})
    public void llRoot(){
        Intent intent = new Intent(this, ComputerActivity.class);
startActivity(intent);
    }
}
