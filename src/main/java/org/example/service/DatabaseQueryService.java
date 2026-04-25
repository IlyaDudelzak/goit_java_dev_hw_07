package org.example.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.example.database.*;
import org.example.exception.DatabaseQueryException;
import org.example.model.*;
import org.example.util.*;


public class DatabaseQueryService {

    public List<MaxSalaryWorker> findMaxSalaryWorkers(int count) throws DatabaseQueryException {
        String sql = SqlReader.readSqlFile("sql/find_max_salary_workers.sql");
        List<MaxSalaryWorker> result = new ArrayList<>();

        try (PreparedStatement pstmt = Database.getInstance().getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, count);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    result.add(new MaxSalaryWorker(
                            rs.getString("NAME"),
                            rs.getInt("SALARY")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("Помилка у методу findTopSalaryWorkers(): " + e.getMessage());
        }
        return result;
    }

    public List<MaxProjectCountClient> findMaxProjectsClients(int limit) throws DatabaseQueryException {
        String sql = SqlReader.readSqlFile("sql/find_max_projects_clients.sql");
        List<MaxProjectCountClient> result = new ArrayList<>();

        try (PreparedStatement pstmt = Database.getInstance().getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, limit);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    result.add(new MaxProjectCountClient(
                            rs.getString("NAME"),
                            rs.getInt("PROJECT_COUNT")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("Помилка у методу findMaxProjectsClients(): " + e.getMessage());
        }
        return result;
    }

    public List<LongestProject> findLongestProjects(int limit) throws DatabaseQueryException {
        String sql = SqlReader.readSqlFile("sql/find_longest_projects.sql");
        List<LongestProject> result = new ArrayList<>();

        try (PreparedStatement pstmt = Database.getInstance().getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, limit);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    result.add(new LongestProject(
                            rs.getString("NAME"),
                            rs.getInt("MONTH_COUNT")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("Помилка у методу findLongestProjects(): " + e.getMessage());
        }
        return result;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() throws DatabaseQueryException {
        String sql = SqlReader.readSqlFile("sql/find_youngest_eldest_workers.sql");
        List<YoungestEldestWorker> result = new ArrayList<>();
        try (ResultSet rs = Database.getInstance().getConnection().createStatement().executeQuery(sql)) {
            while (rs.next()) {
                result.add(new YoungestEldestWorker(
                        rs.getString("TYPE"), rs.getString("NAME"), rs.getString("BIRTHDAY")));
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("Помилка у методу findYoungestEldestWorkers(): " + e.getMessage());
        }
        return result;
    }

    public List<ProjectPrice> printProjectPrices() throws DatabaseQueryException {
        String sql = SqlReader.readSqlFile("sql/print_project_prices.sql");
        List<ProjectPrice> result = new ArrayList<>();
        try (ResultSet rs = Database.getInstance().getConnection().createStatement().executeQuery(sql)) {
            while (rs.next()) {
                result.add(new ProjectPrice(rs.getString("NAME"), rs.getLong("PRICE")));
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("Помилка у методу printProjectPrices(): " + e.getMessage());
        }
        return result;
    }

    public static void main() throws DatabaseQueryException {
        DatabaseQueryService queryService = new DatabaseQueryService();

        System.out.println("=== РЕЗУЛЬТАТИ ВИБІРКИ З БД ===\n");

        System.out.println("--- Max Salary Workers ---");
        List<MaxSalaryWorker> maxSalaryWorkers = queryService.findMaxSalaryWorkers(2);
        maxSalaryWorkers.forEach(System.out::println);

        System.out.println("\n--- Max Projects Clients ---");
        List<MaxProjectCountClient> maxProjectsClients = queryService.findMaxProjectsClients(2);
        maxProjectsClients.forEach(System.out::println);

        System.out.println("\n--- Longest Projects (in months) ---");
        List<LongestProject> longestProjects = queryService.findLongestProjects(2);
        longestProjects.forEach(System.out::println);

        System.out.println("\n--- Youngest and Eldest Workers ---");
        List<YoungestEldestWorker> youngestEldestWorkers = queryService.findYoungestEldestWorkers();
        youngestEldestWorkers.forEach(System.out::println);
        System.out.println("\n--- Project Prices ---");
        List<ProjectPrice> projectPrices = queryService.printProjectPrices();
        projectPrices.forEach(System.out::println);

        System.out.println("\n===============================");
    }
}