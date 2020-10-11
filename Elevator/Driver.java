//Sabneet Bains
import java.io.File;// Basic IO
import java.io.FileNotFoundException;// Basic IO
import java.util.Scanner; // Basic IO
import java.util.ArrayList;// used as dynamic resizing is better in ArrayList

public class Driver {
    public static void main(String args[]) throws FileNotFoundException
    {
        // In case, one forgot to run the driver with arguments
        if (args.length <= 0) {
            System.out.print("\nOops, you seem to have forgotten to provide a command line argument!\n" +
                    "No worries, go ahead and type the input file name: ");
            Scanner user_input = new Scanner(System.in);
            File input_file = new File(user_input.nextLine());
            Scanner instance = new Scanner(input_file);
            ArrayList individuals = new ArrayList();

            // Keep reading line by line
            while (instance.hasNextLine())
            {
                String line = instance.nextLine();
                if (!line.startsWith("//") && (line.length() != 0))
                {
                    String[] split = line.trim().split("\\s+");
                    String pseudonym = split[0];
                    int floor_entered = Integer.parseInt(split[1]);
                    int floor_exited = Integer.parseInt(split[2]);
                    individuals.add(new Employee(pseudonym, floor_entered, floor_exited)); // keep filling the ArrayList with Employees
                }
            }
            System.out.println("\n");
            new ElevatorJourney(individuals); // Time to simulate an elevator journey
        }
    }
}
