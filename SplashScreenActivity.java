package com.example.admin.collegelineup;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.nio.channels.InterruptedByTimeoutException;

public class SplashScreenActivity extends AppCompatActivity {
    Logolauncher logolauncher;
    private ImageView iv1, iv2, iv3, iv4, iv5,iv6,iv7;
    private CardView cardView1, cardView2, cardView3, cardView4, cardView5,cardView6,cardView7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        setUIViews();
        setUPCardView1();
        setUPCardView2();
        setUPCardView4();
        setUPCardView5();
        setUPCardView6();
        setUPCardView7();
        Logolauncher logolauncher=new Logolauncher();
        logolauncher.start();
    }

    private void setUIViews() {
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv4 = (ImageView) findViewById(R.id.iv4);
        iv5 = (ImageView) findViewById(R.id.iv5);
        iv6 = (ImageView) findViewById(R.id.iv6);
        iv7 = (ImageView) findViewById(R.id.iv7);
        cardView1 = (CardView) findViewById(R.id.CardView1);
        cardView2 = (CardView) findViewById(R.id.CardView2);
        cardView3 = (CardView) findViewById(R.id.CardView3);
        cardView4 = (CardView) findViewById(R.id.CardView4);
        cardView5 = (CardView) findViewById(R.id.CardView5);
        cardView6 = (CardView) findViewById(R.id.CardView6);
        cardView7 = (CardView) findViewById(R.id.CardView7);
    }

    private void setUPCardView1()
    {
        ObjectAnimator animation = ObjectAnimator.ofFloat(cardView1, "translationY", 107f);
        animation.start();
        animation.setDuration(3000);
        animation.setRepeatMode(ObjectAnimator.RESTART);
    }


    private void setUPCardView2()
    {
        ObjectAnimator animation = ObjectAnimator.ofFloat(cardView2, "translationX", 108f);
        animation.start();
        animation.setDuration(3000);
        animation.setRepeatMode(ObjectAnimator.RESTART);
    }

    private void setUPCardView4()
    {
        ObjectAnimator animation = ObjectAnimator.ofFloat(cardView4, "translationX", 108f);
        animation.start();
        animation.setDuration(3000);
        animation.setRepeatMode(ObjectAnimator.RESTART);
    }

    private void setUPCardView5()
    {
        ObjectAnimator animation = ObjectAnimator.ofFloat(cardView5, "translationY", -107f);
        animation.start();
        animation.setDuration(3000);
        animation.setRepeatMode(ObjectAnimator.RESTART);
    }


    private void setUPCardView6()
    {
        ObjectAnimator animation = ObjectAnimator.ofFloat(cardView6, "translationX", -108f);
        animation.start();
        animation.setDuration(3000);
        animation.setRepeatMode(ObjectAnimator.RESTART);
    }

    private void setUPCardView7()
    {
        ObjectAnimator animation = ObjectAnimator.ofFloat(cardView7, "translationX", -108f);
        animation.start();
        animation.setDuration(3000);
        animation.setRepeatMode(ObjectAnimator.RESTART);
    }



    private class Logolauncher extends Thread {
        public void run()
        {
            try
            {
                sleep(4000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            Intent intent=new Intent(SplashScreenActivity.this,LoginActivity.class);
            startActivity(intent);
            SplashScreenActivity.this.finish();
        }
    }
}
