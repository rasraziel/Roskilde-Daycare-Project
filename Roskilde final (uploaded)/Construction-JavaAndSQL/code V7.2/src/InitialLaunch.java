//this class is to handle the login, and launch the Main Menu from it.

import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InitialLaunch {
    Scanner scanner = new Scanner(System.in);
    String passwordPattern = "\\d{4}";

    public InitialLaunch() throws Exception {

        AsciiArt.printLogo();

        System.out.println("*************************** ***************");
        System.out.println("|   Welcome to ROSKILDE DAYCARE CENTER   |");
        System.out.println("******************************************");
        System.out.println("");

        boolean ok = true;
        while (ok) {
            System.out.print("Please enter Username: ");
            String un = scanner.next();
            System.out.print("Please enter 4 digit-password: ");
            String pw = validatePassword();

            App.getController().initiateTList();


            if (un.equalsIgnoreCase("admin") && pw.equalsIgnoreCase("1234")) {
                System.out.println("\n");
                Thread.sleep(500);
                new AdminMenu();
                ok = false;
            } else if (un.equalsIgnoreCase("emp") && pw.equalsIgnoreCase("0000")) {
                System.out.println("\n");
                Thread.sleep(500);
                new EmpMenu();
                ok = false;
            } else {
                System.out.println("We couldn't find any user. Please try again!\n\n");
            }
        }

    }


    private String validatePassword() {
        String password = scanner.next();
        boolean match = Pattern.matches(passwordPattern, password);
        while(!match) {
            System.out.println("Wrong format. Try again!  ---> (\"1234\") ");
            System.out.println("Password: ");
            password = scanner.next();
            match = Pattern.matches(passwordPattern, password);
        }
        return password;
    }
}
