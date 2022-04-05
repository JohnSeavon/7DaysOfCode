package entities;

import services.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImdbMovieJsonParser implements JsonParser {

    public List<Movie> parse(String json) {
        Matcher matcher = Pattern.compile(".*\\[(.*)].*").matcher(json);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("no match in " + json);
        }

        json = matcher.group(1);

        String topMovies = json.replace("},{", "},,{");

        String[] moviesArray = topMovies.split(",,");

        List<Movie> movies = new ArrayList<>();

        for (String m : moviesArray) {
            String title = parseAttribute(m, 2);
            String urlImage = parseAttribute(m, 5);
            String year = parseAttribute(m, 4);
            String imDbRating = parseAttribute(m, 7);
            movies.add(new Movie(title, urlImage, year, imDbRating));
        }

        return movies;
    }

    private static String parseAttribute(String movie, int position) {
        String[] attributes = movie.split("\",\"");
        return attributes[position].substring(attributes[position].indexOf(":\"") + 2);
    }
}
