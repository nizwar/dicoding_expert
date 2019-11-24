package id.nizwar.katalogmovie.models;

import android.os.Parcel;
import android.os.Parcelable;

public class KatalogAttrb implements Parcelable {
    private int posterID, score;
    private String title, overview, rilis;


    public KatalogAttrb(int poster, int score, String title, String overview, String rilis) {
        this.posterID = poster;
        this.score = score;
        this.title = title;
        this.overview = overview;
        this.rilis = rilis;
    }

    public int getScore() {
        return score;
    }

    public String getRilis() {
        return rilis;
    }


    private KatalogAttrb(Parcel in) {
        posterID = in.readInt();
        score = in.readInt();
        title = in.readString();
        overview = in.readString();
        rilis = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(posterID);
        dest.writeInt(score);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(rilis);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<KatalogAttrb> CREATOR = new Creator<KatalogAttrb>() {
        @Override
        public KatalogAttrb createFromParcel(Parcel in) {
            return new KatalogAttrb(in);
        }

        @Override
        public KatalogAttrb[] newArray(int size) {
            return new KatalogAttrb[size];
        }
    };

    public int getPosterID() {
        return posterID;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

}
