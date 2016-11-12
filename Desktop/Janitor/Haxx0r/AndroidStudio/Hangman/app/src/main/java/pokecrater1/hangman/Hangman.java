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

public class Hangman extends AppCompatActivity {
    String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        Button b = (Button)this.findViewById(R.id.button1);
        b.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v)  {
                TextView resp = (TextView) findViewById(R.id.response);
                EditText input = (EditText) findViewById(R.id.textView);
                String guess = input.getText().toString();
                abc = changeABC(guess,abc);
                resp.setText(abc);

            }
        });
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

}

