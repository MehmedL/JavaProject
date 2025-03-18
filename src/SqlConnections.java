import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SqlConnections {

    public void connection(String username, String password) {
        try (Connection connection = DriverManager.getConnection(GlobalConsts.URL, username, password)) {
            //System.out.println("Connected to the database successfully!");

            QueriesExecutor queriesExecutor = new QueriesExecutor(connection);

            String role = queriesExecutor.getCurrentRole();

            if(role != null && role.equals("`Sergeant`@`%`")){
                queriesExecutor.executeSergeantQueries();
            }
            else if(role != null && role.equals("`Lieutenant`@`%`")){
                Scanner scanner = new Scanner(System.in);

                System.out.println();
                System.out.println("1. Търсене");
                System.out.println("2. Добавяне");
                System.out.println("3. Редактиране");
                System.out.println("4. Изход");

                int operation = scanner.nextInt();
                String tableName = "";

                while (operation != 4){ // Докато не е избран 4. Изход, могат да се правят промени

                    System.out.println();
                    System.out.println("1. Криминално проявени лица"); // a.k.a Престъпник/таблица Criminal ;)
                    System.out.println("2. Престъпление");

                    int table = scanner.nextInt();

                    if(table == 1){
                        tableName = "Criminal";
                    }else{
                        tableName = "Crime";
                    }

                    if(queriesExecutor.executeLieutenantQueries(operation, tableName) == -1){
                        break;
                    }else{
                        queriesExecutor.executeLieutenantQueries(operation, tableName);
                    }

                    System.out.println();
                    System.out.println("1. Преглед");
                    System.out.println("2. Добавяне");
                    System.out.println("3. Редактиране");
                    System.out.println("4. Изход");

                    operation = scanner.nextInt();
                }// Още два if за другите две роли
            }

        } catch (SQLException e) {
            System.out.println("Login error! Deputies have no access to the DB!"); // Deputy е заместник-няма права
        }
    }
}
