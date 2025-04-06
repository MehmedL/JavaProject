import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnections {

    public void connection(String username, String password) {
        try (Connection connection = DriverManager.getConnection(GlobalConsts.URL, username, password)) {
            QueriesExecutor queriesExecutor = new QueriesExecutor(connection);

            String role = queriesExecutor.getCurrentRole();

            if(role != null && role.equals("`Sergeant`@`%`")){
                queriesExecutor.executeSergeantQueries();
            }
            else if(role != null && role.equals("`Lieutenant`@`%`")){
                Scanner scanner = new Scanner(System.in);

                System.out.println();
                System.out.println("1. View");
                System.out.println("2. Add new");
                System.out.println("3. Exit");

                int operation = scanner.nextInt();
                String tableName = "";

                while (operation != 3){ // Докато не е избран 3. Изход, могат да се правят промени

                    System.out.println();
                    System.out.println("1. Criminal Offenders");
                    System.out.println("2. Crimes");

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
                    System.out.println("1. View");
                    System.out.println("2. Add new");
                    System.out.println("3. Exit");

                    operation = scanner.nextInt();
                }
                scanner.close();
            }
            else if(role != null && role.equals("`Inspector`@`%`")){
                Scanner scanner = new Scanner(System.in);

                System.out.println();
                System.out.println("1. View");
                System.out.println("2. Update");
                System.out.println("3. Exit");

                int operation = scanner.nextInt();
                String tableName = "Crime";

                while (operation != 3) { // Докато не е избран 3. Изход, могат да се правят промени
                    if (queriesExecutor.executeInspectorQueries(operation, tableName) == -1) {
                        break;
                    }

                    System.out.println();
                    System.out.println("1. View");
                    System.out.println("2. Update");
                    System.out.println("3. Exit");

                    operation = scanner.nextInt();
                }
                scanner.close();
            }

            else if(role != null && role.equals("`Captain`@`%`")){
                Scanner scanner = new Scanner(System.in);

                System.out.println();
                System.out.println("1. View");
                System.out.println("2. Add new");
                System.out.println("3. Update");
                System.out.println("4. Delete");
                System.out.println("5. Exit");

                int operation = scanner.nextInt();
                String tableName = "";


                while (operation != 5){ // Докато не е избран 5. Изход, могат да се правят промени
                    if(operation == 1)
                    {
                        System.out.println();
                        System.out.println("1. Criminal Offenders");
                        System.out.println("2. Crimes");
                        System.out.println("3. Police Officers");
                        System.out.println("4. Victims");
                        System.out.println("5. Police Departments");

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
                    else if(operation == 3)
                    {
                        System.out.println();
                        System.out.println("1. Crimes");
                        System.out.println("2. Police Officers");


                        int table = scanner.nextInt();

                        if(table == 1){
                            tableName = "Crime";
                        }else if (table == 2){
                            tableName = "Policeofficer";
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
                        System.out.println("1. Criminal Offenders");
                        System.out.println("2. Crimes");
                        System.out.println("3. Police Officers");
                        System.out.println("4. Victims");

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
                    System.out.println("1. View");
                    System.out.println("2. Add new");
                    System.out.println("3. Update");
                    System.out.println("4. Delete");
                    System.out.println("5. Exit");

                    operation = scanner.nextInt();
                }
                scanner.close();

            }


        } catch (SQLException e) {
            System.out.println("Login error! Deputies have no access to the DB!"); // Deputy е заместник-няма права
        }
    }
}
