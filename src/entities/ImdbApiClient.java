package entities;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImdbApiClient {
    private String apiKey;

    public ImdbApiClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getBody() {

        try {
            HttpClient client = HttpClient.newBuilder().build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey)).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        }
        catch (IOException | InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
