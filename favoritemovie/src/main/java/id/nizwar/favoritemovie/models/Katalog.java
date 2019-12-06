package id.nizwar.favoritemovie.models;

import android.database.Cursor;

import java.util.ArrayList;

public abstract class Katalog {
    String releaseDate;
    String overview;
    double voteAverage;
    String title;
    String originalTitle;
    String backdropPath;
    int id;
    int jenis;
    String posterPath;

    public int getJenis() {
        return jenis;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public int getId() {
        return id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public static ArrayList<Katalog> cursorToArrayKatalog(Cursor katalogCursor) {
        ArrayList<Katalog> listKatalog = new ArrayList<>();
        while (katalogCursor.moveToNext()) {
            listKatalog.add(new KatalogMovieAttrb(
                    katalogCursor.getString(5),
                    katalogCursor.getString(3),
                    katalogCursor.getDouble(4),
                    katalogCursor.getString(2),
                    katalogCursor.getString(6),
                    katalogCursor.getString(7),
                    katalogCursor.getInt(0),
                    katalogCursor.getString(8)
            ));
        }

        return listKatalog;
    }

//    public static Katalog cursorToKatalog(Cursor katalogCursor) {
//        katalogCursor.moveToFirst();
//        return new KatalogMovieAttrb(
//                katalogCursor.getString(5),
//                katalogCursor.getString(3),
//                katalogCursor.getDouble(4),
//                katalogCursor.getString(2),
//                katalogCursor.getString(6),
//                katalogCursor.getString(7),
//                katalogCursor.getInt(0),
//                katalogCursor.getString(8)
//        );
//    }
}
