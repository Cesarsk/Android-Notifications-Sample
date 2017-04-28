package cesarsk.androidnotificationssample.settings;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

import cesarsk.androidnotificationssample.MainActivity;
import cesarsk.androidnotificationssample.notifications.NotificationReceiver;

/**
 * Created by cesarsk on 27/04/17.
 */

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        NotificationReceiver.scheduleNotification(getActivity(), hourOfDay, minute, MainActivity.DEFAULT_NOTIFICATION_RATE);
        Toast.makeText(getActivity(), "Notification set: "+String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute), Toast.LENGTH_SHORT).show();
    }
}

