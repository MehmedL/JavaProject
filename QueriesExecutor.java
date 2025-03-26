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

                    DisplayingExe.printTable(rs, tableName);
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

            if (personId != -1) {
                if(tableName.equals("Criminal")){
                    insertionExe.insertCriminal(personId);
                }
//              else if(tableName.equals("Crime")){
//              }
            } else {
                System.out.println("Failed to insert person.");
            }

            return operation;
        }
        else if (operation == 4) return -1;
        scanner.close();
        return -1;
    }
    public int executeInspectorQueries(int operation, String tableName) {
        if (operation < 1 && operation > 3) {
            System.out.println("Invalid operation!");
            return -1;
        }

        if (!tableName.equals("Crime")) {
            System.out.println("Invalid table name!");
            return -1;
        }

        Scanner scanner = new Scanner(System.in);

        String InspectorQuery = "";

        if (operation == 1) {
            InspectorQuery = "SELECT * FROM " + tableName;

            try (PreparedStatement pstmt = connection.prepareStatement(InspectorQuery);
                 ResultSet rs = pstmt.executeQuery()) {

                System.out.println();

                DisplayingExe.printTable(rs, tableName);
                // DisplayingExe получава rs и името на таблицата, която трябва да бъде изкарана в конзолата
            } catch (SQLException e) {
                System.out.println("Error while executing the query.");
            }

            return operation;
        }
        else if (operation == 2) {
            // UPDATE - редактиране в таблица Crime
        }
        else if (operation == 3) return -1;
        scanner.close();
        return -1;
    }
    // Според мен май трябва да ги разбием на още по отделни класове понеже става страшно черво!
    //Не съм дописал метода executeCaptainQueries.

    public int executeCaptainQueries(int operation, String tableName) {
        //System.out.println(tableName);

        if (operation < 1 && operation > 5) {
            System.out.println("Invalid operation!");
            return -1;
        }

        if (tableName.equals("Person")) {
            System.out.println("Invalid table name!");
            return -1;
        }

        Scanner scanner = new Scanner(System.in);

        String CaptainQuery = "";

        if (operation == 1) {
            CaptainQuery = "SELECT * FROM " + tableName;

            if (tableName.equals("Criminal")) {
                try (PreparedStatement pstmt = connection.prepareStatement(CaptainQuery);
                     ResultSet rs = pstmt.executeQuery()) {

                    System.out.println();

                    DisplayingExe.printTable(rs, tableName);
                } catch (SQLException e) {
                    System.out.println("Error while executing the query.");
                }
            }
            else if (tableName.equals("Policeofficer")){
                try (PreparedStatement pstmt = connection.prepareStatement(CaptainQuery);
                     ResultSet rs = pstmt.executeQuery()) {

                    System.out.println();

                    DisplayingExe.printTable(rs, tableName);
                    // DisplayingExe получава rs и името на таблицата, която трябва да бъде изкарана в конзолата
                } catch (SQLException e) {
                    System.out.println("Error while executing the query.");
                }
            }
            else if (tableName.equals("Victim")){
                try (PreparedStatement pstmt = connection.prepareStatement(CaptainQuery);
                     ResultSet rs = pstmt.executeQuery()) {

                    System.out.println();

                    DisplayingExe.printTable(rs, tableName);
                    // DisplayingExe получава rs и името на таблицата, която трябва да бъде изкарана в конзолата
                } catch (SQLException e) {
                    System.out.println("Error while executing the query.");
                }
            }
            else if (tableName.equals("Crime")){
                try (PreparedStatement pstmt = connection.prepareStatement(CaptainQuery);
                     ResultSet rs = pstmt.executeQuery()) {

                    System.out.println();

                    DisplayingExe.printTable(rs, tableName);
                    // DisplayingExe получава rs и името на таблицата, която трябва да бъде изкарана в конзолата
                } catch (SQLException e) {
                    System.out.println("Error while executing the query.");
                }
            }
            else if (tableName.equals("Department")){
                try (PreparedStatement pstmt = connection.prepareStatement(CaptainQuery);
                     ResultSet rs = pstmt.executeQuery()) {

                    System.out.println();

                    DisplayingExe.printTable(rs, tableName);
                    // DisplayingExe получава rs и името на таблицата, която трябва да бъде изкарана в конзолата
                } catch (SQLException e) {
                    System.out.println("Error while executing the query.");
                }
            }

            return operation;
        }
        else if (operation == 2) {

            InsertionExe insertionExe = new InsertionExe(connection);

            if(tableName.equals("Crime")){
                int crimeId = insertionExe.insertCrime();

                if (crimeId != -1) {
                    System.out.println("Crime successfully added with ID: " + crimeId);
                } else {
                    System.out.println("Failed to add crime.");
                }
            }
//                else if(tableName.equals("Department")){
//
//                }else if(tableName.equals("Policeofficer")){
//
//                }

            // Въвеждаме нов човек и получаваме неговото ID
            int personId = insertionExe.insertPerson();

            // Ако успешно сме въвели човек, добавяме го като криминално лице
            if (personId != -1) {
                if(tableName.equals("Criminal")){
                    insertionExe.insertCriminal(personId);
                }else if(tableName.equals("Victim")){
                    insertionExe.insertVictim(personId);
                }
            } else {
                System.out.println("Failed to insert person.");
            }


            return operation;
        }
        else if (operation == 4) return -1;
        scanner.close();
        return -1;
    }
}