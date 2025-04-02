import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateExe {
    private Connection connection;

    public UpdateExe(Connection connection) {
        this.connection = connection;
    }

    // Метод за актуализиране на запис в таблицата Criminal
    public void updateCriminal() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Въведете CriminalNUM за обновяване: ");
        String criminalNum = scanner.nextLine();
        System.out.println("Нов PersonID за Criminal: ");
        int personId = Integer.parseInt(scanner.nextLine());

        String updateCriminalSQL = "UPDATE Criminal SET PersonID = ? WHERE CriminalNUM = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(updateCriminalSQL)) {
            pstmt.setInt(1, personId);
            pstmt.setString(2, criminalNum);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Записът от таблицата Criminal е успешно обновен.");
            } else {
                System.out.println("Не е намерен запис с CriminalNUM: " + criminalNum);
            }
        } catch (SQLException e) {
            System.out.println("Грешка при обновяване на Criminal: " + e.getMessage());
        }
    }

    // Метод за актуализиране на запис в таблицата Victim
    public void updateVictim() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Въведете VictimNUM за обновяване: ");
        String victimNum = scanner.nextLine();
        System.out.println("Нов PersonID за Victim: ");
        int personId = Integer.parseInt(scanner.nextLine());

        String updateVictimSQL = "UPDATE Victim SET PersonID = ? WHERE VictimNUM = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(updateVictimSQL)) {
            pstmt.setInt(1, personId);
            pstmt.setString(2, victimNum);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Записът от таблицата Victim е успешно обновен.");
            } else {
                System.out.println("Не е намерен запис с VictimNUM: " + victimNum);
            }
        } catch (SQLException e) {
            System.out.println("Грешка при обновяване на Victim: " + e.getMessage());
        }
    }

    // Метод за актуализиране на запис в таблицата PoliceOfficer
    public void updatePoliceOfficer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Въведете OfficerBadgeNUM за обновяване: ");
        String officerBadgeNum = scanner.nextLine();
        System.out.println("Нов Officer Rank: ");
        String officerRank = scanner.nextLine();
        System.out.println("Нов PersonID за Officer: ");
        int personId = Integer.parseInt(scanner.nextLine());
        System.out.println("Нов DepartmentID: ");
        int departmentId = Integer.parseInt(scanner.nextLine());

        String updateOfficerSQL = "UPDATE PoliceOfficer SET OfficerRank = ?, PersonID = ?, DepartmentID = ? WHERE OfficerBadgeNUM = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(updateOfficerSQL)) {
            pstmt.setString(1, officerRank);
            pstmt.setInt(2, personId);
            pstmt.setInt(3, departmentId);
            pstmt.setString(4, officerBadgeNum);

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

    // Метод за актуализиране на запис в таблицата Crime
    public void updateCrime() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Въведете CrimeNUM за обновяване: ");
        String crimeNum = scanner.nextLine();

        System.out.println("Нов Crime Type: ");
        String crimeType = scanner.nextLine();
        System.out.println("Нова Commit Date (YYYY-MM-DD): ");
        String commitDate = scanner.nextLine();
        System.out.println("Нова Closure (ако е приложимо, иначе оставете празно): ");
        String closure = scanner.nextLine();
        System.out.println("Нов CriminalID: ");
        int criminalId = Integer.parseInt(scanner.nextLine());
        System.out.println("Нов VictimID: ");
        int victimId = Integer.parseInt(scanner.nextLine());
        System.out.println("Нов PoliceOfficerID: ");
        int policeOfficerId = Integer.parseInt(scanner.nextLine());
        System.out.println("Нов DepartmentID: ");
        int departmentId = Integer.parseInt(scanner.nextLine());

        String updateCrimeSQL = "UPDATE Crime SET CrimeType = ?, CommitData = ?, Closure = ?, CriminalID = ?, VictimID = ?, PoliceOfficerID = ?, DepartmentID = ? WHERE CrimeNUM = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(updateCrimeSQL)) {
            pstmt.setString(1, crimeType);
            pstmt.setString(2, commitDate);
            pstmt.setString(3, closure);
            pstmt.setInt(4, criminalId);
            pstmt.setInt(5, victimId);
            pstmt.setInt(6, policeOfficerId);
            pstmt.setInt(7, departmentId);
            pstmt.setString(8, crimeNum);

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