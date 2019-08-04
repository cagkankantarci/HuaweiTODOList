package com.example.forev.huaweitodolist.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.forev.huaweitodolist.R;

public class SplashActivity extends AppCompatActivity {
    private static int timeout=3000;
    TextView splashText;
    ImageView splashImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashText = (TextView)findViewById(R.id.splashText);
        splashImage = (ImageView)findViewById(R.id.splashImage);

        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.homeanim);
        splashImage.startAnimation(animation);
        splashText.startAnimation(animation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },timeout);
    }
}
