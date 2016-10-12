package ua.kyslytsia.tctcalc;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    private static EditText inputMinutes;
    private static EditText inputSeconds;
    private static EditText inputMillis;
    private static EditText inputDistancePenalty;
    private static EditText penaltyCost;
    private static GregorianCalendar gregorianCalendar;

    private static TextView timeWithPenalty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputMinutes = (EditText) findViewById(R.id.inputMinutes);
        inputSeconds = (EditText) findViewById(R.id.inputSeconds);
        inputMillis = (EditText) findViewById(R.id.inputMillis);
        inputDistancePenalty = (EditText) findViewById(R.id.inputDistancePenalty);
        penaltyCost = (EditText) findViewById(R.id.penaltyCost);
        timeWithPenalty = (TextView) findViewById(R.id.timeWithPenalty);

        gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(R.string.action_about)
                    .setMessage(R.string.about_text)
                    .setCancelable(true)
                    .setNegativeButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = dialog.create();
            alert.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void takeResult (View v) {
        if (inputMinutes.getText().toString().isEmpty()) {
            inputMinutes.setText("0");
        }

        if (inputSeconds.getText().toString().isEmpty()) {
            inputSeconds.setText("0");
        }

        if (inputMillis.getText().toString().isEmpty()) {
            inputMillis.setText("0");
        }

        if (inputDistancePenalty.getText().toString().isEmpty()) {
            inputDistancePenalty.setText("0");
        }

        if (penaltyCost.getText().toString().isEmpty()) {
            penaltyCost.setText("0");
        }

        int penaltyTime = Integer.valueOf(inputDistancePenalty.getText().toString()) * Integer.valueOf(penaltyCost.getText().toString());
        gregorianCalendar.set(gregorianCalendar.MINUTE, Integer.valueOf(inputMinutes.getText().toString()));
        gregorianCalendar.set(gregorianCalendar.SECOND, Integer.valueOf(inputSeconds.getText().toString()));
        gregorianCalendar.set(gregorianCalendar.MILLISECOND, Integer.valueOf(inputMillis.getText().toString()));

        gregorianCalendar.add(gregorianCalendar.SECOND, penaltyTime);

        timeWithPenalty.setText(gregorianCalendar.get(gregorianCalendar.MINUTE) + ":" + gregorianCalendar.get(gregorianCalendar.SECOND) + ":" + gregorianCalendar.get(gregorianCalendar.MILLISECOND));
    }

    public void clearFields(View v) {
        inputMinutes.setText("");
        inputSeconds.setText("");
        inputMillis.setText("");
        inputDistancePenalty.setText("");

    }
}
