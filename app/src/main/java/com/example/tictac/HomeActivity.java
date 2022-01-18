package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity {

    // this are the name of the edit text in which we will enter the names
    private EditText editTextPlayer1;
    private EditText editTextPlayer2;

    TextView enterName;
    Animation a3;

    // in this strings we will store the names of the players
    private String name1;
    private String name2;


    //Button goButton;
    Button homeButton1;


    // this method is for lets go button
    public void goPage(View view)
    {
        name1 = editTextPlayer1.getText().toString().trim();
        name2 = editTextPlayer2.getText().toString().trim();

        if(name1.isEmpty() || name2.isEmpty( ))
        {
            Toast.makeText(HomeActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }

        if(name1.length()>7 || name2.length()>7)
        {
            Toast.makeText(HomeActivity.this, "Try a small name", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(HomeActivity.this,MainActivity.class);
        intent.putExtra(MainActivity.NAME1,name1);
        intent.putExtra(MainActivity.NAME2,name2);

        intent.putExtra("name1Score","0");
        intent.putExtra("name2Score","0");
        startActivity(intent);
        //HomeActivity.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();

        //goButton = findViewById(R.id.goButton);
        homeButton1 = findViewById(R.id.homeButton1);

        // this are for the names of the player in the edit text
        editTextPlayer1 = findViewById(R.id.editTextPlayer1);
        editTextPlayer2 = findViewById(R.id.editTextPlayer2);

        enterName = findViewById(R.id.enterName);

        a3 = AnimationUtils.loadAnimation(HomeActivity.this,R.anim.fade_out_name);

        enterName = findViewById(R.id.enterName);
        enterName.setText("Enter Your Names Here");
        enterName.setAnimation(a3);

        //we use public void goPage instead of this
//        goButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });

        homeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,GameActivity.class);
                startActivity(intent);
                //HomeActivity.this.finish();
            }
        });
    }
}