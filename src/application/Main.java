/*
#7DaysOfCode Challenge that consumes an API from IMDB
The code executes an HTTP request and prints the response body to the console
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
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/<apiKey>"))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newBuilder().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());
        }
        catch (RuntimeException e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
        catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}