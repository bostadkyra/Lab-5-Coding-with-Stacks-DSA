package cs1632;

import java.util.ArrayList;
import java.util.Stack;

public class Calculator {
    public String evaluate(ArrayList<Token> tokens){
        Stack<Double> stack = new Stack<>();
        boolean divByZero = false;

        try{
            for(int i=tokens.size()-1; i>=0; i--){ //starts at end of ArrayList and works back
                Token tok = tokens.get(i);

                if (tok instanceof NumToken){
                    stack.push(Double.parseDouble(tok.getTokenString())); //type casting ints as doubles and using their value (best for division if result has a decimal)
                }
            }
        }
    }
}
