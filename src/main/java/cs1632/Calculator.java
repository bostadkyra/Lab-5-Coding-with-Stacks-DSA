package cs1632;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class Calculator {
    public String evaluate(ArrayList<Token> tokens){
        Stack<Double> stack = new Stack<>();
        boolean divByZero = false;

        try{
            for(int i=tokens.size()-1; i>=0; i--){ //starts at end of ArrayList and works back
                Token tok = tokens.get(i);

                if (tok instanceof NumToken){
                    stack.push(Double.parseDouble(tok.getTokenString())); //treats ints as doubles and uses their value (best for division if result has a decimal)
                } else if (tok instanceof OpToken){
                    int op = ((OpToken) tok).getOperator(); //typecasts tok as OpToken

                    if (op == ';'){ //means end of equations
                        continue;
                    }
                    if (op != '+' && op != '-' && op != '*' && op != '/'){ //gives an error when anything other than accepted operators are used
                        return "Error: illegal token (" + tok.getTokenString() + ")";
                    }

                    double left = stack.pop(); //left operand is first popped
                    double right = stack.pop(); //right operand is second popped
                    double result = 0;

                    switch(op){
                        case '+': result = left + right;
                            break;
                        case '-': result = left - right;
                            break;
                        case '*': result = left * right;
                            break;
                        case '/':
                            if (right == 0){
                                divByZero = true;
                                result = Double.NaN; //means Not a Number (undefined)
                            } else{
                                result = left / right;
                            }
                            break;
                    }
                    stack.push(result);
                }
            }
            if (divByZero){
                return "Error: division by zero";
            }
            return "Result: " + stack.pop();
        }
        catch (EmptyStackException e){
            return "Error: malformed expression";
        }
    }
}
