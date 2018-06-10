package com.ensias.assistancemedicale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class Welcome extends AppCompatActivity {
    LinearLayout l1;
    Button btnsub;
    Animation uptodown,downtoup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btnsub = (Button)findViewById(R.id.buttonsub);
        l1 = (LinearLayout) findViewById(R.id.l1);

        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);

        l1.setAnimation(uptodown);

    }

    public void   goToAuthentification(View view){

        Intent auth = new Intent(view.getContext(),MainActivity.class);
        startActivity(auth);
    }

  
}