package cesarsk.androidnotificationssample.notifications;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

import java.util.Calendar;

import cesarsk.androidnotificationssample.MainActivity;
import cesarsk.androidnotificationssample.R;
import cesarsk.androidnotificationssample.calledActivity;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Do some operations, like loading values from a file, and show it in the notification or pass it in the calledActivity
        String loadedString = "This is a string loaded from the Receiver";

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //Building our custom Notification
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_flag) //Set icon
                        .setContentTitle("Notification Title")
                        .setContentText("Click to call calledActivity")
                        .setSound(alarmSound)
                        .setColor(Color.argb(0,21,101,192)) //Color your notification
                        .setVibrate(new long[]{300, 300, 300, 300, 300}) //Vibration intensity
                        .setAutoCancel(true);

        //setting activity called clicking on the notification
        Intent resultIntent = new Intent(context, calledActivity.class);
        resultIntent.putExtra(calledActivity.WORD, loadedString);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        //Building virtual stack, allowing the user to press back to go back to the main activity
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(calledActivity.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);
        notificationManager.notify(MainActivity.notifId, mBuilder.build());
    }

    //This method is used to schedule your notification, you can call it and set the notification from here
    public static void scheduleNotification(Context context, int hour, int minute, String mode) {
        int mode_code = Integer.parseInt(mode);
        ComponentName receiver = new ComponentName(context, NotificationBootReceiver.class);
        PackageManager pm = context.getPackageManager();

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationReceiver.class);
        PendingIntent notificationPendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if(mode_code == 0){
            //Notification OFF
            alarmManager.cancel(notificationPendingIntent);
            pm.setComponentEnabledSetting(receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);
            return;
        }

        //Using Calendar Class is the best way to retrieve information about Time
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        //Daily Notifications
        if(mode_code == 2){
            //Pay attention: we need to avoid the case WHERE current time > set time OR we'd receive a notification as soon as we open our app / reboot our phone
            if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
                calendar.setTimeInMillis(calendar.getTimeInMillis() + AlarmManager.INTERVAL_DAY);
            }
            //Inexact is not used if you need an accurate notification, look for a more accurate instruction like alarmManager.setExact
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, notificationPendingIntent);
        }

        //Weekly Notifications
        else if(mode_code == 1){
            if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
                calendar.setTimeInMillis(calendar.getTimeInMillis() + AlarmManager.INTERVAL_DAY*7);
            }

            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, notificationPendingIntent);
        }

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}
