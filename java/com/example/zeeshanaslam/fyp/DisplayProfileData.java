package com.example.zeeshanaslam.fyp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayProfileData extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView name,email,num,loc;
    private ProgressDialog mDialog;
    //private TextView name;

    private static final String TAG = "PostDetailActivity";

    public static final String EXTRA_POST_KEY = "post_key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profile);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_profile);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setNavigationItemSelectedListener(this);

        ////////////////////////////////
        //////////////////////////////////

        //////////////////////////////////////
        /////////////////////////////////

        name=(TextView) findViewById(R.id.DisplayName);
        email=(TextView) findViewById(R.id.DisplayuserEmailId);
        num=(TextView) findViewById(R.id.DisplaymobileNumber);
        loc=(TextView) findViewById(R.id.Displaylocation);

        mDialog=new ProgressDialog(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(getApplicationContext(),"fghjkgfdfghjhgf",Toast.LENGTH_SHORT).show();

        final String uid =FirebaseAuth.getInstance().getCurrentUser().getUid();
        //Toast.makeText(getApplicationContext(),uid,Toast.LENGTH_SHORT).show();


        mDialog.setMessage("Processing..");
        mDialog.show();
        FirebaseDatabase.getInstance().getReference().child("Profile").child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user information
                        ProfileClass user = dataSnapshot.getValue(ProfileClass.class);
                        String authorName = user.getFullName();


                        mDialog.dismiss();
                        name.setText(authorName);
                        email.setText(user.getEmail());
                        num.setText(user.getNumber());
                        loc.setText(user.getLoc());

                        //Toast.makeText(getApplicationContext(),authorName,Toast.LENGTH_SHORT).show();



                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profile);
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
        MenuItem m =  menu.findItem(R.id.iconn);
        m.setIcon(R.drawable.ic_person_black_24dp);
        return true;
    }
/*
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
*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {



        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(getApplicationContext(),"gallery",Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_slideshow) {

            Toast.makeText(getApplicationContext(),"slide",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_manage) {
            Toast.makeText(getApplicationContext(),"mnge",Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profile);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
