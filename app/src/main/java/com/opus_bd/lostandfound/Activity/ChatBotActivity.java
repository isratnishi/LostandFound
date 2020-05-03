package com.opus_bd.lostandfound.Activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.opus_bd.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatBotActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);
        ButterKnife.bind(this);

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);


        webview.loadUrl("http://103.134.88.13:122/CustoomChatBox#totaMessagesContainerLast");


    }
}
