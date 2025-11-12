package ro.pub.cs.systems.eim.practicaltest01var08;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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
    }
}