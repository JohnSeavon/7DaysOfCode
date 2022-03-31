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

            //testing if the code prints rank 1 from moviesArray
            System.out.println(moviesArray[0]);

        }
        catch (RuntimeException e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
        catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
