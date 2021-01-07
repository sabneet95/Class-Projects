/*
Programmer: Sabneet Bains
Course: EN.605.202.85.FA20 Data Structures
 */
import java.io.FileWriter;
import java.io.IOException;
/**
 * The write_output class takes in output_file, adjacency_matrix, paths as parameters and then runs the
 * FileWriter to append to an output file (RISK of appending; use different filenames if needed). Next,
 * a corresponding label is created for each successive adjacency_matrix, as read from the input_file.
 *
 * And conclusively, writes all the matrices and the non-looping paths!
 */
public class write_output {
    public write_output(String output_file, int[][] adjacency_matrix, String paths) throws IOException
    {
        // Initiates the FileWriter to append to output_file; RISK of appending to the same file if not careful!
        FileWriter output = new FileWriter(output_file,true);

        // Creates a label for each successive adjacency_matrix as provided in the input_file
        output.write(" •••=================================================••\n" +
                "•••>     Adjacency Matrix for (" + adjacency_matrix.length + ") directed nodes     <•••\n" +
                "  ••=================================================•••\n\n");

        // For semantics to visualize the matrix
        output.write("\t  _");

        for (int i = 0; i < adjacency_matrix.length; i++) {
            output.write((i + 1) + "_");
        }

        // Writes back each successive adjacency_matrix
        for (int i = 0; i < adjacency_matrix.length; i++)
        {
            output.write("\n\t" + (i + 1) + "| ");
            for (int j = 0; j < adjacency_matrix.length; j++)
            {
                output.write(adjacency_matrix[i][j] + " ");
            }
        }

        // Writes the calculated non-looping paths
        output.write("\n" + paths + "\n");
        output.close();
    }
}