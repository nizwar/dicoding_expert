package id.nizwar.katalogmovie.broadcast;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Calendar;

import id.nizwar.katalogmovie.MainActivity;
import id.nizwar.katalogmovie.NizNotification;
import id.nizwar.katalogmovie.R;

public class DailyReminder extends BroadcastReceiver {
    private static final String SHOW_NOTIF = "SHOW_NOTIF_DIALY";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null)
            if (!intent.getAction().equals(SHOW_NOTIF)) return;

        final Intent repeatIntent = new Intent(context, MainActivity.class);
        repeatIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, repeatIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NizNotification nizNotification = new NizNotification(context);
        NotificationManager notificationManager = nizNotification.getManager();
        Notification.Builder builder = nizNotification.getNotificationBuilder(context);
        builder .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_favorite)
                .setContentTitle(context.getString(R.string.str_dailyrminder_title))
                .setContentText(context.getString(R.string.str_dailyrminder_text))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            assert notificationManager != null;
            notificationManager.notify(1, builder.build());
        }
    }


    public static void setDailyReminder(Context context, boolean status) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 30);
        Intent intent = new Intent(context, DailyReminder.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        if (status) {
            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
        }
    }


}
