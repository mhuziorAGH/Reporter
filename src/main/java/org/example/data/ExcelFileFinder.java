package org.example.data;

import org.example.cli.CLI;
import org.example.cli.ParamsSet;

import java.io.File;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExcelFileFinder {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

    public void run(List<ParamsSet> paramsSets) {
        FileScanner fileScanner = new FileScanner();
        for (ParamsSet params : paramsSets) {
            System.out.printf("Przetwarzam: %s | %s → %s | Raport: %s%n",
                    params.getPath(),
                    params.getFrom(),
                    params.getTo(),
                    params.getWhichReport()
            );

            List<File> files = findExelFile(
                    params.getPath(),
                    YearMonth.parse(params.getFrom(), FORMATTER),
                    YearMonth.parse(params.getTo(),   FORMATTER)
            );

            for (File file : files) {
                fileScanner.readExcelFile(file.getAbsolutePath());
            }
        }
    }

    private List<File> findExelFile(String mainFolder, YearMonth dataFrom, YearMonth dataTo) {
        List<File> findedFiles = new ArrayList<>();

        for (YearMonth current = dataFrom; !current.isAfter(dataTo); current = current.plusMonths(1)) {
            String path = String.format("%s/%d/%02d",
                    mainFolder,
                    current.getYear(),
                    current.getMonthValue()
            );

            File folder = new File(path);

            if (!folder.isDirectory()) {
                System.out.printf("  [POMINIĘTO] Folder nie istnieje: %s%n", path);
                continue;
            }

            File[] pliki = folder.listFiles(
                    (dir, name) -> name.endsWith(".xlsx") || name.endsWith(".xls")
            );

            if (pliki != null) {
                for (File plik : pliki) {
                    System.out.printf("  [ZNALEZIONO] %s%n", plik.getName());
                    findedFiles.add(plik);
                }
            }
        }

        return findedFiles;
    }
}

//KOD do MAIN
//CLI cli = new CLI(args);
//ExcelFileFinder finder = new ExcelFileFinder();
//finder.run(cli.getParamsSets(args));