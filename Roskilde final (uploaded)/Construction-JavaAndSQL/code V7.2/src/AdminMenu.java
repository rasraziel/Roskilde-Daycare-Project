//this is the Main Menu class. It contains no methods, only calls/creates other Menu classes.

import java.util.Scanner;

public class AdminMenu {

    Scanner scanner = new Scanner(System.in);
    MenuManagement mm = new MenuManagement();

    //a delay time is added after certain operations
    public AdminMenu() throws Exception {
        System.out.println("***********************");
        System.out.println("|     ADMIN MENU      |");
        System.out.println("***********************");
        System.out.println("    Manage:");
        System.out.println("|   [1] Parents       |");
        System.out.println("|   [2] Children      |");
        System.out.println("|   [3] Schedule      |");
        System.out.println("|   [4] Payments      |");
        System.out.println("|   [5] Employees     |");
        System.out.println("|   [0] Close Program |");
        System.out.println("***********************\n");

        boolean isWorking = true;
        while (isWorking) {
            System.out.println("Select Option: ");
            String answer = scanner.next();
            Thread.sleep(300);
            switch (answer) {
                case "1":
                    mm.parentMenu("admin");
                    break;
                case "2":
                    mm.childrenMenu("admin");
                    break;
                case "3":
                    mm.scheduleMenu("admin");
                    break;
                case "4":
                    mm.paymentMenu();
                    break;
                case "5":
                    mm.employeeMenu();
                case "0":
                    System.out.println("System closing");
                    Thread.sleep(1000);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choice must be a value between \"0\" and \"5\".\n");
                    break;
            }
        }
    }
}
