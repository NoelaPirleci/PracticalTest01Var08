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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

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

                    // 5. Folosește rezultatul!
                    Toast.makeText(this, "Rezultatul primit este: " + ans_result, Toast.LENGTH_LONG).show();

                }
            } else if (resultCode == RESULT_CANCELED) {
                // Utilizatorul a apăsat "Back" din a doua activitate
                Toast.makeText(this, "Operațiune anulată", Toast.LENGTH_SHORT).show();
            }
        }
    }

}