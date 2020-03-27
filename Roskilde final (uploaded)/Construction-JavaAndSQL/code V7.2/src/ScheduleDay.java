import java.util.ArrayList;

public class ScheduleDay {
    //FIELDS
    //Consider adding boolean isClosed? Ask if this would be a cool feature
    private String day;
    private ArrayList<String> staffOnShift;


    //CONSTRUCTORS
    public ScheduleDay() { this.staffOnShift = new ArrayList<String>(); }

    public ScheduleDay(String day) {
        this.staffOnShift = new ArrayList<String>();
        this.day = day;
    }

    public ScheduleDay(String day, ArrayList<String> staffOnShift) {
        this.day = day;
        this.staffOnShift = staffOnShift;
    }

    //GETTERS & SETTERS

    public ArrayList<String> getStaffOnShift() {
        return staffOnShift;
    }

    public void setStaffOnShift(ArrayList<String> staffOnShift) {
        this.staffOnShift = staffOnShift;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    //toString() + methods

    @Override
    public String toString() {
        String employees = "";
            for (int i = 0; i < staffOnShift.size(); ++i) {
                employees += this.staffOnShift.get(i) + " ";
            }
        return day + " " + employees;
    }

}
