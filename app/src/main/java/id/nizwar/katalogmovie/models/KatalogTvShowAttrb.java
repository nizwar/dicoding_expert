package id.nizwar.katalogmovie.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class KatalogTvShowAttrb extends Katalog implements Parcelable {

    public KatalogTvShowAttrb(
            double popularity,
            String releaseDate,
            String overview,
            double voteAverage,
            String title,
            String originalTitle,
            String originalLanguage,
            String backdropPath,
            boolean adult,
            int id,
            String posterPath,
            boolean video,
            int voteCount) {
        this.popularity = popularity;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.title = title;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.backdropPath = backdropPath;
        this.adult = adult;
        this.id = id;
        this.posterPath = posterPath;
        this.video = video;
        this.voteCount = voteCount;
    }


    public KatalogTvShowAttrb(JSONObject json) {
        try {
            this.popularity = json.getDouble("popularity");
            this.releaseDate = json.getString("first_air_date");
            this.overview = json.getString("overview");
            this.voteAverage = json.getDouble("vote_average");
            this.title = json.getString("name");
            this.originalTitle = json.getString("original_name");
            this.originalLanguage = json.getString("original_language");
            this.backdropPath = json.getString("backdrop_path");
            this.id = json.getInt("id");
            this.posterPath = json.getString("poster_path");
            this.voteCount = json.getInt("view_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.popularity);
        dest.writeString(this.releaseDate);
        dest.writeString(this.overview);
        dest.writeDouble(this.voteAverage);
        dest.writeString(this.title);
        dest.writeString(this.originalTitle);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.backdropPath);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeInt(this.id);
        dest.writeString(this.posterPath);
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
        dest.writeInt(this.voteCount);
    }

    private KatalogTvShowAttrb(Parcel in) {
        this.popularity = in.readDouble();
        this.releaseDate = in.readString();
        this.overview = in.readString();
        this.voteAverage = in.readDouble();
        this.title = in.readString();
        this.originalTitle = in.readString();
        this.originalLanguage = in.readString();
        this.backdropPath = in.readString();
        this.adult = in.readByte() != 0;
        this.id = in.readInt();
        this.posterPath = in.readString();
        this.video = in.readByte() != 0;
        this.voteCount = in.readInt();
    }

    public static final Parcelable.Creator<KatalogTvShowAttrb> CREATOR = new Parcelable.Creator<KatalogTvShowAttrb>() {
        @Override
        public KatalogTvShowAttrb createFromParcel(Parcel source) {
            return new KatalogTvShowAttrb(source);
        }

        @Override
        public KatalogTvShowAttrb[] newArray(int size) {
            return new KatalogTvShowAttrb[size];
        }
    };
}
