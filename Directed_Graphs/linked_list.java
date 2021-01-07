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

/**
 * The public class linked_list defines a generic linked list implementation with add(Integer a) providing the
 * mechanism to insert a new integer node at the end of the said list while contains(Integer c) returns a 
 * corresponding boolean given the comparison element 'c'.
 */

@SuppressWarnings("rawtypes")
public class linked_list<E> {

    Node first; // Declares the first node
    Node last; // Declares the last node
    int size = 0; // Initiates the list size to zero

    // Defines the integer node class with current node set to content and next to null
    private static class Node<E> {
        final Integer item;
        Node next;

        Node(Integer content) {
            this.item = content;
            this.next = null;
        }
    }

    // Defines adding a new node at end of the list
    public void add(Integer a) {
        final Node b = last;
        final Node addedNode = new Node(a);

        last = addedNode;
        if (b == null)
            first = addedNode;
        else
            b.next = addedNode;
        size++;
    }

    // Checks if the list contains a particular item
    public boolean contains(Integer c) {
        int result = -1;
        if (c == null) for (Node x = first; x != null; x = x.next) {
            if (x.item == null) {
                result = 0;
                break;
            }
        }
        else for (Node x = first; x != null; x = x.next) {
            if (c.equals(x.item)) {
                result = 0;
                break;
            }
        }
        return result != -1; // Returns the boolean based on logic above
    }
}