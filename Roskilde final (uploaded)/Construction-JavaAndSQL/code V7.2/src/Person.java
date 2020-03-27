public class Person {

    public enum Gender {
        M, F
    }
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private Gender gender;
    private String address;
    private String phoneNumber;

    public Person(){
    }

    public Person (String firstName, String lastName, String dateOfBirth, Gender gender,
                   String address, String phoneNumber){
        this.firstName=firstName;
        this.lastName=lastName;
        this.dateOfBirth=dateOfBirth;
        this.gender=gender;
        this.address=address;
        this.phoneNumber=phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("%-20s%-20s%-20s%-20s%-30s%-20s", this.firstName, this.lastName, this.dateOfBirth, this.gender, this.address, this.phoneNumber);
    }

    public String toStringConsole() {
        return "Name: " + this.firstName + " " + this.lastName + "\nDOB: " + this.dateOfBirth + "\nGender: " +
                this.gender + "\nAddress: " + this.address + "\nPhone: " + this.phoneNumber + ".";
    }
}
