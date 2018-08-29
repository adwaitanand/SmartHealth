package com.anew.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {
    Button b;
    EditText edname;
    EditText ednum;
    EditText edage;
    String jname;
    String jnum;
    String jage;
    String jblood;
    Spinner spinner;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        firebaseAuth = FirebaseAuth.getInstance();
        edname = (EditText) findViewById(R.id.dname);
        ednum = (EditText) findViewById(R.id.dnumber);
        edage = (EditText) findViewById(R.id.dage);
        jname = edname.getText().toString();
        jnum = ednum.getText().toString();
        jage = edage.getText().toString();
        b = (Button) findViewById(R.id.dsubmi);

        String[] arraySpinner = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        jblood = "O+";
                        break;
                    case 1:
                        jblood = "O-";
                        break;
                    case 2:
                        jblood = "A+";
                        break;
                    case 3:
                        jblood = "A-";
                        break;
                    case 4:
                        jblood = "B+";
                        break;
                    case 5:
                        jblood = "B-";
                        break;
                    case 6:
                        jblood = "AB+";
                        break;
                    case 7:
                        jblood = "AB-";
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("name", edname.getText().toString());
                Log.i("num", ednum.getText().toString());
                Log.i("age", edage.getText().toString());
                Log.i("blood", jblood);
                if (edname.getText().toString().equals("") || ednum.getText().toString().equals("") || edage.getText().toString().equals("")) {
                    Toast.makeText(Main2Activity.this, "Some entries are left to be filled", Toast.LENGTH_LONG).show();
                } else if (Integer.parseInt(edage.getText().toString()) < 17) {
                    Toast.makeText(Main2Activity.this, "Minimum age to be a blood donor is 17 years", Toast.LENGTH_LONG).show();
                } else {
                        sendData();
                    Toast.makeText(Main2Activity.this,"Submitted Sucessfully",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private  void sendData()
    {
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myRef=firebaseDatabase.getReference("donors");
        Person person=new Person( edname.getText().toString(),ednum.getText().toString(),edage.getText().toString(),jblood);
        myRef.child(firebaseAuth.getUid()).setValue(person);
    }
}

