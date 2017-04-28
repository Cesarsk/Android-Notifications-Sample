/*
   Created by Cesarsk on 26 Apr 2017 - 22:39
   http://lucacesarano.tk/

 */

package cesarsk.androidnotificationssample;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cesarsk.androidnotificationssample.settings.TimePickerFragment;

public class MainActivity extends AppCompatActivity {

    public static final String DEFAULT_NOTIFICATION_HOUR = "22";
    public static final String DEFAULT_NOTIFICATION_MINUTE = "46";
    public static String DEFAULT_NOTIFICATION_RATE = "2"; //0 = off; 1 = weekly; 2 = daily
    public static final Integer notifId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Here you can set when you want to receive the notification
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        final TextView textView = (TextView) findViewById(R.id.text);

        textView.setText("Current Notification Rate: daily");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DEFAULT_NOTIFICATION_RATE = "2";
                textView.setText("Current Notification Rate: daily");
                //Toast.makeText(MainActivity.this, "Notification is now daily", Toast.LENGTH_SHORT).show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DEFAULT_NOTIFICATION_RATE = "1";
                textView.setText("Current Notification Rate: weekly");
                Toast.makeText(MainActivity.this, "Notification is now weekly", Toast.LENGTH_SHORT).show();

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DEFAULT_NOTIFICATION_RATE = "0";
                textView.setText("Current Notification Rate: off");
                Toast.makeText(MainActivity.this, "Notification is now off", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }
}
