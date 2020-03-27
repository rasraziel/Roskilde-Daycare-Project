/*CONTROLLER - it takes method calls from the Menu classes and points them in the right direction. It has no methods of its own */

import java.io.FileNotFoundException;

public class Controller {

    private static Service service;

    public Controller(){
        this.service = new Service();
    }

    /*public int getNumber(String errorMessage){
        return service.getNumber(errorMessage);
    }
    */

    public void createParent() throws FileNotFoundException{ service.createParent(); }

    public void createChild() throws FileNotFoundException{ service.createChild(); }

    public void createEmployee() throws FileNotFoundException{ service.createEmployee(); }

    public void createSchedule() throws FileNotFoundException {service.createSchedule();}
    /*
    public int createUniqueId() throws FileNotFoundException{return service.createUniqueId();}

    public String createFirstName(){
        return service.createFirstName();
    }

    public String createLastName(String name){
        return service.createLastName(name);
    }

    public String createPhoneNumber(String name){
        return service.createPhoneNumber(name);
    }

    public String createDateOfBirth(String name){
        return service.createDateOfBirth(name);
    }

    public String createCpr(String name){
        return service.createCpr(name);
    }

    public double createSalary(String name){
        return service.createSalary(name);
    }

    public Enum createGender(String name){
        return service.createGender(name);
    }

    public String createMailAddress(String name){
        return service.createMailAddress(name);
    }

    public boolean createAdmin(String name){
        return service.createAdmin(name);
    }

    public void printSlow(String text, int interval) {
        service.printSlow(text, interval);
    }

    public void pressKey(){
        service.pressKey();
    }

    public int matchParentKidId() throws FileNotFoundException {return service.matchParentKidId(); }
    */
    public void displayParents() {
        service.displayParents();
    }

    public void displayChildren() {
        service.displayChildren();
    }

    public void displayEmployees(){
        service.displayEmployees();
    }

    public void displaySchedules(){service.printAllSchedules();}

    public void updateParent() throws FileNotFoundException { service.updateParent();}

    public void updateChild() throws FileNotFoundException { service.updateChild(); }

    public void updateEmployee() throws FileNotFoundException{ service.updateEmployee(); }

    public void updateSchedule() throws FileNotFoundException {service.modifySchedule();}

    public void deleteEmployee() throws FileNotFoundException{ service.deleteEmployee();}

    public void deleteParent() throws FileNotFoundException{ service.deleteParent(); }

    public void deleteChild() throws FileNotFoundException{ service.deleteChild();}

    public void deleteSchedule() throws FileNotFoundException{service.deleteSchedule();}

    public void initiateTList() throws Exception {service.initiateTList();}

    public void registerPayments() throws Exception{service.registerPayments();}

    public void viewPaid(){service.viewPaid();}

    public void viewUnpaid(){service.viewUnpaid();}


}
