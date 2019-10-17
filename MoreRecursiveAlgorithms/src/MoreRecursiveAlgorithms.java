
import java.util.*;

/**
 * @author Cameron J. Davis
 */
public class MoreRecursiveAlgorithms {

    public static <T extends Comparable<? super T>>
            void recursiveSelectionSort(T[] theArray, int n) {
        // Checks if the size of the array is valid
        if (n >= 0) {
            //finds the index of the largest value of the sub array
            int largest = 0;
            for (int i = 0; i < n; i++) {
                if (theArray[i].compareTo(theArray[largest]) > 0) 
                    largest = i;
            }
            
            if (n > 0) {
                // shift the largest value of the sub-array to the end
                T temp = theArray[largest];
                theArray[largest] = theArray[n - 1];
                theArray[n - 1] = temp;
                // recurse, shorten the array size by one
                recursiveSelectionSort(theArray, n - 1);
            } else {
                System.out.print("Selection Sorted Array: ");
                for (T elem : theArray) 
                    System.out.print(elem + " ");
            }
          // if array size not valid then throw exception
        } else 
            throw new IllegalArgumentException("ERROR: Please enter a valid array length");
    }

    public static <T extends Comparable<? super T>>
            void recursiveBubbleSort(T[] theArray, int n) {
        // check if size is valid
        if (n >= 0) {
            // one pass through array that bubble sorts
            for (int i = 0; i < n - 1; i++) {
                if (theArray[i].compareTo(theArray[i + 1]) > 0) {
                    T temp = theArray[i];
                    theArray[i] = theArray[i + 1];
                    theArray[i + 1] = temp;
                }
            }
            // if more than two values are present, recurse
            if (n > 2)
                recursiveBubbleSort(theArray, n - 1);
            else {
                System.out.print("Bubble Sorted Array: ");
                for (T elem : theArray) 
                    System.out.print(elem + " ");
            }
         // if size not valid, throw exception
        } else 
            throw new IllegalArgumentException("ERROR: Please enter a valid array length");
    }

    public static boolean isInLanguage(String str) {
        // Easy conditions to check if its not in the language
        if (!str.contains("$") || str.length() == 0 || str.endsWith("$")) 
            return false;
        
        // split string into two at the location of '$'
        String[] split = str.split("\\$");
        Stack stack = new Stack();
        Queue<Character> queue = new LinkedList<Character>();

        // fill first half of split string into queue
        for (char c : split[0].toCharArray())
            queue.add(c);
        
        // fill second half of split string into stack
        for (char c : split[1].toCharArray()) 
            stack.add(c);
        
        // the reverse of w has to be the same length as w in order 
        // to potentially be in the language
        if (queue.size() != stack.size()) 
            return false;
        
        // assume true
        boolean isInL = true;
        for (int i = 0; i < split[0].length(); i++) {
            // the moment an inequality is encountered, the word is
            // not in the language
            if (stack.pop() != queue.remove())
                isInL = false;
        }
        return isInL;
    }
    
    public static int convertToNumber(String str) {
        Queue<Character> q = new LinkedList<Character>();
        // Enqueue every character that passes the isDigit() test to the queue
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) 
                q.add(c);
        }

        String num = "";
        // Dequeue every digit and append them to an empty string
        while (!q.isEmpty()) 
            num += q.remove();
        
        // parse the string if there was digits appended
        if (!num.equals(""))
            return Integer.parseInt(num);
        else 
            return -1;
    }
    
    // test client
    public static void main(String[] args) {
        Integer[] selectionTest = {10, 2, 7, 5, 3, 9, 6, 8, 1, 4};
        recursiveSelectionSort(selectionTest, selectionTest.length);
        System.out.println();
        Integer[] bubbleTest = {4, 1, 8, 6, 9, 3, 5, 7, 2, 10};
        recursiveBubbleSort(bubbleTest, bubbleTest.length);
        System.out.println();

        String L = "aba$aba";
        if (isInLanguage(L)) 
            System.out.println("\"" + L + "\"" + " is in the language.");
        else
            System.out.println("\"" + L + "\"" + " is not in the language.");

        String num = "1 2  3   4    5     6      7";
        System.out.println(convertToNumber(num));
    }
}
