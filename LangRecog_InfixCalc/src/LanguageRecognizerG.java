
/**
 * A program that recursively determines whether or not a user given
 * string is a word of the G language
 * <G> = empty string | <E> | <V> <E> | <E> <G> <V>
 * <E> = & | #
 * <V> = W | A
 * 
 * @author Cameron J. Davis
 */
public class LanguageRecognizerG {
    private String input;
    
    public LanguageRecognizerG(String str) {
        this.input = str;
    }
    
    private boolean recursiveRecogG(String str) {
        if (str.compareTo("") == 0) // string in language if empty
            return true;
        else if (str.compareTo("&") == 0 || str.compareTo("#") == 0) //Case 1
            return true;
        else if (str.compareTo("W&") == 0 || str.compareTo("W#") == 0 ||  //Case 2
                str.compareTo("A&") == 0 || str.compareTo("A#") == 0)
            return true;
        else if ((str.charAt(0) == '&' || str.charAt(0) == '#') // recurse with first and last chars removed
                && (str.charAt(str.length()-1) == 'W' || str.charAt(str.length()-1) == 'A')) {
            return recursiveRecogG(str.substring(1, str.length()-1));
        }
        return false;
    }
    
    public void recursivePrintG() {
        if (recursiveRecogG(input)) 
            System.out.println("Recursion: Word \"" + this.input + "\" IS a word of the G-language");
        else
            System.out.println("Recursion: Word \"" + this.input + "\" is NOT a word of the G-language");
    }
    
}
