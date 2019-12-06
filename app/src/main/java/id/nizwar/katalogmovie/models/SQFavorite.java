package id.nizwar.katalogmovie.models;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQFavorite extends SQLiteOpenHelper {
    private static final String DB_FAVORITE = "katalog";
    private static final String TBL_NAME = "favorite";


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
//                        + "original_language TEXT NOT NULL, "           //Index NA
                        + "backdrop_path TEXT NOT NULL, "               //Index 7
//                        + "adult INTEGER NOT NULL, "                    //Index NA
                        + "poster_path TEXT NOT NULL "                 //Index 8
//                        + "video INTEGER NOT NULL, "                    //Index NA
//                        + "vote_count INTEGER NOT NULL, "               //Index NA
//                        + "popularity REAL NOT NULL"                    //Index NA
                        + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void setFavorite(Katalog katalog, int jenis) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_movie", katalog.id);
        contentValues.put("jenis", jenis);
        contentValues.put("title", katalog.title);
        contentValues.put("overview", katalog.overview);
        contentValues.put("vote_average", katalog.voteAverage);
        contentValues.put("release_date", katalog.releaseDate);
        contentValues.put("original_title", katalog.originalTitle);
//        contentValues.put("original_language", katalog.originalLanguage);
        contentValues.put("backdrop_path", katalog.backdropPath);
//        contentValues.put("adult", katalog.adult);
        contentValues.put("poster_path", katalog.posterPath);
//        contentValues.put("video", katalog.video);
//        contentValues.put("vote_count", katalog.voteCount);
//        contentValues.put("popularity", katalog.popularity);
        if (isAvailable(db, katalog.id)) return;
        db.insert(TBL_NAME, null, contentValues);
    }

    public void deleteFavorite(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        if (!isAvailable(db, id)) return;
        db.delete(TBL_NAME, "id_movie = ?", new String[]{String.valueOf(id)});
    }

    public ArrayList<Katalog> getFavorite(int jenis) {
        ArrayList<Katalog> output = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE jenis=?", new String[]{String.valueOf(jenis)});
        if(cursor.getCount() == 0) return new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (jenis == 0) {
                output.add(new KatalogMovieAttrb(
//                        cursor.getDouble(13),
                        cursor.getString(5),
                        cursor.getString(3),
                        cursor.getDouble(4),
                        cursor.getString(2),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getInt(0),
                        cursor.getString(8)
//                                > 0,
//                        cursor.getInt(0),
//                        cursor.getString(10),
//                        cursor.getInt(11) > 0,
//                        cursor.getInt(12)
                ));
            } else if (jenis == 1) {
                output.add(new KatalogTvShowAttrb(
//                        cursor.getDouble(13),
                        cursor.getString(5),
                        cursor.getString(3),
                        cursor.getDouble(4),
                        cursor.getString(2),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getInt(0),
                        cursor.getString(8)
//                                > 0,
//                        cursor.getInt(0),
//                        cursor.getString(10),
//                        cursor.getInt(11) > 0,
//                        cursor.getInt(12)
                ));
            } else {
                return new ArrayList<>();
            }
            cursor.moveToNext();
        }

        return output;
    }

    public boolean isAvailable(SQLiteDatabase db, int id) {
        @SuppressLint("Recycle")
        Cursor getFavorite = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE id_movie = ?", new String[]{String.valueOf(id)});
        return getFavorite.getCount() > 0;
    }
}
