package com.example.admin.collegelineup;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    private EditText email, pwd;
    private Button login,newuser;
    public FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private String e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        progressDialog = new ProgressDialog(this);


        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                    startActivity(intent);
            }
        });

       if (user != null) {
            finish();
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    validate(email.getText().toString(), pwd.getText().toString());
                }
        });

    }

    @SuppressLint("ResourceType")
    private void setupUIViews(){
        email=(EditText)findViewById(R.id.email);
        pwd=(EditText)findViewById(R.id.pwd);
        login=(Button)findViewById(R.id.login);
        newuser=(Button) findViewById(R.id.newuser);

    }


    private void validate(String user_email,String user_pwd) {
        if (validate1()==true) {
        progressDialog.setMessage("Login Happening");
        progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(user_email, user_pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        //Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        checkEmailVerification();

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
    private void checkEmailVerification()
    {
   FirebaseUser firebaseUser=firebaseAuth.getInstance().getCurrentUser();
   boolean emailflag=firebaseUser.isEmailVerified();

   if(emailflag==true)
   {
       finish();
       Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
       startActivity(intent);
       LoginActivity.this.finish();
   }
   else
   {
       Toast.makeText(this,"Verify your Email",Toast.LENGTH_SHORT).show();
       firebaseAuth.signOut();
   }

   }

   private boolean validate1()
   {
       boolean result;
       String e1 = email.getText().toString();
       String e2 = pwd.getText().toString();
       if(e1.isEmpty())
   {
       email.setError("Please Enter Valid Email Address");
       result=false;
   }
   else if(e2.isEmpty())
   {
       pwd.setError("Please Enter Password");
       result=false;
   }
   else {
       result = true;
   }
       return result;

   }
}

