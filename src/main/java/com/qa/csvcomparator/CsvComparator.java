package com.qa.csvcomparator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvComparator {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar csv-comparator.jar <file1.csv> <file2.csv>");
            return;
        }
        Path path1 = Paths.get(args[0]);
        Path path2 = Paths.get(args[1]);
        try {
            List<String[]> csv1 = readCsv(path1);
            List<String[]> csv2 = readCsv(path2);
            List<String> differences = compare(csv1, csv2);
            if (differences.isEmpty()) {
                System.out.println("Files are identical.");
            } else {
                System.out.println("Differences found:");
                differences.forEach(System.out::println);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> readCsv(Path path) throws IOException {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",", -1);
                rows.add(values);
            }
        }
        return rows;
    }

    private static List<String> compare(List<String[]> csv1, List<String[]> csv2) {
        List<String> diffs = new ArrayList<>();
        int maxRows = Math.max(csv1.size(), csv2.size());
        for (int i = 0; i < maxRows; i++) {
            if (i >= csv1.size()) {
                diffs.add("Row " + (i + 1) + " missing in first file: " + Arrays.toString(csv2.get(i)));
                continue;
            }
            if (i >= csv2.size()) {
                diffs.add("Row " + (i + 1) + " missing in second file: " + Arrays.toString(csv1.get(i)));
                continue;
            }
            String[] row1 = csv1.get(i);
            String[] row2 = csv2.get(i);
            int maxCols = Math.max(row1.length, row2.length);
            for (int j = 0; j < maxCols; j++) {
                String val1 = j < row1.length ? row1[j] : "";
                String val2 = j < row2.length ? row2[j] : "";
                if (!val1.equals(val2)) {
                    diffs.add(String.format("Row %d, Column %d differs: '%s' vs '%s'", i + 1, j + 1, val1, val2));
                }
            }
        }
        return diffs;
    }
}
