package com.example.zeeshanaslam.fyp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.example.zeeshanaslam.fyp.view_requests.Request;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class ReqServiceDetail extends AppCompatActivity  implements OnItemSelectedListener{




    private TextView detail,main_selected,price,notes;
    private ArrayList<String>itm;
    private Spinner spinner1, spinner2;
    private Button submit;
    DatabaseReference databaseReference;


    public  String days="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_service_detail);
        detail=findViewById(R.id.selectedData);
        submit=findViewById(R.id.submitBtn);

        notes=findViewById(R.id.editText_notes);
        price=findViewById(R.id.priceOutput);
        main_selected=findViewById(R.id.mainSelected);
        databaseReference = FirebaseDatabase.getInstance().getReference("Request");
        mDialog=new ProgressDialog(this);



        ///////////////////////
        /////////////////////////
        addItemsOnSpinner2();
        addListenerOnSpinnerItemSelection();
        Toast.makeText(getApplicationContext(),spinner1.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();


        //days=Integer.parseInt(spinner1.getSelectedItem().toString());
        //spinner2=
        /////////////////////////////////////////////
        ////////////////////////////////
        SharedPreferences data=getSharedPreferences("APP DATA",0);
        String value=data.getString("MainServiceSelected","") ;

        main_selected.setText(value);
        //intent.putExtra("MainServiceSelected",main_service_selected);

        /////////////////////////////////////////////////////////////////


        Bundle bundle=getIntent().getExtras();

        itm=getIntent().getExtras().getStringArrayList("data");


        if(itm!=null)
        {

            for(int i=0;i<itm.size();i++)
            {
                detail.append(System.getProperty("line.separator"));
                detail.append((i+1)+" ) "+itm.get(i));
            }
            detail.append(System.getProperty("line.separator"));
            //detail.append("      RPS "+getIntent().getExtras().getString("totalAmount"));
        }

        TextView p=findViewById(R.id.priceOutput);
        p.setText("      RPS "+getIntent().getExtras().getString("totalAmount"));




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.setMessage("Processing..");
                mDialog.show();




                saveData();

                mDialog.dismiss();
                Toast.makeText(getApplicationContext(),
                        "Request Submitted",
                        Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);








            }
        });


    }

    public void saveData() {
        final String uid =FirebaseAuth.getInstance().getCurrentUser().getUid();

       // String id = databaseReference.push().getKey();

        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Request");
        String id=databaseReference.push().getKey();
        Request r=new Request(uid,notes.getText().toString(),days,itm,main_selected.getText().toString(),-1,price.getText().toString());
        databaseReference.child(id).setValue(r);


    }


    private ProgressDialog mDialog;
    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add(" 1 Day");
        list.add(" 2 Days");
        list.add(" 3 Days");
        list.add(" 4 Days");
        list.add(" 5 Days");
        list.add(" 6 Days");
        list.add(" 7 Days");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        itm.clear();

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        days=parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
