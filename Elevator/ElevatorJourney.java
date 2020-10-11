//Sabneet Bains
import java.util.ArrayList; // used as dynamic resizing is better in ArrayList

public class ElevatorJourney {
    private int Total_Elevator_Journeyers = 0;
    private int Total_Stairway_Takers = 0;
    private int Full_Elevator = 0;
    private int Empty_Elevator = 0;
    private int Destination_Floor = -1;
    private int Current_Floor = 1;
    private int[] Destination = new int[5];
    private String Direction = "UP";

    public ElevatorJourney(ArrayList<Employee> individuals) {
        var Elevator = new Stack<Employee>();
        var Auxiliary = new Stack<Employee>(); // Used to count temporary step-outs
        // Keep finding the direction until all 49 employees are taken care off
        while(true) {
            switch (Direction) {
                case "UP":
                    if (Current_Floor == 5) {
                        Direction = "DOWN";
                        int i = 4;
                        while (i >= 0) {
                            if (Destination[i] != 0) {
                                Destination_Floor = i + 1;
                                break;
                            }
                            i--;
                        }
                        Current_Floor--;
                    } else {
                        int i = Current_Floor - 1;
                        while (i < 5) {
                            if (Destination[i] != 0) {
                                Destination_Floor = i + 1;
                                break;
                            }
                            i++;
                        }
                        Current_Floor++;
                    }
                    break;

                default:
                    if (Current_Floor == 1) {
                        Direction = "UP";
                        int i = 0;
                        while (i < 5) {
                            if (Destination[i] != 0) {
                                Destination_Floor = i + 1;
                                break;
                            }
                            i++;
                        }
                        Current_Floor++;
                    } else {
                        int i = Current_Floor - 1;
                        while (i >= 0) {
                            if (Destination[i] != 0) {
                                Destination_Floor = i + 1;
                                break;
                            }
                            i--;
                        }
                        Current_Floor--;
                    }
                    break;
            }
            // When the Elevator is not empty let Employees out at their desired floors
            if (individuals.isEmpty() && Elevator.isEmpty()) break;
            if (Current_Floor != Destination_Floor) {
            } else {
                while (Destination[Current_Floor - 1] != 0) {
                    Employee away = Elevator.pop();
                    if (away.getFloor_exited() != Current_Floor) {
                        Auxiliary.push(away); // Employees who have to step-out
                        away.temporaryExit();
                    } else {
                        Destination[Current_Floor - 1]--;
                        System.out.println(away.getPseudonym() + " permanently left. In hindsight, they had "
                                + away.getTemporarily_exited() + " temporary exits.");
                    }
                }
                if (!Elevator.isEmpty()) {
                } else {
                    System.out.println("\nWow, nobody here! The elevator is completely empty :(\n");
                    Empty_Elevator++;
                }
                while (true) {
                    if (Auxiliary.isEmpty()) break;
                    Elevator.push(Auxiliary.pop());
                }
            }
            // Employees already present outside and waiting
            if (Elevator.size() == 5) {
                System.out.println("\nNOOO, the elevator is full!");
                Full_Elevator++;
                while (!individuals.isEmpty() && !(individuals.get(0).getFloor_entered() != Current_Floor)) {
                    System.out.println("Hence, " + individuals.get(0).getPseudonym()
                            + " was unable to get on the elevator and unfortunately had to take the stairs to the"
                            + individuals.get(0).getFloor_exited() + " floor.");
                    Total_Stairway_Takers++;
                    individuals.remove(0);
                }
            } else {
                // Keep on going until the elevator is full and there is no one waiting
                while ((Elevator.size() < 5) && !individuals.isEmpty() && (individuals.get(0).getFloor_entered() == Current_Floor)) {
                    Employee check = individuals.remove(0);
                    Elevator.push(check);
                    Total_Elevator_Journeyers++;
                    Destination[check.getFloor_exited() - 1]++;
                }
                if (Elevator.size() == 5 && !individuals.isEmpty() && individuals.get(0).getFloor_entered() == Current_Floor) {
                    Full_Elevator++;
                    System.out.println("\nNOOO, the elevator is full!");

                    while (!individuals.isEmpty() && individuals.get(0).getFloor_entered() == Current_Floor) {
                        System.out.println("Hence, " + individuals.get(0).getPseudonym()
                                + " was unable to get on the elevator and unfortunately had to take the stairs to the"
                                + individuals.get(0).getFloor_exited() + " floor.");
                        Total_Stairway_Takers++;
                        individuals.remove(0);
                    }
                }
            }
        }
        System.out.println("\nThe total number of people who rode: " + Total_Elevator_Journeyers
                + "\nThe number of time someone could not ride because it was full: " + Total_Stairway_Takers
                + "\nThe number of empty occasions: " + Empty_Elevator
                + "\nThe number of full occasions: " + Full_Elevator);
    }
}
