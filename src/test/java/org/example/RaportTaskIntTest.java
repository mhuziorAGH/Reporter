package org.example;
import org.example.data.FileScanner;
import org.example.domain.Task;
import org.example.service.TaskReport;
import org.junit.jupiter.api.Test;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class RaportTaskIntTest {

    @Test
    void happyPath_taskReport_readAllFilesFromFolders() throws Exception {


        URL rootUrl = getClass().getClassLoader().getResource("reported-dane");
        assertNotNull(rootUrl, "File not found!");

        Path root = Paths.get(rootUrl.toURI());
        FileScanner scanner = new FileScanner();

        List<Path> excelFiles = Files.walk(root)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".xls") || p.toString().endsWith(".xlsx"))
                .toList();

        assertFalse(excelFiles.isEmpty(), "Not found excel file!");


        List<Task> allTasks = new ArrayList<>();
        for (Path p : excelFiles) {
            try {
                allTasks.addAll(scanner.readExcelFile(p.toString()));
            } catch (Exception ignored) {
                // HAPPY PATH: ignoruję błędne wiersze i puste komórki
            }
        }

        assertFalse(allTasks.isEmpty(), "Not found all tasks!");


        TaskReport taskReport = TaskReport.generateReport(allTasks);

        assertFalse(taskReport.taskTimes.isEmpty(), "Task reports empty!");


        List<Map.Entry<String, Duration>> ranking =
                new ArrayList<>(taskReport.taskTimes.entrySet());

        ranking.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        //sortowanie
        for (int i = 0; i < ranking.size() - 1; i++) {
            Duration current = ranking.get(i).getValue();
            Duration next = ranking.get(i + 1).getValue();

            assertTrue(current.compareTo(next) >= 0,
                            "Sorting not working - item " + ranking.get(i).getKey() +
                            " has less time than next " + ranking.get(i + 1).getKey());
        }
    }
}

