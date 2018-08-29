package com.anew.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindDonor extends AppCompatActivity {
Button bfnd;
    Spinner spinner;
    String jblood;
    DatabaseReference databaseDonor;
    RecyclerView recyclerView;
    RecyclerView.Adapter adaptery;

    List<Person> donorList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_donor);
        bfnd=(Button)findViewById(R.id.bFind);
        recyclerView=(RecyclerView)findViewById(R.id.donorss);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        donorList=new ArrayList<>();
        String[] arraySpinner={"O+","O-","A+","A-","B+","B-","AB+","AB-"};
        spinner=(Spinner)findViewById(R.id.spinner2);
         ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        jblood="O+";
                        break;
                    case 1:
                        jblood="O-";
                        break;
                    case 2:
                        jblood="A+";
                        break;
                    case 3:
                        jblood="A-";
                        break;
                    case 4:
                        jblood="B+";
                        break;
                    case 5:
                        jblood="B-";
                        break;
                    case 6:
                        jblood="AB+";
                        break;
                    case 7:
                        jblood="AB-";
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        databaseDonor= FirebaseDatabase.getInstance().getReference("donors");
        bfnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donorList.clear();
                databaseDonor.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            Person person=ds.getValue(Person.class);
                            String nme=person.getPname();
                            String num=person.getPnumber();
                            String ag=person.getPage();
                            String bld=person.getPblood();
                            if(bld.equals(jblood))
                            {
                                Log.i("n",nme);
                                Log.i("a",ag);
                                Log.i("nm",num);
//                                HashMap<String,String> donorDetails=new HashMap<String, String>();
//                                donorDetails.put("name",nme);
//                                donorDetails.put("age",ag);
//                                donorDetails.put("number",num);
                                donorList.add(person);


                            }
//                            String cy=person.getPcity().trim();

                        }
                        adaptery=new MyAdapty(donorList,getApplicationContext());
                        recyclerView.setAdapter(adaptery);



                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
