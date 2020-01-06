package com.softwarica.hamrobazar.TermsandConditions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.softwarica.hamrobazar.R;

public class SafetyActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);

        webView = findViewById(R.id.webView);

        webView.loadUrl("https://hamrobazaar.com/m/safetytips.php");
    }
}
