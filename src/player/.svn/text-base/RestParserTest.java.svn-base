package player;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

import sound.Pair;
import sound.Rest;
import sound.Type;

public class RestParserTest {
    ArrayList<ABCLexer.Token> tokens;
    ABCLexer.Token token;
    Parser parser = new Parser();
    
    @Test
    public void basicRestTest() {
        tokens = new ArrayList<ABCLexer.Token>(); 
        token = new ABCLexer.Token(Type.REST, "z");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        Pair<Rest, Integer> ans = new Pair<Rest, Integer>(new Rest(), 1);
        Pair<Rest, Integer> result = parser.restParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void restNumeratorTest() {
        tokens = new ArrayList<ABCLexer.Token>();
        token = new ABCLexer.Token(Type.REST, "z");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "2");
        tokens.add(token);
        Pair<Rest, Integer> ans = new Pair<Rest, Integer>(new Rest(2,1), 2);
        Pair<Rest, Integer> result = parser.restParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void restNumDefDenTest() {
        tokens = new ArrayList<ABCLexer.Token>();
        token = new ABCLexer.Token(Type.REST, "z");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "2/");
        tokens.add(token);
        Pair<Rest, Integer> ans = new Pair<Rest, Integer>(new Rest(2,2), 2);
        Pair<Rest, Integer> result = parser.restParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void restDefNumDenTest() {
        tokens = new ArrayList<ABCLexer.Token>();
        token = new ABCLexer.Token(Type.REST, "z");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "/3");
        tokens.add(token);
        Pair<Rest, Integer> ans = new Pair<Rest, Integer>(new Rest(1,3), 2);
        Pair<Rest, Integer> result = parser.restParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void restDefNumDefDenTest() {
        tokens = new ArrayList<ABCLexer.Token>();
        token = new ABCLexer.Token(Type.REST, "z");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "/");
        tokens.add(token);
        Pair<Rest, Integer> ans = new Pair<Rest, Integer>(new Rest(1,2), 2);
        Pair<Rest, Integer> result = parser.restParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void restNumDenTest() {
        tokens = new ArrayList<ABCLexer.Token>();
        token = new ABCLexer.Token(Type.REST, "z");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "4/3");
        tokens.add(token);
        Pair<Rest, Integer> ans = new Pair<Rest, Integer>(new Rest(4,3), 2);
        Pair<Rest, Integer> result = parser.restParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
}
