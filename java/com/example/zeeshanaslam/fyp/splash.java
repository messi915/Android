package com.example.zeeshanaslam.fyp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.example.zeeshanaslam.fyp.UserType.UserSelection;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class splash extends AwesomeSplash {


    @Override
    public void initSplash(ConfigSplash configSplash) {
        ActionBar actionBar=getSupportActionBar();
        //actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        ///background animation

        configSplash.setBackgroundColor(R.color.bg_splash);
        configSplash.setAnimCircularRevealDuration(3000);
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);
        configSplash.setRevealFlagX(Flags.REVEAL_BOTTOM);

        ///logo
        configSplash.setLogoSplash(R.drawable.ic_card_travel_black_24dp);

        configSplash.setAnimLogoSplashDuration(2500);
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce);
        ///title
        configSplash.setTitleSplash("LANDS WORTHY");
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(30f);
        configSplash.setAnimTitleDuration(1500);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);


    }

    @Override
    public void animationsFinished() {

        Intent i=new Intent(getApplicationContext(),UserSelection.class);
        startActivity(i);
    }
}
