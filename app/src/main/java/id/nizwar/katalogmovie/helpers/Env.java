package id.nizwar.katalogmovie.helpers;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import id.nizwar.katalogmovie.BuildConfig;

public class Env {
    public static final String IMG_URL780 = "https://image.tmdb.org/t/p/w780";
    static public final String IMG_URL500 = "https://image.tmdb.org/t/p/w780";

    public static String GET_RELEASE_URL(Date date) {
        @SuppressLint("SimpleDateFormat") String waktu = new SimpleDateFormat("yyyy-MM-dd").format(date);
        Log.e("ASASAS",waktu );
        return BASE_URL("movie", null) + "&primary_release_date.gte=" + waktu + "&primary_release_date.lte=" + waktu;
    }

    static public String BASE_URL(String jenis, String cari) {
        String output;
        if (cari != null) {
            output = "https://api.themoviedb.org/3/search/" +
                    jenis +
                    "?api_key=" +
                    BuildConfig.TMDB_API_KEY +
                    "&language=en-US&query=" + cari;
        } else {
            output = "https://api.themoviedb.org/3/discover/" +
                    jenis +
                    "?api_key=" +
                    BuildConfig.TMDB_API_KEY +
                    "&language=en-US";
        }
        return output;
    }
}
