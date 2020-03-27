public class Parent extends Person {
    private int parentId;
    private int numOfKids;
    private String mailAddress;
    private boolean paid;

    public Parent(){
    }

    public Parent(Person obj) {
        super(obj.getFirstName(), obj.getLastName(), obj.getDateOfBirth(), obj.getGender(), obj.getAddress(), obj.getPhoneNumber());
    }

    public Parent (String firstName, String lastName, String dateOfBirth, Gender gender,
                   String address, String phoneNumber, int parentId, int numOfKids, String mailAddress, boolean paid){
        super(firstName, lastName, dateOfBirth, gender, address, phoneNumber);
        this.parentId=parentId;
        this.numOfKids=numOfKids;
        this.mailAddress=mailAddress;
        this.paid = paid;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getNumOfKids() {
        return numOfKids;
    }

    public void setNumOfKids(int numOfKids) {
        this.numOfKids = numOfKids;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public boolean isPaid() { return paid; }

    public void setPaid(boolean paid) { this.paid = paid;}

    public String toString(){
        return super.toString() + String.format("%-20d%-20d%-30s%-20b", this.parentId, this.numOfKids, this.mailAddress, this.paid);
    }

    public String toStringConsole(){
        return super.toStringConsole() + "\nParent ID: " + this.parentId + "\nNumber of children attending: " + this.numOfKids + "\nEmail address: "
                + this.mailAddress + ".";
    }
}
