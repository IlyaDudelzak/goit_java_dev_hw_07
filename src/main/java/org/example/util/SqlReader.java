package org.example.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SqlReader {
    public static String readSqlFile(String filePath) {
        try {
            return String.join("\n", Files.readAllLines(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Помилка читання файлу: " + filePath, e);
        }
    }
}