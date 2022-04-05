/*
#7DaysOfCode Challenge that consumes an API from IMDb
Create your API Key at: https://imdb-api.com/api
Joao Novaes - GitHub: github.com/JohnSeavon
 */
package application;

import entities.HTMLGenerator;
import entities.ImdbApiClient;
import entities.ImdbMovieJsonParser;
import services.APIClient;
import services.Content;
import services.JsonParser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            String apiKey = "yourAPIKey";

            APIClient apiClient = new ImdbApiClient(apiKey);
            String json = apiClient.getBody();

            JsonParser parser = new ImdbMovieJsonParser();
            List<? extends Content> movies = parser.parse(json);

            PrintWriter writer = new PrintWriter("index.html");
            HTMLGenerator.generate(writer, movies);
            writer.close();
        }
        catch (RuntimeException e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Done!");
    }
}
