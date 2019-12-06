package id.nizwar.katalogmovie.broadcast;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import id.nizwar.katalogmovie.helpers.Env;
import id.nizwar.katalogmovie.NizNotification;
import id.nizwar.katalogmovie.PencarianActivity;
import id.nizwar.katalogmovie.R;
import id.nizwar.katalogmovie.models.Katalog;
import id.nizwar.katalogmovie.models.KatalogMovieAttrb;

public class ReleaseToday extends BroadcastReceiver {
    private static final String SHOW_NOTIF = "SHOW_NOTIF_RELEASED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null)
            if (intent.getAction().equals(SHOW_NOTIF)) {
                getAndShowNotif(context);
            }
    }

    private void getAndShowNotif(final Context context) {
        StringRequest request = new StringRequest(Request.Method.GET, Env.GET_RELEASE_URL(Calendar.getInstance().getTime()),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsResults = new JSONObject(response).getJSONArray("results");
                            NizNotification nizNotification = new NizNotification(context);
                            if (jsResults.length() > 0) {
                                int jumlah = 3;
                                if (jsResults.length() < 5) jumlah = jsResults.length();
                                for (int i = 0; i < jumlah; i++) {
                                    final Katalog katalog = new KatalogMovieAttrb(jsResults.getJSONObject(i));
                                    final Intent repeatIntent = new Intent(context, PencarianActivity.class);
                                    repeatIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    repeatIntent.putExtra("query", katalog.getTitle());
                                    repeatIntent.putExtra("jenis", 0);
                                    repeatIntent.putExtra("online", 1);

                                    PendingIntent pendingIntent = PendingIntent.getActivity(context, katalog.getId(), repeatIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                    NotificationManager notificationManager = nizNotification.getManager();
                                    Notification.Builder builder = nizNotification.getNotificationBuilder(context);
                                    builder .setContentIntent(pendingIntent)
                                            .setSmallIcon(R.drawable.ic_ticket)
                                            .setContentTitle(katalog.getTitle() + " " + context.getString(R.string.str_releasetoday))
                                            .setContentText(katalog.getOverview())
                                            .setAutoCancel(true);

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                        assert notificationManager != null;
                                        notificationManager.notify(katalog.getId(), builder.build());
                                    }
                                }
                            }
                        } catch (JSONException ignored) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(context).add(request);

    }

    public static void setReleaseToday(Context context, boolean status) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 30);

        Intent intent = new Intent(context, ReleaseToday.class);
        intent.setAction(SHOW_NOTIF);
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
