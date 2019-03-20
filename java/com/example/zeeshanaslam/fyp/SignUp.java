package com.example.zeeshanaslam.fyp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {


    private EditText email;
    private EditText pass;
    private Button btnReg;
    private TextView txtLogin;

    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        mAuth=FirebaseAuth.getInstance();
        mDialog=new ProgressDialog(this);

        email=findViewById(R.id.edt_emailSignUp);
        pass=findViewById(R.id.edt_pswdSignUp);
        btnReg=findViewById(R.id.btn_register);
        txtLogin=findViewById(R.id.txt_login);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login_Acitvity.class));
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mEmail=email.getText().toString().trim();
                final String mPass=pass.getText().toString().trim();

                if(TextUtils.isEmpty(mEmail)){
                    email.setError("Required field...");
                    return;
                }
                if(TextUtils.isEmpty(mPass)){
                    pass.setError("Required field...");
                    return;
                }

                mDialog.setMessage("Processing..");
                mDialog.show();

                mAuth.createUserWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        SharedPreferences data=getSharedPreferences("APP DATA",0);

                        if(task.isComplete()){
                            Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor editor=data.edit();
                            editor.putString(mEmail,mPass);
                            editor.commit();
                            startActivity(new Intent(getApplicationContext(),profile.class));
                            mDialog.dismiss();
                        }else {
                            Toast.makeText(getApplicationContext(),"Error !!!",Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }
                    }
                });
            }
        });

    }
}

