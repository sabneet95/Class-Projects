//Sabneet Bains
import java.util.Vector; // Basic math library

// Generic Stack implementing E
public class Stack<E> extends Vector<E> {
    public Stack() {
    }

    public E push(E item) {
        addElement(item);
        return item;
    }

    public synchronized E pop() {
        E element;
        element = peek();
        removeElementAt(size() - 1);
        return element;
    }

    public synchronized E peek() {
        assert size() != 0;
        return elementAt(size() - 1);
    }
}
