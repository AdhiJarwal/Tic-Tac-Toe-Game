package com.example.tictac;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    // this will randomly select player 1 or player 2
    Random random = new Random();
    int activeplayer = random.nextInt(2);

    // gameActive = true means game is not over yet and both player can choose any spot to mark
    boolean gameActive = true;
    private SoundPlayer sound;

    public static final String NAME1 = "O";
    public static final String NAME2 = "X";
    private String name1;
    private String name2;

    public static int name1Score = 0;
    public static int name2Score = 0;

    // 2 means unplayed position in the grid
    int[] gameState = {-2,-2,-2,-2,-2,-2,-2,-2,-2};

    // this are the winning positions
    int[][] winnigPositions = {{0,1,2},{3,4,5},{6,7,8}, {0,3,6},{1,4,7},{2,5,8}, {0,4,8},{2,4,6}};

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void dropIn(View view)
    {
        ImageView img1 = findViewById(R.id.imageView);
        ImageView img2 = findViewById(R.id.imageView2);
        ImageView img3 = findViewById(R.id.imageView3);
        ImageView img4 = findViewById(R.id.imageView4);
        ImageView img5 = findViewById(R.id.imageView7);
        ImageView img6 = findViewById(R.id.imageView10);
        ImageView img7 = findViewById(R.id.imageView11);
        ImageView img8 = findViewById(R.id.imageView13);
        ImageView img9 = findViewById(R.id.imageView14);

        ImageView counter= (ImageView) view;


        //it will give the position where we tapped by using get.Tag()
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        //this will check wether the tapped button is already tapped or not, if tappedCounter = -2 means it's empty
        //here gameActive will use to avoid the tapping after the win, we can not tap after the win.
        if(gameState[tappedCounter]==-2 && gameActive)
        {
            gameState[tappedCounter] = activeplayer;

            if (activeplayer == 0)
            {
                counter.setImageResource(R.drawable.x1);
                activeplayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("Your Turn " + name1);
            }
            else {
                counter.setImageResource(R.drawable.o);
                activeplayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("Your Turn " +  name2);

            }

            //disable all the image view click button
            disableImageView();

            //this for loop is to check whether the player has won or not
            for(int[] winningPosition : winnigPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]]) {

                    if(gameState[winningPosition[0]] != -2) {

                        //winner is declared

                        //we will change gameActive = false so we can not tap after winning
                        gameActive = false;
                        String winner = name1;

                        if (gameState[winningPosition[0]] == 0) {
                            // 0 is for X
                            winner = name2;
                        }

                        // this is to increment scores if players want to paly again
                        // scores are passing after increment to the play again activity
                        if (winner == name1)
                        {
                            name1Score++;
                        }

                        else if (winner == name2)

                        {
                            name2Score++;
                        }

                        // if x is the winner
                        if (gameState[0] == 0 && gameState[1] == 0 && gameState[2] == 0)
                        {
                            img1.setImageResource(R.drawable.x3);
                            img2.setImageResource(R.drawable.x3);
                            img3.setImageResource(R.drawable.x3);

                        }

                        else if (gameState[3] == 0 && gameState[4] == 0 && gameState[5] == 0)
                        {
                            img4.setImageResource(R.drawable.x3);
                            img5.setImageResource(R.drawable.x3);
                            img6.setImageResource(R.drawable.x3);
                        }

                        else if (gameState[6] == 0 && gameState[7] == 0 && gameState[8] == 0)
                        {
                            img7.setImageResource(R.drawable.x3);
                            img8.setImageResource(R.drawable.x3);
                            img9.setImageResource(R.drawable.x3);
                        }

                        else if (gameState[0] == 0 && gameState[3] == 0 && gameState[6] == 0)
                        {
                            img1.setImageResource(R.drawable.x3);
                            img4.setImageResource(R.drawable.x3);
                            img7.setImageResource(R.drawable.x3);
                        }

                        else if (gameState[1] == 0 && gameState[4] == 0 && gameState[7] == 0)
                        {
                            img2.setImageResource(R.drawable.x3);
                            img5.setImageResource(R.drawable.x3);
                            img8.setImageResource(R.drawable.x3);
                        }

                        else if (gameState[2] == 0 && gameState[5] == 0 && gameState[8] == 0)
                        {
                            img3.setImageResource(R.drawable.x3);
                            img6.setImageResource(R.drawable.x3);
                            img9.setImageResource(R.drawable.x3);
                        }

                        // means x have won by two diagonals, then both diagonals will change and score will incement by 2
                        else if(gameState[0] == 0 && gameState[4] == 0 && gameState[8] == 0
                                && gameState[2] == 0 && gameState[4] == 0 && gameState[6] == 0)
                        {
                            img1.setImageResource(R.drawable.x2);
                            img5.setImageResource(R.drawable.x2);
                            img9.setImageResource(R.drawable.x2);
                            img3.setImageResource(R.drawable.x2);
                            img5.setImageResource(R.drawable.x2);
                            img7.setImageResource(R.drawable.x2);

                            Toast.makeText(MainActivity.this, name2 + " Scored 1 bonus point", Toast.LENGTH_SHORT).show();
                        }
                        else if (gameState[0] == 0 && gameState[4] == 0 && gameState[8] == 0)
                        {
                            img1.setImageResource(R.drawable.x3);
                            img5.setImageResource(R.drawable.x3);
                            img9.setImageResource(R.drawable.x3);
                        }

                        else if (gameState[2] == 0 && gameState[4] == 0 && gameState[6] == 0)
                        {
                            img3.setImageResource(R.drawable.x3);
                            img5.setImageResource(R.drawable.x3);
                            img7.setImageResource(R.drawable.x3);
                        }

                        // if O is the winner
                        if (gameState[0] == 1 && gameState[1] == 1 && gameState[2] == 1) {
                            img1.setImageResource(R.drawable.o3);
                            img2.setImageResource(R.drawable.o3);
                            img3.setImageResource(R.drawable.o3);
                        }
                        else if (gameState[3] == 1 && gameState[4] == 1 && gameState[5] == 1) {
                            img4.setImageResource(R.drawable.o3);
                            img5.setImageResource(R.drawable.o3);
                            img6.setImageResource(R.drawable.o3);
                        } else if (gameState[6] == 1 && gameState[7] == 1 && gameState[8] == 1) {
                            img7.setImageResource(R.drawable.o3);
                            img8.setImageResource(R.drawable.o3);
                            img9.setImageResource(R.drawable.o3);
                        } else if (gameState[0] == 1 && gameState[3] == 1 && gameState[6] == 1) {
                            img1.setImageResource(R.drawable.o3);
                            img4.setImageResource(R.drawable.o3);
                            img7.setImageResource(R.drawable.o3);
                        } else if (gameState[1] == 1 && gameState[4] == 1 && gameState[7] == 1) {
                            img2.setImageResource(R.drawable.o3);
                            img5.setImageResource(R.drawable.o3);
                            img8.setImageResource(R.drawable.o3);
                        } else if (gameState[2] == 1 && gameState[5] == 1 && gameState[8] == 1) {
                            img3.setImageResource(R.drawable.o3);
                            img6.setImageResource(R.drawable.o3);
                            img9.setImageResource(R.drawable.o3);
                        }

                        // means o have won by two diagonals, then both diagonals will change and score will incement by 2
                        else if(gameState[0] == 1 && gameState[4] == 1 && gameState[8] == 1
                                && gameState[2] == 1 && gameState[4] == 1 && gameState[6] == 1)
                        {
                            img1.setImageResource(R.drawable.o1);
                            img5.setImageResource(R.drawable.o1);
                            img9.setImageResource(R.drawable.o1);
                            img3.setImageResource(R.drawable.o1);
                            img5.setImageResource(R.drawable.o1);
                            img7.setImageResource(R.drawable.o1);

                            Toast.makeText(MainActivity.this, name1 + " Scored 1 bonus point", Toast.LENGTH_SHORT).show();
                        }

                        else if (gameState[0] == 1 && gameState[4] == 1 && gameState[8] == 1) {
                            img1.setImageResource(R.drawable.o3);
                            img5.setImageResource(R.drawable.o3);
                            img9.setImageResource(R.drawable.o3);
                        } else if (gameState[2] == 1 && gameState[4] == 1 && gameState[6] == 1) {
                            img3.setImageResource(R.drawable.o3);
                            img5.setImageResource(R.drawable.o3);
                            img7.setImageResource(R.drawable.o3);
                        }


                        //this will print,at which position the player has won the game
//                    for (int element: gameState)
//                    {
//                        System.out.println("\n########################  After win gamestate is : " + element);
//                    }

                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText(winner + " won the Game");

                        //this will display the the replay button
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                        //this is the view which showing whose turn earlier.
                        //i'm unable disable it after winnig so i used it for showing Game Over message
                        TextView status = findViewById(R.id.status);
                        status.setText("Hurray !!!");

                        sound.playWinSound();
                        disableImageView();
                    }

                }

                else {
                    boolean gameIsover = true;

                    //this for loop will check if all the possibilities of winning are not satified and game is not over means it is draw
                    for (int counterState : gameState)
                    {
                        if (counterState == -2)
                        {
                            gameIsover = false;
                        }
                    }

                    if (gameIsover==true)
                    {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        //winnerMessage.setText("Draw !!!");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                        TextView status = findViewById(R.id.status);
                        status.setText("Draw !!!");

                        sound.playlossSound();
                        disableImageView();
                    }

                }
            }

            //enable all images
            EnableImageView();

        }
    }

    public void disableImageView()
    {
        ImageView img1 = findViewById(R.id.imageView);
        ImageView img2 = findViewById(R.id.imageView2);
        ImageView img3 = findViewById(R.id.imageView3);
        ImageView img4 = findViewById(R.id.imageView4);
        ImageView img5 = findViewById(R.id.imageView7);
        ImageView img6 = findViewById(R.id.imageView10);
        ImageView img7 = findViewById(R.id.imageView11);
        ImageView img8 = findViewById(R.id.imageView13);
        ImageView img9 = findViewById(R.id.imageView14);

        img1.setEnabled(false);
        img2.setEnabled(false);
        img3.setEnabled(false);
        img4.setEnabled(false);
        img5.setEnabled(false);
        img6.setEnabled(false);
        img7.setEnabled(false);
        img8.setEnabled(false);
        img9.setEnabled(false);
    }

    public void EnableImageView()
    {
        ImageView img1 = findViewById(R.id.imageView);
        ImageView img2 = findViewById(R.id.imageView2);
        ImageView img3 = findViewById(R.id.imageView3);
        ImageView img4 = findViewById(R.id.imageView4);
        ImageView img5 = findViewById(R.id.imageView7);
        ImageView img6 = findViewById(R.id.imageView10);
        ImageView img7 = findViewById(R.id.imageView11);
        ImageView img8 = findViewById(R.id.imageView13);
        ImageView img9 = findViewById(R.id.imageView14);

        img1.setEnabled(true);
        img2.setEnabled(true);
        img3.setEnabled(true);
        img4.setEnabled(true);
        img5.setEnabled(true);
        img6.setEnabled(true);
        img7.setEnabled(true);
        img8.setEnabled(true);
        img9.setEnabled(true);
    }

    public void playAgain(View view){

        //this is the onClick method of the Play Again button
        Intent play = new Intent(this,MainActivity.class);
        play.putExtra(MainActivity.NAME1,name1);
        play.putExtra(MainActivity.NAME2,name2);

        play.putExtra("name1Score", name1Score + "");
        play.putExtra("name2Score",name2Score + "");

        startActivity(play);
        MainActivity.this.finish();
    }

    public void homeButton3(View view)
    {
        Intent intent = new Intent(MainActivity.this,GameActivity.class);
        startActivity(intent);
        //MainActivity.this.finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sound = new SoundPlayer(this);

        Intent i = getIntent();
        name1 = i.getStringExtra(NAME1);
        name2 = i.getStringExtra(NAME2);

        name1Score = Integer.parseInt(i.getStringExtra("name1Score"));
        name2Score = Integer.parseInt(i.getStringExtra("name2Score"));

        TextView status = findViewById(R.id.status);
        status.setText("Your Turn " +  name2);

        TextView p1o = findViewById(R.id.p1o);
        p1o.setText(name1 + " " + name1Score);

        TextView p2x = findViewById(R.id.p2x);
        p2x.setText(name2 + " " + name2Score);

        getSupportActionBar().hide();
    }
}