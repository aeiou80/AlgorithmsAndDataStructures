

/**
 * A program that implements recursive algorithms for solving problems
 * and processing information
 *
 * @author Cameron J Davis
 */
public class BasicRecursion {
    
    public static int c(int n, int k){
        // Base cases
        // (k = n): If there is time to visit every planet, then
        // there is one group of every planet.
        if (k == n){
            System.out.println("c(" + n + ", " + k + ") = 1");
            return 1;
        // (k = 0): If there is no time to visit any planets, then
        // there is one group of nothing.
        } else if (k == 0){
            System.out.println("c(" + n + ", " + k + ") = 1");
            return 1;
        // (k > n): If there is more than enough time to visit all
        // planets, then there can't be a group of anything.
        } else if (k > n){
            System.out.println("c(" + n + ", " + k + ") = 0");
            return 0;
        // If no base cases are triggered, recurse.
        } else {
            System.out.println("c(" + n + ", " + k + ") = c(" 
                            + (n-1) + ", " + (k-1) + ")" + " + c(" + (n-1) + ", " + k + ")");
            return c(n-1,k-1) + c(n-1, k);
        }
    }
    
    public static int P(int n){
        // Base cases
        // (n = 1): The parades of length 1 are a float and a band.
        if (n == 1) {
            System.out.println("P(1) = 2");
            return 2;
        // (n = 2): The parades of length 2 are the following: float-band,
        // band-float, and float-float.
        } else if (n == 2){
            System.out.println("P(2) = 3");
            return 3;
        // If no base cases are triggered, recurse.
        } else {
            System.out.println("P(" + n + ") = P(" + (n-1) + ") + P(" + (n-2) + ")");
            return P(n-1) + P(n-2);
        }
    }
    
    public static void writeLine(char ch, int n){
        //print a character as long as there's atleast one left
        if (n > 0)
            System.out.print(ch);
        //keep recursing as long as we have more than one character to print
        if (n > 1)
            writeLine(ch, n-1);
    }
    
    public static void writeBlock(char ch, int n, int m) {
        //same idea as writeLine
        if (m > 0) {
            writeLine(ch, n);
            System.out.println();
        }
        if (m > 1)
            writeBlock(ch, n, m-1);
    }
    
    public static void reverseDigits(int num){
        // Integers less than 10 shouldn't be further divided
        if (num < 10) {
        System.out.println(num);
        }
        else {
         // The remainder of this operation tells us the
         // last digit of the given integer
         System.out.print(num % 10);
         reverseDigits(num / 10);
        }
    }
    
    public static int myBinarySearch(String [] anArray, int first, int last, String value){
        
        int index;
        if (first > last) {
            index = -1;     //value not in array
        }
        else {
            //if the value is in the array
            int mid = (first + last) / 2;
            if (value.compareTo(anArray[mid]) == 0) {
                index = mid;    //value found at the midpoint of the array
            } 
            //if the string lexicographically precedes the string in the middle
            //then search the first half of the array
            else if (value.compareTo(anArray[mid]) < 0) {
                index = myBinarySearch(anArray, first, mid-1, value);
            }
            //otherwise, search the second half of the array if the string
            //lexicographically follows the string in the middle
            else {
                index = myBinarySearch(anArray, mid+1, last, value);
            }
        }
        
        return index;
        
    }
    
    public static void main (String args[]){
        // Spock's Dilemma test
        c(3, 2);
        System.out.println();
        
        // Parade Organization test
        P(3);
        System.out.println();
        
        writeLine('*', 5);
        System.out.println("\n");
        writeBlock('*', 5, 3);
        
        System.out.println();
        reverseDigits(1297532);
        
        //myBinarySearch test
        System.out.println();
        String[] array = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        System.out.println(myBinarySearch(array, 0, array.length-1, "a"));
    }
}
