package com.iappstogo.connect3game;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 Yellow and 1 - Red, 2 - empty

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2} , {3, 4, 5}, {6, 7, 8},  {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    boolean gameActive = true;

    public void dropIn(View imgView){
        ImageView counter = (ImageView) imgView;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    gameActive = false;
                    Toast.makeText(this, winner + " has own!", Toast.LENGTH_LONG).show();
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has own!");

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
                else if (isAllDone()) {
                    Toast.makeText(this, "No One is winner!", Toast.LENGTH_LONG).show();
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText("No one won!");

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }


        }

    }

    private boolean isAllDone() {
        boolean allDone = true;
        for (int i=0; i< gameState.length; i++){
            if (gameState[i] == 2)
                allDone = false;
        }
        return allDone;
    }

    public void playAgain(View btnView) {
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.gameGridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counters = (ImageView) gridLayout.getChildAt(i);
            counters.setImageDrawable(null);

        }

        for (int i=0; i< gameState.length; i++){
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
