package player;

import java.util.ArrayList;
import sound.ABCHeader;
import sound.Key;
import sound.KeyMeterTempo;
import static org.junit.Assert.*;
import org.junit.Test;
import sound.Chord;
import sound.Measure;
import sound.Note;
import sound.Pair;
import sound.PlayableElement;
import sound.Type;
import sound.Voice;

public class MusicParserTest {
    ArrayList<ABCLexer.Token> tokens;
    ABCLexer.Token token;
    Parser parser = new Parser();
    
    @Test
    public void basicMusicTest() {
        ArrayList<String> voices = new ArrayList<String>();
        voices.add("V1");
        Key key = Key.C_MINOR;
        KeyMeterTempo kmt = new KeyMeterTempo(key, 4, 4, 120, 1, 4);
        ABCHeader header = new ABCHeader("Test", 1, "Kaivan Wadia", kmt, voices);
        tokens = new ArrayList<ABCLexer.Token>();
        token = new ABCLexer.Token(Type.VOICE, "1");
        tokens.add(token);
        token = new ABCLexer.Token(Type.TUPLET, "(3");
        tokens.add(token);
        token = new ABCLexer.Token(Type.START_CHORD, "[");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "/");
        tokens.add(token);        
        token = new ABCLexer.Token(Type.NOTE, "e");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "/");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "g");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "/");
        tokens.add(token);
        token = new ABCLexer.Token(Type.END_CHORD, "]");
        tokens.add(token);
        token = new ABCLexer.Token(Type.START_CHORD, "[");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "d");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "/");
        tokens.add(token);        
        token = new ABCLexer.Token(Type.NOTE, "f");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "/");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "a");
        tokens.add(token);
        token = new ABCLexer.Token(Type.END_CHORD, "]");
        tokens.add(token);
        token = new ABCLexer.Token(Type.START_CHORD, "[");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "e");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "/");
        tokens.add(token);        
        token = new ABCLexer.Token(Type.NOTE, "g");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "/");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "b");
        tokens.add(token);
        token = new ABCLexer.Token(Type.END_CHORD, "]");
        tokens.add(token);
        token = new ABCLexer.Token(Type.TUPLET, "(3");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "e");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "/");
        tokens.add(token);        
        token = new ABCLexer.Token(Type.NOTE, "g");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "/");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        token = new ABCLexer.Token(Type.LENGTH, "/");
        tokens.add(token);
        token = new ABCLexer.Token(Type.START_CHORD, "[");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "g");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "e");
        tokens.add(token);
        token = new ABCLexer.Token(Type.END_CHORD, "]");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "g");
        tokens.add(token);
        token = new ABCLexer.Token(Type.BAR, "|");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "a");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "b");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "c");
        tokens.add(token);
        token = new ABCLexer.Token(Type.NOTE, "d");
        tokens.add(token);
        token = new ABCLexer.Token(Type.START_REPEAT, "|]");
        tokens.add(token);
        token = new ABCLexer.Token(Type.EOF, "");
        tokens.add(token);
    }

}
