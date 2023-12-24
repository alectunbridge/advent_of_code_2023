package advent_of_code_2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
    public static List<String> readInputLinesFromFile(String filename) {
        try (Stream<String> lines = Files.lines(Paths.get(ClassLoader.getSystemResource(filename).toURI()))) {
            return lines.collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
