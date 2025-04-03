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
    public void insertVictim(int personId) {
        String victimNum = generateVictimNumber();
        String insertCriminalSQL = "INSERT INTO Victim (VictimNUM, PersonID) VALUES (?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(insertCriminalSQL)) {
            pstmt.setString(1, victimNum);
            pstmt.setInt(2, personId);

            int victimRows = pstmt.executeUpdate();
            if (victimRows > 0) {
                System.out.println("Victim successfully added with PersonID: " + personId + ", CriminalNUM: " + victimNum);
            }
        } catch (SQLException e) {
            System.out.println("Error inserting victim: " + e.getMessage());
        }
    }

    public void insertPoliceOfficer(int personId) {
        Scanner scanner = new Scanner(System.in);

        // Въвеждане на данни за полицейския служител
        System.out.println("Officer Rank: ");
        String officerRank = scanner.nextLine();
        System.out.println("Department ID: ");
        int departmentID = Integer.parseInt(scanner.nextLine());

        // Генериране на уникален OfficerBadgeNUM
        String officerBadgeNum = generateOfficerBadgeNUM();

        // SQL заявка за вмъкване в таблицата PoliceOfficer
        String insertOfficerSQL = "INSERT INTO PoliceOfficer (OfficerBadgeNUM, OfficerRank, PersonID, DepartmentID) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(insertOfficerSQL)) {
            pstmt.setString(1, officerBadgeNum);
            pstmt.setString(2, officerRank);
            pstmt.setInt(3, personId);
            pstmt.setInt(4, departmentID);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("PoliceOfficer successfully added with OfficerBadgeNUM: " + officerBadgeNum +
                        ", PersonID: " + personId +
                        ", DepartmentID: " + departmentID);
            }
        } catch (SQLException e) {
            System.out.println("Error inserting victim: " + e.getMessage());
        }
    }

    public int insertCrime(){

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Crime Type: ");
            String CrimeType = scanner.nextLine();
            System.out.println("Date of commit (YYYY-MM-DD): ");
            String CommitDate = scanner.nextLine();
            System.out.println("Closure: ");
            String Closure = scanner.nextLine();
            System.out.println("Criminal ID: ");
            int CriminalID = scanner.nextInt();
            System.out.println("Victim ID: ");
            int VictimID = scanner.nextInt();
            System.out.println("Police Officer ID: ");
            int PoliceOfficerID = scanner.nextInt();
            System.out.println("Department ID: ");
            int DepartmentID = scanner.nextInt();

            String CrimeNUM = generateCrimeNum();

            String insertCrimeSQL = "INSERT INTO Crime (CrimeNUM, CrimeType, CommitDate, Closure, CriminalID, VictimID, PoliceOfficerID, DepartmentID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(insertCrimeSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, CrimeNUM);
                pstmt.setString(2, CrimeType);
                pstmt.setString(3, CommitDate);
                pstmt.setString(4, Closure);
                pstmt.setInt(5, CriminalID);
                pstmt.setInt(6, VictimID);
                pstmt.setInt(7, PoliceOfficerID);
                pstmt.setInt(8, DepartmentID);

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Failed to insert into Crime.");
                }

            }
        }catch (SQLException e) {
            e.printStackTrace();  // Log the error
        }
        return -1;
    }

    private String generateCrimeNum() {
        String lastCrimeNum = null;
        String query = "SELECT CrimeNUM FROM Crime ORDER BY CrimeNUM DESC LIMIT 1";

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                lastCrimeNum = rs.getString("CrimeNUM");
            }
        } catch (SQLException e) {
            System.out.println("Грешка при извличане на последния CrimeNUM: " + e.getMessage());
        }

        if (lastCrimeNum == null) {
            return "CR0000001";
        }

        // Предполагаме, че CrimeNUM има формат "CR" последван от 7 цифри.
        int lastNum = Integer.parseInt(lastCrimeNum.substring(2));
        return String.format("CR%07d", lastNum + 1);
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

    private String generateVictimNumber() {
        String lastVictimNum = null;
        String query = "SELECT VictimNUM FROM Victim ORDER BY VictimNUM DESC LIMIT 1";

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                lastVictimNum = rs.getString("VictimNUM");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving last VictimNUM: " + e.getMessage());
        }

        if (lastVictimNum == null) {
            return "V0000001";
        }

        int lastNum = Integer.parseInt(lastVictimNum.substring(1));
        return String.format("V%07d", lastNum + 1);
    }
    private String generateOfficerBadgeNUM() {
        String lastOfficerBadgeNum = null;
        String query = "SELECT OfficerBadgeNum FROM PoliceOfficer ORDER BY OfficerBadgeNum DESC LIMIT 1";

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                lastOfficerBadgeNum = rs.getString("OfficerBadgeNUM");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving last OfficerBadgeNUM: " + e.getMessage());
        }

        if (lastOfficerBadgeNum == null) {
            return "B0000001";
        }

        int lastNum = Integer.parseInt(lastOfficerBadgeNum.substring(1));
        return String.format("B%07d", lastNum + 1);
    }

}

//Мисля че е готово Направи един преглед и ти за всеки случай да не съм изпуснал нещо

