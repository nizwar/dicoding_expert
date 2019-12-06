package id.nizwar.katalogmovie.models;

<<<<<<< HEAD
=======
import android.annotation.SuppressLint;
import android.content.ContentValues;
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQFavorite extends SQLiteOpenHelper {
    private static final String DB_FAVORITE = "katalog";
<<<<<<< HEAD
    static final String TBL_NAME = "favorite";
=======
    private static final String TBL_NAME = "favorite";
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76


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
<<<<<<< HEAD
                        + "backdrop_path TEXT NOT NULL, "               //Index 7
                        + "poster_path TEXT NOT NULL "                 //Index 8
=======
//                        + "original_language TEXT NOT NULL, "           //Index NA
                        + "backdrop_path TEXT NOT NULL, "               //Index 7
//                        + "adult INTEGER NOT NULL, "                    //Index NA
                        + "poster_path TEXT NOT NULL "                 //Index 8
//                        + "video INTEGER NOT NULL, "                    //Index NA
//                        + "vote_count INTEGER NOT NULL, "               //Index NA
//                        + "popularity REAL NOT NULL"                    //Index NA
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
                        + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

<<<<<<< HEAD
//    public void setFavorite(Katalog katalog, int jenis) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("id_movie", katalog.id);
//        contentValues.put("jenis", jenis);
//        contentValues.put("title", katalog.title);
//        contentValues.put("overview", katalog.overview);
//        contentValues.put("vote_average", katalog.voteAverage);
//        contentValues.put("release_date", katalog.releaseDate);
//        contentValues.put("original_title", katalog.originalTitle);
//        contentValues.put("backdrop_path", katalog.backdropPath);
//        contentValues.put("poster_path", katalog.posterPath);
//        if (isAvailable(db, katalog.id)) return;
//
//        db.insert(TBL_NAME, null, contentValues);
//        context.getContentResolver().notifyChange(CONTENT_URI, null);
//    }
//
//    public void deleteFavorite(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        if (!isAvailable(db, id)) return;
//        db.delete(TBL_NAME, "id_movie = ?", new String[]{String.valueOf(id)});
//
//        context.getContentResolver().notifyChange(CONTENT_URI, null);
//    }

    public ArrayList<Katalog> getFavorite(int jenis, String cari) {
        ArrayList<Katalog> output = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        if (jenis >= 0) {
            if (cari != null) {
                cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE jenis=? AND title LIKE ?", new String[]{String.valueOf(jenis), "%" + cari + "%"});
            } else {
                cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE jenis=?", new String[]{String.valueOf(jenis)});
            }
        } else {
            if (cari != null) {
                cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE title LIKE ?", new String[]{"%" + cari + "%"});
            } else {
                cursor = db.rawQuery("SELECT * FROM " + TBL_NAME, new String[]{});
            }
        }
        if (cursor.getCount() == 0) return new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            jenis = cursor.getInt(1);
            if (jenis == 0) {
                output.add(new KatalogMovieAttrb(
=======
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
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
                        cursor.getString(5),
                        cursor.getString(3),
                        cursor.getDouble(4),
                        cursor.getString(2),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getInt(0),
                        cursor.getString(8)
<<<<<<< HEAD
                ));
            } else if (jenis == 1) {
                output.add(new KatalogTvShowAttrb(
=======
//                                > 0,
//                        cursor.getInt(0),
//                        cursor.getString(10),
//                        cursor.getInt(11) > 0,
//                        cursor.getInt(12)
                ));
            } else if (jenis == 1) {
                output.add(new KatalogTvShowAttrb(
//                        cursor.getDouble(13),
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
                        cursor.getString(5),
                        cursor.getString(3),
                        cursor.getDouble(4),
                        cursor.getString(2),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getInt(0),
                        cursor.getString(8)
<<<<<<< HEAD
=======
//                                > 0,
//                        cursor.getInt(0),
//                        cursor.getString(10),
//                        cursor.getInt(11) > 0,
//                        cursor.getInt(12)
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
                ));
            } else {
                return new ArrayList<>();
            }
            cursor.moveToNext();
        }
<<<<<<< HEAD
        cursor.close();
        return output;
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
=======

        return output;
    }

    public boolean isAvailable(SQLiteDatabase db, int id) {
        @SuppressLint("Recycle")
        Cursor getFavorite = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE id_movie = ?", new String[]{String.valueOf(id)});
        return getFavorite.getCount() > 0;
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
    }
}
