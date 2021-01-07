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

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The driver takes in a commandline argument for the directory containing the input files
 * otherwise, prompts the user for a directory. Then goes through each file in the directory
 * and runs the following algorithms: Normal QuickSort, QuickSort Hundred, QuickSort Fifty,
 * QuickSort Median-of-Three, and Natural Merge Sort. The outputs are written as csv files
 * for further statistical analysis.
 */

public class driver {

    // If the user provides faulty input, an IOException takes care of bad I/O
    public static void main(String[] args) throws IOException {
        File input_directory;

        if (args.length == 1) {
            input_directory = new File(args[0]);
        }

        // When the user forgets to provide the input directory as a command-line argument
        else {
            System.out.println("\n<!> Oops, you didn't provide the required input directory! <!>\n");
            Scanner IO = new Scanner(System.in);

            // Reads the input path
            System.out.print("No worries, go ahead and type the input file directory: ");
            input_directory = new File(IO.nextLine());
            IO.close();
        }

        File[] input_files = input_directory.listFiles();

        if (input_files != null) {

            Scanner IO;
            int[] data; // Initiates variable to read integers one by one

            for (int i = 0; i < input_files.length; i++) {
                ArrayList<String> line = new ArrayList<>();

                IO = new Scanner(new BufferedReader(new FileReader(input_files[i])));

                while (IO.hasNext()) {
                    line.add(IO.next());
                }

                data = new int[line.size()];

                for (int q = 0; q < line.size(); q++) {
                    data[q] = Integer.parseInt(line.get(q));
                }

                int[] array = data; // fills an array with data
                int n = array.length;

                quick_sort q = new quick_sort();
                natural_merge_sort m = new natural_merge_sort();

                int[] fill = array;

                // Normal QuickSort
                q.write_output(array, String.valueOf(input_files[i]), 0, n-1, 0, i + 1);

                // Quicksort Hundred
                array = fill;
                q.write_output(array, String.valueOf(input_files[i]), 0, n-1, 1, i + 1);

                // Quicksort Fifty
                array = fill;
                q.write_output(array, String.valueOf(input_files[i]), 0, n-1, 2, i + 1);

                // Quicksort Median-of-Three
                array = fill;
                q.write_output(array, String.valueOf(input_files[i]), 0, n-1, 3, i + 1);

                // Natural Merge Sort
                array = fill;
                m.write_output(array, String.valueOf(input_files[i]), i + 1);
            }
        }
    }
}