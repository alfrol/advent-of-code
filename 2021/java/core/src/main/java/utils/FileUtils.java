package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

public final class FileUtils {

    public static Optional<String> readFile(Class<?> clazz, String name) {
        try (InputStream inputStream = clazz.getResourceAsStream(name)) {
            return Optional.of(readFromInputStream(inputStream));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        }

        return builder.toString().trim();
    }
}
