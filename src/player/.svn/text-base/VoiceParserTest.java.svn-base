package player;
import static org.junit.Assert.*;
import org.junit.Test;
import sound.Chord;
import sound.Measure;
import sound.Note;
import sound.Pair;
import sound.PlayableElement;
import sound.Type;
import sound.Voice;

import java.util.ArrayList;


public class VoiceParserTest {
    ArrayList<ABCLexer.Token> tokens;
    ABCLexer.Token token;
    Parser parser = new Parser();
    
    @Test
    public void basicVoiceTest() {
        tokens = new ArrayList<ABCLexer.Token>();
        token = new ABCLexer.Token(Type.VOICE, "1");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "C");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "3/4");
        tokens.add(token);
        token = new ABCLexer.Token(Type.START_CHORD, "[");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "e");
        tokens.add(token);
        token = new ABCLexer.Token(Type.END_CHORD, "]");
        tokens.add(token);
        token = new ABCLexer.Token(Type.BAR, "|");
        tokens.add(token);
        token = new ABCLexer.Token(Type.VOICE, "2");
        tokens.add(token);
        ArrayList<PlayableElement> elems = new ArrayList<PlayableElement>();
        Note note = new Note('C', 3, 4);
        elems.add(note);
        Voice previousVoice = new Voice("1");
        Chord chord = new Chord(new Note('c'));
        chord = chord.addNote(new Note('e'));
        elems.add(chord);
        Measure measure = new Measure(elems, true, false, false, false, false);
        Voice voice = new Voice("1");
        voice.addMeasure(measure);
        Pair<Voice, Integer> ans = new Pair<Voice, Integer>(voice, 8);
        Pair<Voice, Integer> result = parser.voiceParser(tokens, 0, previousVoice);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
    
    @Test
    public void secondVoiceTest() {
        tokens = new ArrayList<ABCLexer.Token>();
        token = new ABCLexer.Token(Type.VOICE, "1");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "C");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "3/4");
        tokens.add(token);
        token = new ABCLexer.Token(Type.START_CHORD, "[");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "e");
        tokens.add(token);
        token = new ABCLexer.Token(Type.END_CHORD, "]");
        tokens.add(token);
        token = new ABCLexer.Token(Type.BAR, "|");
        tokens.add(token);
        token = new ABCLexer.Token(Type.EOF, "");
        tokens.add(token);
        ArrayList<PlayableElement> elems = new ArrayList<PlayableElement>();
        Note note = new Note('C', 3, 4);
        elems.add(note);
        Measure previous = new Measure(elems, false, true, false, false, false);
        Voice previousVoice = new Voice("1");
        previousVoice.addMeasure(previous);
        Chord chord = new Chord(new Note('c'));
        chord = chord.addNote(new Note('e'));
        elems.add(chord);
        Measure measure = new Measure(elems, true, false, false, false, false);
        Voice voice = new Voice("1");
        voice.addMeasure(measure);
        Pair<Voice, Integer> ans = new Pair<Voice, Integer>(voice, 8);
        Pair<Voice, Integer> result = parser.voiceParser(tokens, 0, previousVoice);
        assertEquals(ans.getVal1().toString(), result.getVal1().toString());
        assertEquals(ans.getVal2(), result.getVal2());
    }
}
