/*SERVICE - service contains all the methods and calculations for the program to run.
It will be a large class. We can break it to sub-classes if needed.
Please try to keep similar methods grouped together, for example all retrieval or population methods*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class Service {

    private static Database database = new Database();
    Scanner scanner = new Scanner(System.in);

    public Service(){
    }

    public void createParent() throws FileNotFoundException{
        Parent parent = new Parent();
        String firstName = createFirstName();
        parent.setFirstName(firstName);
        parent.setLastName(createLastName(firstName));
        parent.setDateOfBirth(createDateOfBirth(firstName));
        parent.setGender(createGender(firstName));
        parent.setAddress(createAddress(firstName));
        parent.setMailAddress(createMailAddress(firstName));
        parent.setPhoneNumber(createPhoneNumber(firstName));
        parent.setParentId(createUniqueId());
        parent.setPaid(false);
        printSlow(".................", 100);
        System.out.println();
        System.out.println(parent.toStringConsole());
        System.out.println("Please check information is correct. Type 'yes' to proceed. Type 'no' to cancel.");
        String answer = scanner.next();
        while(!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
            System.out.println("Not a valid response. Please type 'yes' to proceed. Type 'no' to cancel.");
            answer = scanner.next();
        }
        if(answer.equalsIgnoreCase("yes")){
            database.getParents().add(parent);
            saveFile(database.getParents(), 4);
        } else {
            System.out.println("Deleting information.");
        }
        new MenuManagement(); //CHECKME - returns to right menu?
    }

    public void createChild() throws FileNotFoundException{
        Child child = new Child();
        String firstName = createFirstName();
        int index = 0;
        int numOfKids = 0;
        child.setFirstName(firstName);
        child.setLastName(createLastName(firstName));
        child.setDateOfBirth(createDateOfBirth(firstName));
        child.setGender(createGender(firstName));
        child.setAddress(createAddress(firstName));
        child.setPhoneNumber(createPhoneNumber(firstName));
        child.setChildId(createUniqueId());
        index = matchParentKidId();
        child.setParentId(database.getParents().get(index).getParentId());
        printSlow(".................", 100);
        System.out.println();
        System.out.println(child.toStringConsole());
        System.out.println("Please check information is correct. Type 'yes' to proceed. Type 'no' to cancel.");
        String answer = scanner.next();
        while(!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
            System.out.println("Not a valid response. Please type 'yes' to proceed. Type 'no' to cancel.");
            answer = scanner.next();
        }
        if(answer.equalsIgnoreCase("yes")){
            database.getChildren().add(child);
            numOfKids = database.getParents().get(index).getNumOfKids() + 1;
            database.getParents().get(index).setNumOfKids(numOfKids);
            saveFile(database.getChildren(), 2);
            saveFile(database.getParents(), 4);
        } else {
            System.out.println("Deleting information.");
        }
        new MenuManagement(); //CHECKME - returns to right menu?
    }

    public void createEmployee() throws FileNotFoundException{
        Employee employee = new Employee();
        String firstName = createFirstName();
        employee.setFirstName(firstName);
        employee.setLastName(createLastName(firstName));
        employee.setDateOfBirth(createDateOfBirth(firstName));
        employee.setGender(createGender(firstName));
        employee.setAddress(createAddress(firstName));
        employee.setPhoneNumber(createPhoneNumber(firstName));
        employee.setCpr(createCpr(firstName));
        employee.setSalary(createSalary(firstName));
        employee.setIsAdmin(createAdmin(firstName));
        printSlow(".................", 100);
        System.out.println();
        System.out.println(employee.toStringConsole());
        System.out.println("Please check information is correct. Type 'yes' to proceed. Type 'no' to cancel.");
        String answer = scanner.next();
        while(!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
            System.out.println("Not a valid response. Please type 'yes' to proceed. Type 'no' to cancel.");
            answer = scanner.next();
        }
        if(answer.equalsIgnoreCase("yes")){
            database.getEmployees().add(employee);
            saveFile(database.getEmployees(), 1);
        } else {
            System.out.println("Deleting information.");
        }
        new MenuManagement(); //CHECKME - returns to right menu?
    }

    //misc methods


    public int getNumber(String errorMessage) { //method to return only a number with zero chance of error

        boolean error;
        int number=0;
        do	{
            try {
                Scanner scanner = new Scanner(System.in);
                number = scanner.nextInt();
                error=false;
            }
            catch(InputMismatchException e) {
                System.out.println(errorMessage);
                error=true;
            }
        }
        while(error);
        return number;
    }

    //=============================================================================NEW CODE BELOW

    public void pressKey() {
        try {
            System.out.print("Press Enter to continue...");
            System.in.read();
        } catch (Exception e){
            }
    }

    //=============================================================================NEW CODE BELOW

    public void printSlow(String text, int interval) {
        try {
            for (char ch : text.toCharArray()) {
                System.out.print(ch + " ");
                TimeUnit.MILLISECONDS.sleep(interval);
            }
        } catch (Exception e) {
        }

    }

    //creation methods
    //=============================================================================NEW CODE BELOW

    public String createFirstName(){
        System.out.println("Please enter First Name: ");
        return scanner.next();
    }

    //=============================================================================NEW CODE BELOW

    public String createLastName(String firstName){
        System.out.println("Please enter " + firstName + "'s Surname: ");
        return scanner.next();
    }

    public int createUniqueId() throws FileNotFoundException{
        int pid = 0;
        Scanner fileParentID = new Scanner(new File("pid.txt"));
        pid = fileParentID.nextInt() + 1;
        fileParentID.close();
        PrintStream fileUpdate = new PrintStream(new FileOutputStream("pid.txt"));
        fileUpdate.print(pid);
        fileUpdate.flush();
        fileUpdate.close();
        return pid;
    }

    public int matchParentKidId() throws FileNotFoundException{
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Select the parent:");
        displayParents();
        System.out.println("Enter parent ID:");
        int readPid = myScanner.nextInt();
        boolean flag = false;
        int index = 0, i = 0;

        while(!flag && index<database.getParents().size()){
            if(database.getParents().get(i).getParentId()==readPid){
                flag = true;
                index = i;
            }
         i++;
        }
        if(!flag){
            System.out.println("Wrong parent ID. Try again:");
            matchParentKidId();
        }
        return index;
    }

    //=============================================================================NEW CODE BELOW

    public String createMailAddress(String firstName) {
        System.out.println("Please enter " + firstName + "'s email address: ");
        return scanner.next();
    }

    //=============================================================================NEW CODE BELOW HAS ERROR CHECKME
    // it does not accept any answer as correct, even if M or F are entered

    public Person.Gender createGender(String firstName){
        Person.Gender gender = Person.Gender.M;
        System.out.println("What is " + firstName + "'s gender? Please enter 'M' or 'F'");
        String answer = scanner.next();
        if(answer.equalsIgnoreCase("m")){
            return gender;
        } else if(answer.equalsIgnoreCase("f")){
            return Person.Gender.F;
        } else {
            System.out.println("Not a valid answer. Please try again.");
            createGender(firstName);
        }
        return gender;
    }

    //=============================================================================NEW CODE BELOW HAS ERROR CHECKME
    // not correct address, just using as a placeholder for now
    public String createAddress(String firstName){
        System.out.println("Please enter " + firstName + "'s address.");
        return scanner.next() + " " + scanner.next() + " " + scanner.next();
    }

    //=============================================================================NEW CODE BELOW HAS ERROR CHECKME
    // if incorrect info is entered the first time, it does not correct it the next time round.

    public String createDateOfBirth(String firstName){
        int year, month, day;
        boolean ok = true;
        do {
            System.out.println("Please enter " + firstName + "'s Date of birth: ");
            System.out.println("Year of birth: ");
            year = getNumber("Not a valid year. Please try again");
            System.out.println("Please enter a numerical month. For example, enter '1' for January.");
            month = getNumber("Please enter a numerical month. For example, enter '1' for January.");
            System.out.println("Please enter a valid day of the month, between 1 and 31");
            day = getNumber("Please enter a valid day of the month, between 1 and 31");
            if (year < 2020 && year > 1900 && month > 0 && month < 13 && day > 0 && day < 32) {
                ok = false;
            } else {
                System.out.println("Not a valid date of birth. Please try again\n");
            }
        } while (ok);
        return year + "-" + month + "-" + day;
    }

    //=============================================================================NEW CODE BELOW

    String phoneNumberPattern = "\\d{8}";
    public String createPhoneNumber(String firstName) {
        Scanner console = new Scanner(System.in);
        String errorMessage = "Sorry, not a valid 8-digit number. Please re-enter.";
        System.out.println("Please enter " + firstName + "'s phone number: ");
        String number = console.next();
        boolean match = Pattern.matches(phoneNumberPattern, number);
        while(!match){
            System.out.println(errorMessage);
            System.out.println("Please enter " + firstName + "'s phone number: ");
            number = scanner.next();
            match = Pattern.matches(phoneNumberPattern, number);
        }
        return number;
    }

    //=============================================================================NEW CODE BELOW

    public boolean createAdmin(String firstName){
         System.out.println("Will " + firstName + " be a System Administrator? Please type 'yes' or 'no'");
        String answer = scanner.next();
        if(answer.equalsIgnoreCase("yes")){
            return true;
        }
        return false;
    }

    //=============================================================================NEW CODE BELOW

    public double createSalary(String firstName){
        System.out.println("Please enter " + firstName + "'s monthly Salary: ");
        return getNumber("Not a valid number. Please try again");
    }

    //=============================================================================NEW CODE BELOW

    public String createCpr(String firstName) {
        System.out.println("Please enter " + firstName + "'s first six CPR digits: ");
        int cpr1 = getNumber("Not a valid number. Please try again");
        while (cpr1 > 31032020){
            cpr1 = getNumber("Not a valid number. Please try again");
        }
        System.out.println("Please enter " + firstName + "'s last four CPR digits: ");
        int cpr2 = getNumber("Not a valid number. Please try again");
        while (cpr2 > 9999){
            cpr2 = getNumber("Not a valid number. Please try again");
        }
        return cpr1 + "-" + cpr2;
    }

    public boolean doesParentExist(int id){
        for (int i=0; i<database.getParents().size(); i++) {
            if (database.getParents().get(i).getParentId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean doesChildExist(int id){
        for(int i=0; i<database.getChildren().size(); i++){
            if(database.getChildren().get(i).getChildId()==id){
                return true;
            }
        }
        return false;
    }

    /* public boolean doesEmployeeExist(int id){
        for(int i=0; i<database.getEmployees().size(); i++){
            if(database.getEmployees().get(i).getEmployeeId()==id){
                return true;
            }
        }
        return false;
    } */

    public void updateParent() throws FileNotFoundException{
        String firstName = "";
        int index=0;
        System.out.println("Please enter the Parent ID you wish to modify.");
        int number = getNumber("Number does not exist. Please try again");
        boolean check = doesParentExist(number);
        if(!check){
            System.out.println("Person does not exist on system. Please try again.");
            updateParent();
        }
        for(int i=0; i<database.getParents().size(); i++){
            if(database.getParents().get(i).getParentId()==number){
                index = i;
            }
        }
        boolean ok = true;

        do{
            System.out.println("Update:");
            System.out.println("|   [1] Name");
            System.out.println("|   [2] Address");
            System.out.println("|   [3] Phone Number");
            System.out.println("|   [4] Email");
            System.out.println("|   [5] Return To Previous Menu");
            System.out.println("*******************************\n");
            System.out.println("Select Option: ");

            String answer = scanner.next();
            switch (answer){
                case "1":
                    firstName = createFirstName();
                    database.getParents().get(index).setFirstName(firstName);
                    database.getParents().get(index).setLastName(createLastName(firstName));
                    System.out.println("The name has been updated!");
                    saveFile(database.getParents(), 4);
                    break;
                case "2":
                    database.getParents().get(index).setAddress(createAddress(firstName));
                    System.out.println("The address has been updated!");
                    saveFile(database.getParents(), 4);
                    break;
                case "3":
                    database.getParents().get(index).setPhoneNumber(createPhoneNumber(firstName));
                    System.out.println("The phone number has been updated!");
                    saveFile(database.getParents(), 4);
                    break;
                case "4":
                    database.getParents().get(index).setMailAddress(createMailAddress(firstName));
                    System.out.println("The email address has been updated!");
                    saveFile(database.getParents(), 4);
                    break;
                case "5":
                    System.out.println("\n");
                    ok = false;
                    break;
                default:
                    System.out.println("Choice must be a value between \"1\" and \"5\".\n");
                    break;
            }
        } while (ok);
        //new AdminMenu();
    }

    public void updateChild() throws FileNotFoundException{
        String firstName = "";
        int index=0;
        System.out.println("Please enter the Child ID you wish to modify.");
        int number = getNumber("Number does not exist. Please try again");
        boolean check = doesChildExist(number);
        if(!check){
            System.out.println("Person does not exist on system. Please try again.");
            updateChild();
        }
        for(int i=0; i<database.getChildren().size(); i++){
            if(database.getChildren().get(i).getChildId()==number){
                index = i;
            }
        }
        System.out.println("To change, press [1] for name, [2] for address, [3] for phone number.");
        String answer = scanner.next();
        switch (answer){
            case "1":
                firstName = createFirstName();
                database.getChildren().get(index).setFirstName(firstName);
                database.getChildren().get(index).setLastName(createLastName(firstName));
                System.out.println("The name has been updated!");
                saveFile(database.getChildren(), 2);
                //menu option?
                break;
            case "2":
                database.getChildren().get(index).setAddress(createAddress(firstName));
                System.out.println("The address has been updated!");
                saveFile(database.getChildren(), 2);
                //menu option??
                break;
            case "3":
                database.getChildren().get(index).setPhoneNumber(createPhoneNumber(firstName));
                System.out.println("The phone has been updated!");
                saveFile(database.getChildren(), 2);
                //menu option??
                break;
        }
        //new AdminMenu();
    }

    public void updateEmployee() throws FileNotFoundException{
        displayEmployees();
        int number =-1;
        System.out.println("Please enter the CPR number of the Employee you wish to modify:");
        String cpr = scanner.next();
        for(int i=0; i<database.getEmployees().size(); i++){
            if(cpr.equalsIgnoreCase(database.getEmployees().get(i).getCpr())){
                number=i;
            }
        }
        if(number==-1){
            System.out.println("Sorry, number does not match. Please try again.");
            updateEmployee();
        }

        String firstName = database.getEmployees().get(number).getFirstName();
        System.out.println("To change, press [1] for name, [2] for address, [3] for phone number, [4] for CPR, [5] for salary.");
        String answer = scanner.next();
        switch (answer){
            case "1":
                database.getEmployees().get(number).setFirstName(createFirstName());
                database.getEmployees().get(number).setLastName(createLastName(firstName));
                System.out.println("The name has been updated!");
                saveFile(database.getEmployees(), 1);
                //menu option?
                break;
            case "2":
                database.getEmployees().get(number).setAddress(createAddress(firstName));
                System.out.println("The address has been updated!");
                saveFile(database.getEmployees(), 1);
                //menu option??
                break;
            case "3":
                database.getEmployees().get(number).setPhoneNumber(createPhoneNumber(firstName));
                System.out.println("The phone number has been updated!");
                saveFile(database.getEmployees(), 1);
                //menu option??
                break;
            case "4":
                database.getEmployees().get(number).setCpr(createCpr(firstName));
                System.out.println("The cpr has been updated!");
                saveFile(database.getEmployees(), 1);
                //menu option??
                break;
            case "5":
                database.getEmployees().get(number).setSalary(createSalary(firstName));
                System.out.println("The salary has been updated!");
                saveFile(database.getEmployees(), 1);
                //menu option??
                break;
            default:
                System.out.println("Incorrect. try again");
        }
        //new AdminMenu();
    }

    //display methods

    public void displayParents() {
        System.out.println("PARENTS");
        System.out.printf("%-20s%-20s%-20s%-20s%-30s%-20s%-20s%-20s%-20s%-20s\n", "First Name", "Last Name", "DOB", "Gender", "Address", "Telephone", "Parent ID", "Number of children", "E-mail", "Payment status");
        System.out.println("******************************************************************************************************************************************************************************************");
        for (int i = 0; i < database.getParents().size(); i++) {
            System.out.println(database.getParents().get(i));
        }
        System.out.println("===========================================================================================================================================================================================");
    }

    public void displayChildren() {
        String parentName = "", parentAddress = "", parentPhone = "";
        System.out.println("CHILDREN");
        System.out.printf("%-20s%-20s%-20s%-20s%-30s%-20s%-20s%-20s%-30s\n", "First Name", "Last Name", "DOB", "Gender", "Address", "Parent's Telephone", "Child ID", "Parent ID", "Parent Name");
        System.out.println("******************************************************************************************************************************************************************************************");
        for (int i = 0; i < database.getChildren().size(); i++){
            for(int j = 0; j<database.getParents().size(); j++){
                if(database.getChildren().get(i).getParentId()==database.getParents().get(j).getParentId()){
                    parentName = database.getParents().get(j).getFirstName() + " " + database.getParents().get(j).getLastName();
                }
            }
            System.out.println(database.getChildren().get(i) + parentName);
        }
        System.out.println("===========================================================================================================================================================================================");
    }

    public void displayEmployees() {
            System.out.println("EMPLOYEES");
            System.out.printf("%-20s%-20s%-20s%-20s%-30s%-20s%-20s%-20s%-20s\n", "First Name", "Last Name", "DOB", "Gender", "Address", "Telephone", "CPR", "Salary", "Admin status");
            System.out.println("******************************************************************************************************************************************************************************************");
            for (int i = 0; i < database.getEmployees().size(); i++) {
                System.out.println(database.getEmployees().get(i));
            }
            System.out.println("===========================================================================================================================================================================================");
        }

    //deletion methods

    public void deleteParent() throws FileNotFoundException{
        String firstName = "";
        System.out.println("Please enter the Parent ID you wish to delete.");
        int number = getNumber("Number does not exist. Please try again");
        boolean check = doesParentExist(number);
        if(!check){
            System.out.println("Person does not exist on system. Please try again.");
            updateParent();
        }
        for (int i=0; i<database.getParents().size(); i++){
            if(database.getParents().get(i).getParentId()==number){
                firstName = database.getParents().get(i).getFirstName();
            }
        }
        System.out.println("Are you sure you want to delete " + firstName + " and all their data? This CANNOT be reversed.");
        System.out.println("Type 'yes' to proceed, or any other letter to cancel");
        String answer = scanner.next();
        if(!answer.equalsIgnoreCase("yes")){
            System.out.println("Information not deleted. Reverting to previous menu.");
            new MenuManagement();
        }
        for (int i=0; i<database.getParents().size(); i++){
            if(database.getParents().get(i).getParentId()==number){
                database.getParents().remove(i);
            }
        }
        saveFile(database.getParents(), 4);
        for (int i=0; i<database.getChildren().size(); i++){
            if(database.getChildren().get(i).getParentId()==number){
                database.getChildren().remove(i);
            }
        }
        saveFile(database.getChildren(), 2);
        System.out.println("Information deleted. Reverting to previous Menu.");
        new MenuManagement();
    }

    public void deleteChild() throws FileNotFoundException{
        displayChildren();
        System.out.println("Please enter the Child ID you wish to delete.");
        int number = getNumber("Number does not exist. Please try again");
        boolean check = doesChildExist(number);
        if(!check){
            System.out.println("Person does not exist on system. Please try again.");
            updateChild();
        }
        String firstName = database.getChildren().get(number).getFirstName();
        int pid = database.getChildren().get(number).getParentId();
        int index = 0;
        System.out.println("Are you sure you want to delete " + firstName + " and all their data? This CANNOT be reversed.");
        System.out.println("Type 'yes' to proceed, or any other letter to cancel");
        String answer = scanner.next();
        if(!answer.equalsIgnoreCase("yes")){
            System.out.println("Information not deleted. Reverting to previous menu.");
            new MenuManagement();
        }

        for (int i=0; i<database.getParents().size(); i++){
            if(database.getParents().get(i).getParentId()==pid){
                index = i;
            }
        }
        int numOfKids = database.getParents().get(index).getNumOfKids() - 1;
        database.getParents().get(index).setNumOfKids(numOfKids);
        for (int i=0; i<database.getChildren().size(); i++){
            if(database.getChildren().get(i).getChildId()==number){
                database.getChildren().remove(i);
                break;
            }
        }
        saveFile(database.getChildren(), 2);
        saveFile(database.getParents(), 4);
        System.out.println("Information deleted. Reverting to previous Menu.");
        new MenuManagement();
    }

    public void deleteEmployee() throws FileNotFoundException{
        displayEmployees();
        int number =-1;
        System.out.println("Please enter the CPR number of the Employee you wish to modify:");
        String cpr = scanner.next();
        for(int i=0; i<database.getEmployees().size(); i++){
            if(cpr.equalsIgnoreCase(database.getEmployees().get(i).getCpr())){
                number=i;
            }
        }
        if(number==-1){
            System.out.println("Sorry, number does not match. Please try again.");
            updateEmployee();
        }
        String firstName = database.getEmployees().get(number).getFirstName();
        System.out.println("Are you sure you want to delete " + firstName + " and all their data? This CANNOT be reversed.");
        System.out.println("Type 'yes' to proceed, or any other letter to cancel");
        String answer = scanner.next();
        if(!answer.equalsIgnoreCase("yes")){
            System.out.println("Information not deleted. Reverting to previous menu.");
            new MenuManagement();
        }
        database.getEmployees().remove(number);
        saveFile(database.getEmployees(),1);
        System.out.println("Information deleted. Reverting to previous Menu.");
        new MenuManagement();
    }

    //file handling methods

    //=============================================================================NEW CODE BELOW

    //Pass fileType = 1 for employees.txt, = 2 for children.txt, = 3 for schedules.txt
    // = 4 for parents.txt, = 5 for parentsunpaid.txt
    public File chooseFile(int fileType) {
        String fileName = "ERROR.txt";

        switch (fileType) {
            case 1:
                fileName = "employees.txt";
                break;
            case 2:
                fileName = "children.txt";
                break;
            case 3:
                fileName = "schedules.txt";
                break;
            case 4:
                fileName = "parents.txt";
                break;
        }

        File destination = new File(fileName);

        return destination;
    }

    //=============================================================================NEW CODE BELOW

    //Method handling the saving of files
    public void saveFile(ArrayList list, int fileType) throws FileNotFoundException {
        PrintStream toFile = new PrintStream(chooseFile(fileType));
        for (int i = 0; i < list.size(); i++) {
            if(i == list.size() - 1) {
                toFile.print(list.get(i));
            } else {
                toFile.print(list.get(i));
                toFile.print("\n");
            }
        }
        toFile.flush();
        toFile.close();
    }

    //=============================================================================NEW CODE BELOW

    //Method in charge of loading files into ArrayLists
    public void loadFile(ArrayList list, int fileType) throws Exception {
        Scanner scFile = new Scanner(chooseFile(fileType));

        while (scFile.hasNext()) {
            Scanner currentLine = new Scanner(scFile.nextLine());
            switch (fileType) {
                case 1:
                    Employee eAdd = new Employee(personLine(currentLine, fileType));
                    list.add(employeeLine(eAdd, currentLine));
                    break;
                case 2:
                    Child cAdd = new Child(personLine(currentLine, fileType));
                    list.add(childLine(cAdd, currentLine));
                    break;
                case 3:
                    list.add(scheduleLine(currentLine));
                    break;
                case 4:
                    Parent paAdd = new Parent(personLine(currentLine, fileType));
                    list.add(parentLine(paAdd, currentLine));
                    break;
            }
        }
    }

    //=============================================================================NEW CODE BELOW

    //Method converting the contents of a Scanner into a Person object
    public Person personLine(Scanner toAdd, int fileType) {
        Person newGuy = new Person();
        newGuy.setFirstName(toAdd.next());
        newGuy.setLastName(toAdd.next());
        newGuy.setDateOfBirth(toAdd.next());

        //Manual conversion from String to gender
        switch (toAdd.next()) {
            case "M":
                newGuy.setGender(Person.Gender.M);
                break;
            case "F":
                newGuy.setGender(Person.Gender.F);
                break;
        }

        //Child objects have these fields as null, therefore skip condition if fileType corresponds to Child
        if (fileType != 2) {
            //Extracting address from file
            String tempAddress = "";
            int totalATokens = 3;
            for (int i = 0; i < totalATokens; ++i) {
                tempAddress += toAdd.next() + " ";
            }
            newGuy.setAddress(tempAddress);
            newGuy.setPhoneNumber(toAdd.next());
        }
        return newGuy;
    }

    //=============================================================================NEW CODE BELOW

    //Method converting the contents of a Scanner into an Employee object
    public Employee employeeLine(Person currentObj, Scanner line)    {
        Employee toList = new Employee(currentObj);
        toList.setCpr(line.next());
        toList.setSalary(Double.parseDouble(line.next()));
        toList.setIsAdmin(line.nextBoolean());

        return toList;
    }

    //=============================================================================NEW CODE BELOW

    //Method converting the contents of a Scanner into a Child object
    public Child childLine(Person currentObj , Scanner line) {
        Child toList = new Child(currentObj);
        toList.setAddress(line.next() +  " " + line.next() + " " + line.next());
        toList.setPhoneNumber(line.next());
        toList.setChildId(line.nextInt());
        toList.setParentId(line.nextInt());

        return toList;
    }

    //=============================================================================NEW CODE BELOW

    //Method converting the contents of a Scanner into a Schedule object
    public ScheduleWeek scheduleLine(Scanner line) {
        ScheduleWeek toList = new ScheduleWeek();
        toList.setWeekNumber(line.nextInt());

        for (int i = 0; i < toList.getDaysList().size(); ++i) {

            //Discard day
            line.next();

            ArrayList<String> staffList = new ArrayList();
            while (line.hasNext()) {
                String buffer = line.nextLine();
                line = new Scanner(buffer);
                Scanner checkNext = new Scanner(buffer);
                if (isDay(checkNext.next())) {
                    break;
                }
                staffList.add(line.next());
            }
            toList.getDaysList().get(i).setStaffOnShift(staffList);
        }
        return toList;
    }

    //=============================================================================NEW CODE BELOW

    //Condition used by logic inside ScheduleLine()
    public boolean isDay(String token) {
        return (token.equals("MON") || token.equals("TUE") || token.equals("WED") || token.equals("THU") ||
                token.equals("FRI") || token.equals("SAT") || token.equals("SUN"));
    }

    //=============================================================================NEW CODE BELOW

    //Method converting the contents of a Scanner into a Parent object
    public Parent parentLine(Person currentObj, Scanner line) {
        Parent toList = new Parent(currentObj);
        toList.setParentId(line.nextInt());
        toList.setNumOfKids(line.nextInt());
        toList.setMailAddress(line.next());
        toList.setPaid(line.nextBoolean());
        return toList;
    }

    //=============================================================================NEW CODE BELOW

    //calculation methods

    public void initiateTList() throws Exception {
        loadFile(database.getEmployees(), 1);
        loadFile(database.getChildren(), 2);
        loadFile(database.getSchedules(), 3);
        loadFile(database.getParents(), 4);
    }



    //display methods

    public void registerPayments() throws FileNotFoundException, Exception {
        int listSize = database.getParents().size();
        System.out.println("Please enter the ID of the parent you want to register the payment for:");
        Scanner input = new Scanner(System.in);
        int pid = input.nextInt();
        boolean flag = false;
        for (int i = 0; i < listSize; i++) {
            if (database.getParents().get(i).getParentId() == pid && database.getParents().get(i).getNumOfKids()!=0 && !database.getParents().get(i).isPaid()) {
                System.out.println("The parent has " + database.getParents().get(i).getNumOfKids() + " child/children");
                System.out.println("The amount to pay is " + database.getParents().get(i).getNumOfKids() * 3600 + " DKK");
                System.out.print("Register payment (y/n):");
                String answer = input.next();
                while (!answer.equals("y") && !answer.equals("n")) {
                    System.out.println("Wrong entry. Try again: (y/n)");
                    answer = input.next();
                }
                if (answer.equals("y")) {
                    String invoice = prepareInvoice(database.getParents().get(i).getFirstName(), database.getParents().get(i).getLastName(), database.getParents().get(i).getNumOfKids());
                    System.out.println(invoice);
                    MailService.sendMail(database.getParents().get(i).getMailAddress(), invoice);
                    database.getParents().get(i).setPaid(true);
                } else {
                    System.out.println("No payment entered!");
                }
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("The parent was not found! Try again!");
            registerPayments();
        } else {
            saveFile(database.getParents(), 4);
        }
    }

    public void viewPaid(){
        System.out.println("PAID INVOICES");
        System.out.printf("%-20s%-20s%-20s%-20s%-30s%-20s%-20s%-20s%-30s%-20s\n", "First Name", "Last Name", "DOB", "Gender", "Address", "Telephone", "Parent ID", "Number Of Children", "Email", "Payment Status");
        System.out.println("************************************************************************************************************************************************************************************************************");
        for (int i = 0; i < database.getParents().size(); i++) {
        if(database.getParents().get(i).isPaid())
            System.out.println(database.getParents().get(i));
        }
        System.out.println("============================================================================================================================================================================================================");
    }

    public void viewUnpaid(){
        System.out.println("UNPAID INVOICES");
        System.out.printf("%-20s%-20s%-20s%-20s%-30s%-20s%-20s%-20s%-30s%-20s\n", "First Name", "Last Name", "DOB", "Gender", "Address", "Telephone", "Parent ID", "Number Of Children", "Email", "Payment Status");
        System.out.println("************************************************************************************************************************************************************************************************************");
        for (int i = 0; i < database.getParents().size(); i++) {
            if(!database.getParents().get(i).isPaid() && database.getParents().get(i).getNumOfKids()!=0)
                System.out.println(database.getParents().get(i));
        }
        System.out.println("============================================================================================================================================================================================================");
    }

    public String prepareInvoice(String fname, String lname, int numOfCh) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "************************************************\n" +
                "|~~~~~~~  ROSKILDE DAYCARE INVOICE  ~~~~~~~~~~~|\n" +
                "************************************************\n\n" +
                "                    Invoice                    \n\n" +
                "   Date: " + sdf.format(date) + "\n" +
                "   Name: Mr/Mrs " + fname + " " + lname + "\n" +
                "   Number of children: " + numOfCh + "\n" +
                "   Net Amount: " + 3000 * numOfCh + " DKK\n" +
                "   Total Amount (including VAT 25%): " + 3000 * numOfCh * 1.25 + " DKK\n\n\n" +
                "                  Thank you!                    \n" +
                "************************************************";
    }


    //ALL SCHEDULE METHODS BELOW THIS
    public int createWeekNumber() {
        System.out.println("What week would you like to create a new schedule for?" +
                "\n(Enter 0 to cancel.)");
        boolean keepLooping = true;
        int weekForSchedule = -1;
        while (keepLooping) {
            weekForSchedule = getNumber("Not a valid week number, please try again");
            if (weekForSchedule == 0) {
                return 0;
            } else if (!scheduleExists(weekForSchedule) && weekForSchedule > 0 && weekForSchedule < 52) {
                keepLooping = false;
            } else {
                System.out.println("This week isn't available\n" +
                        "Please choose a new week:\n");
            }
        }
        return weekForSchedule;
    }

    public void createSchedule() throws FileNotFoundException {
        int weekNumber = createWeekNumber();
        if (weekNumber == 0) {
            return;
        }
        database.getSchedules().add(new ScheduleWeek(weekNumber));
        System.out.println("A new schedule has been created for week " +
            weekNumber + ".");
        sortSchedules();
        saveFile(database.getSchedules(), 3);
    }

    public void sortSchedules() {
        int totalSchedules = database.getSchedules().size();

        for (int i = 0; i < totalSchedules - 1; ++i) {
            int earliest = database.getSchedules().get(i).getWeekNumber();
            int currentSched;
            int swapIndex = i;

            for (int j = i; j < totalSchedules; ++j) {
                currentSched = database.getSchedules().get(j).getWeekNumber();
                if (earliest > currentSched) {
                    earliest = currentSched;
                    swapIndex = j;
                }
            }

            //do the swap
            ScheduleWeek temp = new ScheduleWeek(scheduleByWeekNum(database.getSchedules().get(i).getWeekNumber()).getWeekNumber(),
                    scheduleByWeekNum(database.getSchedules().get(i).getWeekNumber()).getDaysList());
            database.getSchedules().set(i, scheduleByWeekNum(earliest));
            database.getSchedules().set(swapIndex, temp);
        }
    }

    public void deleteSchedule() throws FileNotFoundException {
        System.out.println("Which week's schedule would you like to delete?" +
                "\n(Enter 0 to cancel.)");
        printAllSchedules();
        int toRemove;
        do {
            toRemove = getNumber("Not a valid week.");
            if (toRemove == 0) {
                return;
            }
        } while (!scheduleExists(toRemove));

        database.getSchedules().remove(scheduleByWeekNum(toRemove));
        System.out.println("The schedule for week " + toRemove + " has been removed.");
        saveFile(database.getSchedules(), 3);
    }

    public void printAllSchedules() {
        for (int i = 0; i < database.getSchedules().size(); ++i) {
            ScheduleWeek currentSched = database.getSchedules().get(i);
            System.out.println(scheduleCPRToName(currentSched, printScheduleWeek(currentSched)));

        }
    }

    public String printScheduleWeek(ScheduleWeek schedule) {
        String empCPR = null;
        String schedBod = "";
        String blackList = "";
        schedBod += "Week #" + schedule.getWeekNumber();
        schedBod += "\n**********************************\n";
        for (int i = 0; i < schedule.getDaysList().size(); ++i) {
            ArrayList<String> currentShift = schedule.getDaysList().get(i).getStaffOnShift();
            for (int j = 0; j < currentShift.size(); ++j) {
                empCPR = currentShift.get(j);

                if (!blackList.contains(empCPR)) {
                    schedBod += (empCPR + " (" + schedule.getDaysList().get(i).getDay());
                    blackList += (empCPR + " ");

                    for (int k = i + 1; k < schedule.getDaysList().size(); ++k) {
                        if (schedule.getDaysList().get(k).getStaffOnShift().contains(empCPR)) {
                            schedBod += (", " + schedule.getDaysList().get(k).getDay());
                        }
                    }
                    schedBod += ")\n";
                }
            }
        }
        schedBod += "**********************************\n";
        return schedBod;
    }

    public String scheduleCPRToName(ScheduleWeek schedule, String toReplace) {
        String employeeName = "";
        for (int i = 0; i < schedule.getDaysList().size(); ++i) {
            ArrayList<String> currentStaff = schedule.getDaysList().get(i).getStaffOnShift();
            for (String employeeCPR : currentStaff) {
                employeeName = employeeByCPR(employeeCPR).getFirstName() +  " " +
                        employeeByCPR(employeeCPR).getLastName();
                toReplace = toReplace.replace(employeeCPR, employeeName);
            }
        }
        return toReplace;
    }

    public Employee employeeByCPR(String cpr) {

        for (Employee eMatch : database.getEmployees()) {
            if (eMatch.getCpr().equals(cpr)) {
                return eMatch;
            }
        }

        return null;
    }

    public boolean scheduleExists(int newSchedule) {
        boolean exists = false;

        for (int i = 0; i < database.getSchedules().size(); ++i) {
            int currentWeek = database.getSchedules().get(i).getWeekNumber();
            if (newSchedule == currentWeek) {
                exists = true;
            }
        }

        return exists;
    }

    public void modifySchedule() throws FileNotFoundException {
        System.out.println("What would you like to do?\n[1]. Add employees to shift\n[2]. Remove employees from shift" +
                "\n[0]. Cancel");
        int choice;
        do {
            choice = getNumber("Not a valid option.");
            if (choice == 0) {
                return;
            }
        } while (choice < 1 || choice > 2);

        ScheduleDay toMod = shiftListByDay();

        displayEmployees();
        int empID;

        switch (choice) {
            case 1:
                System.out.println("Choose employee to add");
                do {
                    empID = getNumber("Not a valid employee.") - 1;
                } while (empID < 0 || empID >= database.getEmployees().size());
                toMod.getStaffOnShift().add(database.getEmployees().get(empID).getCpr());
                System.out.println("The employee has been added to the shift.");
                saveFile(database.getSchedules(), 3);
                break;
            case 2:
                System.out.println("Choose employee to remove");
                do {
                    empID = getNumber("Not a valid employee.") - 1;
                } while (empID < 0 || empID >= database.getEmployees().size());
                toMod.getStaffOnShift().remove(database.getEmployees().get(empID).getPhoneNumber());
                System.out.println("The employee has been removed from the shift.");
                saveFile(database.getSchedules(), 3);
                break;
        }
    }

    public ScheduleDay shiftListByDay() {
        System.out.println("Which week's schedule would you like to change?\n(Enter week #)");
        printAllSchedules();
        int weekNum;
        do {
            weekNum = getNumber("Not a valid week number, please try again");
        } while (!scheduleExists(weekNum));

        ScheduleWeek week = scheduleByWeekNum(weekNum);


        System.out.println("Which day's shifts would you like to change?");
        System.out.println("[1]. MON   [2]. TUE   [3]. WED   [4]. THU   " +
                "[5]. FRI   [6]. SAT   [7]. SUN");
        int day;

        do {
            day = getNumber("Not a valid week date");
        } while (day < 0 || day > 7);
        return week.getDaysList().get(day - 1);
    }



    public ScheduleWeek scheduleByWeekNum(int weekNum) {
        ScheduleWeek match = null;
        for (ScheduleWeek current : database.getSchedules()) {
            if (weekNum == current.getWeekNumber()) {
                match = current;
                return match;
            }
        }
        return match;
    }
}
