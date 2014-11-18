package player;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;
import sound.ABCHeader;
import sound.Key;
import sound.KeyMeterTempo;
import sound.Pair;
import sound.Type;

public class HeaderParserTest {
    ArrayList<ABCLexer.Token> tokens;
    ABCLexer.Token token;
    Parser parser = new Parser();
    
    @Test
    public void allHeaderTest() throws ParseException {
        tokens = new ArrayList<ABCLexer.Token>();
        token = new ABCLexer.Token(Type.HEADER_X, "1");
        tokens.add(token);
        token = new ABCLexer.Token(Type.HEADER_T, "Test");
        tokens.add(token);
        token = new ABCLexer.Token(Type.HEADER_C, "Kaivan Wadia");
        tokens.add(token);
        token = new ABCLexer.Token(Type.HEADER_L, "1/2");
        tokens.add(token);
        token = new ABCLexer.Token(Type.HEADER_M, "2/3");
        tokens.add(token);
        token = new ABCLexer.Token(Type.HEADER_Q, "200");
        tokens.add(token);
        token = new ABCLexer.Token(Type.HEADER_K, "Cm");
        tokens.add(token);
        token = new ABCLexer.Token(Type.EOH, "");
        tokens.add(token);
        Key key = Key.C_MINOR;
        KeyMeterTempo kmt = new KeyMeterTempo(key, 2, 3, 200, 1, 2);
        ABCHeader header = new ABCHeader("Test", 1, "Kaivan Wadia", kmt, new ArrayList<String>());
        Pair<ABCHeader, Integer> ans = new Pair<ABCHeader, Integer>(header, 8);
        Pair<ABCHeader, Integer> result = parser.abcHeaderParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void defaultHeaderTest() throws ParseException {
        tokens = new ArrayList<ABCLexer.Token>();
        token = new ABCLexer.Token(Type.HEADER_X, "1");
        tokens.add(token);
        token = new ABCLexer.Token(Type.HEADER_T, "Test");
        tokens.add(token);
        token = new ABCLexer.Token(Type.HEADER_K, "Cm");
        tokens.add(token);
        token = new ABCLexer.Token(Type.EOH, "");
        tokens.add(token);
        Key key = Key.C_MINOR;
        KeyMeterTempo kmt = new KeyMeterTempo(key, 4, 4, 100, 1, 8);
        ABCHeader header = new ABCHeader("Test", 1, "Unknown Composer", kmt, new ArrayList<String>());
        Pair<ABCHeader, Integer> ans = new Pair<ABCHeader, Integer>(header, 4);
        Pair<ABCHeader, Integer> result = parser.abcHeaderParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void feFieldsHeaderTest() throws ParseException {
        tokens = new ArrayList<ABCLexer.Token>();
        token = new ABCLexer.Token(Type.HEADER_X, "1");
        tokens.add(token);
        token = new ABCLexer.Token(Type.HEADER_T, "Test");
        tokens.add(token);
        token = new ABCLexer.Token(Type.HEADER_C, "Kaivan Wadia");
        tokens.add(token);
        token = new ABCLexer.Token(Type.HEADER_L, "1/2");
        tokens.add(token);
        token = new ABCLexer.Token(Type.HEADER_V, "V1");
        tokens.add(token);
        token = new ABCLexer.Token(Type.HEADER_V, "V2");
        tokens.add(token);
        token = new ABCLexer.Token(Type.HEADER_K, "Cm");
        tokens.add(token);
        token = new ABCLexer.Token(Type.EOH, "");
        tokens.add(token);
        ArrayList<String> voices = new ArrayList<String>();
        voices.add("V1");
        voices.add("V2");
        Key key = Key.C_MINOR;
        KeyMeterTempo kmt = new KeyMeterTempo(key, 4, 4, 100, 1, 2);
        ABCHeader header = new ABCHeader("Test", 1, "Kaivan Wadia", kmt, voices);
        Pair<ABCHeader, Integer> ans = new Pair<ABCHeader, Integer>(header, 8);
        Pair<ABCHeader, Integer> result = parser.abcHeaderParser(tokens, 0);
        System.out.println(ans.getVal1().toString());
        System.out.println(result.getVal1().toString());
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
}
