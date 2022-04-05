package services;

import java.util.List;

public interface JsonParser {
    List<? extends Content> parse(String json);
}
