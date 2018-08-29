package com.anew.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddDonor extends AppCompatActivity {
    EditText er;
    Button bn;
    String yname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donor);
        er=(EditText)findViewById(R.id.qname);
        bn=(Button)findViewById(R.id.poi);
        yname=er.getText().toString();
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("n",yname);
            }
        });
    }
}
