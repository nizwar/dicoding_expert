package id.nizwar.favoritemovie.models;

import android.os.Parcel;
import android.os.Parcelable;

public class KatalogTvShowAttrb extends Katalog implements Parcelable {

    KatalogTvShowAttrb(
            String releaseDate,
            String overview,
            double voteAverage,
            String title,
            String originalTitle,
            String backdropPath,
            int id,
            String posterPath
    ) {
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.title = title;
        this.originalTitle = originalTitle;
        this.backdropPath = backdropPath;
        this.id = id;
        this.posterPath = posterPath;
        jenis = 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.releaseDate);
        dest.writeString(this.overview);
        dest.writeDouble(this.voteAverage);
        dest.writeString(this.title);
        dest.writeString(this.originalTitle);
        dest.writeString(this.backdropPath);
        dest.writeInt(this.id);
        dest.writeString(this.posterPath);
        dest.writeInt(this.jenis);
    }

    private KatalogTvShowAttrb(Parcel in) {
        this.releaseDate = in.readString();
        this.overview = in.readString();
        this.voteAverage = in.readDouble();
        this.title = in.readString();
        this.originalTitle = in.readString();
        this.backdropPath = in.readString();
        this.id = in.readInt();
        this.posterPath = in.readString();
        this.jenis = in.readInt();
    }

    public static final Creator<KatalogTvShowAttrb> CREATOR = new Creator<KatalogTvShowAttrb>() {
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
