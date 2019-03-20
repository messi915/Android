package com.example.zeeshanaslam.fyp.UserType;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zeeshanaslam.fyp.Employee.Employee_login_Acitvity;
import com.example.zeeshanaslam.fyp.MainActivity;
import com.example.zeeshanaslam.fyp.R;
import com.example.zeeshanaslam.fyp.login_Acitvity;

public class UserSelection extends AppCompatActivity {
    private Button employee,user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);
        employee=findViewById(R.id.employee);
        user=findViewById(R.id.user);


        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),login_Acitvity.class);
                startActivity(i);
            }
        });
        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Employee_login_Acitvity.class);
                startActivity(i);
            }
        });
    }
}
