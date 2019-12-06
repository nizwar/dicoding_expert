package id.nizwar.katalogmovie.models;

import android.net.Uri;
import android.provider.BaseColumns;

public class CUFavorite {
    static final String AUTHORITY = "id.nizwar.katalogmovie.katalog";
    private static final String SCHEME = "content";

    public static class FavoriteColumns implements BaseColumns {
        static final String TBL_NAME = "favorite";

        public static final Uri CONTENT_URI = new Uri.Builder()
                .scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TBL_NAME)
                .build();
    }
}
