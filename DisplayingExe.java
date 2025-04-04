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
                    String department = rs.getString("DepartmentID");

                    System.out.println();

                    // Горна граница на таблицата
                    System.out.println("+------+------------+-----------------+--------------+------------+--------------+------------+--------------------+----------------+");
                    // Заглавен ред с имената на колоните
                    System.out.printf("| %-4s | %-10s | %-15s | %-12s | %-10s | %-12s | %-10s | %-18s | %-14s |%n",
                            "ID", "Crime №", "Crime Type", "Commit date", "Closure", "Criminal ID", "Victim ID", "Police Officer ID", "Department ID");
                    // Разделителна линия под заглавния ред
                    System.out.println("+------+------------+-----------------+--------------+------------+--------------+------------+--------------------+----------------+");
                    // Ред с данни
                    System.out.printf("| %-4d | %-10s | %-15s | %-12s | %-10s | %-12d | %-10d | %-18d | %-14d |%n",
                            id, crimeNum, crimeType, date, closure, criminalId, victimId, officerId, department);
                    // Долна граница на таблицата
                    System.out.println("+------+------------+-----------------+--------------+------------+--------------+------------+--------------------+----------------+");
                }
                else if(tableName.equals("Victim"))
                {
                    int id = rs.getInt("ID");
                    String victimNUM = rs.getString("VictimNum");
                    int personID = rs.getInt("PersonID");

                    System.out.println();

                    System.out.println("+-----+---------------+----------+");
                    System.out.printf("| %-3s | %-13s | %-8s |%n", "ID", "Victim №", "PersonID");
                    System.out.println("+-----+---------------+----------+");
                    System.out.printf("| %-3d | %-13s | %-8d |%n", id, victimNUM, personID);
                    System.out.println("+-----+---------------+----------+");
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

                    System.out.println();

                    // Отпечатване на линията на горната граница
                    System.out.println("+-----+---------------+---------------+---------------+---------------+---------------+----------+");
                    // Отпечатване на заглавния ред
                    System.out.printf("| %-3s | %-13s | %-13s | %-13s | %-13s | %-13s | %-8s |%n",
                            "ID", "EGN", "FirstName", "MiddleName", "LastName", "BirthDate", "Sex");
                    // Отпечатване на разделителната линия
                    System.out.println("+-----+---------------+---------------+---------------+---------------+---------------+----------+");
                    // Отпечатване на реда с данни
                    System.out.printf("| %-3d | %-13s | %-13s | %-13s | %-13s | %-13s | %-8s |%n",
                            id, EGN, firstName, middleName, lastName, birthDate, sex);
                    // Отпечатване на линията на долната граница
                    System.out.println("+-----+---------------+---------------+---------------+---------------+---------------+----------+");
                }
                else if(tableName.equals("Policeofficer"))
                {
                    int id = rs.getInt("ID");
                    String officerBadgeNum =rs.getString("OfficerBadgeNum");
                    String officerRank = rs.getString("OfficerRank");
                    int personId = rs.getInt("PersonId");
                    int departmentID = rs.getInt("DepartmentId");

                    System.out.println();

                    System.out.println("+-----+-----------------+---------------+------------+--------------+");
                    // Отпечатване на заглавния ред
                    System.out.printf("| %-3s | %-15s | %-13s | %-10s | %-12s |%n",
                            "ID", "OfficerBadgeNum", "OfficerRank", "PersonId", "DepartmentId");
                    // Отпечатване на разделителната линия
                    System.out.println("+-----+-----------------+---------------+------------+--------------+");
                    // Отпечатване на реда с данни
                    System.out.printf("| %-3d | %-15s | %-13s | %-10d | %-12d |%n",
                            id, officerBadgeNum, officerRank, personId, departmentID);
                    // Отпечатване на линията на долната граница
                    System.out.println("+-----+-----------------+---------------+------------+--------------+");

                }
                else if(tableName.equals("Criminal"))
                {
                    int id = rs.getInt("ID");
                    String criminalNum = rs.getString("CriminalNum");
                    int personId = rs.getInt("PersonId");

                    System.out.println();

                    // Отпечатване на горната граница на таблицата
                    System.out.println("+-----+---------------+-----------+");

                    // Отпечатване на заглавния ред с имената на колоните
                    System.out.printf("| %-3s | %-13s | %-9s |%n", "ID", "CriminalNum", "PersonID");

                    // Отпечатване на разделителната линия
                    System.out.println("+-----+---------------+-----------+");

                    // Отпечатване на реда с данни (стойностите на променливите)
                    System.out.printf("| %-3d | %-13s | %-9d |%n", id, criminalNum, personId);

                    // Отпечатване на долната граница на таблицата
                    System.out.println("+-----+---------------+-----------+");

                }
                else if(tableName.equals("Department"))
                {
                    int id = rs.getInt("ID");
                    String departmentName = rs.getString("DepartmentName");

                    System.out.println();

                    System.out.println("ID:" + id + ", Der" + ", DepartmentName:" + departmentName);
                    // Примерни ширини за колоните:

                    System.out.println("+-----+------------------+");
                    // Отпечатване на заглавния ред с имената на колоните
                    System.out.printf("| %-3s | %-16s |%n", "ID", "DepartmentName");
                    System.out.println("+-----+------------------+");
                    // Отпечатване на реда с данни
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

// Готово с преправено принтиране.