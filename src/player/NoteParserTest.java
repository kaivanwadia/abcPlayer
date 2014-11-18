package player;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

import sound.Note;
import sound.Pair;
import sound.Type;

public class NoteParserTest {
    ArrayList<ABCLexer.Token> tokens;
    ABCLexer.Token token;
    Parser parser = new Parser();
    
    @Test
    public void basicNoteTest() {
        tokens = new ArrayList<ABCLexer.Token>(); 
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        Pair<Note, Integer> ans = new Pair<Note, Integer>(new Note('c'), 1);
        Pair<Note, Integer> result = parser.noteParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void accidentalNoteTest() {
        tokens = new ArrayList<ABCLexer.Token>(); 
        token = new ABCLexer.Token(Type.ACCIDENTAL, "^^");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        Pair<Note, Integer> ans = new Pair<Note, Integer>(new Note('c', 2), 2);
        Pair<Note, Integer> result = parser.noteParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
        
        tokens = new ArrayList<ABCLexer.Token>(); 
        token = new ABCLexer.Token(Type.ACCIDENTAL, "__");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "C");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        ans = new Pair<Note, Integer>(new Note('C', -2), 2);
        result = parser.noteParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
        
        tokens = new ArrayList<ABCLexer.Token>(); 
        token = new ABCLexer.Token(Type.ACCIDENTAL, "=");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "C");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        ans = new Pair<Note, Integer>(new Note('C', 0, 0, 1, 1, true), 2);
        result = parser.noteParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void octaveNoteTest() {
        tokens = new ArrayList<ABCLexer.Token>(); 
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        token = new ABCLexer.Token(Type.OCTAVE, "''");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        Pair<Note, Integer> ans = new Pair<Note, Integer>(new Note('c', 2, 0, 1, 1, false), 2);
        Pair<Note, Integer> result = parser.noteParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
        
        tokens = new ArrayList<ABCLexer.Token>(); 
        token = new ABCLexer.Token(Type.NOTE, "C");
        tokens.add(token);
        token = new ABCLexer.Token(Type.OCTAVE, ",,");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        ans = new Pair<Note, Integer>(new Note('C', -2, 0, 1, 1, false), 2);
        result = parser.noteParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void lengthNoteTest() {
        tokens = new ArrayList<ABCLexer.Token>(); 
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "2/3");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        Pair<Note, Integer> ans = new Pair<Note, Integer>(new Note('c', 2, 3), 2);
        Pair<Note, Integer> result = parser.noteParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
        
        tokens = new ArrayList<ABCLexer.Token>(); 
        token = new ABCLexer.Token(Type.NOTE, "C");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "/");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        ans = new Pair<Note, Integer>(new Note('C', 1, 2), 2);
        result = parser.noteParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void octaveAccidentalNoteTest() {
        tokens = new ArrayList<ABCLexer.Token>(); 
        token = new ABCLexer.Token(Type.ACCIDENTAL, "^");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        token = new ABCLexer.Token(Type.OCTAVE, "'");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        Pair<Note, Integer> ans = new Pair<Note, Integer>(new Note('c', 1, 1, 1, 1, false), 3);
        Pair<Note, Integer> result = parser.noteParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
        
        tokens = new ArrayList<ABCLexer.Token>(); 
        token = new ABCLexer.Token(Type.ACCIDENTAL, "_");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "C");
        tokens.add(token);
        token = new ABCLexer.Token(Type.OCTAVE, ",");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        ans = new Pair<Note, Integer>(new Note('C', -1, -1, 1, 1, false), 3);
        result = parser.noteParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void octaveLengthNoteTest() {
        tokens = new ArrayList<ABCLexer.Token>(); 
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        token = new ABCLexer.Token(Type.OCTAVE, "'");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "/");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        Pair<Note, Integer> ans = new Pair<Note, Integer>(new Note('c', 1, 0, 1, 2, false), 3);
        Pair<Note, Integer> result = parser.noteParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
        
        tokens = new ArrayList<ABCLexer.Token>(); 
        token = new ABCLexer.Token(Type.NOTE, "C");
        tokens.add(token);
        token = new ABCLexer.Token(Type.OCTAVE, ",");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "1/");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        ans = new Pair<Note, Integer>(new Note('C', -1, 0, 1, 2, false), 3);
        result = parser.noteParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void octaveAccidentalLengthNoteTest() {
        tokens = new ArrayList<ABCLexer.Token>();
        token = new ABCLexer.Token(Type.ACCIDENTAL, "_");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        token = new ABCLexer.Token(Type.OCTAVE, "'");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "/");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        Pair<Note, Integer> ans = new Pair<Note, Integer>(new Note('c', 1, -1, 1, 2, false), 4);
        Pair<Note, Integer> result = parser.noteParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
        
        tokens = new ArrayList<ABCLexer.Token>(); 
        token = new ABCLexer.Token(Type.ACCIDENTAL, "^^");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "C");
        tokens.add(token);
        token = new ABCLexer.Token(Type.OCTAVE, ",");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "/4");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        ans = new Pair<Note, Integer>(new Note('C', -1, 2, 1, 4, false), 4);
        result = parser.noteParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
}
