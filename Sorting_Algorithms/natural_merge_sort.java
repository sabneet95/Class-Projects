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

public class natural_merge_sort {

    Node head = null;
    int comparisons = 0; // Initiates the total comparisons
    int exchanges = 0; // Initiates the total exchanges

    // Custom Node class
    private static class Node {
        final int item;
        Node next;
        Node(int content) {
            this.item = content;
            this.next = null;
        }
    }

    // recursive method combines the nodes
    public Node combine(Node left, Node right) {

        Node result;
        exchanges++;

        // Base
        if (left == null) return right;
        if (right == null) return left;

        if (left.item <= right.item)
        {
            result = left;
            result.next = combine(left.next, right);
        }
        else
            {
                result = right;
                result.next = combine(left, right.next);
            }
        return result;
    }

    // recursive merge sort algorithm
    public Node merge_sort(Node head) {
        comparisons++;
        if (head == null || head.next == null) return head; // Base

        Node sorted = find_sorted(head);
        Node unsorted = sorted.next;

        sorted.next = null;

        Node left = merge_sort(head);
        Node right = merge_sort(unsorted);

        return combine(left, right);
    }

    // finds pre-sorted patterns
    public Node find_sorted(Node head) {

        if (head == null) return null;

        Node next_pointer = head.next;
        Node current_pointer = head;

        while (next_pointer.item > current_pointer.item) {
            next_pointer = next_pointer.next;
            current_pointer = current_pointer.next;
        }
        return current_pointer;
    }

    // appends new content into the linked list
    public void append(int content) {
        Node appended = new Node(content);
        appended.next = head;
        head = appended;
    }

    // Runs the recursive merge sort and writes the results into a .csv file
    public void write_output(int[] arr, String input_file, int file_number) throws IOException {

        for (int j : arr) {
            append(j);
        }

        this.head = merge_sort(this.head);

        PrintWriter print = new PrintWriter(new FileWriter("natural_merge_sort_results.csv", true));

        while (head != null) {
            head = head.next;
        }
        if(file_number == 1)
        {
            print.print("SORT TYPE," + "FILE NAME," + "NUMBER OF COMPARISONS," + "NUMBER OF EXCHANGES,\n");
        }
        print.print("Natural Merge Sort," + input_file + "," + comparisons + "," + exchanges + ",\n");
        print.close();
    }
}