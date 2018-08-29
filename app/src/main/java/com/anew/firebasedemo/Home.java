package com.anew.firebasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    Button logout,submit,vcity;
    EditText nome,city,vname;
    DatabaseReference databasePerson;
    TextView gcity;
    Boolean flag=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth=FirebaseAuth.getInstance();
        databasePerson= FirebaseDatabase.getInstance().getReference("persons");//person parameter is used to create it as root node
        logout=(Button)findViewById(R.id.logout);
        submit=(Button)findViewById(R.id.submit);
        nome=(EditText)findViewById(R.id.nome);
        city=(EditText)findViewById(R.id.city);
        gcity=(TextView) findViewById(R.id.gcity);
        vcity=(Button)findViewById(R.id.vcity);
        vname=(EditText)findViewById(R.id.vname);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(Home.this,Login.class));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEntries();
            }
        });
        vcity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databasePerson.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            Person person=ds.getValue(Person.class);
                            String nm=person.getPname();
//                            String cy=person.getPcity().trim();
                            if(nm.equals(vname.getText().toString()))
                            {
//                                gcity.setText(cy);
//                                flag=true;
                                break;
                            }

                        }
                        if(!flag)
                        {
                            Toast.makeText(Home.this,"Not found",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logoutmenu:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(Home.this,Login.class));
        }
        return super.onOptionsItemSelected(item);
    }
    private void addEntries()
    {
        String nm=nome.getText().toString();
        String cty=city.getText().toString();
        if(nm.isEmpty()||cty.isEmpty())
        {
            Toast.makeText(this,"Enter Details",Toast.LENGTH_LONG).show();
        }
        else
        {
            String id=databasePerson.push().getKey();//push method generates a unique id which is retrived using getKey method
//            Person person=new Person(id,nm,cty);
//            databasePerson.child(id).setValue(person);
            //child is used to create child node here it is id in which the person object is stored
            Toast.makeText(this,"Submitted Succesfully",Toast.LENGTH_LONG).show();

        }
    }
}
