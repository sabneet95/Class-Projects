//Sabneet Bains
public class Employee {
    private final String pseudonym;
    private final int floor_entered;
    private final int floor_exited;
    private int temporarily_exited;

    public Employee(String pseudonym, int floor_entered, int floor_exited) {
        this.pseudonym = pseudonym;
        this.floor_entered = floor_entered;
        this.floor_exited = floor_exited;
        this.temporarily_exited = 0;
    }
    public String getPseudonym() {
        return pseudonym;
    }
    public int getFloor_entered() {
        return floor_entered;
    }
    public int getFloor_exited() {
        return floor_exited;
    }
    public int getTemporarily_exited() {
        return temporarily_exited;
    }
    public void temporaryExit()
    {
        temporarily_exited = temporarily_exited + 1;
    }
}

