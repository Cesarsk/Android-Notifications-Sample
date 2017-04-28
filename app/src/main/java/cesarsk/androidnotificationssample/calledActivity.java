package cesarsk.androidnotificationssample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class calledActivity extends Activity {
    public final static String WORD = "com.example.cesarsk.say_it.WORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_called);

        //preparing bundle to receive args
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String loadedWord = bundle.getString(WORD);
        TextView text = (TextView)findViewById(R.id.text);
        text.setText(loadedWord);
    }
}
