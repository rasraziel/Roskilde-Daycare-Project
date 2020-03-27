import java.util.ArrayList;

public class ScheduleWeek {
    //FIELDS
    //Consider using a static counter for automatic week numbering
    private int weekNumber;
    private ArrayList<ScheduleDay> daysList;

    //CONSTRUCTORS
    public ScheduleWeek() {
        this.daysList = new ArrayList<ScheduleDay>();
        generateEmptySchedule(-1);
    }

    public ScheduleWeek(int weekNumber) {
        this.daysList = new ArrayList<ScheduleDay>();
        generateEmptySchedule(weekNumber);
    }

    public ScheduleWeek(int weekNumber, ArrayList<ScheduleDay> daysList) {
        this.weekNumber = weekNumber;
        this.daysList = daysList;
    }

    //GETTERS & SETTERS
    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public ArrayList<ScheduleDay> getDaysList() {
        return daysList;
    }

    public void setDaysList(ArrayList<ScheduleDay> daysList) {
        this.daysList = daysList;
    }

    //toString() + methods
    @Override
    public String toString() {
        String days = "";
        for (int i = 0; i < daysList.size(); ++i) {
            days += daysList.get(i) + " ";
        }
        return weekNumber + " " + days;
    }

    public void generateEmptySchedule(int weekNumber) {
        this.weekNumber = weekNumber;
        this.daysList.add(new ScheduleDay("MON"));
        this.daysList.add(new ScheduleDay("TUE"));
        this.daysList.add(new ScheduleDay("WED"));
        this.daysList.add(new ScheduleDay("THU"));
        this.daysList.add(new ScheduleDay("FRI"));
        this.daysList.add(new ScheduleDay("SAT"));
        this.daysList.add(new ScheduleDay("SUN"));

    }

}
