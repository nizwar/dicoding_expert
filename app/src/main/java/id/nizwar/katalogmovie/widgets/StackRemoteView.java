package id.nizwar.katalogmovie.widgets;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import id.nizwar.katalogmovie.helpers.Env;
import id.nizwar.katalogmovie.R;
import id.nizwar.katalogmovie.models.Katalog;
import id.nizwar.katalogmovie.models.SQFavorite;

import static id.nizwar.katalogmovie.widgets.FavoriteWidget.EXTRA_ITEM;

class StackRemoteView implements RemoteViewsService.RemoteViewsFactory {
    private SQFavorite favorite;
    private ArrayList<Katalog> listKatalog;
    private final Context context;

    StackRemoteView(Context context) {
        favorite = new SQFavorite(context);
        this.context = context;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        listKatalog = favorite.getFavorite(-1, null);
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        return listKatalog.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.v_widgetitem);
        try {
            Bitmap bitmap = Picasso.get().load(Env.IMG_URL500 + listKatalog.get(position).getBackdropPath()).get();
            remoteViews.setImageViewBitmap(R.id.imgCover, bitmap);
        } catch (IOException ignored) {
        }
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_ITEM,  listKatalog.get(position).getId());

        Intent intent = new Intent();
        intent.putExtras(bundle);

        remoteViews.setOnClickFillInIntent(R.id.imgCover, intent);
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}

