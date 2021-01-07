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
import java.io.PrintWriter;

/**
 * The driver takes in a commandline argument for the directory containing the input files
 * otherwise, prompts the user for a directory. Then goes through each file in the directory
 * and runs the following algorithms: Normal QuickSort, QuickSort Hundred, QuickSort Fifty,
 * QuickSort Median-of-Three, and Natural Merge Sort. The outputs are written as csv files
 * for further statistical analysis.
 */

public class quick_sort {

    int comparisons = 0; // Initiates the total comparisons
    int exchanges = 0; // Initiates the total exchanges

    // Insertion sort needed for the quick sort algorithms
    public int[] insertion_sort(int[] array) {

        for (int i = 1; i < array.length; ++i) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {

                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
        return array;
    }

    // Flips the individual elements
    public void flip(int[] array, int a, int b) {

        exchanges++;
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    // Selects the first item of the partition as the pivot
    public int first_item_partition(int[] array, int low, int high) {

        int pivot = array[low]; // pivot selection
        int i = (high + 1);

        for (int j = high; j > low; j--) {

            if (array[j] >= pivot) {

                i--;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                exchanges++;
            }
        }

        int temp = array[i - 1];
        array[i - 1] = array[low];
        array[low] = temp;
        return i - 1;
    }

    // Treats a partition of size 100 as a stopping case
    public int hundred_partition(int[] array, int low, int high) {

        int pivot = array[low]; // pivot selection
        int i = (high + 1);

        for (int j = high; j > low; j--) {

            // Stopping case
            if (high - low <= 100) {

                array = insertion_sort(array);
                break;
            }

            else if (array[j] >= pivot) {

                i--;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                exchanges++;
            }
        }

        int temp = array[i - 1];
        array[i - 1] = array[low];
        array[low] = temp;
        return i - 1;
    }

    // Treats a partition of size 50 as a stopping case
    public int fifty_partition(int[] array, int low, int high) {

        int pivot = array[low]; // pivot selection
        int i = (high + 1);

        for (int j = high; j > low; j--) {

            // Stopping case
            if (high - low <= 50) {

                array = insertion_sort(array);
                break;
            }

            else if (array[j] >= pivot) {

                i--;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                exchanges++;
            }
        }

        int temp = array[i - 1];
        array[i - 1] = array[low];
        array[low] = temp;
        return i - 1;
    }

    // Selects the median-of-three as the pivot
    public int median_of_three_partition(int[] array, int left, int right) {

        int center = (left + right) / 2;

        if (array[left] > array[center])
            flip(array, left, center);

        if (array[left] > array[right])
            flip(array, left, right);

        if (array[center] > array[right])
            flip(array, center, right);

        flip(array, center, right - 1);

        return array[right - 1];
    }

    // Used for array manipulation in median-of-three
    public int median_partition(int[] array, int left, int right, double pivot) {

        int left_pointer = left;
        int right_pointer = right - 1;

        while (true)
        {
            while (array[++left_pointer] < pivot)
                ;
            while (array[--right_pointer] > pivot)
                ;
            if (left_pointer >= right_pointer)
                break;
            else
                flip(array, left_pointer, right_pointer);
        }
        flip(array, left_pointer, right - 1);
        return left_pointer;
    }

    // In case of size 1 or 2; used in median-of-three
    public void partition_sort(int[] array, int left, int right) {

        int size = right - left + 1;

        // When size is one
        if (size <= 1) return;

        // When size is two
        if (size == 2) {
            if (array[left] > array[right]) {
                flip(array, left, right);
            }
        }

        else {
                if (array[left] > array[right - 1])
                    flip(array, left, right - 1);

                if (array[left] > array[right])
                    flip(array, left, right);

                if (array[right - 1] > array[right])
                    flip(array, right - 1, right);
            }
    }

    // Final sort that takes the array and quick sorts according to the chosen type
    public void final_sort(int[] array, int low, int high, int type) {

        // Normal QuickSort
        if (low < high && type == 0) {

            int k = first_item_partition(array, low, high);
            comparisons++;
            final_sort(array, low, k - 1, 0);
            final_sort(array, k + 1, high, 0);
        }

        // QuickSort Hundred
        else if (low < high && type == 1) {

            int k = hundred_partition(array, low, high);
            if (100 - k >= 100) {
                comparisons++;
                final_sort(array, low, k - 1, 1);
                final_sort(array, k + 1, high, 1);
            }
        }

        // QuickSort Fifty
        else if (low < high && type == 2) {

            int k = fifty_partition(array, low, high);
            if (100 - k >= 100) {
                comparisons++;
                final_sort(array, low, k - 1, 1);
                final_sort(array, k + 1, high, 1);
            }
        }

        // QuickSort Median-of-Three
        else if (low < high && type == 3) {

            int size = high - low + 1;
            if (size <= 3)
                partition_sort(array, low, high);

            else {

                double median = median_of_three_partition(array, low, high);
                int partition = median_partition(array, low, high, median);
                comparisons++;
                final_sort(array, low, partition - 1, 3);
                final_sort(array, partition + 1, high, 3);
            }
        }
    }

    // Runs the chosen recursive quick sort and writes the results into a .csv file
    public void write_output(int[] array, String input_file, int low, int high, int type, int file_number)
            throws IOException {

        final_sort(array, low, high, type);

        PrintWriter print = new PrintWriter(new FileWriter("quick_sort_results.csv", true));

        switch (type) {
            case 0 -> {
                if(file_number == 1)
                {
                    print.print("SORT TYPE," + "FILE NAME," + "NUMBER OF COMPARISONS," + "NUMBER OF EXCHANGES,\n");
                }
                print.print("Normal Quicksort," + input_file + "," + comparisons + "," + exchanges + ",\n");
            }
            case 1 -> print.print("Quicksort Hundred," + input_file + "," + comparisons + "," + exchanges + ",\n");
            case 2 -> print.print("Quicksort Fifty," + input_file + "," + comparisons + "," + exchanges + ",\n");
            case 3 -> print.print("Quicksort Median-of-Three," + input_file + "," + comparisons + "," + exchanges + ",\n");
        }
        print.close();
    }
}