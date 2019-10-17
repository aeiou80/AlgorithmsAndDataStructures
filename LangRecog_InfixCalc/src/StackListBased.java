import java.util.*;

/**
 * A class that emulates a stack using LinkedList
 * @author Cameron J. Davis
 */
public class StackListBased {
    
    private LinkedList<Object> list;
    
    public StackListBased() {
        createStack();
    }
    
    private void createStack() {
       list = new LinkedList<Object>();
    }
    
    public void popAll() {
        list.clear();
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    public void push (Object top) {
        list.push(top);
    }
    
    public Object pop() {
        return list.pop();
    }
    
    public Object peek() {
        return list.peek();
    }
    
}
