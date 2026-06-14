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
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExcelFileFinder {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final FileScanner fileScanner;

    public ExcelFileFinder() {
        this(new FileScanner());
    }

    ExcelFileFinder(FileScanner fileScanner) {
        this.fileScanner = Objects.requireNonNull(fileScanner);
    }

    public List<Task> run(ParamsSet params) {
        LocalDate dateFrom =
                LocalDate.parse(params.getFrom(), FORMATTER);

        LocalDate dateTo =
                LocalDate.parse(params.getTo(), FORMATTER);

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

        List<File> files = findExcelFiles(params.getPath());

        List<Task> allTasks = new ArrayList<>();

        for (File file : files) {
            allTasks.addAll(
                    fileScanner.readExcelFile(file.getAbsolutePath())
            );
        }

        return allTasks.stream()
                .filter(task ->
                        isTaskInDateRange(task, dateFrom, dateTo)
                )
                .collect(Collectors.toList());
    }

    private List<File> findExcelFiles(String inputPath) {
        Path path = Paths.get(inputPath);

        if (!Files.exists(path)) {
            throw new IllegalArgumentException(
                    "The specified path does not exist: " + inputPath
            );
        }

        if (Files.isRegularFile(path)) {
            if (!isExcelFile(path)) {
                throw new IllegalArgumentException(
                        "The specified file is not an Excel file: "
                                + inputPath
                );
            }

            return List.of(path.toFile());
        }

        if (Files.isDirectory(path)) {
            try (Stream<Path> paths = Files.walk(path)) {
                return paths
                        .filter(Files::isRegularFile)
                        .filter(this::isExcelFile)
                        .map(Path::toFile)
                        .collect(Collectors.toList());

            } catch (IOException exception) {
                throw new IllegalStateException(
                        "Failed to search directory: " + inputPath,
                        exception
                );
            }
        }

        throw new IllegalArgumentException(
                "The specified path is neither a file nor a directory: "
                        + inputPath
        );
    }

    private boolean isExcelFile(Path path) {
        String fileName = path.getFileName()
                .toString()
                .toLowerCase(Locale.ROOT);

        return !fileName.startsWith("~$")
                && (
                fileName.endsWith(".xlsx")
                        || fileName.endsWith(".xls")
        );
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