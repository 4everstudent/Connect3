package a4everstudent.connect3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


// TODO: 16-09-2016 Fix layout in multiple devices

public class MainActivity extends AppCompatActivity {

    //0=yellow, 1=red
    int activePlayer = 0;
    //2 means not played yet
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8},{0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2, 4, 6}};
    boolean gameisActive = true;

    public void playAgain(View view){

        //hide winner layout
        final LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        gameisActive = true;
        activePlayer = 0;

        for(int i=0; i < gameState.length; i++){
             gameState[i]= 2;

        }


        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    private void checkWin(){
        String winner;

        for (int [] winningPosition: winningPositions){

            if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]]== gameState[winningPosition[2]]&&
                    gameState[winningPosition[0]] != 2){

                //someone won the game, set state to inactive, not allowing more plays
                gameisActive = false;


                //checks who won
                if(gameState[winningPosition[0]]==1){
                    winner = "Red ";
                }
                else{
                    winner = "Yellow ";
                }

                //change winner message
                TextView winnerMessage = (TextView) findViewById(R.id.winnerMsg);
                winnerMessage.setText(winner + "has won!");

                //show winner layout
                final LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);

            }

        }
    }

    public void dropIn(View view){

        ImageView counter = (ImageView) view;


        int  tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && gameisActive){

            gameState[tappedCounter] =activePlayer;

            counter.setTranslationY(-1000f);

            if(activePlayer == 0){

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;
            }
            else{

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            checkWin();



        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
