package entities;

import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {

    public static void generate(PrintWriter writer, Movie movie, int position) {
        writer.println("""
                <head>
                	<meta charset=\"utf-8\">
                	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
                	<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">					
                </head>
                <body>
                """);

        String divTemplate =
                """
                <div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem;\">
                    <h4 class=\"card-header\">#%d %s</h4>
                    <div class=\"card-body\">
                        <img class=\"card-img\" src=\"%s\" alt=\"%s\">
                        <p class=\"card-text mt-2\">Rating: %s - Year: %s</p>
                    </div>
                </div>
                """;

        writer.println(String.format(divTemplate, position, movie.getTitle(), movie.getUrlImage(), movie.getTitle(), movie.getImDbRating(), movie.getYear()));

        writer.println("""
                </body>
                </html>
                """);
    }

    public static void generate(PrintWriter writer, List<Movie> movies) {
        writer.println("""
                <head>
                	<meta charset=\"utf-8\">
                	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
                	<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">					
                </head>
                <body>
                """);
        int cont = 1;
        for (Movie movie : movies) {
            String divTemplate =
                    """
                    <div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem;\">
                        <h4 class=\"card-header\">#%d %s</h4>
                        <div class=\"card-body\">
                            <img class=\"card-img\" src=\"%s\" alt=\"%s\">
                            <p class=\"card-text mt-2\">Rating: %s - Year: %s</p>
                        </div>
                    </div>
                    """;
            writer.println(String.format(divTemplate, cont, movie.getTitle(), movie.getUrlImage(), movie.getTitle(), movie.getImDbRating(), movie.getYear()));
            cont += 1;
        }
        writer.println("""
                </body>
                </html>
                """);
    }
}
