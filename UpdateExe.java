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
        System.out.print("Въведете OfficerBadgeNUM за обновяване: ");
        String officerBadgeNum = scanner.nextLine();

        System.out.println("Нов Officer Rank: ");
        String officerRank = scanner.nextLine();

        System.out.println("Нов DepartmentID: ");
        int departmentId = Integer.parseInt(scanner.nextLine());

        String updateOfficerSQL = "UPDATE PoliceOfficer SET OfficerRank = ?, DepartmentID = ? WHERE OfficerBadgeNUM = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(updateOfficerSQL)) {
            pstmt.setString(1, officerRank);
            pstmt.setInt(2, departmentId);
            pstmt.setString(3, officerBadgeNum);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Записът от таблицата PoliceOfficer е успешно обновен.");
            } else {
                System.out.println("Не е намерен запис с OfficerBadgeNUM: " + officerBadgeNum);
            }
        } catch (SQLException e) {
            System.out.println("Грешка при обновяване на PoliceOfficer: " + e.getMessage());
        }
    }

    public void updateCrime() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Въведете CrimeNUM за обновяване: ");
        String crimeNum = scanner.nextLine();

        System.out.println("Нов Closure: ");
        String closure = scanner.nextLine();

        String updateCrimeSQL = "UPDATE Crime SET Closure = ? WHERE CrimeNUM = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(updateCrimeSQL)) {
            pstmt.setString(1, closure);
            pstmt.setString(2, crimeNum);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Записът от таблицата Crime е успешно обновен.");
            } else {
                System.out.println("Не е намерен запис с CrimeNUM: " + crimeNum);
            }
        } catch (SQLException e) {
            System.out.println("Грешка при обновяване на Crime: " + e.getMessage());
        }
    }
}
