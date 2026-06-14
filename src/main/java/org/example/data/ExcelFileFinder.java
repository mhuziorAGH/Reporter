package org.example.data;

import org.example.cli.ParamsSet;
import org.example.domain.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExcelFileFinder {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<Task> run(ParamsSet params) {
        FileScanner fileScanner = new FileScanner();

        LocalDate dateFrom = LocalDate.parse(params.getFrom(), FORMATTER);
        LocalDate dateTo = LocalDate.parse(params.getTo(), FORMATTER);

        if (dateFrom.isAfter(dateTo)) {
            throw new IllegalArgumentException(
                    "The start date cannot be later than the end date"
            );
        }

        System.out.printf(
                "Processing: %s | %s → %s | Report: %s%n",
                params.getPath(),
                dateFrom,
                dateTo,
                params.getWhichReport()
        );

        List<File> files = findExcelFilesRecursively(params.getPath());

        List<Task> allTasks = new ArrayList<>();

        for (File file : files) {
            System.out.printf(
                    "  [READING] %s%n",
                    file.getAbsolutePath()
            );

            List<Task> tasksFromFile =
                    fileScanner.readExcelFile(file.getAbsolutePath());

            allTasks.addAll(tasksFromFile);
        }

        return allTasks.stream()
                .filter(task -> isTaskInDateRange(task, dateFrom, dateTo))
                .collect(Collectors.toList());
    }

    private List<File> findExcelFilesRecursively(String mainFolder) {
        Path rootPath = Paths.get(mainFolder);

        if (!Files.isDirectory(rootPath)) {
            throw new IllegalArgumentException(
                    "The specified path is not a directory: " + mainFolder
            );
        }

        try (Stream<Path> paths = Files.walk(rootPath)) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(this::isExcelFile)
                    .peek(path -> System.out.printf(
                            "  [FOUND] %s%n",
                            path.toAbsolutePath()
                    ))
                    .map(Path::toFile)
                    .collect(Collectors.toList());

        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Failed to search directory: " + mainFolder,
                    exception
            );
        }
    }

    private boolean isExcelFile(Path path) {
        String fileName = path.getFileName()
                .toString()
                .toLowerCase(Locale.ROOT);

        return !fileName.startsWith("~$")
                && (fileName.endsWith(".xlsx")
                || fileName.endsWith(".xls"));
    }

    private boolean isTaskInDateRange(
            Task task,
            LocalDate dateFrom,
            LocalDate dateTo
    ) {
        LocalDate taskDate = task.getStartDate();

        if (taskDate == null) {
            return false;
        }

        return !taskDate.isBefore(dateFrom)
                && !taskDate.isAfter(dateTo);
    }
}