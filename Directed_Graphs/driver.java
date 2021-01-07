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

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Driver class takes in command-line arguments: args[0] as the input file path
 * and args [1] as the output file. Given an accidental omission of these arguments
 * provides the user with an opportunity to re-enter through the console.
 *
 * Once provided with appropriate file paths, executes the read_input function to read
 * the input file one integer at a time.
 */
public class driver {
    // If the user provides faulty input, an IOException takes care of bad I/O
    @SuppressWarnings("InstantiationOfUtilityClass")
    public static void main(String[] args) throws IOException {

        if (args.length == 2)
        {
            new read_input(String.valueOf(args[0]), String.valueOf(args[1]));
        }

        // When the user forgets to provide the command-line arguments
        else
        {
            System.out.println("\n<!> Oops, you didn't provide the required (two) command-line arguments! <!>\n");
            Scanner IO = new Scanner(System.in);

            // Reads the input path
            System.out.print("No worries, go ahead and type the input file name: ");
            String input_file = String.valueOf(new File(IO.nextLine()));

            // Reads the output path and closes IO
            System.out.print("Now, tell me what would you like the output file's name to be: ");
            String output_file = String.valueOf(new File(IO.nextLine()));
            IO.close();

            // Executes the read_input function and subsequent logic
            new read_input(input_file, output_file);
        }
    }
}