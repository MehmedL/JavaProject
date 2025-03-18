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
                else if(tableName.equals("Victim"))
                {
                    int id = rs.getInt("ID");
                    String victimNUM = rs.getString("VictimNum");
                    int personID = rs.getInt("PersonID");

                    System.out.println("ID: " + id + ", Victim №: " + victimNUM + "PersonID: " + personID);
                }
                else if(tableName.equals("Person"))
                {
                    int id = rs.getInt("ID");
                    int EGN = rs.getInt("EGN");
                    String firstName = rs.getString("FirstName");
                    String middleName = rs.getString("MiddleName");
                    String lastName = rs.getString("LastName");
                    String birthDate = rs.getString("BirthDate");
                    String sex = rs.getString("Sex");

                    System.out.println("ID: " + id + ", EGN: " + EGN + ", FirstName" + firstName + ", MiddleName" + middleName + ", LastName" + lastName
                    + ", BirthDate" + birthDate + ", Sex" + sex);
                }
                else if(tableName.equals("Policeofficer"))
                {
                    int id = rs.getInt("ID");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// Трябва по аналогичен начин да се добави идкарването на всяка таблица