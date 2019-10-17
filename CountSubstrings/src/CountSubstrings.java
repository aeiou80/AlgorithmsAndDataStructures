
import java.io.*;
import java.util.*;
/**
 * A program that calculates the amount of strings and substrings that are case-sensitively
 * matching with a pattern word provided by the user. If a match is detected more than once
 * inside a single string, it is only counted once.
 * @author Cameron J. Davis
 */
public class CountSubstrings {
    
 /*
 * Returns the lowest index at which substring pattern begins in text (or
 * else -1).
 */
    private static int findBrute(List<Character> text, List<Character> pattern) {
	int n = text.size();
	int m = pattern.size();
	for (int i = 0; i <= n - m; i++) { // try every starting index 
							     // within text
		int k = 0; // k is index into pattern
		while (k < m && text.get(i + k) == pattern.get(k))
				// kth character of pattern matches
				k++;
		if (k == m) // if we reach the end of the pattern,
                    return i; // substring text[i..i+m-1] is a match
	}
	return -1; // search failed
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        
        System.out.print("Please enter the name of the text file: ");
        Scanner scanner = new Scanner(System.in);
        File textFile = new File(scanner.nextLine());
        
        System.out.print("Please enter the pattern to look for: ");
        scanner = new Scanner(System.in);
        ArrayList<Character> patternAL = new ArrayList<Character>(0);
        LinkedList<Character> patternLL = new LinkedList<Character>();
        for (char c: scanner.nextLine().toCharArray()) { 
            patternAL.add(c); 
            patternLL.add(c); // create pattern char ArrayList and LinkedList to use in both procedures
        }
        
        /*
        *   Using ArrayList
        */
        int matches = 0;
        scanner = new Scanner(textFile);    // start scanning the text file
        double startTime = System.currentTimeMillis();
        while (scanner.hasNextLine()) { // keep scanning while the text has another line present
            String[] words = scanner.nextLine().split(" "); // Split the scanned line of strings into an array
            for (int i = 0; i < words.length; i++) { // traverse the array of strings
                ArrayList<Character> wordChars = new ArrayList<Character>(0);
                for (char c: words[i].toCharArray())
                    wordChars.add(c);   // create a char array for each string in the line
                
                if (findBrute(wordChars, patternAL) != -1) // if the search doesn't fail, its a match
                    matches++;
            }
        }
        double endTime = System.currentTimeMillis();
        System.out.println("Using ArrayLists: " + matches + " matches, derived in " + (endTime - startTime) + " milliseconds.");
        
        /*
        *   Using LinkedList
        */
        matches = 0;
        scanner = new Scanner(textFile);
        startTime = System.currentTimeMillis();
        while (scanner.hasNextLine()) {
            String[] words = scanner.nextLine().split(" ");

            for (int i = 0; i < words.length; i++) {
                LinkedList<Character> wordChars = new LinkedList<Character>();
                for (char c: words[i].toCharArray())
                    wordChars.add(c);
                
                if (findBrute(wordChars, patternLL) != -1) 
                    matches++;
            }
        }
        endTime = System.currentTimeMillis(); 
        System.out.println("Using LinkedLists: " + matches + " matches, derived in " + (endTime - startTime) + " milliseconds.");
    }
}
