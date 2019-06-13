package com.example.admin.collegelineup;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {
    private EditText name, department, pwd, email;
    private Button register;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = (EditText) findViewById(R.id.name);
        department = (EditText) findViewById(R.id.department);
        email = (EditText) findViewById(R.id.email);
        pwd = (EditText) findViewById(R.id.pwd);
        register = (Button) findViewById(R.id.register);

        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (validate()) {
                            String e3 = email.getText().toString().trim();
                            String e4 = pwd.getText().toString().trim();

                            firebaseAuth.createUserWithEmailAndPassword(e3, e4).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegistrationActivity.this, "Registeration Successfully", Toast.LENGTH_SHORT).show();
                                        sentEmailVerification();

                                    } else {
                                        Toast.makeText(RegistrationActivity.this, "Registeration Unsuccessfully", Toast.LENGTH_SHORT).show();
                                    }
                             }
                    });
               }
            }
        });

    }

        private boolean validate () {
            boolean result;
            String e1 = name.getText().toString();
            String e2 = department.getText().toString();
            String e3 = email.getText().toString();
            String e4 = pwd.getText().toString();

            if (e1.isEmpty() )
            {
                name.setError("Please Enter Name");
                result=false;
            }
            else if(!(e2.contentEquals("comp")) || (e2.contentEquals("COMP")))
            {
                department.setError("Please Enter Valid Department");
                result=false;
            }
            else if(e3.isEmpty())
            {
                email.setError("Please Enter Valid Email Address");
                result=false;
            }
            else if(e4.isEmpty())
            {
                pwd.setError("Please Enter Password");
                result=false;
            }
            else {
                result = true;
            }
            return result;
        }

        private void sentEmailVerification()
        {

            final FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
            if(firebaseUser!=null)
            {
                firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(RegistrationActivity.this, "Registeration Successfully/n Verify your Email", Toast.LENGTH_SHORT).show();
                            firebaseAuth.signOut();
                            finish();
                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(RegistrationActivity.this, "Verify Your Email", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
