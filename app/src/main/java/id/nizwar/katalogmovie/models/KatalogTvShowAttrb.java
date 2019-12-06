package id.nizwar.katalogmovie.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class KatalogTvShowAttrb extends Katalog implements Parcelable {

    KatalogTvShowAttrb(
<<<<<<< HEAD
=======
//            double popularity,
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
            String releaseDate,
            String overview,
            double voteAverage,
            String title,
            String originalTitle,
<<<<<<< HEAD
            String backdropPath,
            int id,
            String posterPath
    ) {
=======
//            String originalLanguage,
            String backdropPath,
//            boolean adult,
            int id,
            String posterPath
//            boolean video,
//            int voteCount
    ) {
//        this.popularity = popularity;
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.title = title;
        this.originalTitle = originalTitle;
<<<<<<< HEAD
        this.backdropPath = backdropPath;
        this.id = id;
        this.posterPath = posterPath;
        jenis = 0;
=======
//        this.originalLanguage = originalLanguage;
        this.backdropPath = backdropPath;
//        this.adult = adult;
        this.id = id;
        this.posterPath = posterPath;
//        this.video = video;
//        this.voteCount = voteCount;
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
    }


    public KatalogTvShowAttrb(JSONObject json) {
        try {
<<<<<<< HEAD
=======
//            this.popularity = json.getDouble("popularity");
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
            this.releaseDate = json.getString("first_air_date");
            this.overview = json.getString("overview");
            this.voteAverage = json.getDouble("vote_average");
            this.title = json.getString("name");
            this.originalTitle = json.getString("original_name");
<<<<<<< HEAD
            this.backdropPath = json.getString("backdrop_path");
            this.id = json.getInt("id");
            this.posterPath = json.getString("poster_path");
            jenis = 0;
=======
//            this.originalLanguage = json.getString("original_language");
            this.backdropPath = json.getString("backdrop_path");
            this.id = json.getInt("id");
            this.posterPath = json.getString("poster_path");
//            this.voteCount = json.getInt("view_count");
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
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
<<<<<<< HEAD
=======
//        dest.writeDouble(this.popularity);
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
        dest.writeString(this.releaseDate);
        dest.writeString(this.overview);
        dest.writeDouble(this.voteAverage);
        dest.writeString(this.title);
        dest.writeString(this.originalTitle);
<<<<<<< HEAD
        dest.writeString(this.backdropPath);
        dest.writeInt(this.id);
        dest.writeString(this.posterPath);
        dest.writeInt(this.jenis);
    }

    private KatalogTvShowAttrb(Parcel in) {
=======
//        dest.writeString(this.originalLanguage);
        dest.writeString(this.backdropPath);
//        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeInt(this.id);
        dest.writeString(this.posterPath);
//        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
//        dest.writeInt(this.voteCount);
    }

    private KatalogTvShowAttrb(Parcel in) {
//        this.popularity = in.readDouble();
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
        this.releaseDate = in.readString();
        this.overview = in.readString();
        this.voteAverage = in.readDouble();
        this.title = in.readString();
        this.originalTitle = in.readString();
<<<<<<< HEAD
        this.backdropPath = in.readString();
        this.id = in.readInt();
        this.posterPath = in.readString();
        this.jenis = in.readInt();
=======
//        this.originalLanguage = in.readString();
        this.backdropPath = in.readString();
//        this.adult = in.readByte() != 0;
        this.id = in.readInt();
        this.posterPath = in.readString();
//        this.video = in.readByte() != 0;
//        this.voteCount = in.readInt();
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
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
