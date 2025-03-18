import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        String prefixedUsername = "2KCLUEa8MkJ89gc." + username; // Има задължителен префикс от базата, защото е с remote достъп

//        System.out.println(prefixedUsername);
//        System.out.println(password);

        SqlConnections sqlConnections = new SqlConnections();
        sqlConnections.connection(prefixedUsername, password);
//=========================================================================================
//Test
        // Създаване на обект от класа за връзка с базата

//        // SQL заявка за извеждане на таблица
//        String query = "SELECT * FROM test.person";
//
//        // Създаване на обект от TablePrinter и извеждане на данните
//        DisplayingExe displayingExe = new DisplayingExe();
//        displayingExe.printTable(query, connection);
//
//        // Затваряне на връзката
//        try {
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}

// Идеята ми е:
// В main въвеждаме потр.име и парола, които дават достъп до базата според правата на ролите
// Извиква се sqlConnections, който осигурява свързването с базата и извиква QueriesExecutor методи, според ролята на логнатия user
// В GlobalConsts е url към базата
// В QueriesExecutor ще има методи за всяка роля,в които се извикват методи от други класове - засега InsertionExe и DisplayingExe,
// Защото така няма да се повтаря код, например :
// Лейтенант, инспектор и капитан могат да преглеждат таблица Crime, затова ще направим метод в DisplayingExe, който изкарва резултати от нея
// и ще го извикваме и за тримата и тнт
// DisplayingExe ще има методи, които изкарват в конзолата всяка таблица
// InsertionExe ще има методи, които добавят в съответна таблица
// За редактиране още нямам много идея
// И ще направим един DeleteExe клас, който ще изтрива от съответната таблица

