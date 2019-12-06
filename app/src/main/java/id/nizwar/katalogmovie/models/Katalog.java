package id.nizwar.katalogmovie.models;

import android.database.Cursor;

import java.util.ArrayList;

public abstract class Katalog {
<<<<<<< HEAD
=======
//    double popularity;
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
    String releaseDate;
    String overview;
    double voteAverage;
    String title;
    String originalTitle;
<<<<<<< HEAD
    String backdropPath;
=======
//    String originalLanguage;
    String backdropPath;
//    boolean adult;
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
    int id;
    int jenis;
    String posterPath;
<<<<<<< HEAD

    public int getJenis() {
        return jenis;
    }
=======
//    boolean video;
//    int voteCount;

//    public double getPopularity() {
//        return popularity;
//    }
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76

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

<<<<<<< HEAD
=======
//    public String getOriginalLanguage() {
//        return originalLanguage;
//    }

>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
    public String getBackdropPath() {
        return backdropPath;
    }

<<<<<<< HEAD
=======
//    public boolean getAdult() {
//        return adult;
//    }

>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
    public int getId() {
        return id;
    }

    public String getPosterPath() {
        return posterPath;
    }

<<<<<<< HEAD
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
=======
//    public boolean getVideo() {
//        return video;
//    }
//
//    public int getVoteCount() {
//        return voteCount;
//    }
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76

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
