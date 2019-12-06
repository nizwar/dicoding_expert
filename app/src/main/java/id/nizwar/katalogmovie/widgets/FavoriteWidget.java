package id.nizwar.katalogmovie.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import id.nizwar.katalogmovie.KatalogDetailActivity;
import id.nizwar.katalogmovie.R;

public class FavoriteWidget extends AppWidgetProvider {

    public static final String EXTRA_ITEM = "id.nizwar.katalogmovie.EXTRA_ITEM";
    public static final String OPEN_ACTION = "id.nizwar.katalogmovie.OPEN_ACTION";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        Intent intent = new Intent(context, StackWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_favorite);
        views.setRemoteAdapter(R.id.stackWidget, intent);

        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        Intent openIntent = new Intent(context, FavoriteWidget.class);
        openIntent.setAction(OPEN_ACTION);
        openIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        PendingIntent openPendingIntent = PendingIntent.getBroadcast(context, 0, openIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.stackWidget, openPendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction() != null) {
            if (OPEN_ACTION.equals(intent.getAction())) {
                int favorite = intent.getIntExtra(EXTRA_ITEM, -1);
                if (favorite == -1) return;
                Intent intentFav = new Intent(context, KatalogDetailActivity.class);
                intentFav.putExtra("id_favorite", favorite);
                intentFav.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentFav);
            }
            int appIdWidget = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            appWidgetManager.notifyAppWidgetViewDataChanged(appIdWidget, R.id.stackWidget);
        }
    }

}

