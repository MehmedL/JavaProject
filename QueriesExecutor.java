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

            DisplayingExe.printTable(rs, "Criminal");
        } catch (SQLException e) {
            System.out.println("Error while executing the query.");
        }
    }

    public int executeLieutenantQueries(int operation, String tableName) {
        String lieutenantQuery = "";

        if (operation == 1) {
            lieutenantQuery = "SELECT * FROM " + tableName;

            try (PreparedStatement pstmt = connection.prepareStatement(lieutenantQuery);
                 ResultSet rs = pstmt.executeQuery()) {

                System.out.println(tableName);

                DisplayingExe.printTable(rs, tableName);
            } catch (SQLException e) {
                System.out.println("Error while executing the query.");
            }

            return operation;
        }
        else if (operation == 2) {
            if(tableName.equals("Criminal")){
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
            } else{
                System.out.println("Още няма направена функционалност а добавяне на престъпление.");
            }

        }
        else if (operation == 4) return -1;
        return -1;
    }
}
