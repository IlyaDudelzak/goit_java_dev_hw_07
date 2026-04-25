package org.example.service;

import org.example.database.Database;
import org.example.util.SqlReader;
import java.sql.Statement;

public class DatabasePopulateService {
    public static void main() {
        String sql = SqlReader.readSqlFile("sql/populate_db.sql");
        try (Statement stmt = Database.getInstance().getConnection().createStatement()) {
            stmt.execute(sql);
            System.out.println("БД успішно наповнена даними.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}