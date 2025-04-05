import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayingExe {
    // Метод за извеждане на таблица на конзолата
    public static void printTable(ResultSet rs, String tableName) {
        try {
            while (rs.next()) {
                // Добавяме rs.next(), за да четем редовете
                if (tableName.equals("Crime")) {
                    int id = rs.getInt("ID");
                    String crimeNum = rs.getString("CrimeNUM");
                    String crimeType = rs.getString("CrimeType");
                    String date = rs.getString("CommitDate");
                    String closure = rs.getString("Closure");
                    int criminalId = rs.getInt("CriminalID");
                    int victimId = rs.getInt("VictimID");
                    int officerId = rs.getInt("PoliceOfficerID");
                    int department = rs.getInt("DepartmentID");

                    System.out.println();
                    System.out.println("+------+------------+-----------------+--------------+------------+--------------+------------+--------------------+----------------+");
                    System.out.printf("| %-4s | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s | %-18s | %-14s |%n",
                            "ID", "Crime №", "Crime Type", "Commit date", "Closure", "Criminal ID", "Victim ID", "Officer ID", "Department ID");
                    System.out.println("+------+------------+-----------------+--------------+------------+--------------+------------+--------------------+----------------+");
                    System.out.printf("| %-4d | %-10s | %-15s | %-12s | %-10s | %-12d | %-10d | %-18d | %-14d |%n",
                            id, crimeNum, crimeType, date, closure, criminalId, victimId, officerId, department);
                    System.out.println("+------+------------+-----------------+--------------+------------+--------------+------------+--------------------+----------------+");

                } else if (tableName.equals("Victim")) {
                    int id = rs.getInt("ID");
                    String victimNUM = rs.getString("VictimNum");
                    int personID = rs.getInt("PersonID");

                    System.out.println();
                    System.out.println("+-----+---------------+----------+");
                    System.out.printf("| %-3s | %-13s | %-8s |%n", "ID", "Victim №", "PersonID");
                    System.out.println("+-----+---------------+----------+");
                    System.out.printf("| %-3d | %-13s | %-8d |%n", id, victimNUM, personID);
                    System.out.println("+-----+---------------+----------+");

                }else if (tableName.equals("Policeofficer")) {
                    int id = rs.getInt("ID");
                    String officerBadgeNum = rs.getString("OfficerBadgeNum");
                    String officerRank = rs.getString("OfficerRank");
                    int personId = rs.getInt("PersonId");
                    int departmentID = rs.getInt("DepartmentId");

                    System.out.println();
                    System.out.println("+-----+-----------------+---------------+------------+--------------+");
                    System.out.printf("| %-3s | %-15s | %-13s | %-10s | %-12s |%n",
                            "ID", "Badge №", "Rank", "PersonID", "DepartmentID");
                    System.out.println("+-----+-----------------+---------------+------------+--------------+");
                    System.out.printf("| %-3d | %-15s | %-13s | %-10d | %-12d |%n",
                            id, officerBadgeNum, officerRank, personId, departmentID);
                    System.out.println("+-----+-----------------+---------------+------------+--------------+");

                } else if (tableName.equals("Criminal")) {
                    int id = rs.getInt("ID");
                    String criminalNum = rs.getString("CriminalNum");
                    int personId = rs.getInt("PersonId");

                    System.out.println();
                    System.out.println("+-----+---------------+-----------+");
                    System.out.printf("| %-3s | %-13s | %-9s |%n", "ID", "Criminal №", "PersonID");
                    System.out.println("+-----+---------------+-----------+");
                    System.out.printf("| %-3d | %-13s | %-9d |%n", id, criminalNum, personId);
                    System.out.println("+-----+---------------+-----------+");

                } else if (tableName.equals("Department")) {
                    int id = rs.getInt("ID");
                    String departmentName = rs.getString("DepartmentName");

                    System.out.println();
                    System.out.println("+-----+------------------+");
                    System.out.printf("| %-3s | %-16s |%n", "ID", "Department Name");
                    System.out.println("+-----+------------------+");
                    System.out.printf("| %-3d | %-16s |%n", id, departmentName);
                    System.out.println("+-----+------------------+");
                }
                else {
                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
