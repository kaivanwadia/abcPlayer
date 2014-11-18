package player;
import static org.junit.Assert.*;
import org.junit.Test;
import sound.Chord;
import sound.Note;
import sound.Pair;
import sound.Type;
import java.util.ArrayList;

public class ChordParserTest {
    ArrayList<ABCLexer.Token> tokens;
    ABCLexer.Token token;
    Parser parser = new Parser();
    
    @Test
    public void basicChordTest() {
        tokens = new ArrayList<ABCLexer.Token>();
        token = new ABCLexer.Token(Type.START_CHORD, "[");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "e");
        tokens.add(token);
        token = new ABCLexer.Token(Type.END_CHORD, "]");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        Chord chord = new Chord(new Note('c'));
        chord = chord.addNote(new Note('e'));
        Pair<Chord, Integer> ans = new Pair<Chord, Integer>(chord, 4);
        Pair<Chord, Integer> result = parser.chordParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void accidentalChordTest() {
        tokens = new ArrayList<ABCLexer.Token>();
        token = new ABCLexer.Token(Type.START_CHORD, "[");
        tokens.add(token);
        token = new ABCLexer.Token(Type.ACCIDENTAL, "^");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        token = new ABCLexer.Token(Type.ACCIDENTAL, "_");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "e");
        tokens.add(token);
        token = new ABCLexer.Token(Type.END_CHORD, "]");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        Chord chord = new Chord(new Note('c', 1));
        chord = chord.addNote(new Note('e', -1));
        Pair<Chord, Integer> ans = new Pair<Chord, Integer>(chord, 6);
        Pair<Chord, Integer> result = parser.chordParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void accidentalLengthChordTest() {
        tokens = new ArrayList<ABCLexer.Token>();
        token = new ABCLexer.Token(Type.START_CHORD, "[");
        tokens.add(token);
        token = new ABCLexer.Token(Type.ACCIDENTAL, "^");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "2/3");
        tokens.add(token);
        token = new ABCLexer.Token(Type.ACCIDENTAL, "_");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "e");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "3/2");
        tokens.add(token);
        token = new ABCLexer.Token(Type.END_CHORD, "]");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        Chord chord = new Chord(new Note('c', 1, 2, 3));
        chord = chord.addNote(new Note('e', -1, 3, 2));
        Pair<Chord, Integer> ans = new Pair<Chord, Integer>(chord, 8);
        Pair<Chord, Integer> result = parser.chordParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
}
