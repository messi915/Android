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

public class login_Acitvity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Button btnLog;
    private TextView txtReg;

    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__acitvity);

        mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!= null){
            //startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        mDialog=new ProgressDialog(this);


        email=findViewById(R.id.edt_emailSignIn);
        pass=findViewById(R.id.edt_pswdSignIn);
        btnLog=findViewById(R.id.btn_registerLogin);
        txtReg=findViewById(R.id.txt_register);

        txtReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
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

                mAuth.signInWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //FirebaseAuth.getInstance().signOut();
                        if(task.isSuccessful()){
                            SharedPreferences data=getSharedPreferences("APP DATA",0);
                            String value=data.getString(mEmail,"") ;
                            if(value!=""){

                                Toast.makeText(getApplicationContext(),"Found",Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                i.putExtra("Email",mEmail);
                                startActivity(i);
                                mDialog.dismiss();
                            }
                            else {

                                Toast.makeText(getApplicationContext(),"not Found",Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor editor=data.edit();
                                editor.putString(mEmail,mPass);
                                editor.commit();
                                Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getApplicationContext(),profile.class);
                                i.putExtra("Email",mEmail);
                                startActivity(i);
                                mDialog.dismiss();

                            }



                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Incorrect Email or Password !!!",Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }
                    }
                });
            }
        });


    }
}
