package com.example.zeeshanaslam.fyp.EmployeeRunningService;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zeeshanaslam.fyp.R;
import com.example.zeeshanaslam.fyp.view_requests.Request;
import com.example.zeeshanaslam.fyp.view_requests.RunningService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EmployeerunningRequestDetail extends AppCompatActivity {
    private String EVENT_DATE_TIME = "2019-03-20 4:42:00";
    private String pattern = "yyyy-MM-dd";

    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private LinearLayout linear_layout_1, linear_layout_2;
    private TextView tv_days, tv_hour, tv_minute, tv_second;
    private Handler handler = new Handler();
    private Runnable runnable;



    private TextView detail,main_selected,price,days,notes;
    private ArrayList<String> itm;
    private Button submit;
    DatabaseReference kk;
    private String clientUID,employeeUID;




    private void addDay()
    {
        String d=days.getText().toString();
        String ltr=Character.toString(d.charAt(1));
        //d.charAt(0);
       int dh=Integer.parseInt(ltr);
     //  Toast.makeText(getApplicationContext(),dh+"",Toast.LENGTH_SHORT).show();

        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,dh);
        calendar.getTime();

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
        String date=simpleDateFormat.format(calendar.getTime());
        date=date+" 00:00:00";
        EVENT_DATE_TIME=date;
       // Toast.makeText(getApplicationContext(),date,Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_running_request_detail);
        initUI();


        final String rupee = getIntent().getStringExtra("rupees");
        final String title = getIntent().getStringExtra("title");

        main_selected.setText("aaaaa");


        DatabaseReference kk=FirebaseDatabase.getInstance().getReference("RunningService");
        kk.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String uid =FirebaseAuth.getInstance().getCurrentUser().getUid();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    RunningService request=dataSnapshot1.getValue(RunningService.class);
                    if(uid.compareTo(request.getEmployeeID())==0 && rupee.compareTo(request.getPrice())==0) {
                        main_selected.setText(title);
                        price.setText(rupee);
                        days.setText(request.getDate());
                    }

                }
              }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        countDownStart();



    }
    private void initUI() {
        linear_layout_1 = findViewById(R.id.linear_layout_1);
        linear_layout_2 = findViewById(R.id.linear_layout_2);
        tv_days = findViewById(R.id.tv_days);
        tv_hour = findViewById(R.id.tv_hour);
        tv_minute = findViewById(R.id.tv_minute);
        tv_second = findViewById(R.id.tv_second);

        detail=findViewById(R.id.selectedData);
        submit=findViewById(R.id.submitBtn);
        price=findViewById(R.id.priceOutput);
        notes=findViewById(R.id.editText_notes);
        main_selected=findViewById(R.id.mainSelected);

        days=findViewById(R.id.spinner);

    }
    private void countDownStart() {
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 1000);
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                    addDay();
                    Date event_date = dateFormat.parse(EVENT_DATE_TIME);
                   //addDay();

                    Date current_date = new Date();
                    if (!current_date.after(event_date)) {
                        long diff = event_date.getTime() - current_date.getTime();
                        long Days = diff / (24 * 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;
                        //
                        tv_days.setText(String.format("%02d", Days));
                        tv_hour.setText(String.format("%02d", Hours));
                        tv_minute.setText(String.format("%02d", Minutes));
                        tv_second.setText(String.format("%02d", Seconds));
                    } else {
                        linear_layout_1.setVisibility(View.VISIBLE);
                        linear_layout_2.setVisibility(View.GONE);
                        handler.removeCallbacks(runnable);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }

    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }
}
