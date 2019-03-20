package com.example.zeeshanaslam.fyp.Employee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.zeeshanaslam.fyp.EmployeeRunningService.EmployeeRunningRequest;
import com.example.zeeshanaslam.fyp.MainActivity;
import com.example.zeeshanaslam.fyp.R;
import com.example.zeeshanaslam.fyp.runningService.RunningRequest;
import com.example.zeeshanaslam.fyp.view_requests.Request;
import com.example.zeeshanaslam.fyp.view_requests.RequestedService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Employee_ViewRequest extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "PostDetailActivity";

    public static final String EXTRA_POST_KEY = "post_key";
    private ArrayList<String>name,price;
    private RecyclerView recyclerView;



    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name=new ArrayList<String>();
        price=new ArrayList<String>();


        mDialog=new ProgressDialog(this);

        recyclerView=(RecyclerView)findViewById(R.id.programminglist);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));


        /////////////////////////////////

        final String uid =FirebaseAuth.getInstance().getCurrentUser().getUid();


        ///////////////////////////////////////





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mDialog.setMessage("Processing..");
        mDialog.show();

        DatabaseReference kk=FirebaseDatabase.getInstance().getReference("Request");
        kk.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String uid =FirebaseAuth.getInstance().getCurrentUser().getUid();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Request request=dataSnapshot1.getValue(Request.class);
                    if(request.isStatus()==-1) {
                        name.add(request.getMainName());
                        price.add(request.getPrice());
                    }

                }
                mDialog.dismiss();
                EmployeeRequestedService requestedService=new EmployeeRequestedService(name,price,getApplicationContext());
                recyclerView.setAdapter(requestedService);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });








    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent i=new Intent(getApplicationContext(),Employee_Main_Activity.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {
            Intent i=new Intent(getApplicationContext(),EmployeeRunningRequest.class);
            startActivity(i);

        } else if (id == R.id.nav_manage) {
            Intent i=new Intent(getApplicationContext(),Employee_DisplayProfileData.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
