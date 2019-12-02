package id.nizwar.katalogmovie.models;

public class Katalog {
    private int posterID, score;
    private String title, overview, rilis;

    public int getPosterID() {
        return posterID;
    }

    void setPosterID(int posterID) {
        this.posterID = posterID;
    }

    public int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRilis() {
        return rilis;
    }

    void setRilis(String rilis) {
        this.rilis = rilis;
    }
}
