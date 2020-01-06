package com.softwarica.hamrobazar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.softwarica.hamrobazar.TermsandConditions.PostActivity;
import com.softwarica.hamrobazar.TermsandConditions.SafetyActivity;
import com.softwarica.hamrobazar.TermsandConditions.TermsActivity;

public class TermsConditionsActivity extends AppCompatActivity {

    CheckBox chkTerms, chkSafety, chkAd;
    Button btnAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);

        chkTerms = findViewById(R.id.chkTerms);
        chkSafety = findViewById(R.id.chkSafety);
        chkAd = findViewById(R.id.chkAd);
        btnAgree = findViewById(R.id.btnAgree);

        chkTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TermsConditionsActivity.this, TermsActivity.class));
            }
        });

        chkSafety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TermsConditionsActivity.this, SafetyActivity.class));
            }
        });

        chkAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TermsConditionsActivity.this, PostActivity.class));
            }
        });

        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chkTerms.isChecked() && chkSafety.isChecked() && chkAd.isChecked()) {
                    startActivity(new Intent(TermsConditionsActivity.this, DashboardActivity.class));
                }
                else {
                    Toast.makeText(TermsConditionsActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
