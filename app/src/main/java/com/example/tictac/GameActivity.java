package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    Button playButton, aboutusButton;
    TextView tictactoe, welcomeTo;
    Animation a1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getSupportActionBar().hide();

        playButton = findViewById(R.id.playButton);
        aboutusButton = findViewById(R.id.aboutusButton);
        a1 = AnimationUtils.loadAnimation(GameActivity.this,R.anim.fade_out);

        tictactoe = findViewById(R.id.tictactoe);
        tictactoe.setText("Tic Tac Toe");
        tictactoe.setAnimation(a1);

//        welcomeTo = findViewById(R.id.welcomeTo);
//        welcomeTo.setText("Welcome To");
//        welcomeTo.setAnimation(a1);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GameActivity.this,HomeActivity.class);
                startActivity(intent);
                //GameActivity.this.finish();

            }
        });

        aboutusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameActivity.this,AboutUsActivity.class);
                startActivity(intent);
                //GameActivity.this.finish();
            }
        });


    }
}