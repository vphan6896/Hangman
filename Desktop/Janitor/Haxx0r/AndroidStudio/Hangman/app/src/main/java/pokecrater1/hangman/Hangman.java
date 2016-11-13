package pokecrater1.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.view.View.OnKeyListener;

import java.util.Random;

public class Hangman extends AppCompatActivity {
    String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    TextView initialABC;
    String[] movieList = new String[20];
    String word = movieList(movieList).toUpperCase();
    TextView phrase;
    String underScores = "";
    String newUnderScores = "";
    int counter;
    TextView numWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        initialABC = (TextView) this.findViewById(R.id.initialABC);
        initialABC.setText(abc);
        phrase = (TextView) this.findViewById(R.id.phraseid);
        newUnderScores = underScores(word, underScores);
        phrase.setText(newUnderScores);
        numWrong = (TextView) findViewById(R.id.numWrong);
        numWrong.setText("Number Wrong: " + counter);
        submitButton();
        playAgainButton();
    }

    public void playAgainButton() {
        Button playAgain = (Button) this.findViewById(R.id.buttonPlay);
        playAgain.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 0;
                abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                word = movieList(movieList).toUpperCase();
                setContentView(R.layout.activity_hangman);
                initialABC = (TextView) findViewById(R.id.initialABC);
                initialABC.setText(abc);
                phrase = (TextView) findViewById(R.id.phraseid);
                newUnderScores = underScores(word, underScores);
                phrase.setText(newUnderScores);
                numWrong = (TextView) findViewById(R.id.numWrong);
                numWrong.setText("Number Wrong: " + counter);

                submitButton();

                playAgainButton();
            }
        });
    }

    public void submitButton() {
        Button b = (Button) this.findViewById(R.id.button1);
        b.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView resp = (TextView) findViewById(R.id.response);
                EditText input = (EditText) findViewById(R.id.textView);
                String guess = input.getText().toString();
                initialABC.setText("");
                abc = changeABC(guess, abc);
                resp.setText(abc);
                // changes the word into underscores
                newUnderScores = guessResults(guess, word, newUnderScores);
                TextView phrase = (TextView) findViewById(R.id.phraseid);
                phrase.setText(newUnderScores);
                counter = checkWinOrLose(guess, word, counter);
                TextView numWrong = (TextView) findViewById(R.id.numWrong);
                numWrong.setText("Number Wrong: " + counter);
                if (counter >= 5) {
                    gameOver();
                }
            }

        });
    }


    public static String underScores(String word, String underScores) {
        for (int index = 0; index < word.length(); index++) {
            if(word.charAt(index) == ' ') {
                underScores += "_";
            }
            else
                underScores += "*";
        }
        return underScores;
    }

    // changes the current phrase to insert the guess if it matches a letter in
    // the secret phrase
    public static String newCurrentPhrase(String guess, String phrase, String newUnderScores) {
        String result = "";

        for (int i = 0; i < phrase.length(); i++) {
            String letterString = phrase.charAt(i) + "";
            if (letterString.equals(guess)) {
                result += guess;
            } else if (letterString.equals(" ")) {
                result += "_";
            } else {
                result += newUnderScores.charAt(i);
            }
        }
        return result;
    }



    // checks the guess and makes a new current phrase if the guess was a letter
    // of the secret phrase
    public static String guessResults(String guess, String secretPhrase, String currentPhrase) {
        if (secretPhrase.contains(guess)) {
            currentPhrase = newCurrentPhrase(guess, secretPhrase, currentPhrase);
        } else {
        }
        return currentPhrase;
    }

    public static String movieList(String[] movieList) {
        movieList[0] = "The Godfather";
        movieList[1] = "The Shawshank Redemption";
        movieList[2] = "Spirited Away";
        movieList[3] = "Raging Bull";
        movieList[4] = "Casablanca";
        movieList[5] = "Citizen Kane";
        movieList[6] = "The Wizard of Oz";
        movieList[7] = "Forrest Gump";
        movieList[8] = "Gladiator";
        movieList[9] = "Titanic";
        movieList[10] = "Saving Private Ryan";
        movieList[11] = "Unforgiven";
        movieList[12] = "Raiders of the Lost Ark";
        movieList[13] = "Jaws";
        movieList[14] = "Jurassic Park";
        movieList[15] = "Rush Hour";
        movieList[16] = "Good Will Hunting";
        movieList[17] = "Grave of the Fireflies";
        movieList[18] = "Howl's Moving Castle";
        movieList[19] = "My Neighbor Totoro";
        Random r = new Random();
        int choose = r.nextInt(20);
        return movieList[choose];

    }

    // changes the abc string based off of the guess
    public static String changeABC(String guess, String abc) {
        String beginning = abc.substring(0, abc.indexOf(guess));
        String end = "";

        if (!guess.equals("Z")) {
            end = abc.substring(abc.indexOf(guess) + 1);
        } else {
            beginning = abc.substring(0, abc.indexOf(guess));
        }
        return beginning + end;
    }

    // checks to see if the guess was a letter of the secret phrase and then
    // returns 1 if the guess is wrong or 0 if it is right

    public static int checkWinOrLose(String guess, String phrase, int counter) {
        if (!phrase.contains(guess)) {
            counter++;
        }
        return counter;
    }

    //this is where the game ends
    public void gameOver() {
        Button b = (Button) this.findViewById(R.id.button1);
        b.setEnabled(false);
    }


}
