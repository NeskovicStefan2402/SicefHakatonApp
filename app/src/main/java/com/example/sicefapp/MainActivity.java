package com.example.sicefapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText etUsername,etPassword;
    Button btnPrijava;
    ImageView pozadina,profile;
    TextView txt1,txt2;
    public static String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        etUsername=findViewById(R.id.editText);
        etPassword=findViewById(R.id.editText3);
        pozadina=findViewById(R.id.imageView);
        profile=findViewById(R.id.imageView2);
        btnPrijava=findViewById(R.id.button);
        ucitajLogin();
    }
    public boolean proveriUsername(String username){
        if(username.equals("Stefan98")){
            return true;
        }
        return false;
    }

    public boolean proveriPassword(String password){
        if(password.equals("Stefan98")){
            return true;
        }
        return false;
    }
    public void ucitajLogin(){
        Animation animation1= AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animacija1);
        etUsername.startAnimation(animation1);
        etPassword.startAnimation(animation1);
        pozadina.startAnimation(animation1);
        profile.startAnimation(animation1);
        btnPrijava.startAnimation(animation1);

    }
    public void ugasiLogin(){
        Animation animation2= AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animacija2);
        etUsername.startAnimation(animation2);
        etPassword.startAnimation(animation2);
        pozadina.startAnimation(animation2);
        profile.startAnimation(animation2);
        btnPrijava.startAnimation(animation2);
        btnPrijava.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation arg0) {
                pokreniIntent();
            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
    }
    public void pokreniIntent(){
        Intent i =new Intent(this,menuActivity.class);
        startActivity(i);
        finish();
    }

    public void prijava(View view) {
        if(proveriUsername(etUsername.getText().toString()) && proveriPassword(etPassword.getText().toString())){
            ugasiLogin();
        }else if(!proveriUsername(etUsername.getText().toString()) && proveriPassword(etPassword.getText().toString())){
            etUsername.setHint("Username");
            etUsername.setError("Niste korektno uneli ovo polje!");
        }else if(!proveriPassword(etPassword.getText().toString()) && proveriUsername(etUsername.getText().toString())){
            etPassword.setHint("Password");
            etPassword.setError("Niste korektno uneli ovo polje!");
        }else{
            etUsername.setHint("Username");
            etUsername.setError("Niste korektno uneli ovo polje!");
            etPassword.setHint("Password");
            etPassword.setError("Niste korektno uneli ovo polje!");
        }
    }
}
