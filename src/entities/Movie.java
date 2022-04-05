package entities;

import services.Content;

public record Movie(String title, String urlImage, String year, String rating) implements Content {
}
