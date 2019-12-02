package id.nizwar.katalogmovie.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import id.nizwar.katalogmovie.R;

public class KatalogMovieAttrb extends Katalog implements Parcelable {

    private KatalogMovieAttrb(int poster, int score, String title, String overview, String rilis) {
        this.setPosterID(poster);
        this.setScore(score);
        this.setTitle(title);
        this.setOverview(overview);
        this.setRilis(rilis);
    }

    @SuppressLint("Recycle")
    public static ArrayList<Katalog> dummy(Context context) {
        ArrayList<Katalog> output = new ArrayList<>();
        Resources res = context.getResources();
        for (int i = 0; i < res.getStringArray(R.array.movie_title).length; i++) {
            output.add(
                    new KatalogMovieAttrb(
                            res.obtainTypedArray(R.array.movie_poster).getResourceId(i, -1),
                            res.getIntArray(R.array.movie_score)[i],
                            res.getStringArray(R.array.movie_title)[i],
                            res.getStringArray(R.array.movie_desc)[i],
                            res.getStringArray(R.array.movie_release)[i]
                    )
            );
        }
        return output;
    }


    private KatalogMovieAttrb(Parcel in) {
        this.setPosterID(in.readInt());
        this.setScore(in.readInt());
        this.setTitle(in.readString());
        this.setOverview(in.readString());
        this.setRilis(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.getPosterID());
        dest.writeInt(this.getScore());
        dest.writeString(this.getTitle());
        dest.writeString(this.getOverview());
        dest.writeString(this.getRilis());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<KatalogMovieAttrb> CREATOR = new Creator<KatalogMovieAttrb>() {
        @Override
        public KatalogMovieAttrb createFromParcel(Parcel in) {
            return new KatalogMovieAttrb(in);
        }

        @Override
        public KatalogMovieAttrb[] newArray(int size) {
            return new KatalogMovieAttrb[size];
        }
    };

}
