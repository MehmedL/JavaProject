import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteExe {
    private Connection connection;

    public DeleteExe(Connection connection) {
        this.connection = connection;
    }

    // Метод за изтриване на запис в таблицата Criminal
    public void deleteCriminal() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter CriminalNUM to delete: ");
        String criminalNum = scanner.nextLine();

        String deleteCriminalSQL = "DELETE FROM Criminal WHERE CriminalNUM = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(deleteCriminalSQL)) {
            pstmt.setString(1, criminalNum);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("The record from the Criminal table has been successfully deleted.");
            } else {
                System.out.println("No record found with CriminalNUM: " + criminalNum);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting from Criminal: " + e.getMessage());
        }
    }

    // Метод за изтриване на запис в таблицата Victim
    public void deleteVictim() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter VictimNUM to delete: ");
        String victimNum = scanner.nextLine();

        String deleteVictimSQL = "DELETE FROM Victim WHERE VictimNUM = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(deleteVictimSQL)) {
            pstmt.setString(1, victimNum);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("The record from the Victim table has been successfully deleted.");
            } else {
                System.out.println("No record found with VictimNUM: " + victimNum);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting from Victim: " + e.getMessage());
        }
    }

    // Метод за изтриване на запис в таблицата PoliceOfficer
    public void deletePoliceOfficer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter OfficerBadgeNUM to delete: ");
        String officerBadgeNum = scanner.nextLine();

        String deleteOfficerSQL = "DELETE FROM PoliceOfficer WHERE OfficerBadgeNUM = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(deleteOfficerSQL)) {
            pstmt.setString(1, officerBadgeNum);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("The record from the PoliceOfficer table has been successfully deleted.");
            } else {
                System.out.println("No record found with OfficerBadgeNUM: " + officerBadgeNum);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting from PoliceOfficer: " + e.getMessage());
        }
    }

    // Метод за изтриване на запис в таблицата Crime
    public void deleteCrime() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter CrimeNUM to delete: ");
        String crimeNum = scanner.nextLine();

        String deleteCrimeSQL = "DELETE FROM Crime WHERE CrimeNUM = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(deleteCrimeSQL)) {
            pstmt.setString(1, crimeNum);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("The record from the Crime table has been successfully deleted.");
            } else {
                System.out.println("No record found with CrimeNUM: " + crimeNum);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting from Crime: " + e.getMessage());
        }
    }
}
