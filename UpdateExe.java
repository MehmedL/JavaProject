import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateExe {
    private Connection connection;

    public UpdateExe(Connection connection) {
        this.connection = connection;
    }
    // Метод за актуализиране на запис в таблицата PoliceOfficer
    public void updatePoliceOfficer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert OfficerBadgeNUM to be updated: ");
        String officerBadgeNum = scanner.nextLine();

        System.out.println("New Officer Rank: ");
        String officerRank = scanner.nextLine();

        System.out.println("New DepartmentID: ");
        int departmentId = Integer.parseInt(scanner.nextLine());

        String updateOfficerSQL = "UPDATE PoliceOfficer SET OfficerRank = ?, DepartmentID = ? WHERE OfficerBadgeNUM = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(updateOfficerSQL)) {
            pstmt.setString(1, officerRank);
            pstmt.setInt(2, departmentId);
            pstmt.setString(3, officerBadgeNum);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Successfully updated PoliceOfficer record!");
            } else {
                System.out.println("Record not found, OfficerBadgeNUM: " + officerBadgeNum);
            }
        } catch (SQLException e) {
            System.out.println("Could not update record with PoliceOfficer: " + e.getMessage());
        }
    }

    public void updateCrime() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("New CrimeNUM to be updated: ");
        String crimeNum = scanner.nextLine();

        System.out.println("New Closure: ");
        String closure = scanner.nextLine();

        String updateCrimeSQL = "UPDATE Crime SET Closure = ? WHERE CrimeNUM = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(updateCrimeSQL)) {
            pstmt.setString(1, closure);
            pstmt.setString(2, crimeNum);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Successfully updated Crime record!");
            } else {
                System.out.println("Not found record with CrimeNUM: " + crimeNum);
            }
        } catch (SQLException e) {
            System.out.println("Could not update Crime: " + e.getMessage());
        }
    }
}
