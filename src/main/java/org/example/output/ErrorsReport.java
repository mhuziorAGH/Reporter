package org.example.output;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ErrorsReport{

    public static void saveToFile(List<String> lines) {
        String fileName = "ErrorLog";
        try {
            Files.write(Path.of(fileName), lines);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file: " + fileName, e);
        }
    }
}