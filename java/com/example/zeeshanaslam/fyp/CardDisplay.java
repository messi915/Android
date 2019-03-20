package com.example.zeeshanaslam.fyp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CardDisplay extends AppCompatActivity {
    private int [] img={R.drawable.sair,R.drawable.selectric,R.drawable.splumbing,R.drawable.sclean,R.drawable.sapplainces,
            R.drawable.spaint,R.drawable.spest,R.drawable.spest,R.drawable.spest,R.drawable.spest};


    static String[] nameArray = {"SHOP REPAIR","CIRCUIT REPAIR","INSTALLATION OF OUTLET","NEW CIRCUIT","BASIN REPAIR"};
    static String[] priceArray = {"3","2","3","6","5"};
    static Integer[] itemId={1,2,3,4,5};
    private RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private static ArrayList<ItemModel> data;
    private TextView total;
    private Button nextBtn;
    private int pre_id,cur_id;
    private ArrayList<String> nameArr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_display);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nextBtn=findViewById(R.id.next);


        ////////////////////////////////////////
        /////////////////////////////////////
        ////getting index of selected object and pre index///
        Intent intent = getIntent();
        String user_name = intent.getStringExtra("image_id");
       // String item_name=intent.getStringExtra("name");
        pre_id=Integer.parseInt(intent.getStringExtra("pre_id"));
        cur_id=Integer.parseInt(user_name);
        /////////////////////////////////////////////
        //////////////////////////////////////////
        ///getting name of selected object////////

        nameArr=new ArrayList<String>();

        ////////////////////////////////////////
        ///////////////////////////////



        recyclerView =findViewById(R.id.recyler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String []a=findNameArray();

        total=findViewById(R.id.total);
        adapter = new CustomeAdapter(a,priceArray,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(receiver, new IntentFilter("ADD_PAYMENT"));




        ////////////////////////////////
        ////////////////////////////////
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(getApplicationContext(),ReqServiceDetail.class);
                i.putStringArrayListExtra("data",nameArr);

                i.putExtra("totalAmount",total.getText().toString());

                //Toast.makeText(getApplicationContext(),nameArr.size()+"",Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),nameArr.get(1)+"",Toast.LENGTH_SHORT).show();

                startActivity(i);


            }
        });
    }

    private String[] findNameArray()
    {
        if(cur_id==0 && pre_id==0)
        {
            String [] name={"A/C MAINTENANCE","GAS LEAK REPAIR","INSTALLATION OF NEW THERMOSTAT","REPLACEMENT OF MOTOR CAPACITOR","A/C GAS FILL UP"};
            return name;
        }
        if(cur_id==1 && pre_id==0)
        {
            String [] name={"GAS LEAK REPAIR","ROUTINE REPAIR","WATER LEAKAGE REPAIR","MOTOR CAPACITOR","A/C GAS FILL UP"};
            return name;
        }
        if(cur_id==2 && pre_id==0)
        {
            String [] name={"GAS LEAK REPAIR","ROUTINE REPAIR","WATER LEAKAGE REPAIR","MOTOR CAPACITOR","COMPRESSOR INSTALLATION"};
            return name;
        }
        if(cur_id==0 && pre_id==1)
        {
            String [] name={"LIGHT REPLACEMENT","HOLDER INSTALLATION","SWITCH REPAIR","INSTALLATION OF NEW LIGHTING POINT"};
            return name;
        }
        if(cur_id==1 && pre_id==1)
        {
            String [] name={"SHORT CIRCUIT","SWITCH REPAIR","NEW WINDOW EXHAUST FAN","INSTALLATION OF NEW POWER OUTLET"};
            return name;
        }

        if(cur_id==2 && pre_id==1)
        {
            String [] name={"INSTALLATION OF 3 PHASE MAIN CIRCUIT BREAKER","INSTALLATION OF 3 PHASE CIRCUIT BREAKER","INSTALLATION OF SINGLE PHASE MAIN CIRCUIT BREAKER"};
            return name;
        }
        if(cur_id==0 && pre_id==2)
        {
            String [] name={"VALVE REPLACEMENT","FAUCET REPAIR","CLOGGED SINK"};
            return name;
        }
        if(cur_id==1 && pre_id==2)
        {
            String [] name={"CLOGGED DRAIN","VALVE REPLACEMENT","SHOWER DOOR REPAIR"};
            return name;
        }
        if(cur_id==2 && pre_id==2)
        {
            String [] name={"FLUSH NOT WORKING","FLOAT LEVEL REPLACEMENT","VALVE REPLACEMENT","REPAIR OR REPLACE WATER MIXER","TOILET LEAKAGE"};
            return name;
        }
        if(cur_id==3 && pre_id==2)
        {
            String [] name={"FLOAT LEVEL REPLACEMENT","VALVE REPLACEMENT","PIPE LEAKAGE REPAIR","WATER TANK LEAK","WATER TANK INSTALLATION"};
            return name;
        }

        if(cur_id==4 && pre_id==2)
        {
            String [] name={"TRANSPARENT FILTER MAINTeNANCE","CENTRAL FILTER REPAIR","DOUBLE FILTER REPLACEMENT"};
            return name;
        }
        if(cur_id==0 && pre_id==3)
        {
            String [] name={"HOURLY CLEANING SERVICE / HRS"};
            return name;
        }
        if(cur_id==1 && pre_id==3)
        {
            String [] name={"DEEP CLEANING"};
            return name;
        }
        if(cur_id==2 && pre_id==3)
        {
            String [] name={"FAN CLEANING","HOOD AND FILTER CLEANING","FULL SYSTEM CLEANING"};
            return name;
        }
        if(cur_id==3 && pre_id==3)
        {
            String [] name={"REQUEST ASSESSMENT"};
            return name;
        }
        if(cur_id==0 && pre_id==4)
        {
            String [] name={"REQUEST ASSESSMENT"};
            return name;
        }
        if(cur_id==1 && pre_id==4)
        {
            String [] name={"REQUEST ASSESSMENT"};
            return name;
        }
        if(cur_id==2 && pre_id==4)
        {
            String [] name={"REQUEST ASSESSMENT"};
            return name;
        }
        if(cur_id==3 && pre_id==4)
        {
            String [] name={"REQUEST ASSESSMENT"};
            return name;
        }
        if(cur_id==0 && pre_id==5)
        {
            String [] name={"SMALL ROOM 4*4","MEDIUM ROOM 6*6","LARGE ROOM 9*6","VERY LARGE ROOM 10*8"};
            return name;
        }
        if(cur_id==1 && pre_id==5)
        {
            String [] name={"SMALL APARTMENT","MEDIUM APARTMENT","LARGE APARTMENT","VERY LARGE APARTMENT"};
            return name;
        }
        if(cur_id==2 && pre_id==5)
        {
            String [] name={"SMALL ROOM 4*4","SMALL APARTMENT","MEDIUM APARTMENT","LARGE APARTMENT"};
            return name;
        }
        if(cur_id==3 && pre_id==5)
        {
            String [] name={"REQUEST ASSESSMENT"};
            return name;
        }

        if(cur_id==0 && pre_id==6)
        {
            String [] name={"TERMITES SPRAY","ANT SPRAY","BED BUG SPRAY","COCKROACHES SPRAY"};
            return name;
        }
        if(cur_id==1 && pre_id==6)
        {
            String [] name={"TERMITES SPRAY","PREVENTIVE SPRAY","ANT SPRAY","COCKROACHES SPRAY","RODENT CONTROL"};
            return name;
        }

        if(cur_id==2 && pre_id==6)
        {
            String [] name={"TERMITES SPRAY","PREVENTIVE SPRAY","ANT SPRAY","COCKROACHES SPRAY","RODENT CONTROL"};
            return name;
        }








        return null;
    }




    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String d=total.getText().toString();
            int n=Integer.parseInt(d);

            if (intent != null) {
                String str = intent.getStringExtra("index");
                int nmbr=Integer.parseInt(str);
                String msg = intent.getStringExtra("msg");


                if(msg=="add")
                {
                    n=n+Integer.parseInt(priceArray[nmbr]);

                    if(nameArr.contains(intent.getStringExtra("name"))==false)
                        nameArr.add(intent.getStringExtra("name"));

                    total.setText(n+"");

                }
                else if(msg=="sub")
                {
                    String k=intent.getStringExtra("name");
                    nameArr.remove(k);
                    n=n-Integer.parseInt(priceArray[nmbr]);
                    total.setText(n+"");
                }
                // get all your data from intent and do what you want
            }
        }
    };

   public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem m =  menu.findItem(R.id.iconn);
        // m.setIcon(R.drawable.deleteicon);
        m.setIcon(img[0]);


        return true;
    }

}
