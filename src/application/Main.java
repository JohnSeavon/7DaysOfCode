/*
#7DaysOfCode Challenge that consumes an API from IMDB
Create your API Key at: https://imdb-api.com/api
Joao Novaes - GitHub: github.com/JohnSeavon
 */
package application;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

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

            String json = response.body().substring(response.body().indexOf("[") + 1, response.body().lastIndexOf("]"));

            String topMovies = json.replace("},{", "},,{");

            String[] moviesArray = topMovies.split(",,");

            List<String> titles = new ArrayList<>();
            List<String> urlImages = new ArrayList<>();
            List<String> years = new ArrayList<>();
            List<String> imDbRatings = new ArrayList<>();

            for (String movies : moviesArray) {
                String[] attribute = movies.split("\",\"");
                titles.add(attribute[2].substring(attribute[2].indexOf(":\"") + 2));
                urlImages.add(attribute[5].substring(attribute[5].indexOf(":\"") + 2));
                years.add(attribute[4].substring(attribute[4].indexOf(":\"") + 2));
                imDbRatings.add(attribute[7].substring(attribute[7].indexOf(":\"") + 2));
            }

            // Testing if it prints the list of titles of all the 250 movies
            System.out.println();
            int count = 1;
            for (String x : titles) {
                System.out.println("#" + count + ": " + x);
                count += 1;
            }
            System.out.println();

            // Testing any position from the lists
            int position = 11;
            System.out.println("#" + position + ": "
                    + titles.get(position - 1)
                    + ", Year: " + years.get(position - 1)
                    + ", IMDb Rating: " + imDbRatings.get(position - 1)
                    + ", Image: " + urlImages.get(position - 1));

        }
        catch (RuntimeException e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
        catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
