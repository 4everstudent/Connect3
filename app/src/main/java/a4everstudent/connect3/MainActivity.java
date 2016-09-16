package a4everstudent.connect3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


// TODO: 16-09-2016 Fix layout in multiple devices

public class MainActivity extends AppCompatActivity {

    //0=yellow, 1=red
    int activePlayer = 0;
    //2 means not played yet
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8},{0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2, 4, 6}};


    private void checkWin(){
        for (int [] winningPosition: winningPositions){
            if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]]== gameState[winningPosition[2]]&&
                    gameState[winningPosition[0]] != 2){
                //someone won the game
                System.out.println(gameState[winningPosition[0]]+" We have a winner!");
            }
            else {

            }
        }
    }

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        int  tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2){

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

            counter.animate().translationYBy(1000f).rotation(360).setDuration(1000);

            checkWin();



        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
