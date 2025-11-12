package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {

    Button play_button;
    EditText riddle_edit_text;
    EditText answer_edit_text;

    boolean hasResult = false;
    String result_from_sec_activity;
    // Cheie pentru salvarea în Bundle
    private static final String SAVED_RESULT_KEY = "saved_result";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        // ===== Secțiunea de restaurare =====
        if (savedInstanceState != null) {
            // Verificăm dacă cheia noastră există în Bundle
            if (savedInstanceState.containsKey(SAVED_RESULT_KEY)) {
                // Restaurăm valoarea
                result_from_sec_activity = savedInstanceState.getString(SAVED_RESULT_KEY);
                hasResult = true;

                // Afișăm un Toast pentru a confirma că s-a restaurat
                Toast.makeText(this, "Rezultat restaurat: " + result_from_sec_activity, Toast.LENGTH_LONG).show();
            }
        }

        play_button = (Button) findViewById(R.id.play_button);
        riddle_edit_text = (EditText) findViewById(R.id.riddle_edit_text);
        answer_edit_text = (EditText) findViewById(R.id.answer_edit_text);


        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // iau answer din edittext
                String answer = answer_edit_text.getText().toString();

                // creez intentul
                Intent intent = new Intent(PracticalTest01Var08MainActivity.this, PracticalTest01Var08PlayActivity.class);

                // adaug constantele in intent
                intent.putExtra(Constants.KEY_ANSWER, answer);

                // pornesc activitatea pentru un rezultat
                startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // verific daca e raspunsul la cererea mea
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {

            // verific daca s-a terminat ok
            if (resultCode == RESULT_OK) {

                // verific ca intentul inapoiat nu e null
                if (intent != null) {

                    // 4. Extrage rezultatul folosind aceeași cheie
                    String ans_result = intent.getStringExtra(Constants.KEY_RESULT_RIDDLE);
                    result_from_sec_activity = ans_result;
                    hasResult = true; // Marcăm că avem un rezultat valid
                    // 5. Folosește rezultatul!
                    Toast.makeText(this, "Rezultatul primit este: " + ans_result, Toast.LENGTH_LONG).show();

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Operațiune anulată", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Salvăm rezultatul doar dacă avem unul
        if (hasResult) {
            outState.putString(SAVED_RESULT_KEY, result_from_sec_activity);
        }
    }



}