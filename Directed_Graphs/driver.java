/*
Programmer: Sabneet Bains
Course: EN.605.202.85.FA20 Data Structures
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * The driver class takes in command-line arguments: args[0] as the input file path
 * and args [1] as the output file. Given an accidental omission of these arguments
 * provides the user with an opportunity to re-enter through the console.
 *
 * Once provided with appropriate file paths, executes the read_input function to read
 * the input file one integer at a time.
 */
public class driver {
    @SuppressWarnings("InstantiationOfUtilityClass")
    // If the user provides faulty input, an IOException takes care of bad I/O
    public static void main(String[] args) throws IOException {

        if (args.length == 2)
        {
            new read_input(String.valueOf(args[0]), String.valueOf(args[1]));
        }

        // When the user forgets to provide the command-line arguments
        else
        {
            System.out.println("\n<!> Oops, you didn't provide the required (two) command-line arguments! <!>\n");

            // Reads the input path
            System.out.print("No worries, go ahead and type the input file name: ");
            String input_file = String.valueOf(new File(new Scanner(System.in).nextLine()));

            // Reads the output path
            System.out.print("Now, tell me what would you like the output file's name to be: ");
            String output_file = String.valueOf(new File(new Scanner(System.in).nextLine()));

            // Executes the read_input function and subsequent logic
            new read_input(input_file, output_file);
        }
    }
}