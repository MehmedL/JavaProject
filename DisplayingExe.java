import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DisplayingExe {
    // Метод за извеждане на таблица на конзолата
    public void printTable(String query, Connection connection) {
        if (connection == null) {
            System.out.println("Няма активна връзка с базата данни.");
            return;
        }

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Брой колони в резултата
            int columnCount = resultSet.getMetaData().getColumnCount();

            // Заглавия на колоните
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSet.getMetaData().getColumnName(i) + "\t");
            }
            System.out.println();

            // Данни от редовете
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
