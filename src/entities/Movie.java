package entities;

public class Movie {
    private String title;
    private String urlImage;
    private String year;
    private String imDbRating;

    public Movie(String title, String urlImage, String year, String imDbRating) {
        this.title = title;
        this.urlImage = urlImage;
        this.year = year;
        this.imDbRating = imDbRating;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getYear() {
        return year;
    }


    public String getImDbRating() {
        return imDbRating;
    }

    @Override
    public String toString() {
        return title + ", Year: " + year + ", IMDb Rating: " + imDbRating + ", Image: " + urlImage;
    }
}
