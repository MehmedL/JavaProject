import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayingExe {
    // Метод за извеждане на таблица на конзолата
    public static void printTable(ResultSet rs, String tableName) {
        try {
            while (rs.next()) { // Добавяме rs.next(), за да четем редовете
                if (tableName.equals("Crimе")) {
                    int id = rs.getInt("ID");
                    String crimeNum = rs.getString("CrimeNUM");
                    String crimeType = rs.getString("CrimeType");
                    String date = rs.getString("CommitDate");
                    String closure = rs.getString("Closure");
                    int criminalId = rs.getInt("CriminalID");
                    int victimId = rs.getInt("VictimID");
                    int officerId = rs.getInt("PoliceOfficerID");
                    String department = rs.getString("DepartmentID");

                    System.out.println("ID: " + id + ", Crime №: " + crimeNum + ", Crime Type: " + crimeType + ", Commit date: " + date
                            + ", Closure: " + closure + ", Criminal ID: " + criminalId + ", Victim ID: " + victimId + ", Police Officer ID: " + officerId
                            + ", Department Name: " + department);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// Трябва по аналогичен начин да се добави идкарването на всяка таблица