package com.sid.todo;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN=4000;
    Animation topanim, bottomanim;
    ImageView image;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        topanim = AnimationUtils.loadAnimation(this, R.anim.animation_top);
        bottomanim = AnimationUtils.loadAnimation(this, R.anim.animation_bottom);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        image= findViewById(R.id.fire);






        image.setAnimation(topanim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, SignIn.class);
                startActivity(intent);

                finish();
            }
        },SPLASH_SCREEN);








    }


}
