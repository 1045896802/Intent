# 自定义WebView验证隐式Intent的使用

## 一. 实验内容

1.本实验通过自定义WebView加载URL来验证隐式Intent的使用。

2.实验包含两个应用：

(1)第一个应用：获取URL地址并启动隐式Intent的调用。

 ![Image text](https://github.com/1045896802/Intent/blob/master/img/1y.png)

(2)第二个应用：自定义WebView来加载URL

 ![Image text](https://github.com/1045896802/Intent/blob/master/img/2y.png)
 
 3.参考文献
 
 https://developer.android.google.cn/guide/components/intents-filters

## 二. 关键代码

  1.第一个应用：获取URL地址并启动隐式Intent的调用。
  
   (1)activity_intent.xml

    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        tools:context=".IntentActivity">

        <EditText
            android:id="@+id/editText_address"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@string/editText_address" />

        <Button
            android:id="@+id/button_toAddress"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@string/button_toAddress" />
    </LinearLayout>
    
   (2)IntentActivity.java

    package com.example.intent;

    import android.content.Intent;
    import android.net.Uri;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.util.Patterns;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Toast;

    public class IntentActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_intent);

            Button button_toWebView = (Button) this.<View>findViewById(R.id.button_toAddress);
            button_toWebView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText editText = (EditText) findViewById(R.id.editText_address);
                    String uri = editText.getText().toString();
                    Intent intent = new Intent();
                    if (Patterns.WEB_URL.matcher(uri).matches()) {
                        intent.setAction("android.intent.action.VIEW");
                        intent.setData(Uri.parse(uri)); //为Intent设置DATA属性
                        startActivity(intent);
                    } else {
                        Toast.makeText(IntentActivity.this, "uri地址不合法", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

  
  2.第二个应用：自定义WebView来加载URL
  
  (1)activity_web_view.xml
  
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        tools:context=".WebViewActivity">

        <EditText
            android:id="@+id/editText_address"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@string/editText_address" />

        <Button
            android:id="@+id/button_toAddress"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@string/button_toAddress" />

        <WebView
            android:id="@+id/webView"
            android:layout_width="match_parent"
            android:layout_height="match_parent"></WebView>

    </LinearLayout>
    
  (2)WebViewActivity.java
  
    package com.example.intent;

    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
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
                        webView.loadUrl(uri);

                    } else {
                        Toast.makeText(WebViewActivity.this, "uri地址不合法", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }


## 三. 实验结果及截图
  
  1.获取URL地址并启动隐式Intent的调用
  
  ![Image text](https://github.com/1045896802/Intent/blob/master/img/1r.png)
  
  2.自定义WebView来加载URL
  
  ![Image text](https://github.com/1045896802/Intent/blob/master/img/2r.png)



