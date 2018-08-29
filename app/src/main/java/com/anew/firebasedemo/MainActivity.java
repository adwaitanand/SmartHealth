package com.anew.firebasedemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
   private EditText name,email,password;
   private Button register;
    private TextView loglink;
   private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();

        firebaseAuth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                {
                    //upload data to database
                    String em,pd;
                    em=email.getText().toString().trim();
                    pd=password.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(em,pd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this,"Registered Sucessfully",Toast.LENGTH_SHORT).show();
                               sendEmailVerification();
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,"Registration Failed",Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
            }
        });
       loglink.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(MainActivity.this,Login.class));
           }
       });
    }
    private void setup()
    {
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        register=(Button)findViewById(R.id.register);
        loglink=(TextView)findViewById(R.id.loglink);
    }

    private boolean validate()
    {
       Boolean res=false;
        String nm,em,pd;
        nm=name.getText().toString();
        em=email.getText().toString();
        pd=password.getText().toString();
        if(nm.isEmpty()||em.isEmpty()||pd.isEmpty())
        {
            Toast.makeText(this,"Enter Details",Toast.LENGTH_LONG).show();
        }
        else
            res=true;
        return res;
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this,"A verification email has been sent to your email id,PLEASE VERIFY!",Toast.LENGTH_LONG).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(MainActivity.this,Login.class));
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Verification mail not sent",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
