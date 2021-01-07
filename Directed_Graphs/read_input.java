/*
Programmer: Sabneet Bains
Course: EN.605.202.85.FA20 Data Structures
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class read_input {
    /**
     * The read_input function takes in input_file and output_file strings as parameters and then runs a Scanner
     * instance to read the input_file one integer at a time. Given the input_file nature, the first integer is
     * taken as the adjacency matrix's size and the rest as the 2-d adjacency_matrix itself.
     *
     * Once filled, the adjacency_matrix is used as a parameter for the print_non_looping_paths
     * to get back the said paths in the directed graph. Ultimately it is all written to the
     * output_file before closing the input_file.
     */
    public read_input(String input_file, String output_file) throws IOException
    {
        // Starts a new Scanner instance
        Scanner input = new Scanner(new File(input_file));

        // Read as long as there are integers in the input; faulty strings ignored
        while (input.hasNextInt())
        {
            // Gets the adjacency matrix size
            int adjacency_matrix_size = input.nextInt();

            // Predefines the matrix
            int[][] adjacency_matrix = new int[adjacency_matrix_size][adjacency_matrix_size];

            for (int i = 0; i < adjacency_matrix_size; i++)
            {
                for (int j = 0; j < adjacency_matrix_size; j++)
                {
                    // Fills the matrix with the integers from input
                    adjacency_matrix[i][j] = input.nextInt();
                }
            }
            // Runs the print_non_looping_paths and subsequent logic
            String paths = print_non_looping_paths(adjacency_matrix);

            // Calls the write_output function to write the output file
            new write_output(output_file, adjacency_matrix, paths);
        }
        input.close();
    }
    /**
     * The print_non_looping_paths function takes in 2-d adjacency_matrix as a parameter and then, while looping
     * through its row and columns, keeps track of already traversed nodes. After that, the root node is set to
     * the size of adjacency_matrix filled with -1s.
     *
     * Finally calls the find_paths function to find all non-looping
     * paths and formats the results in a user-friendly fashion for the output file.
     */
    static String print_non_looping_paths(int[][] adjacency_matrix)
    {
        StringBuilder non_looping_paths = new StringBuilder();

        for (int i = 0; i < adjacency_matrix.length; i++)
        {
            for (int j = 0; j < adjacency_matrix.length; j++)
            {
                // To keep track of already traversed nodes
                boolean[] traversed = new boolean[adjacency_matrix.length];

                // Set root to adjacency_matrix.length
                int[] root = new int[adjacency_matrix.length];

                // Fill the root elements with -1
                int k = 0;
                while (k < root.length) {
                    root[k] = -1;
                    k++;
                }

                // Check if already traversed nodes
                if (i != j)
                {
                    traversed[i] = true;
                }

                // Creates a label for current node to node non-looping paths
                non_looping_paths.append("""

                         ___________________________________
                        [ All non-looping paths from\s""").append(i + 1).append(" to ").append(j + 1).append(" ]\n\n");

                // Creates a StringBuffer for subsequent logic
                StringBuffer paths = new StringBuffer();

                // Calls the find_paths function to recursively find all non-looping paths
                new find_paths(adjacency_matrix, root, traversed, i, j, i, 0, false, paths);

                // In case, no path is found, prints the No Path Found message
                if (paths.toString().equals(""))
                {
                    paths.append("\t<!> No Path Found").append("\n");
                }
                non_looping_paths.append(paths);
            }
        }
        return non_looping_paths.toString();
    }
}