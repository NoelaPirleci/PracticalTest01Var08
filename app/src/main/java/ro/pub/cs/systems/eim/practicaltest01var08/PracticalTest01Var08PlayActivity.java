package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var08PlayActivity extends AppCompatActivity {

    EditText answer_edit_text_play;
    Button check_button;
    Button back_button;
    String answer_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_play);

        answer_edit_text_play = (EditText) findViewById(R.id.answer_edit_text_sec);
        check_button = (Button) findViewById(R.id.check_button);
        back_button = (Button) findViewById(R.id.back_button);

        // primesc intentul
        Intent intent = getIntent();

        // extrag datele din intent
        if (intent != null && intent.hasExtra(Constants.KEY_ANSWER)) {
            answer_intent = intent.getStringExtra(Constants.KEY_ANSWER);
        }

        // setez listeneri pentru cele 2 butoane
        check_button.setOnClickListener(new ButtonClickListener());
        back_button.setOnClickListener(new ButtonClickListener());

    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            // creez un intent pentru rezultat
            Intent resultIntent = new Intent();

            // extrag valoarea care ma intereseaza
            String answer_riddle = answer_edit_text_play.getText().toString();

            if (view.getId() == R.id.check_button) {

                if (answer_riddle.equals(answer_intent)) {

                    resultIntent.putExtra(Constants.KEY_RESULT_RIDDLE, "Yes");

                    // 3. Setează codul de rezultat ca "OK" și atașează datele
                    setResult(RESULT_OK, resultIntent);
                } else {
                    resultIntent.putExtra(Constants.KEY_RESULT_RIDDLE, "No");

                    // 3. Setează codul de rezultat ca "OK" și atașează datele
                    setResult(RESULT_OK, resultIntent);
                }

            }

            // inchide activitatea curenta
            finish();
        }
    }

}