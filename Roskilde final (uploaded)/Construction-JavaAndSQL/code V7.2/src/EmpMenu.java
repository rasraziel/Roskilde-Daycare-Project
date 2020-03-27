import java.util.Scanner;

public class EmpMenu {

    Scanner scanner = new Scanner(System.in);
    MenuManagement mm = new MenuManagement();

    public EmpMenu() throws Exception {
        System.out.println("***********************");
        System.out.println("|   EMPLOYEE MENU     |");
        System.out.println("***********************");
        System.out.println("    Manage:");
        System.out.println("|   [1] Parents       |");
        System.out.println("|   [2] Children      |");
        System.out.println("|   [3] Schedule      |");
        System.out.println("|   [0] Close Program |");
        System.out.println("***********************");


        boolean isWorking = true;

        while (isWorking) {
            System.out.println("Select Option: ");
            String answer = scanner.next();
            Thread.sleep(300);
            switch (answer) {
                case "1":
                    mm.parentMenu("emp");
                    break;
                case "2":
                    mm.childrenMenu("emp");
                    break;
                case "3":
                    mm.scheduleMenu("emp");
                    break;
                case "0":
                    System.out.println("System closing");
                    Thread.sleep(1000);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choice must be a value between \"0\" and \"3\".\n");
                    break;
            }
        }
    }

}
