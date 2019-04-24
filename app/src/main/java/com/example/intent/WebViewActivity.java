package com.example.intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Button button_toWebView = (Button) this.<View>findViewById(R.id.button_toAddress);
        button_toWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText_address);
                String uri = editText.getText().toString();

                if (Patterns.WEB_URL.matcher(uri).matches()) {
                    WebView webView = (WebView) findViewById(R.id.webView);
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.setWebViewClient(new WebViewClient()/* {
                        //覆盖shouldOverrideUrlLoading 方法
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);
                            return true;
                        }
                    }*/);
                    webView.loadUrl("http://www.baidu.com");

                } else {
                    Toast.makeText(WebViewActivity.this, "uri地址不合法", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
