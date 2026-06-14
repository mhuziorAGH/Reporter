package org.example.data;

import org.example.cli.ParamsSet;
import org.example.domain.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExcelFileFinderTestDirectoriesDates {

    @Mock
    private FileScanner fileScanner;

    @Mock
    private ParamsSet params;

    @TempDir
    Path tempDirectory;

    private ExcelFileFinder finder;

    @BeforeEach
    void setUp() {
        finder = new ExcelFileFinder(fileScanner);
    }

    @Test
    void shouldSearchDirectoryRecursivelyAndFilterTasksByDate()
            throws IOException {

        Path nestedDirectory = Files.createDirectories(
                tempDirectory.resolve("department/reports/2025")
        );

        Path excelFile = Files.createFile(
                nestedDirectory.resolve("tasks.xlsx")
        );

        Path textFile = Files.createFile(
                nestedDirectory.resolve("notes.txt")
        );

        Path temporaryExcelFile = Files.createFile(
                nestedDirectory.resolve("~$tasks.xlsx")
        );

        configureParams(
                tempDirectory,
                "2025-01-01",
                "2025-01-31"
        );

        Task beforeRange = taskWithDate("2024-12-31");
        Task firstDay = taskWithDate("2025-01-01");
        Task middleDay = taskWithDate("2025-01-15");
        Task lastDay = taskWithDate("2025-01-31");
        Task afterRange = taskWithDate("2025-02-01");
        Task withoutDate = taskWithoutDate();

        when(fileScanner.readExcelFile(
                excelFile.toFile().getAbsolutePath()
        )).thenReturn(List.of(
                beforeRange,
                firstDay,
                middleDay,
                lastDay,
                afterRange,
                withoutDate
        ));

        List<Task> result = finder.run(params);

        assertEquals(
                List.of(firstDay, middleDay, lastDay),
                result
        );

        verify(fileScanner).readExcelFile(
                excelFile.toFile().getAbsolutePath()
        );

        verify(fileScanner, never()).readExcelFile(
                textFile.toFile().getAbsolutePath()
        );

        verify(fileScanner, never()).readExcelFile(
                temporaryExcelFile.toFile().getAbsolutePath()
        );

        verifyNoMoreInteractions(fileScanner);
    }

    @Test
    void shouldReadOnlySpecifiedExcelFile() throws IOException {
        Path selectedFile = Files.createFile(
                tempDirectory.resolve("selected.xlsx")
        );

        Path otherFile = Files.createFile(
                tempDirectory.resolve("other.xlsx")
        );

        configureParams(
                selectedFile,
                "2025-01-01",
                "2025-12-31"
        );

        Task task = taskWithDate("2025-06-15");

        when(fileScanner.readExcelFile(
                selectedFile.toFile().getAbsolutePath()
        )).thenReturn(List.of(task));

        List<Task> result = finder.run(params);

        assertEquals(List.of(task), result);

        verify(fileScanner).readExcelFile(
                selectedFile.toFile().getAbsolutePath()
        );

        verify(fileScanner, never()).readExcelFile(
                otherFile.toFile().getAbsolutePath()
        );

        verifyNoMoreInteractions(fileScanner);
    }

    @Test
    void shouldAcceptXlsFile() throws IOException {
        Path excelFile = Files.createFile(
                tempDirectory.resolve("legacy-report.xls")
        );

        configureParams(
                excelFile,
                "2025-01-01",
                "2025-12-31"
        );

        Task task = taskWithDate("2025-03-20");

        when(fileScanner.readExcelFile(
                excelFile.toFile().getAbsolutePath()
        )).thenReturn(List.of(task));

        List<Task> result = finder.run(params);

        assertEquals(List.of(task), result);

        verify(fileScanner).readExcelFile(
                excelFile.toFile().getAbsolutePath()
        );
    }

    @Test
    void shouldIncludeStartAndEndDates() throws IOException {
        Path excelFile = Files.createFile(
                tempDirectory.resolve("dates.xlsx")
        );

        configureParams(
                excelFile,
                "2025-04-01",
                "2025-04-30"
        );

        Task startDateTask = taskWithDate("2025-04-01");
        Task endDateTask = taskWithDate("2025-04-30");

        when(fileScanner.readExcelFile(
                excelFile.toFile().getAbsolutePath()
        )).thenReturn(List.of(
                startDateTask,
                endDateTask
        ));

        List<Task> result = finder.run(params);

        assertEquals(
                List.of(startDateTask, endDateTask),
                result
        );
    }

    @Test
    void shouldRejectDateRangeWhenStartDateIsAfterEndDate() {
        when(params.getFrom()).thenReturn("2025-12-31");
        when(params.getTo()).thenReturn("2025-01-01");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> finder.run(params)
        );

        assertEquals(
                "The start date cannot be later than the end date",
                exception.getMessage()
        );

        verify(params).getFrom();
        verify(params).getTo();

        verifyNoInteractions(fileScanner);
    }

    @Test
    void shouldRejectPathThatDoesNotExist() {
        Path missingPath = tempDirectory.resolve("missing");

        configureParams(
                missingPath,
                "2025-01-01",
                "2025-12-31"
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> finder.run(params)
        );

        assertTrue(
                exception.getMessage()
                        .contains("The specified path does not exist")
        );

        verifyNoInteractions(fileScanner);
    }

    @Test
    void shouldRejectNonExcelFile() throws IOException {
        Path textFile = Files.createFile(
                tempDirectory.resolve("report.txt")
        );

        configureParams(
                textFile,
                "2025-01-01",
                "2025-12-31"
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> finder.run(params)
        );

        assertTrue(
                exception.getMessage()
                        .contains("is not an Excel file")
        );

        verifyNoInteractions(fileScanner);
    }

    private void configureParams(
            Path path,
            String dateFrom,
            String dateTo
    ) {
        when(params.getPath()).thenReturn(path.toString());
        when(params.getFrom()).thenReturn(dateFrom);
        when(params.getTo()).thenReturn(dateTo);
    }

    private Task taskWithDate(String date) {
        Task task = org.mockito.Mockito.mock(Task.class);

        when(task.getStartDate()).thenReturn(
                LocalDate.parse(date)
        );

        return task;
    }

    private Task taskWithoutDate() {
        Task task = org.mockito.Mockito.mock(Task.class);

        when(task.getStartDate()).thenReturn(null);

        return task;
    }
}