public class Child extends Person{

    private int childId;
    private int parentId;

    public Child(){
    }

    public Child(Person obj) {
        super(obj.getFirstName(), obj.getLastName(), obj.getDateOfBirth(), obj.getGender(), obj.getAddress(), obj.getPhoneNumber());
    }

    public Child(String firstName, String lastName, String dateOfBirth, Gender gender,
                 String address, String phoneNumber, int childId, int parentId){
        super(firstName, lastName, dateOfBirth, gender, address, phoneNumber);
        this.childId=childId;
        this.parentId=parentId;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String toString() {
        return super.toString() + String.format("%-20d%-20d", this.childId, this.parentId);
    }

    public String toStringConsole(){
        return super.toStringConsole() +  "\nChild ID: " + this.childId + "\nParent ID: " + this.parentId + ".";
    }
}
