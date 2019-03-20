package com.example.zeeshanaslam.fyp.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zeeshanaslam.fyp.MainActivity;
import com.example.zeeshanaslam.fyp.R;
import com.example.zeeshanaslam.fyp.view_requests.Request;
import com.example.zeeshanaslam.fyp.view_requests.RunningService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Employee_RequestDetails extends AppCompatActivity {
    private TextView detail,main_selected,price,days,notes;
    private ArrayList<String> itm;
    private Button submit;
    DatabaseReference kk;
    private String clientUID,employeeUID;


   // public  String days="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);
        detail=findViewById(R.id.selectedData);
        submit=findViewById(R.id.submitBtn);
        price=findViewById(R.id.priceOutput);
        notes=findViewById(R.id.editText_notes);
        main_selected=findViewById(R.id.mainSelected);

        days=findViewById(R.id.spinner);

        ////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////
        final String rupees = getIntent().getStringExtra("rupees");
        final String title = getIntent().getStringExtra("title");


        kk=FirebaseDatabase.getInstance().getReference("Request");
        kk.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key="";
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Request request=dataSnapshot1.getValue(Request.class);
                    if(rupees.compareTo(request.getPrice())==0 && title.compareTo(request.getMainName())==0) {

                        main_selected.setText(title);
                        price.setText(rupees);
                        itm=request.getItm();
                        notes.setText(request.getNotes());
                        for(int i=0;i<itm.size();i++)
                        {
                            detail.append(System.getProperty("line.separator"));
                            detail.append((i+1)+" ) "+itm.get(i));
                        }
                        days.setText(request.getDays());
                       break;
                    }


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                kk=FirebaseDatabase.getInstance().getReference("Request");
                kk.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String key="";
                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                        {
                            Request request=dataSnapshot1.getValue(Request.class);
                            if(rupees.compareTo(request.getPrice())==0 && title.compareTo(request.getMainName())==0 && request.isStatus()==-1) {
                                clientUID=request.getUid();
                                key=dataSnapshot1.getKey();

                                kk.child(key).child("status").setValue(1);

                                employeeUID =FirebaseAuth.getInstance().getCurrentUser().getUid();

                                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("RunningService");
                                String id=databaseReference.push().getKey();
                                RunningService r=new RunningService(employeeUID,clientUID,main_selected.getText().toString(),price.getText().toString(),days.getText().toString());
                                databaseReference.child(id).setValue(r);
                                Toast.makeText(getApplicationContext(),
                                        "Request Accepted",
                                        Toast.LENGTH_SHORT).show();


                                break;
                            }


                        }


                        Intent intent=new Intent(getApplicationContext(),Employee_Main_Activity.class);
                        startActivity(intent);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });

    }

    private void saveRequest()
    {


    }
}
