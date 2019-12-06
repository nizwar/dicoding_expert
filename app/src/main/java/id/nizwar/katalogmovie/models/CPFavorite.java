package id.nizwar.katalogmovie.models;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import static id.nizwar.katalogmovie.models.CUFavorite.AUTHORITY;
import static id.nizwar.katalogmovie.models.CUFavorite.FavoriteColumns.CONTENT_URI;
import static id.nizwar.katalogmovie.models.CUFavorite.FavoriteColumns.TBL_NAME;

public class CPFavorite extends ContentProvider {
    private static final int FAVORITE = 1;
    private static final int FAVORITE_ID = 2;
    private SQFavorite sqFavorite;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, TBL_NAME, FAVORITE);
        sUriMatcher.addURI(AUTHORITY, TBL_NAME + "/#", FAVORITE_ID);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (uri.getLastPathSegment() != null)
            sqFavorite.getReadableDatabase().delete(SQFavorite.TBL_NAME, selection, selectionArgs);
        if (getContext() != null) getContext().getContentResolver().notifyChange(CONTENT_URI, null);
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long added;
        if (sUriMatcher.match(uri) == FAVORITE) {
            added = sqFavorite.getReadableDatabase().insert(SQFavorite.TBL_NAME, null, values);
        } else {
            added = 0;
        }
        if (getContext() != null) getContext().getContentResolver().notifyChange(CONTENT_URI, null);
        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public boolean onCreate() {
        sqFavorite = new SQFavorite(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        cursor = sqFavorite.getReadableDatabase().query(SQFavorite.TBL_NAME, null, selection, selectionArgs, null, null, null);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
