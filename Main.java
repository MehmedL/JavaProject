import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        String prefixedUsername = "2KCLUEa8MkJ89gc." + username; // Има задължителен префикс от базата, защото е с remote достъп

        SqlConnections sqlConnections = new SqlConnections();
        sqlConnections.connection(prefixedUsername, password);

    }
}


