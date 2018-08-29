package com.anew.firebasedemo;

import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import saschpe.android.customtabs.CustomTabsHelper;
import saschpe.android.customtabs.WebViewFallback;

public class FirstPage extends AppCompatActivity {
    Button hospital;
    Button bdonor;
    Button fdonor;
    Button logout;
    Button disease;
    Button credits;
    Button bmi;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        firebaseAuth=FirebaseAuth.getInstance();
        hospital=(Button)findViewById(R.id.btnHospital);
        bdonor=(Button)findViewById(R.id.btnAddDonor);
        fdonor=(Button)findViewById(R.id.btnFindDonor);
        logout=(Button)findViewById(R.id.btnLogout);
        disease=(Button)findViewById(R.id.btnDisease);
        credits=(Button)findViewById(R.id.btnCredits);
        bmi=(Button)findViewById(R.id.btnBmi);

           bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstPage.this,Bmi.class));
            }
        });

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstPage.this,MapsActivity.class));
            }
        });
        bdonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstPage.this,Main2Activity.class));
            }
        });
        fdonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstPage.this,FindDonor.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(FirstPage.this,Login.class));
            }
        });
        disease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://www.nhsinform.scot/illnesses-and-conditions/a-to-z";
                CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                        .addDefaultShareMenuItem()
                        .setToolbarColor(FirstPage.this.getResources().getColor(R.color.colorPrimary))
                        .setShowTitle(true)
                        .build();

// This is optional but recommended
                CustomTabsHelper.addKeepAliveExtra(FirstPage.this, customTabsIntent.intent);

// This is where the magic happens...
                CustomTabsHelper.openCustomTab(FirstPage.this, customTabsIntent,
                        Uri.parse(url),
                        new WebViewFallback());
            }
        });

        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstPage.this,CreditsPage.class));
            }
        });
    }
}
