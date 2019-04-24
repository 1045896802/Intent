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
