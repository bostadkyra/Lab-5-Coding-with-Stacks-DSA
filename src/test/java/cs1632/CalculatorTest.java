package cs1632;

import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calc;

    @BeforeEach
    public void setUp(){ //creates new Calculator object for each test
        calc = new Calculator();
    }

    private ArrayList<Token> tokens(String prefixEq){
        try{
            //since Tokenizer reads from InputStream, ByteArrayInputStream lets it read a
            //string the way it would read System.in
            Tokenizer tokenizer = new Tokenizer(
                    new ByteArrayInputStream(prefixEq.getBytes(StandardCharsets.UTF_8)));
            ArrayList<Token> list = new ArrayList<>();
            Token tok;
            do{
                tok = tokenizer.nextToken();
                if (tok != null) list.add(tok);
            } while (tok != null && !(tok instanceof OpToken && ((OpToken) tok).getOperator() == ';'));
            return list;
        } catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void testAddition() {
        assertEquals("Result: 7.0",calc.evaluate(tokens("+ 3 4;")));
        assertEquals("Result: 13.0",calc.evaluate(tokens("+ 12 1;")));
    }

    @org.junit.jupiter.api.Test
    void testSubtraction() {
        assertEquals("Result: 7.0",calc.evaluate(tokens("- 10 3;")));
        assertEquals("Result: -7.0",calc.evaluate(tokens("- 3 10;")));
    }

    @org.junit.jupiter.api.Test
    void testMultiplication() {
        assertEquals("Result: 0.0",calc.evaluate(tokens("* 0 3;")));
        assertEquals("Result: 200.0",calc.evaluate(tokens("* 100 2;")));
    }

    @org.junit.jupiter.api.Test
    void testDivision() {
        assertEquals("Result: 4.0",calc.evaluate(tokens("/ 12 3;")));
        assertEquals("Result: 0.5",calc.evaluate(tokens("/ 5 10;")));
        assertEquals("Error: division by zero",calc.evaluate(tokens("/ 100 0;")));
        assertEquals("Result: 0.0",calc.evaluate(tokens("/ 0 10;")));
    }
}