package player;
import static org.junit.Assert.*;

import org.junit.Test;

import sound.Duplet;
import sound.Note;
import sound.Pair;
import sound.Quadruplet;
import sound.Triplet;
import sound.Tuplet;
import sound.Type;

import java.util.ArrayList;

public class TupletParserTest {
    ArrayList<ABCLexer.Token> tokens;
    ABCLexer.Token token;
    Parser parser = new Parser();
    
    @Test
    public void basicTuplet() {
        tokens = new ArrayList<ABCLexer.Token>();
        token = new ABCLexer.Token(Type.TUPLET, "(2");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "e");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        Duplet tuplet = new Duplet(new Note('c'), new Note('e'));
        Pair<Tuplet, Integer> ans = new Pair<Tuplet, Integer>(tuplet, 3);
        Pair<Tuplet, Integer> result = parser.tupletParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void accidentalOctaveTripletTupletTest() {
        tokens = new ArrayList<ABCLexer.Token>();
        token = new ABCLexer.Token(Type.TUPLET, "(3");
        tokens.add(token);
        token = new ABCLexer.Token(Type.ACCIDENTAL, "_");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "e");
        tokens.add(token);
        token = new ABCLexer.Token(Type.OCTAVE, "'");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "C");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        Triplet tuplet = new Triplet(new Note('c', -1), new Note('e', 1, 0, 1, 1, false), new Note('C'));
        Pair<Tuplet, Integer> ans = new Pair<Tuplet, Integer>(tuplet, 6);
        Pair<Tuplet, Integer> result = parser.tupletParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void accidentalOctaveQuadrupletTupletTest() {
        tokens = new ArrayList<ABCLexer.Token>();
        token = new ABCLexer.Token(Type.TUPLET, "(4");
        tokens.add(token);
        token = new ABCLexer.Token(Type.ACCIDENTAL, "^");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "e");
        tokens.add(token);
        token = new ABCLexer.Token(Type.OCTAVE, "'");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "C");
        tokens.add(token);
        token = new ABCLexer.Token(Type.ACCIDENTAL, "=");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "d");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        Quadruplet tuplet = new Quadruplet(new Note('c', 1), new Note('e', 1, 0, 1, 1, false), new Note('C'), new Note('d', 0, 0, 1, 1, true));
        Pair<Tuplet, Integer> ans = new Pair<Tuplet, Integer>(tuplet, 8);
        Pair<Tuplet, Integer> result = parser.tupletParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
}
