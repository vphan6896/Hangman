package pokecrater1.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;

public class Hangman extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        Button button1 = (Button)this.findViewById(R.id.click_btn);
        button1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v)  {

            }
        }
    }
}

