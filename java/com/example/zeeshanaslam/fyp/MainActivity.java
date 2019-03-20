package com.example.zeeshanaslam.fyp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zeeshanaslam.fyp.runningService.RunningRequest;
import com.example.zeeshanaslam.fyp.view_requests.ViewRequest;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String []data={"Air Conditioning","Electrical","Plumbing","Cleaning","Appliances","Painting","Gardening","Pest Control","Gardening","Pest Control"};
   // private int [] images={R.drawable.1};
    private int [] imag={R.drawable.airr,R.drawable.electriccall,R.drawable.plumbingg,R.drawable.cleaningg,R.drawable.appliancess,
            R.drawable.paintingg,R.drawable.gardeningg,R.drawable.pestt,R.drawable.gardeningg,R.drawable.pestt};

    private TextView presonName,personEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        personEmail=(TextView)findViewById(R.id.personEmaill);
        presonName=(TextView)findViewById(R.id.personName);

        //presonName.setText("");

        RecyclerView programminglist=(RecyclerView) findViewById(R.id.programminglist);
        programminglist.setLayoutManager(new GridLayoutManager(this, 2));

        //ImageView img=findViewById(R.id.imgicon);
        //img.setImageResource(R.drawable.profileimage);
        //programminglist.setAdapter(new ProgrammingAdapter(data,images));

        runAnimation(programminglist,0);
        Intent intent = getIntent();
        //get the attached extras from the intent
        //we should use the same key as we used to attach the data.
        String user_name = intent.getStringExtra("Email");
        //personEmail.setText(user_name);


    }

    private void runAnimation(RecyclerView programminglist, int i) {
        Context context=programminglist.getContext();
        LayoutAnimationController controller=null;
        if(i==0)
            controller=AnimationUtils.loadLayoutAnimation(context,R.anim.layout_fall_down);

        ProgrammingAdapter adapter=new ProgrammingAdapter(data,imag,this);
        programminglist.setAdapter(adapter);

        programminglist.setLayoutAnimation(controller);
        programminglist.getAdapter().notifyDataSetChanged();
        programminglist.scheduleLayoutAnimation();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem m =  menu.findItem(R.id.iconn);
        m.setIcon(R.drawable.ic_home_black_24dp);


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
        Toast.makeText(getApplicationContext(),"Found",Toast.LENGTH_SHORT).show();

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent i=new Intent(getApplicationContext(),ViewRequest.class);
            startActivity(i);


        } else if (id == R.id.nav_slideshow) {
            Intent i=new Intent(getApplicationContext(),RunningRequest.class);
            startActivity(i);


        } else if (id == R.id.nav_manage) {
            Intent i=new Intent(getApplicationContext(),DisplayProfileData.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getApplicationContext(),"Successfully logged out",Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
