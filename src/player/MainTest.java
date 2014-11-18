package player;

import java.io.FileNotFoundException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

import player.ABCLexer.LexerException;

public class MainTest {
    
    @Test
    public void fileTest1() throws FileNotFoundException, LexerException, ParseException, MidiUnavailableException, InvalidMidiDataException {
        String name = "sample_abc/invention.abc";
        Main.play(name);
    }
    
    @Test
    public void fileTest2() throws FileNotFoundException, LexerException, ParseException, MidiUnavailableException, InvalidMidiDataException {
        String name = "sample_abc/paddy.abc";
        Main.play(name);
    }
    
}
