package id.nizwar.katalogmovie.helpers;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;

import java.util.ArrayList;

import id.nizwar.katalogmovie.models.CUFavorite;
import id.nizwar.katalogmovie.models.Katalog;

public class DataObserver extends ContentObserver {

    public DataObserver(Handler handler) {
        super(handler);
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
    }

    public static class LoadKatalogAsync extends AsyncTask<Void, Void, ArrayList<Katalog>> {
        @SuppressLint("StaticFieldLeak")
        private final Context context;
        private final LoadKatalogsCallback callBack;
        private final int jenis;
        private final String query;

        public LoadKatalogAsync(Context context, int jenis, String query, LoadKatalogsCallback callback) {
            this.context = context;
            this.jenis = jenis;
            this.query = query;
            callBack = callback;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            callBack.preExecute();
        }

        @Override
        protected ArrayList<Katalog> doInBackground(Void... voids) {
            Context context = this.context;
            Cursor dataCursor;
            if (query != null) {
                dataCursor = context.getContentResolver().query(CUFavorite.FavoriteColumns.CONTENT_URI,
                        null,
                        "jenis=? AND title LIKE ?",
                        new String[]{String.valueOf(jenis), "%"+query+"%"},
                        null);
            } else {
                dataCursor = context.getContentResolver().query(CUFavorite.FavoriteColumns.CONTENT_URI,
                        null,
                        "jenis = ?",
                        new String[]{String.valueOf(jenis)},
                        null);
            }
            assert dataCursor != null;
            return Katalog.cursorToArrayKatalog(dataCursor);
        }

        @Override
        protected void onPostExecute(ArrayList<Katalog> katalogs) {
            super.onPostExecute(katalogs);
            if (katalogs != null)
                callBack.postExecute(katalogs);
        }
    }

    public interface LoadKatalogsCallback {
        void preExecute();

        void postExecute(ArrayList<Katalog> katalogs);
    }
}
