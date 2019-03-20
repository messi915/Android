package com.example.zeeshanaslam.fyp.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zeeshanaslam.fyp.MainActivity;
import com.example.zeeshanaslam.fyp.ProfileClass;
import com.example.zeeshanaslam.fyp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Employee_profile extends AppCompatActivity  {

    private Button submit;
    private EditText fullName;
    private EditText email;
    private EditText number;
    private EditText loc;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference("Profile");

        submit = (Button) findViewById(R.id.submitProfileBtn);
        fullName = (EditText) findViewById(R.id.fullName);
        email = (EditText) findViewById(R.id.userEmailId);
        number = (EditText) findViewById(R.id.mobileNumber);
        loc = (EditText) findViewById(R.id.location);

        submit.setOnClickListener(new View.OnClickListener() {

                                      @Override
                                      public void onClick(View v) {
                                          if(checkData()==true)
                                          {
                                              Toast.makeText(getApplicationContext(), "Plz enter data ", Toast.LENGTH_LONG).show();

                                          }
                                          else
                                          {
                                              saveData();
                                              Intent i = new Intent(getApplicationContext(), Employee_Main_Activity.class);
                                              startActivity(i);
                                          }


                                      }

                                      public void saveData() {
                                          final String uid =FirebaseAuth.getInstance().getCurrentUser().getUid();

                                          String id = databaseReference.push().getKey();
                                          ProfileClass obj = new ProfileClass(fullName.getText().toString(), email.getText().toString(), number.getText().toString(), loc.getText().toString());
                                          databaseReference.child(uid).setValue(obj);
                                          Toast.makeText(getApplicationContext(), "full name save", Toast.LENGTH_LONG).show();

                                      }

                                  }

        );
    }
        public Boolean checkData()
        {
            if(TextUtils.isEmpty(fullName.getText().toString()) || TextUtils.isEmpty(email.getText().toString())
                    ||  TextUtils.isEmpty(number.getText().toString())
                    || (TextUtils.isEmpty(loc.getText().toString())) )
                return true;
            return false;
        }


}
