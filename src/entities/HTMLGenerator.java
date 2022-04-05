package entities;

import services.Content;

import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {

    public static void generate(PrintWriter writer, List<? extends Content> contentList) {
        writer.println("""
                <head>
                	<meta charset="utf-8">
                	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
                	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
                	    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
                </head>
                <body>
                	<div class="container">
                		<div class="row">
                """);
        int count = 1;
        for (Content content : contentList) {
            String divTemplate =
                    """
                                <div class="col-sx-12 col-sm-4 col-md-3 col-lg-3">
                                    <div class="card text-white bg-dark mb-2" style="max-width: 18rem; margin-top: 5px;">
                                            <img class="card-img" src="%s" alt="%s">
                                        <div class="card-body">
                                            <h6 class="card-title">#%d %s</h6>
                                            <p class="card-text mt-2">Rating: %s - Year: %s</p>
                                        </div>
                                    </div>
                                </div>
                    """;
            writer.println(String.format(divTemplate, content.urlImage(), content.title(), count, content.title(), content.rating(), content.year()));
            count++;
        }
        writer.println("""
                		</div>
                	</div>
                </body>
                </html>
                """);
    }
}
