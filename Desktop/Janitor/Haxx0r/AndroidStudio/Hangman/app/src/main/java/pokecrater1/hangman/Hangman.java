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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        Button b = (Button)this.findViewById(R.id.button1);
        b.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v)  {
                TextView resp = (TextView) findViewById(R.id.response);
                EditText name = (EditText) findViewById(R.id.textView);
                String str = "Welcome " + name.getText().toString() + " !";
                resp.setText(str);
            }
        });
    }
}

