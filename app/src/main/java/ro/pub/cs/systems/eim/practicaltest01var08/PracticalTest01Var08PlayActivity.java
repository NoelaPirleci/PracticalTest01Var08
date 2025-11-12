package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
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

public class PracticalTest01Var08PlayActivity extends AppCompatActivity {

    EditText answer_edit_text_play;
    Button check_button;
    Button back_button;
    String answer_intent;

    private Intent serviceIntent;
    private BroadcastReceiver serviceDataReceiver;
    private IntentFilter intentFilter;

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


        // pornesc serviciul
        serviceIntent = new Intent(this, PracticalTest01Var08Service.class);
        startService(serviceIntent);

        // initializez receiver-ul
        serviceDataReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // verific daca actiunea e cea corecta
                if (PracticalTest01Var08Service.ACTION_UPDATE_UI.equals(intent.getAction())) {

                    // extrag datele
                    String val_result = intent.getStringExtra(PracticalTest01Var08Service.KEY_VAL_ANSWER);

                    // actualizez valoarea edittextului answer
                    if (answer_edit_text_play.getText().toString().isEmpty()) {
                        answer_edit_text_play.setText(val_result);
                    }

                    // afisez in toast
                    Toast.makeText(context, "Rezultatul este: " + val_result, Toast.LENGTH_LONG).show();
                }
            }
        };

        // creez filtru pt actiunea pe care o ascult
        intentFilter = new IntentFilter();
        intentFilter.addAction(PracticalTest01Var08Service.ACTION_UPDATE_UI);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(serviceDataReceiver, intentFilter, RECEIVER_EXPORTED);
        }
    }

    @Override
    protected void onPause() {
        unregisterReceiver(serviceDataReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // opresc serviciul cand e activitatea distrusa
        stopService(serviceIntent);
        super.onDestroy();
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