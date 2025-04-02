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
                System.out.println("1. Преглед");
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
                    }else if (table == 2){
                        tableName = "Crime";
                    }
                    else {
                        break;
                    }

                    if(queriesExecutor.executeLieutenantQueries(operation, tableName) == -1){
                        break;
                    }

                    System.out.println();
                    System.out.println("1. Преглед");
                    System.out.println("2. Добавяне");
                    System.out.println("3. Редактиране");
                    System.out.println("4. Изход");

                    operation = scanner.nextInt();
                }
                scanner.close();

            }
            else if(role != null && role.equals("`Inspector`@`%`")){
                Scanner scanner = new Scanner(System.in);

                System.out.println();
                System.out.println("1. Преглед");
                System.out.println("2. Редактиране");
                System.out.println("3. Изход");

                int operation = scanner.nextInt();
                String tableName = "Crime"; // Винаги работим с "Crime"

                while (operation != 3) { // Докато не е избран 3. Изход, могат да се правят промени
                    if (queriesExecutor.executeInspectorQueries(operation, tableName) == -1) {
                        break;
                    }

                    System.out.println();
                    System.out.println("1. Преглед");
                    System.out.println("2. Редактиране");
                    System.out.println("3. Изход");

                    operation = scanner.nextInt();
                }
                scanner.close();
            }

            else if(role != null && role.equals("`Captain`@`%`")){
                Scanner scanner = new Scanner(System.in);

                System.out.println();
                System.out.println("1. Преглед");
                System.out.println("2. Добавяне");
                System.out.println("3. Редактиране");
                System.out.println("4. Изтриване");
                System.out.println("5. Изход");

                int operation = scanner.nextInt();
                String tableName = "";


                while (operation != 5){ // Докато не е избран 5. Изход, могат да се правят промени
                    if(operation == 1)
                    {
                        System.out.println();
                        System.out.println("1. Криминално проявени лица"); // a.k.a Престъпник/таблица Criminal ;)
                        System.out.println("2. Престъпление");
                        System.out.println("3. Полицаи");
                        System.out.println("4. Потърпевши");
                        System.out.println("5. Полицейски Отдел");

                        int table = scanner.nextInt();

                        if(table == 1){
                            tableName = "Criminal";
                        }else if (table == 2){
                            tableName = "Crime";
                        }
                        else if (table == 3)
                        {
                            tableName = "Policeofficer";
                        }
                        else if (table == 4)
                        {
                            tableName = "Victim";
                        }
                        else if (table == 5)
                        {
                            tableName ="Department";
                        }
                        else {
                            break;
                        }

                        if(queriesExecutor.executeCaptainQueries(operation, tableName) == -1){
                            break;
                        }
                    }
                    else {
                        System.out.println();
                        System.out.println("1. Криминално проявени лица"); // a.k.a Престъпник/таблица Criminal ;)
                        System.out.println("2. Престъпление");
                        System.out.println("3. Полицаи");
                        System.out.println("4. Потърпевши");

                        int table = scanner.nextInt();

                        if(table == 1){
                            tableName = "Criminal";
                        }else if (table == 2){
                            tableName = "Crime";
                        }
                        else if (table == 3)
                        {
                            tableName = "Policeofficer";
                        }
                        else if (table == 4)
                        {
                            tableName = "Victim";
                        }
                        else {
                            break;
                        }

                        if(queriesExecutor.executeCaptainQueries(operation, tableName) == -1){
                            break;
                        }
                    }


                    System.out.println();
                    System.out.println("1. Преглед");
                    System.out.println("2. Добавяне");
                    System.out.println("3. Редактиране");
                    System.out.println("4. Изход");

                    operation = scanner.nextInt();
                }
                scanner.close();

            }


        } catch (SQLException e) {
            System.out.println("Login error! Deputies have no access to the DB!"); // Deputy е заместник-няма права
        }
    }
}