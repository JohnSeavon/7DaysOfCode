/*
#7DaysOfCode Challenge that consumes an API from IMDb
Create your API Key at: https://imdb-api.com/api
Joao Novaes - GitHub: github.com/JohnSeavon
 */
package application;

import entities.Movie;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        try {
            String apiKey = "yourAPIKey";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newBuilder().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            String[] moviesArray = parseJsonMovies(json);

            List<Movie> movies = new ArrayList<>();

            for (String m : moviesArray) {
                String title = parseAttribute(m, 2);
                String urlImage = parseAttribute(m, 5);
                String year = parseAttribute(m, 4);
                String imDbRating = parseAttribute(m, 7);
                movies.add(new Movie(title, urlImage, year, imDbRating));
            }

            // Testing if it prints the list of titles of all the 250 movies
            System.out.println();
            int count = 1;
            for (Movie m : movies) {
                System.out.println("#" + count + ": " + m.getTitle());
                count += 1;
            }
            System.out.println();

            // Testing any position from the lists
            int position = 11;
            System.out.println("#" + position + ": " + movies.get(position - 1));

        }
        catch (RuntimeException e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
        catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String [] parseJsonMovies(String json) {
        Matcher matcher = Pattern.compile(".*\\[(.*)\\].*").matcher(json);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("no match in " + json);
        }

        json = matcher.group(1);

        String topMovies = json.replace("},{", "},,{");

        return topMovies.split(",,");
    }

    private static String parseAttribute(String movie, int position) {
        String[] attributes = movie.split("\",\"");
        return attributes[position].substring(attributes[position].indexOf(":\"") + 2);
    }
}
