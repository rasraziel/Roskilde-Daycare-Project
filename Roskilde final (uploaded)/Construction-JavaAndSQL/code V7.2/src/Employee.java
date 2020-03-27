public class Employee extends Person {

    private String cpr;
    private double salary;
    boolean isAdmin;

    public Employee(){
    }

    public Employee(Person obj) {
        super(obj.getFirstName(), obj.getLastName(), obj.getDateOfBirth(), obj.getGender(), obj.getAddress(), obj.getPhoneNumber());
    }

    public Employee (String firstName, String lastName, String dateOfBirth, Gender gender,
                     String address, String phoneNumber, String cpr, double salary, boolean isAdmin){
        super(firstName, lastName, dateOfBirth, gender, address, phoneNumber);
        this.cpr=cpr;
        this.salary=salary;
        this.isAdmin=isAdmin;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String toString(){
        return super.toString() + String.format("%-20s%-20.2f%-20b", this.cpr, this.salary, this.isAdmin);}

    public String toStringConsole(){
        return super.toStringConsole() + "\nCPR: " + this.cpr + "\nSalary: " + this.salary + "\nAdmin? " + this.isAdmin + ".";
    }

}
