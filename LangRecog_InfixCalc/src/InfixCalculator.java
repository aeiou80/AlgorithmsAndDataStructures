

/**
 * A program that takes an arithmetic infix expression, evaluates it,
 * and converts it to a postfix expression.
 * @author Cameron J. Davis
 */
public class InfixCalculator {
    
    public InfixCalculator(String input) {
        System.out.println("infix: " + input);
        convertPostFix(input);
    }

    // returns true if the precedence of op1 is greater than
    // or equal to the precedence of op2, otherwise return false;
    private boolean hasPrec(char op1, char op2) {
        if ((op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/')) {
            return false;
        } else if (op1 == '(' || op1 == ')') {
            return false;
        } else {
            return true;
        }
    }
    
    // applies a given operator to two given operands and returns the result
    private int doOp(char op, int y, int x) {
        switch (op) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x * y;
            case '/':
                return x / y;
        }
        return 0;
    }

    public void convertPostFix(String exp) {

        String postFix = new String("");
        StackListBased stack = new StackListBased();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            
            // value encountered: look for more digits as this allows for
            // multi-digit numbers to be used
            if (Character.isDigit(c)) {
                while (i < exp.length() && Character.isDigit(exp.charAt(i))) 
                    postFix += exp.charAt(i++);
                postFix += " ";
            }
            
            // because of the above if statement, c needs to be updated
            // in a case where an operator comes right after a digit with no
            // white space in between them
            if (i < exp.length()) 
                c = exp.charAt(i);
            
            // open brack encountered: push to stack
            if (c == '(') {
                stack.push(c);
            
            // close bracket encountered: while the top of the stack isn't 
            // the corresponding open bracket, pop items off the stack and 
            // append them to the postfix expression
            } else if (c == ')') {
                while (!stack.isEmpty() && (char)stack.peek() != '(') 
                    postFix += stack.pop() + " ";
                stack.pop();
            
            // operator encountered: if the stack isn't empty, pop and append
            // items from the stack onto the postfix expression while the top of
            // the stack has a greater or equal precedence to the current element
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                if (!stack.isEmpty()) {
                    while (!stack.isEmpty() && hasPrec((char)stack.peek(), c) && (char)stack.peek() != '(')
                        postFix += stack.pop() + " ";
                }
                stack.push(c);
            }
        }
        
        // end of expression reached: append remaining items
        // to the postfix expression
        while (!stack.isEmpty() && (char)stack.peek() != '(') 
            postFix += stack.pop();

        System.out.println("postfix: " + postFix);
    }
    
    public void evaluateInfix(String exp) {
        
        char[] elems = exp.toCharArray();
        StackListBased opStack = new StackListBased();
        StackListBased valStack = new StackListBased();
        
        for (int i = 0; i < elems.length; i++) {
            // whitespace encountered: ignore and continue
            if (elems[i] == ' ')
                continue;
            
            // operand encountered: search for more than one digit and
            // push the operand onto the operand stack
            if (Character.isDigit(elems[i])) {
                StringBuffer sbuf = new StringBuffer();
                while (Character.isDigit(elems[i]) && i < elems.length-1) 
                    sbuf.append(elems[i++]);
                valStack.push(Integer.parseInt(sbuf.toString()));
            }
            
            // open bracket encountered: push on operator stack
            if (elems[i] == '(')
                opStack.push(elems[i]);
            
            // close bracket encountered: keep performing operations
            // until the corresponding open bracket is encountered
            else if (elems[i] == ')') {
                while ((char)opStack.peek() != '(')
                    valStack.push(doOp((char)opStack.pop(), (int)valStack.pop(), (int)valStack.pop()));
                opStack.pop();
            }
            
            // operator encountered: keep performing operations while the 
            // top of the operator stack has a greater or equal precedence 
            // to the current element being looked at
            else if (elems[i] == '*' || elems[i] == '/' || elems[i] == '+' || elems[i] == '-') {
                while (!opStack.isEmpty() && hasPrec((char)opStack.peek(), elems[i]))
                    valStack.push(doOp((char)opStack.pop(), (int)valStack.pop(), (int)valStack.pop()));
                opStack.push(elems[i]);
            }
        }
        
        // end of expression has been reached
        // perform remaining operations and return the result
        while (!opStack.isEmpty())
            valStack.push(doOp((char)opStack.pop(), (int)valStack.pop(), (int)valStack.pop()));
            
        System.out.println("result: " + (int)valStack.pop());
    }
}
