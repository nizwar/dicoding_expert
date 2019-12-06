package id.nizwar.favoritemovie.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQFavorite extends SQLiteOpenHelper {
    private static final String DB_FAVORITE = "katalog";
    static final String TBL_NAME = "favorite";


    public SQFavorite(Context context) {
        super(context, DB_FAVORITE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TBL_NAME +
                        " (id_movie INTEGER PRIMARY KEY, "              //Index 0
                        + "jenis INTEGER NOT NULL, "                    //Index 1
                        + "title TEXT NOT NULL, "                       //Index 2
                        + "overview TEXT NOT NULL, "                    //Index 3
                        + "vote_average REAL NOT NULL, "                //Index 4
                        + "release_date TEXT NOT NULL, "                //Index 5
                        + "original_title TEXT NOT NULL, "              //Index 6
                        + "backdrop_path TEXT NOT NULL, "               //Index 7
                        + "poster_path TEXT NOT NULL "                 //Index 8
                        + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Katalog getFavoriteById(int id) {
        Katalog output = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor getFavorite = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE id_movie = ?", new String[]{String.valueOf(id)});
        if (getFavorite.getCount() > 0) {
            getFavorite.moveToFirst();
            int jenis = getFavorite.getInt(1);
            if (jenis == 0) {
                output = new KatalogMovieAttrb(
                        getFavorite.getString(5),
                        getFavorite.getString(3),
                        getFavorite.getDouble(4),
                        getFavorite.getString(2),
                        getFavorite.getString(6),
                        getFavorite.getString(7),
                        getFavorite.getInt(0),
                        getFavorite.getString(8)
                );
            } else {
                output = new KatalogTvShowAttrb(
                        getFavorite.getString(5),
                        getFavorite.getString(3),
                        getFavorite.getDouble(4),
                        getFavorite.getString(2),
                        getFavorite.getString(6),
                        getFavorite.getString(7),
                        getFavorite.getInt(0),
                        getFavorite.getString(8));
            }
        }
        getFavorite.close();
        return output;
    }

//    @SuppressLint("Recycle")
//    public Cursor getFavoriteCursor(String jenis) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor;
//        if (jenis != null) {
//            cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE jenis = ?", new String[]{jenis});
//        } else {
//            cursor = db.rawQuery("SELECT * FROM " + TBL_NAME, new String[]{});
//        }
//        return cursor;
//    }

//    public long insertCV(ContentValues contentValues) {
//        return this.getReadableDatabase().insert(TBL_NAME, null, contentValues);
//    }

    public boolean isAvailable(SQLiteDatabase db, int id) {
        boolean output;
        Cursor getFavorite = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE id_movie = ?", new String[]{String.valueOf(id)});
        output = getFavorite.getCount() > 0;
        getFavorite.close();
        return output;
    }
}
