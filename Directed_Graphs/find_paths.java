/*
Programmer: Sabneet Bains
Course: EN.605.202.85.FA20 Data Structures
 */
public class find_paths {
    /**
     * The find_paths function takes in adjacency_matrix, root, traversed, head, tail, source_node, destination_node,
     * b, and paths as parameters and then checks if already traversed: if so, recursively finds all non-looping paths.
     * Then calls itself to find paths in other cases and set already traversed paths as true along the way.
     */
    public find_paths(int[][] adjacency_matrix, int[] root, boolean[] traversed, int head, int tail,
                      int source_node, int destination_node, boolean b, StringBuffer paths)
    {
        // If source_node and destination_node are still smaller than the adjacency_matrix.length
        if ((source_node < adjacency_matrix.length) && (destination_node < adjacency_matrix.length))
        {
            // If already traversed
            if (b)
            {
                // Creates a label for the head of the path
                paths.append("\t⦿  ").append(head + 1);

                // Calls the recursively_find_path function to backtrack and find all the non-looping paths
                recursively_find_path(root, head, root[tail], paths);

                // Creates a label for the tail of the path
                paths.append(" → ").append(tail + 1).append("\n");
                return;
            }

            // If adjacency_matrix's element at [source_node][destination_node] does not equal zero and not traversed
            if ((adjacency_matrix[source_node][destination_node] != 0) && (!traversed[destination_node]))
            {
                // boolean b logic
                b = destination_node == tail;

                // Sets the root[destination_node] element to source_node and traversed to true
                root[destination_node] = source_node;
                traversed[destination_node] = true;

                // Calls the find_paths function to keep the logic going with new parameters
                new find_paths(adjacency_matrix, root, traversed, head, tail, destination_node,
                        0, b, paths);

                // Changes the root[destination_node] element to -1 and traversed to false
                root[destination_node] = -1;
                traversed[destination_node] = false;
            }
            // Calls the find_paths function to keep the logic going with new parameters
            new find_paths(adjacency_matrix, root, traversed, head, tail, source_node,
                    destination_node + 1, false, paths);
        }
    }
    /**
     * The recursively_find_path function takes in root, head, tail, and paths as parameters and then
     * recursively runs itself while appending the tails of the non-looping paths.
     */
    public void recursively_find_path(int[] root, int head, int tail, StringBuffer paths)
    {
        // If not the base case of no path
        if (tail != head)
        {
            // Recursion
            recursively_find_path(root, head, root[tail], paths);
            paths.append(" → ").append(tail + 1);
        }
    }
}