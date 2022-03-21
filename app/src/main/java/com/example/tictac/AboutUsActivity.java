package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity {

    Button homeButton;
    Button linkedin;
    TextView tictactoe1;
    Animation a2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        getSupportActionBar().hide();

        homeButton = findViewById(R.id.homeButton);
        linkedin = findViewById(R.id.linkedin);
        a2 = AnimationUtils.loadAnimation(AboutUsActivity.this,R.anim.fade_out_name);

        tictactoe1 = findViewById(R.id.tictactoe1);
        tictactoe1.setText("Tic Tac Toe");
        tictactoe1.setAnimation(a2);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutUsActivity.this,GameActivity.class);
                startActivity(intent);
                //AboutUsActivity.this.finish();
                //..........................................
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i3 = new Intent(Intent.ACTION_VIEW);
                i3.setData(Uri.parse("https://linkedin.com/in/adjrwl"));
                startActivity(i3);
                //AboutUsActivity.this.finish();
            }
        });
    }
}