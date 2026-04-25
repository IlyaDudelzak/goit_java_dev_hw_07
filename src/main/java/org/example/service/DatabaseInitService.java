package org.example.service;

import org.example.database.Database;
import org.example.util.SqlReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Statement;

public class DatabaseInitService {
    public static void main() {
        String dbFilePath = "./megasoft.mv.db";

        try {
            boolean deleted = Files.deleteIfExists(Paths.get(dbFilePath));
            if (deleted) {
                System.out.println("Старий файл БД видалено.");
            }
        } catch (IOException e) {
            System.err.println("Не вдалося видалити файл БД (можливо, він використовується): " + e.getMessage());
        }

        String sql = SqlReader.readSqlFile("sql/init_db.sql");

        try (Statement stmt = Database.getInstance().getConnection().createStatement()) {
            stmt.execute(sql);
            System.out.println("БД успішно проініціалізована з чистого аркуша.");
        } catch (Exception e) {
            throw new RuntimeException("Помилка під час ініціалізації БД", e);
        }
    }
}