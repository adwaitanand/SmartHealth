package com.anew.firebasedemo;

import android.app.ProgressDialog;
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

public class Login extends AppCompatActivity {
   private EditText email,password;
    private Button login;
    private TextView signuplink,resetlink;
   private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    int count=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       setup();
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        FirebaseUser user=firebaseAuth.getCurrentUser();//checks if already a user as logged in to the app or not
        if(user!=null)
        {
            finish();//destroys the current activity
            startActivity(new Intent(Login.this,FirstPage.class));//this makes the user login without asking the uer again to login

        }
        signuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(Login.this,MainActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(email.getText().toString(),password.getText().toString());
            }
        });
        resetlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,PasswordActivity.class
                ));
            }
        });
    }
    private void setup()
    {

        email=(EditText)findViewById(R.id.eid);
        password=(EditText)findViewById(R.id.pid);
        login=(Button)findViewById(R.id.login);
        signuplink=(TextView)findViewById(R.id.signup);
        resetlink=(TextView)findViewById(R.id.forgotP);

    }
    private boolean validate()
    {
        Boolean res=false;
        String em,pd;
        em=email.getText().toString();
        pd=password.getText().toString();
        if(em.isEmpty()||pd.isEmpty())
        {
            Toast.makeText(this,"Enter Details",Toast.LENGTH_LONG).show();
        }
        else
            res=true;
        return res;
    }
    private void validate(String usr,String pwd)
    {
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword
                (usr,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    //isSucessful() checks in the db and matches email nd password in the db and return boolean
                    progressDialog.dismiss();
                    checkEmailVerification();
                }
                else
                {
                    progressDialog.dismiss();
                    count--;
                    Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_LONG).show();
                    if(count==0)
                    {
                    login.setEnabled(false);
                    }
                    else
                    Toast.makeText(Login.this,"More "+count+"attempts left!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void checkEmailVerification()
    {
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        Boolean emflag=firebaseUser.isEmailVerified();
        if(emflag)
        {
            startActivity(new Intent(Login.this,FirstPage.class));//If user has already verified
        }
        else
        {
            Toast.makeText(this,"Please verify your email",Toast.LENGTH_LONG).show();
            firebaseAuth.signOut();//until the user verifies the email the user will not be logged in
        }
    }
}
