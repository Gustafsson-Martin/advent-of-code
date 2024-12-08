package aoc;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Util {
    private static Path getResourcePath(String resourcePath) {
        try {
            URL url = Util.class.getResource(resourcePath);
            return Paths.get(url.toURI());
        } catch (Exception e) {
            throw new RuntimeException("Error loading resource " + resourcePath, e);
        }
    }
    public static List<String> parseInputResource(String resourcePath) {
        List<String> lines = new ArrayList<>(List.of());
        try (Stream<String> stream = Files.lines(getResourcePath(resourcePath))) {
            stream.forEach(lines::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }
}
