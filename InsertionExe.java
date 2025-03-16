import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertionExe {
    private Connection connection;

    public InsertionExe(Connection connection) {
        this.connection = connection;
    }

    public int insertPerson() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("EGN: ");
        String egn = scanner.nextLine();
        System.out.println("First Name: ");
        String firstName = scanner.nextLine();
        System.out.println("Middle Name: ");
        String middleName = scanner.nextLine();
        System.out.println("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.println("Date of birth (YYYY-MM-DD): ");
        String birthDate = scanner.nextLine();
        System.out.println("Sex (F-Female/ M-Male): ");
        String sex = scanner.nextLine();

        String insertPersonSQL = "INSERT INTO Person (EGN, FirstName, MiddleName, LastName, BirthDate, Sex) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(insertPersonSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, egn);
            pstmt.setString(2, firstName);
            pstmt.setString(3, middleName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, birthDate);
            pstmt.setString(6, sex);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to insert into Person.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);  // Взима последното въведено ID
                }
            }
        } catch (SQLException e) {
            System.out.println("Error inserting person: " + e.getMessage());
        }
        return -1;
    }

    public void insertCriminal(int personId) {
        String criminalNum = generateCriminalNumber();
        String insertCriminalSQL = "INSERT INTO Criminal (CriminalNUM, PersonID) VALUES (?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(insertCriminalSQL)) {
            pstmt.setString(1, criminalNum);
            pstmt.setInt(2, personId);

            int criminalRows = pstmt.executeUpdate();
            if (criminalRows > 0) {
                System.out.println("Criminal successfully added with PersonID: " + personId + ", CriminalNUM: " + criminalNum);
            }
        } catch (SQLException e) {
            System.out.println("Error inserting criminal: " + e.getMessage());
        }
    }

    private String generateCriminalNumber() {
        String lastCriminalNum = null;
        String query = "SELECT CriminalNUM FROM Criminal ORDER BY CriminalNUM DESC LIMIT 1";

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                lastCriminalNum = rs.getString("CriminalNUM");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving last CriminalNUM: " + e.getMessage());
        }

        if (lastCriminalNum == null) {
            return "C0000001";
        }

        int lastNum = Integer.parseInt(lastCriminalNum.substring(1));
        return String.format("C%07d", lastNum + 1);
    }
}

// По аналогичен начин трябва да се добави insertPolice

