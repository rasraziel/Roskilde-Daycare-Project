import java.util.ArrayList;

public class Database {

    private static ArrayList <Parent> parents;
    private static ArrayList <Child> children;
    private static ArrayList <Employee> employees;
    private static ArrayList <ScheduleWeek> schedules;
    private static int pid; //cumulative parent ID

    public Database(){
        parents = new ArrayList<Parent>();
        children = new ArrayList<Child>();
        employees = new ArrayList<Employee>();
        schedules = new ArrayList<ScheduleWeek>();
        pid = 0;
    }

    public ArrayList<Parent> getParents() {
        return parents;
    }

    public void setParents(ArrayList<Parent> parents) {
        this.parents = parents;
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Child> children) {
        this.children = children;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<ScheduleWeek> getSchedules() {
        return schedules;
    }

    public  void setSchedules(ArrayList<ScheduleWeek> schedules) {
        Database.schedules = schedules;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
