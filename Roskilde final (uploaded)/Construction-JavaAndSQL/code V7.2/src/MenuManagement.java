import java.io.FileNotFoundException;
import java.util.Scanner;

public class MenuManagement {

    Scanner scanner = new Scanner(System.in);

    //accessing the parent menu based on the user type (admin/employee)
    public void parentMenu(String user) throws Exception {
        System.out.println("**********************************");
        System.out.println("|         MANAGE PARENTS         |");
        System.out.println("**********************************");
        System.out.println("|   [1] Create New Parent        |");
        System.out.println("|   [2] Update                   |");
        System.out.println("|   [3] Delete                   |");
        System.out.println("|   [4] Display Parents' Table   |");
        System.out.println("|   [5] Return To Previous Menu  |");
        System.out.println("|   [0] Close Program            |");
        System.out.println("**********************************");


        boolean isWorking = true;

        while (isWorking) {
            System.out.println("Select Option: ");

            String answer = scanner.next();

            switch (answer) {
                case "1":
                    App.getController().createParent();
                    parentMenu(user);
                    break;
                case "2":
                    App.getController().updateParent();
                    parentMenu(user);
                    break;
                case "3":
                    App.getController().deleteParent();
                    parentMenu(user);
                    break;
                case "4":
                    App.getController().displayParents();
                    parentMenu(user);
                    break;
                case "5":
                    System.out.println("\n");
                    Thread.sleep(300);
                    if (user.equals("admin")) {
                        new AdminMenu();
                    } else {
                        new EmpMenu();
                    }
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choice must be a value between \"0\" and \"5\".\n");
                    break;
            }
        }
    }


    //accessing the children menu based on the user type (admin/employee)
    public void childrenMenu(String user) throws Exception {
        System.out.println("**********************************");
        System.out.println("|        MANAGE CHILDREN         |");
        System.out.println("**********************************");
        System.out.println("|   [1] Create New Child         |");
        System.out.println("|   [2] Update                   |");
        System.out.println("|   [3] Delete                   |");
        System.out.println("|   [4] Display Children's Table |");
        System.out.println("|   [5] Return To Previous Menu  |");
        System.out.println("|   [0] Close Program            |");
        System.out.println("**********************************");


        boolean isWorking = true;

        while (isWorking) {
            System.out.println("Select Option: ");

            String answer = scanner.next();

            switch (answer) {
                case "1":
                    App.getController().createChild();
                    childrenMenu(user);
                    break;
                case "2":
                    App.getController().updateChild();
                    childrenMenu(user);
                    break;
                case "3":
                    App.getController().deleteChild();
                    childrenMenu(user);
                    break;
                case "4":
                    App.getController().displayChildren();
                    childrenMenu(user);
                    break;
                case "5":
                    System.out.println("\n");
                    Thread.sleep(300);
                    if (user.equals("admin")) {
                        new AdminMenu();
                    } else {
                        new EmpMenu();
                    }
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choice must be a value between \"0\" and \"5\".\n");
                    break;
            }
        }
    }


    //accessing the schedule menu based on the user type (admin/employee)
    public void scheduleMenu(String user) throws Exception {
        if (user.equals("admin")) {
            System.out.println("**********************************");
            System.out.println("|         MANAGE SCHEDULE        |");
            System.out.println("**********************************");
            System.out.println("|   [1] Create                   |");
            System.out.println("|   [2] Modify                   |");
            System.out.println("|   [3] Delete                   |");
            System.out.println("|   [4] Display Schedule         |");
            System.out.println("|   [5] Return To Previous Menu  |");
            System.out.println("|   [0] Close program            |");
            System.out.println("**********************************");

            boolean isWorking = true;

            while (isWorking) {
                System.out.println("Select Option: ");

                String answer = scanner.next();

                switch (answer) {
                    case "1":
                        App.getController().createSchedule();
                        scheduleMenu(user);
                        break;
                    case "2":
                        App.getController().updateSchedule();
                        scheduleMenu(user);
                        break;
                    case "3":
                        App.getController().deleteSchedule();
                        scheduleMenu(user);
                        break;
                    case "4":
                        App.getController().displaySchedules();
                        scheduleMenu(user);
                        break;
                    case "5":
                        System.out.println("\n");
                        Thread.sleep(300);
                        new AdminMenu();
                        break;
                    case "0":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Choice must be a value between \"0\" and \"5\".\n");
                        break;
                }
            }

        } else {
            System.out.println("**********************************");
            System.out.println("|            SCHEDULE            |");
            System.out.println("**********************************");
            System.out.println("|   [1] Display                  |");
            System.out.println("|   [2] Return To Previous Menu  |");
            System.out.println("|   [0] Close Program            |");
            System.out.println("**********************************");

            boolean isWorking = true;

            while (isWorking) {
                System.out.println("Select Option: ");

                String answer = scanner.next();

                switch (answer) {
                    case "1":
                        //App.getController().displaySchedule();
                        scheduleMenu(user);
                        break;
                    case "2":
                        System.out.println("\n");
                        Thread.sleep(300);
                        new EmpMenu();
                        break;
                    case "0":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Choice must be a value between \"0\" and \"2\".\n");
                        break;
                }
            }
        }
    }


    //this menu is accessed only by the administrator
    public void paymentMenu() throws Exception {
        System.out.println("**********************************");
        System.out.println("|         MANAGE PAYMENTS        |");
        System.out.println("**********************************");
        System.out.println("|   [1] Register Payment         |");
        System.out.println("|   [2] Display Paid Invoices    |");
        System.out.println("|   [3] Display Due Invoices     |");
        System.out.println("|   [4] Return To Previous Menu  |");
        System.out.println("|   [0] Close Program            |");
        System.out.println("**********************************");
        boolean isWorking = true;
        while (isWorking) {
            System.out.println("Select Option: ");

            String answer = scanner.next();

            switch (answer) {
                case "1":
                    App.getController().registerPayments();
                    paymentMenu();
                    break;
                case "2":
                    App.getController().viewPaid();
                    paymentMenu();
                    break;
                case "3":
                    App.getController().viewUnpaid();
                    paymentMenu();
                    break;
                case "4":
                    System.out.println("\n");
                    Thread.sleep(300);
                    new AdminMenu();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choice must be a value between \"0\" and \"4\".\n");
                    break;
            }
        }

    }


    //this menu is accessed only by the administrator
    public void employeeMenu() throws Exception {
        System.out.println("**********************************");
        System.out.println("|        MANAGE EMPLOYEES        |");
        System.out.println("**********************************");
        System.out.println("|   [1] Create New Employee      |");
        System.out.println("|   [2] Update                   |");
        System.out.println("|   [3] Delete                   |");
        System.out.println("|   [4] Display Employees' Table |");
        System.out.println("|   [5] Return To Previous Menu  |");
        System.out.println("|   [0] Close Program            |");
        System.out.println("**********************************");

        boolean isWorking = true;

        while (isWorking) {
            System.out.println("Select Option: ");

            String answer = scanner.next();

            switch (answer) {
                case "1":
                    App.getController().createEmployee();
                    employeeMenu();
                    break;
                case "2":
                    App.getController().updateEmployee();
                    employeeMenu();
                    break;
                case "3":
                    App.getController().deleteEmployee();
                    employeeMenu();
                    break;
                case "4":
                    App.getController().displayEmployees();
                    employeeMenu();
                    break;
                case "5":
                    System.out.println("\n");
                    Thread.sleep(300);
                    new AdminMenu();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choice must be a value between \"0\" and \"5\".\n");
                    break;
            }
        }

    }
}
