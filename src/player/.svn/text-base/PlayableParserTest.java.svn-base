package player;
import static org.junit.Assert.*;

import org.junit.Test;

import player.ABCLexer.Token;

import sound.Chord;
import sound.Note;
import sound.Pair;
import sound.PlayableElement;
import sound.Quadruplet;
import sound.Rest;
import sound.Type;


import java.util.ArrayList;


public class PlayableParserTest {
    ArrayList<ABCLexer.Token> tokens;
    ABCLexer.Token token;
    Parser parser = new Parser();
    
    @Test
    public void chordPlayableTest() {
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
        Pair<PlayableElement, Integer> ans = new Pair<PlayableElement, Integer>(chord, 6);
        Pair<PlayableElement, Integer> result = parser.playableElementParser(tokens, 0);
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
        Pair<PlayableElement, Integer> ans = new Pair<PlayableElement, Integer>(tuplet, 8);
        Pair<PlayableElement, Integer> result = parser.playableElementParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void restPlayableTest() {
        tokens = new ArrayList<ABCLexer.Token>(); 
        token = new ABCLexer.Token(Type.REST, "z");
        tokens.add(token);
        tokens.add(new ABCLexer.Token(Type.EOF));
        Pair<PlayableElement, Integer> ans = new Pair<PlayableElement, Integer>(new Rest(), 1);
        Pair<PlayableElement, Integer> result = parser.playableElementParser(tokens, 0);
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
        Pair<PlayableElement, Integer> ans = new Pair<PlayableElement, Integer>(new Note('c', 1, -1, 1, 2, false), 4);
        Pair<PlayableElement, Integer> result = parser.playableElementParser(tokens, 0);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
}
