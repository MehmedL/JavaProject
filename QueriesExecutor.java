import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueriesExecutor {

    private Connection connection;

    public QueriesExecutor(Connection connection) {
        this.connection = connection;
    }

    // Метод за проверка на текущата роля на потребителя
    public String getCurrentRole() {
        String roleQuery = "SELECT CURRENT_ROLE()";
        String role = null;

        try (PreparedStatement statement = connection.prepareStatement(roleQuery);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                role = resultSet.getString(1); // Вземане на ролята
            }
        } catch (SQLException e) {
            System.out.println("Error while checking the user role.");
        }

        return role;
    }

    // Метод за изпълнение на заявките за "Sergeant" роля
    public void executeSergeantQueries() {
        String sergeantQuery = "SELECT * FROM Criminal";

        try (PreparedStatement pstmt = connection.prepareStatement(sergeantQuery);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String criminalNum = rs.getString("CriminalNUM");
                int personId = rs.getInt("PersonID");

                System.out.println("ID: " + id + ", CriminalNUM: " + criminalNum + ", PersonID: " + personId);
            }// Вместо това да се извиква printTable за таблица Criminal
        } catch (SQLException e) {
            System.out.println("Error while executing the query.");
        }
    }

    public int executeLieutenantQueries(int operation, String tableName) {
        if (operation < 1 && operation > 4) {
            System.out.println("Invalid operation!");
            return -1;
        }

        if (!tableName.equals("Criminal") && !tableName.equals("Crime")) {
            System.out.println("Invalid table name!");
            return -1;
        }

        Scanner scanner = new Scanner(System.in);

        String lieutenantQuery = "";

        if (operation == 1) {
            lieutenantQuery = "SELECT * FROM " + tableName;

            if (tableName.equals("Criminal")) {
                try (PreparedStatement pstmt = connection.prepareStatement(lieutenantQuery);
                     ResultSet rs = pstmt.executeQuery()) {

                    System.out.println();

                    while (rs.next()) { // Изкарва резултатите
                        int id = rs.getInt("ID");
                        String criminalNum = rs.getString("CriminalNUM");
                        int personId = rs.getInt("PersonID");

                        System.out.println("ID: " + id + ", CriminalNUM: " + criminalNum + ", PersonID: " + personId);
                    }// И тук да стане с извикване на метод
                } catch (SQLException e) {
                    System.out.println("Error while executing the query.");
                }
            } else {
                try (PreparedStatement pstmt = connection.prepareStatement(lieutenantQuery);
                     ResultSet rs = pstmt.executeQuery()) {

                    System.out.println();

                    DisplayingExe.printTable(rs, tableName);
                    // DisplayingExe получава rs и името на таблицата, която трябва да бъде изкарана в конзолата
                } catch (SQLException e) {
                    System.out.println("Error while executing the query.");
                }

                // Двата while цикъла също трябва да са в отделни методи в DisplayingExe, но ми омръзна и ще ги правя друг ден
            }

            return operation;
        }
        else if (operation == 2) {
            InsertionExe insertionExe = new InsertionExe(connection);

            // Въвеждаме нов човек и получаваме неговото ID
            int personId = insertionExe.insertPerson();

            // Ако успешно сме въвели човек, добавяме го като криминално лице
            if (personId != -1) {
                insertionExe.insertCriminal(personId);
            } else {
                System.out.println("Failed to insert person.");
            }

            return operation;
        }
        else if (operation == 4) return -1;
        return -1;
    }
}
