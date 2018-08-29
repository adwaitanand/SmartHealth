package com.anew.firebasedemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordActivity extends AppCompatActivity {
    EditText email;
    Button reset;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        email=(EditText)findViewById(R.id.emails);
        reset=(Button)findViewById(R.id.reset);
        firebaseAuth=FirebaseAuth.getInstance();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em=email.getText().toString().trim();
                if(em.equals(""))
                {
                    Toast.makeText(PasswordActivity.this,"Please enter your registered email id",Toast.LENGTH_LONG).show();
                }
                else
                {
                   firebaseAuth.sendPasswordResetEmail(em).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful())
                           {
                               Toast.makeText(PasswordActivity.this,"An email has been sent to reset your password",Toast.LENGTH_LONG).show();
                               finish();
                               startActivity(new Intent(PasswordActivity.this,Login.class));
                           }
                           else
                           {
                               Toast.makeText(PasswordActivity.this,"Error in sending password reset email",Toast.LENGTH_LONG).show();
                           }
                       }
                   });
                }

            }
        });
    }
}
