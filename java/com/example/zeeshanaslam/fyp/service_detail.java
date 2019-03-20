package com.example.zeeshanaslam.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class service_detail extends AppCompatActivity {

    private int [] img={R.drawable.sair,R.drawable.selectric,R.drawable.splumbing,R.drawable.sclean,R.drawable.sapplainces,
            R.drawable.spaint,R.drawable.spest,R.drawable.spest,R.drawable.spest,R.drawable.spest};



    //private int [] innerimg={R.drawable.innerair1,R.drawable.innerair1,R.drawable.innerair1};


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),MainActivity.class);

        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);


        //setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.service_detail_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        Intent intent = getIntent();
        String user_name = intent.getStringExtra("image_id");
        //int id=Integer.getInteger(user_name);
        int id=Integer.parseInt(user_name);

        if(id==0)
        {
            int [] innerimg={R.drawable.airr,R.drawable.innerair1,R.drawable.innerair2};
            String []data={"CENTRAL","SPLIT UNIT","WINDOW TYPE"};
            ServiceDetail serviceDetail=new ServiceDetail(data,innerimg,id,this);
            recyclerView.setAdapter(serviceDetail);

        }
        else if(id==1)
        {
            int [] innerimg={R.drawable.electriccall,R.drawable.innerelectric2,R.drawable.innerelectric3};

            String []data={"LIGHTING","SWITCHES & POWER OUTTLETS","DISTRIBUTION BOARD"};
            ServiceDetail serviceDetail=new ServiceDetail(data,innerimg,id,this);
            recyclerView.setAdapter(serviceDetail);

        }

        else if(id==2)
        {
            int [] innerimg={R.drawable.plumbingg,R.drawable.plumbingg,R.drawable.plumbingg,R.drawable.plumbingg,R.drawable.plumbingg,R.drawable.plumbingg,R.drawable.plumbingg};

            String []data={"SINK","SHOWER","TOILET","PIPNG & WATER TANKS","FILTERS"};
            ServiceDetail serviceDetail=new ServiceDetail(data,innerimg,id,this);
            recyclerView.setAdapter(serviceDetail);

        }
        else if(id==3)
        {
            int [] innerimg={R.drawable.cleaningg,R.drawable.cleaningg,R.drawable.cleaningg,R.drawable.cleaningg,R.drawable.cleaningg,R.drawable.cleaningg};
            String []data={"HOURLY CLEANING","DEEP CLEANING","KITCHEN HOOD CLEANING","FLOOR POLISHING"};
            ServiceDetail serviceDetail=new ServiceDetail(data,innerimg,id,this);
            recyclerView.setAdapter(serviceDetail);

        }
        else if(id==4)
        {
            int [] innerimg={R.drawable.appliancess,R.drawable.appliancess,R.drawable.appliancess,R.drawable.appliancess};
            String []data={"FRIDGE AND FREEZER","GAS COOKER & OVEN","WASHING MACHINE & DRYER","WATER COOLER"};
            ServiceDetail serviceDetail=new ServiceDetail(data,innerimg,id,this);
            recyclerView.setAdapter(serviceDetail);

        }

        else if(id==5)
        {
            int [] innerimg={R.drawable.paintingg,R.drawable.paintingg,R.drawable.paintingg,R.drawable.paintingg,R.drawable.paintingg,R.drawable.paintingg};
            String []data={"ROOMS","APARTMENTS","REPAIRING DAMAGED AREAS","EXTERIOR PAINTING"};
            ServiceDetail serviceDetail=new ServiceDetail(data,innerimg,id,this);
            recyclerView.setAdapter(serviceDetail);

        }
        else if(id==6)
        {
            int [] innerimg={R.drawable.pestt,R.drawable.pestt,R.drawable.pestt,R.drawable.pestt};
            String []data={"APARTMENT","FLOOR","HOUSE"};
            ServiceDetail serviceDetail=new ServiceDetail(data,innerimg,id,this);
            recyclerView.setAdapter(serviceDetail);

        }
        else
        {
            int [] innerimg={R.drawable.pestt,R.drawable.pestt,R.drawable.pestt,R.drawable.pestt};

            String []data={"CENNNNNTRAL","SPLIT UNIT","WINDOW TYPE"};
            ServiceDetail serviceDetail=new ServiceDetail(data,innerimg,id,this);
            recyclerView.setAdapter(serviceDetail);

        }
        //recyclerView.setAdapter(serviceDetail);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        Intent intent = getIntent();
        String user_name = intent.getStringExtra("image_id");
        //int id=Integer.getInteger(user_name);
        int id=Integer.parseInt(user_name);
        MenuItem m =  menu.findItem(R.id.iconn);
       // m.setIcon(R.drawable.deleteicon);
        m.setIcon(img[id]);


        return true;
    }
}
