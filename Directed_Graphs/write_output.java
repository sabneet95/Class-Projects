/*
Programmer: Sabneet Bains
Course: EN.605.202.85.FA20 Data Structures

MIT License

Copyright (c) [2020] [Sabneet Bains]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
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
@SuppressWarnings("rawtypes")
public class write_output {
    public write_output(String output_file, linked_list[] adjacency_matrix, String paths) throws IOException
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
                if (adjacency_matrix[i].contains(j))
                    output.write("1 ");
                else
                    output.write("0 ");
            }
        }

        // Writes the calculated non-looping paths
        output.write("\n" + paths + "\n");
        output.close();
    }
}