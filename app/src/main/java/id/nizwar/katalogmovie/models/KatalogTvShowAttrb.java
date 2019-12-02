package id.nizwar.katalogmovie.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import id.nizwar.katalogmovie.R;

public class KatalogTvShowAttrb extends Katalog implements Parcelable {

    private KatalogTvShowAttrb(int poster, int score, String title, String overview, String rilis) {
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
        for (int i = 0; i < res.getStringArray(R.array.tvshow_title).length; i++) {
            output.add(
                    new KatalogTvShowAttrb(
                            res.obtainTypedArray(R.array.tvshow_poster).getResourceId(i, -1),
                            res.getIntArray(R.array.tvshow_score)[i],
                            res.getStringArray(R.array.tvshow_title)[i],
                            res.getStringArray(R.array.tvshow_desc)[i],
                            res.getStringArray(R.array.tvshow_release)[i]
                    )
            );
        }
        return output;
    }


    private KatalogTvShowAttrb(Parcel in) {
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

    public static final Creator<KatalogTvShowAttrb> CREATOR = new Creator<KatalogTvShowAttrb>() {
        @Override
        public KatalogTvShowAttrb createFromParcel(Parcel in) {
            return new KatalogTvShowAttrb(in);
        }

        @Override
        public KatalogTvShowAttrb[] newArray(int size) {
            return new KatalogTvShowAttrb[size];
        }
    };

}
