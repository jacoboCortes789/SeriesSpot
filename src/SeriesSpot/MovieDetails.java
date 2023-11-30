package SeriesSpot;

import java.util.List;

public class MovieDetails {
    private String title;
    private String overview;
    private String releaseDate;
    private double voteAverage;
    private int voteCount;
    private String posterPath;
    private List<String> genres; // Nuevos campos
    private String director;
    private List<String> cast;
    private int runtime;

    public MovieDetails(String title, String overview, String releaseDate, double voteAverage, int voteCount, String posterPath) {
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.posterPath = posterPath;
    }

    // Getters y setters para los nuevos campos
    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    // Resto de los getters y setters...

    // Getter para obtener el título
    public String getTitle() {
        return title;
    }

    // Setter para establecer el título
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter para obtener la descripción (overview)
    public String getOverview() {
        return overview;
    }

    // Setter para establecer la descripción (overview)
    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}

