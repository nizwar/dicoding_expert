package id.nizwar.katalogmovie;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

public class NizNotification extends ContextWrapper {
    private static final String CHANNEL_ID = "id.nizwar.katalogmovie.CHANNEL_ID";
    private static final String CHANNEL_NAME = "Nizwar Channel";
    NotificationManager manager;

    public NizNotification(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    private void createChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel nizChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            nizChannel.enableLights(true);
            nizChannel.enableVibration(true);
            nizChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            nizChannel.setLightColor(getResources().getColor(R.color.colorAccent));
            if (getManager() != null) {
                manager.createNotificationChannel(nizChannel);
            }
        }
    }

    public NotificationManager getManager() {
        if (manager == null) manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        return manager;
    }

    public Notification.Builder getNotificationBuilder(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new Notification.Builder(getApplicationContext(), CHANNEL_ID);
        }else{
            return new Notification.Builder(context);
        }
    }
}
