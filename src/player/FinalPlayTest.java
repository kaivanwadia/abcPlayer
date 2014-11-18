package player;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

import player.ABCLexer.LexerException;

import junit.framework.Assert;

import sound.ABCFile;
import sound.ABCMusic;
import sound.IntegratedPlayerTest;
import sound.Pitch;
import sound.SequencePlayer;

public class FinalPlayTest {
    
    @Test
    public void testSample1() throws FileNotFoundException, LexerException, MidiUnavailableException, InvalidMidiDataException, ParseException {
        String file = "sample_abc/piece1.abc";
        ABCLexer lexer = new ABCLexer(file);
        ArrayList<ABCLexer.Token> input = lexer.getTokens();
        Parser parser = new Parser();
        ABCFile abcfile = parser.abcFileParser(input);
        
        SequencePlayer sp = abcfile.play();
        SequencePlayer player = new SequencePlayer(140,12);

        player.addNote(new Pitch('C').toMidiNote(), 0, 12);
        player.addNote(new Pitch('C').toMidiNote(), 12, 12);
        player.addNote(new Pitch('C').toMidiNote(), 24, 9);
        player.addNote(new Pitch('D').toMidiNote(), 33, 3);
        player.addNote(new Pitch('E').toMidiNote(), 36, 12);
        player.addNote(new Pitch('E').toMidiNote(), 48, 9);
        player.addNote(new Pitch('D').toMidiNote(), 57, 3);
        player.addNote(new Pitch('E').toMidiNote(), 60, 9);
        player.addNote(new Pitch('F').toMidiNote(), 69, 3);
        player.addNote(new Pitch('G').toMidiNote(), 72, 24);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 96, 4);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 100, 4);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 104, 4);
        player.addNote(new Pitch('G').toMidiNote(), 108, 4);
        player.addNote(new Pitch('G').toMidiNote(), 112, 4);
        player.addNote(new Pitch('G').toMidiNote(), 116, 4);
        player.addNote(new Pitch('E').toMidiNote(), 120, 4);
        player.addNote(new Pitch('E').toMidiNote(), 124, 4);
        player.addNote(new Pitch('E').toMidiNote(), 128, 4);
        player.addNote(new Pitch('C').toMidiNote(), 132, 4);
        player.addNote(new Pitch('C').toMidiNote(), 136, 4);
        player.addNote(new Pitch('C').toMidiNote(), 140, 4);
        player.addNote(new Pitch('G').toMidiNote(), 144, 9);
        player.addNote(new Pitch('F').toMidiNote(), 153, 3);
        player.addNote(new Pitch('E').toMidiNote(), 156, 9);
        player.addNote(new Pitch('D').toMidiNote(), 165, 3);
        player.addNote(new Pitch('C').toMidiNote(), 168, 24);
        
        System.out.println(abcfile.header.toString());
        
        Assert.assertTrue(IntegratedPlayerTest.comparePlayers(sp, player));
        Assert.assertEquals(140, abcfile.header.keyMetTempo.bpm);
        
        //sp.play();
        
    }
    
    @Test
    public void testSample1_1() throws FileNotFoundException, LexerException, MidiUnavailableException, InvalidMidiDataException, ParseException {
        String file = "sample_abc/piece1-1.abc";
        ABCLexer lexer = new ABCLexer(file);
        ArrayList<ABCLexer.Token> input = lexer.getTokens();
        Parser parser = new Parser();
        ABCFile abcfile = parser.abcFileParser(input);
        
//        ABCMusic music = abcfile.music;
//        System.out.println(music.toString());
        
        SequencePlayer sp = abcfile.play();
        SequencePlayer player = new SequencePlayer(140,12);
        
        player.addNote(new Pitch('C').toMidiNote(), 0, 12);
        player.addNote(new Pitch('C').toMidiNote(), 12, 12);
        player.addNote(new Pitch('C').toMidiNote(), 24, 9);
        player.addNote(new Pitch('D').toMidiNote(), 33, 3);
        player.addNote(new Pitch('E').toMidiNote(), 36, 12);
        player.addNote(new Pitch('E').toMidiNote(), 48, 9);
        player.addNote(new Pitch('D').toMidiNote(), 57, 3);
        player.addNote(new Pitch('E').toMidiNote(), 60, 9);
        player.addNote(new Pitch('F').toMidiNote(), 69, 3);
        player.addNote(new Pitch('G').toMidiNote(), 72, 24);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 96, 4);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 100, 4);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 104, 4);
        player.addNote(new Pitch('G').toMidiNote(), 108, 4);
        player.addNote(new Pitch('G').toMidiNote(), 112, 4);
        player.addNote(new Pitch('G').toMidiNote(), 116, 4);
        player.addNote(new Pitch('E').toMidiNote(), 120, 4);
        player.addNote(new Pitch('E').toMidiNote(), 124, 4);
        player.addNote(new Pitch('E').toMidiNote(), 128, 4);
        player.addNote(new Pitch('C').toMidiNote(), 132, 4);
        player.addNote(new Pitch('C').toMidiNote(), 136, 4);
        player.addNote(new Pitch('C').toMidiNote(), 140, 4);
        player.addNote(new Pitch('G').toMidiNote(), 144, 9);
        player.addNote(new Pitch('F').toMidiNote(), 153, 3);
        player.addNote(new Pitch('E').toMidiNote(), 156, 9);
        player.addNote(new Pitch('D').toMidiNote(), 165, 3);
        player.addNote(new Pitch('C').toMidiNote(), 168, 24);
        
        System.out.println(abcfile.header.toString());
        
        Assert.assertTrue(IntegratedPlayerTest.comparePlayers(sp, player));
        Assert.assertEquals(140, abcfile.header.keyMetTempo.bpm);
        
        //sp.play();
        
    }

    @Test
    public void testSample1_2() throws FileNotFoundException, LexerException, MidiUnavailableException, InvalidMidiDataException, ParseException {
        String file = "sample_abc/piece1-2.abc";
        ABCLexer lexer = new ABCLexer(file);
        ArrayList<ABCLexer.Token> input = lexer.getTokens();
        Parser parser = new Parser();
        ABCFile abcfile = parser.abcFileParser(input);
        
        SequencePlayer sp = abcfile.play();
        SequencePlayer player = new SequencePlayer(140,12);
        
        player.addNote(new Pitch('C').toMidiNote(), 0, 12);
        player.addNote(new Pitch('C').toMidiNote(), 12, 12);
        player.addNote(new Pitch('C').toMidiNote(), 24, 9);
        player.addNote(new Pitch('D').toMidiNote(), 33, 3);
        player.addNote(new Pitch('E').toMidiNote(), 36, 12);
        player.addNote(new Pitch('E').toMidiNote(), 48, 9);
        player.addNote(new Pitch('D').toMidiNote(), 57, 3);
        player.addNote(new Pitch('E').toMidiNote(), 60, 9);
        player.addNote(new Pitch('F').toMidiNote(), 69, 3);
        player.addNote(new Pitch('G').toMidiNote(), 72, 24);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 96, 4);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 100, 4);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 104, 4);
        player.addNote(new Pitch('G').toMidiNote(), 108, 4);
        player.addNote(new Pitch('G').toMidiNote(), 112, 4);
        player.addNote(new Pitch('G').toMidiNote(), 116, 4);
        player.addNote(new Pitch('E').toMidiNote(), 120, 4);
        player.addNote(new Pitch('E').toMidiNote(), 124, 4);
        player.addNote(new Pitch('E').toMidiNote(), 128, 4);
        player.addNote(new Pitch('C').toMidiNote(), 132, 4);
        player.addNote(new Pitch('C').toMidiNote(), 136, 4);
        player.addNote(new Pitch('C').toMidiNote(), 140, 4);
        player.addNote(new Pitch('G').toMidiNote(), 144, 9);
        player.addNote(new Pitch('F').toMidiNote(), 153, 3);
        player.addNote(new Pitch('E').toMidiNote(), 156, 9);
        player.addNote(new Pitch('D').toMidiNote(), 165, 3);
        player.addNote(new Pitch('C').toMidiNote(), 168, 24);
        
        System.out.println(abcfile.header.toString());
        Assert.assertTrue(IntegratedPlayerTest.comparePlayers(sp, player));
        Assert.assertEquals(140, abcfile.header.keyMetTempo.bpm);
        //sp.play();   
    }
    
    @Test
    public void testSample1_2_sharps() throws FileNotFoundException, LexerException, MidiUnavailableException, InvalidMidiDataException, ParseException {
        String file = "sample_abc/piece1-2_sharps.abc";
        ABCLexer lexer = new ABCLexer(file);
        ArrayList<ABCLexer.Token> input = lexer.getTokens();
        Parser parser = new Parser();
        ABCFile abcfile = parser.abcFileParser(input);
        
        SequencePlayer sp = abcfile.play();
        SequencePlayer player = new SequencePlayer(140,12);
        
        player.addNote(new Pitch('C').accidentalTranspose(1).toMidiNote(), 0, 12);
        player.addNote(new Pitch('C').accidentalTranspose(1).transpose(-Pitch.OCTAVE).toMidiNote(), 12, 12);
        player.addNote(new Pitch('C').accidentalTranspose(1).transpose(2*Pitch.OCTAVE).toMidiNote(), 24, 9);
        player.addNote(new Pitch('D').toMidiNote(), 33, 3);
        player.addNote(new Pitch('E').toMidiNote(), 36, 12);
        player.addNote(new Pitch('E').toMidiNote(), 48, 9);
        player.addNote(new Pitch('D').toMidiNote(), 57, 3);
        player.addNote(new Pitch('E').toMidiNote(), 60, 9);
        player.addNote(new Pitch('F').toMidiNote(), 69, 3);
        player.addNote(new Pitch('G').toMidiNote(), 72, 24);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 96, 4);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 100, 4);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 104, 4);
        player.addNote(new Pitch('G').toMidiNote(), 108, 4);
        player.addNote(new Pitch('G').toMidiNote(), 112, 4);
        player.addNote(new Pitch('G').toMidiNote(), 116, 4);
        player.addNote(new Pitch('E').toMidiNote(), 120, 4);
        player.addNote(new Pitch('E').toMidiNote(), 124, 4);
        player.addNote(new Pitch('E').toMidiNote(), 128, 4);
        player.addNote(new Pitch('C').toMidiNote(), 132, 4);
        player.addNote(new Pitch('C').toMidiNote(), 136, 4);
        player.addNote(new Pitch('C').toMidiNote(), 140, 4);
        player.addNote(new Pitch('G').toMidiNote(), 144, 9);
        player.addNote(new Pitch('F').toMidiNote(), 153, 3);
        player.addNote(new Pitch('E').toMidiNote(), 156, 9);
        player.addNote(new Pitch('D').toMidiNote(), 165, 3);
        player.addNote(new Pitch('C').toMidiNote(), 168, 24);
        
        System.out.println(abcfile.header.toString());
        Assert.assertTrue(IntegratedPlayerTest.comparePlayers(sp, player));
        Assert.assertEquals(140, abcfile.header.keyMetTempo.bpm);
        //sp.play();   
    }
    
    @Test
    public void testSample1_2_sharps_naturals() throws FileNotFoundException, LexerException, MidiUnavailableException, InvalidMidiDataException, ParseException {
        String file = "sample_abc/piece1-2_sharps_naturals.abc";
        ABCLexer lexer = new ABCLexer(file);
        ArrayList<ABCLexer.Token> input = lexer.getTokens();
        Parser parser = new Parser();
        ABCFile abcfile = parser.abcFileParser(input);
        
        SequencePlayer sp = abcfile.play();
        SequencePlayer player = new SequencePlayer(140,12);
        
        player.addNote(new Pitch('C').accidentalTranspose(1).toMidiNote(), 0, 12);
        player.addNote(new Pitch('C').accidentalTranspose(1).transpose(-Pitch.OCTAVE).toMidiNote(), 12, 12);
        player.addNote(new Pitch('C').transpose(2*Pitch.OCTAVE).toMidiNote(), 24, 9);
        player.addNote(new Pitch('C').toMidiNote(), 33, 3);
        player.addNote(new Pitch('E').toMidiNote(), 36, 12);
        player.addNote(new Pitch('E').toMidiNote(), 48, 9);
        player.addNote(new Pitch('D').toMidiNote(), 57, 3);
        player.addNote(new Pitch('E').toMidiNote(), 60, 9);
        player.addNote(new Pitch('F').toMidiNote(), 69, 3);
        player.addNote(new Pitch('G').toMidiNote(), 72, 24);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 96, 4);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 100, 4);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 104, 4);
        player.addNote(new Pitch('G').toMidiNote(), 108, 4);
        player.addNote(new Pitch('G').toMidiNote(), 112, 4);
        player.addNote(new Pitch('G').toMidiNote(), 116, 4);
        player.addNote(new Pitch('E').toMidiNote(), 120, 4);
        player.addNote(new Pitch('E').toMidiNote(), 124, 4);
        player.addNote(new Pitch('E').toMidiNote(), 128, 4);
        player.addNote(new Pitch('C').toMidiNote(), 132, 4);
        player.addNote(new Pitch('C').toMidiNote(), 136, 4);
        player.addNote(new Pitch('C').toMidiNote(), 140, 4);
        player.addNote(new Pitch('G').toMidiNote(), 144, 9);
        player.addNote(new Pitch('F').toMidiNote(), 153, 3);
        player.addNote(new Pitch('E').toMidiNote(), 156, 9);
        player.addNote(new Pitch('D').toMidiNote(), 165, 3);
        player.addNote(new Pitch('C').toMidiNote(), 168, 24);
        
        System.out.println(abcfile.header.toString());
        Assert.assertTrue(IntegratedPlayerTest.comparePlayers(sp, player));
        Assert.assertEquals(140, abcfile.header.keyMetTempo.bpm);
        //sp.play();   
    }
    
    @Test
    public void testSample1_2_sharps_naturals_changedKey() throws FileNotFoundException, LexerException, MidiUnavailableException, InvalidMidiDataException, ParseException {
        String file = "sample_abc/piece1-2_sharps_naturals_changedKey.abc";
        ABCLexer lexer = new ABCLexer(file);
        ArrayList<ABCLexer.Token> input = lexer.getTokens();
        Parser parser = new Parser();
        ABCFile abcfile = parser.abcFileParser(input);
        
        SequencePlayer sp = abcfile.play();
        SequencePlayer player = new SequencePlayer(140,12);
        
        player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 0, 12);
        player.addNote(new Pitch('F').accidentalTranspose(1).transpose(-Pitch.OCTAVE).toMidiNote(), 12, 12);
        player.addNote(new Pitch('F').transpose(2*Pitch.OCTAVE).toMidiNote(), 24, 9);
        player.addNote(new Pitch('F').transpose(Pitch.OCTAVE).toMidiNote(), 33, 3);
        player.addNote(new Pitch('E').toMidiNote(), 36, 12);
        player.addNote(new Pitch('E').toMidiNote(), 48, 9);
        player.addNote(new Pitch('D').toMidiNote(), 57, 3);
        player.addNote(new Pitch('E').toMidiNote(), 60, 9);
        player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 69, 3);
        player.addNote(new Pitch('G').toMidiNote(), 72, 24);
        player.addNote(new Pitch('C').accidentalTranspose(-1).transpose(Pitch.OCTAVE).toMidiNote(), 96, 4);
        player.addNote(new Pitch('C').accidentalTranspose(-1).transpose(Pitch.OCTAVE).toMidiNote(), 100, 4);
        player.addNote(new Pitch('C').accidentalTranspose(-1).transpose(Pitch.OCTAVE).toMidiNote(), 104, 4);
        player.addNote(new Pitch('G').toMidiNote(), 108, 4);
        player.addNote(new Pitch('G').toMidiNote(), 112, 4);
        player.addNote(new Pitch('G').toMidiNote(), 116, 4);
        player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 120, 4);
        player.addNote(new Pitch('F').toMidiNote(), 124, 4);
        player.addNote(new Pitch('F').toMidiNote(), 128, 4);
        player.addNote(new Pitch('C').accidentalTranspose(-1).toMidiNote(), 132, 4);
        player.addNote(new Pitch('C').accidentalTranspose(-1).toMidiNote(), 136, 4);
        player.addNote(new Pitch('C').accidentalTranspose(-1).toMidiNote(), 140, 4);
        player.addNote(new Pitch('G').toMidiNote(), 144, 9);
        player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 153, 3);
        player.addNote(new Pitch('E').toMidiNote(), 156, 9);
        player.addNote(new Pitch('D').toMidiNote(), 165, 3);
        player.addNote(new Pitch('C').toMidiNote(), 168, 24);
        
        System.out.println(abcfile.header.toString());
        Assert.assertTrue(IntegratedPlayerTest.comparePlayers(sp, player));
        Assert.assertEquals(140, abcfile.header.keyMetTempo.bpm);
        sp.play();   
    }
    
    @Test
    public void testSample1_3() throws FileNotFoundException, LexerException, MidiUnavailableException, InvalidMidiDataException, ParseException {
        String file = "sample_abc/piece1-3.abc";
        ABCLexer lexer = new ABCLexer(file);
        ArrayList<ABCLexer.Token> input = lexer.getTokens();
        Parser parser = new Parser();
        ABCFile abcfile = parser.abcFileParser(input);
        
        SequencePlayer sp = abcfile.play();
        SequencePlayer player = new SequencePlayer(80,6);
        
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 0, 2);
        player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 0, 2);
        player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 0, 2);
        player.addNote(new Pitch('C').toMidiNote(), 0, 2);
        
        player.addNote(new Pitch('D').transpose(Pitch.OCTAVE).toMidiNote(), 2, 2);
        player.addNote(new Pitch('F').transpose(Pitch.OCTAVE).toMidiNote(), 2, 2);
        player.addNote(new Pitch('A').transpose(Pitch.OCTAVE).toMidiNote(), 2, 2);

        player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 4, 2);
        player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 4, 2);
        player.addNote(new Pitch('B').transpose(Pitch.OCTAVE).toMidiNote(), 4, 2);
        
        player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 6, 2);
        player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 8, 2);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 10, 2);
        
        player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 12, 6);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 12, 6);
        player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 12, 6);
        
        player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 18, 6);            
        
        
        System.out.println(abcfile.header.toString());
        Assert.assertTrue(IntegratedPlayerTest.comparePlayers(sp, player));
        Assert.assertEquals(80, abcfile.header.keyMetTempo.bpm);
        //sp.play();   
    }
    
    @Test
    public void testSample1_4() throws FileNotFoundException, LexerException, MidiUnavailableException, InvalidMidiDataException, ParseException {
        String file = "sample_abc/piece1-4.abc";
        ABCLexer lexer = new ABCLexer(file);
        ArrayList<ABCLexer.Token> input = lexer.getTokens();
        Parser parser = new Parser();
        ABCFile abcfile = parser.abcFileParser(input);
        
        SequencePlayer sp = abcfile.play();
        
        SequencePlayer player = new SequencePlayer(80, 6);
        
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 0, 2);
        player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 0, 2);
        player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 0, 2);
        player.addNote(new Pitch('C').toMidiNote(), 0, 2);
        
        player.addNote(new Pitch('D').transpose(Pitch.OCTAVE).toMidiNote(), 2, 2);
        player.addNote(new Pitch('F').transpose(Pitch.OCTAVE).toMidiNote(), 2, 2);
        player.addNote(new Pitch('A').transpose(Pitch.OCTAVE).toMidiNote(), 2, 2);

        player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 4, 2);
        player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 4, 2);
        player.addNote(new Pitch('B').transpose(Pitch.OCTAVE).toMidiNote(), 4, 2);
        
        player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 6, 2);
        player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 8, 2);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 10, 2);
        
        player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 12, 6);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 12, 6);
        player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 12, 6);
        
        player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 18, 6);            
        
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(),  24,   2);
        player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(),  24,   2);
        player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(),  24,   2);
        player.addNote(new Pitch('C').toMidiNote(),  24,   2);
             
        player.addNote(new Pitch('D').transpose(Pitch.OCTAVE).toMidiNote(),  26,   2);
        player.addNote(new Pitch('F').transpose(Pitch.OCTAVE).toMidiNote(),  26,   2);
        player.addNote(new Pitch('A').transpose(Pitch.OCTAVE).toMidiNote(),  26,   2);

        player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(),  28,   2);
        player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(),  28,   2);
        player.addNote(new Pitch('B').transpose(Pitch.OCTAVE).toMidiNote(),  28,   2);
             
        player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(),  30,   2);
        player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(),  32,   2);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(),  34,   2);
            
        player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(),  36,   6);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(),  36,   6);
        player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(),  36,   6);
              
        player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(),  42,   6);

        
        System.out.println(abcfile.header.toString());
        Assert.assertTrue(IntegratedPlayerTest.comparePlayers(sp, player));
        Assert.assertEquals(80, abcfile.header.keyMetTempo.bpm);
        //sp.play();  
    }
    
    @Test
    public void testSample1_5() throws FileNotFoundException, LexerException, MidiUnavailableException, InvalidMidiDataException, ParseException {
        String file = "sample_abc/piece1-5.abc";
        ABCLexer lexer = new ABCLexer(file);
        ArrayList<ABCLexer.Token> input = lexer.getTokens();
        Parser parser = new Parser();
        ABCFile abcfile = parser.abcFileParser(input);
        
        SequencePlayer sp = abcfile.play();
        
        SequencePlayer player = new SequencePlayer(140, 12);
        
        player.addNote(new Pitch('C').toMidiNote(),  0,    12);
        player.addNote(new Pitch('C').toMidiNote(),  12,   12);
        player.addNote(new Pitch('C').toMidiNote(),  24,   9);
        player.addNote(new Pitch('D').toMidiNote(),  33,   3);
        player.addNote(new Pitch('E').toMidiNote(),  36,   12);
    
        player.addNote(new Pitch('E').toMidiNote(),  48,   9);
        player.addNote(new Pitch('D').toMidiNote(),  57,   3);
        player.addNote(new Pitch('E').toMidiNote(),  60,   9);
        player.addNote(new Pitch('F').toMidiNote(),  69,   3);
        player.addNote(new Pitch('G').toMidiNote(),  72,   24);
    
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(),  96,   4);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(),  100,  4);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(),  104,  4);
        player.addNote(new Pitch('G').toMidiNote(),  108,  4);
        player.addNote(new Pitch('G').toMidiNote(),  112,  4);
        player.addNote(new Pitch('G').toMidiNote(),  116,  4);
        player.addNote(new Pitch('E').toMidiNote(),  120,  4);
        player.addNote(new Pitch('E').toMidiNote(),  124,  4);
        player.addNote(new Pitch('E').toMidiNote(),  128,  4);
        player.addNote(new Pitch('C').toMidiNote(),  132,  4);
        player.addNote(new Pitch('C').toMidiNote(),  136,  4);
        player.addNote(new Pitch('C').toMidiNote(),  140,  4);
    
        player.addNote(new Pitch('C').toMidiNote(),  144,  12);
        player.addNote(new Pitch('C').toMidiNote(),  156,  12);
        player.addNote(new Pitch('C').toMidiNote(),  168,  9);
        player.addNote(new Pitch('D').toMidiNote(),  177,  3);
        player.addNote(new Pitch('E').toMidiNote(),  180,  12);
    
        player.addNote(new Pitch('G').toMidiNote(),  192,  9);
        player.addNote(new Pitch('F').toMidiNote(),  201,  3);
        player.addNote(new Pitch('E').toMidiNote(),  204,  9);
        player.addNote(new Pitch('D').toMidiNote(),  213,  3);
        player.addNote(new Pitch('C').toMidiNote(),  216,  24);
        
        System.out.println(abcfile.header.toString());
        Assert.assertTrue(IntegratedPlayerTest.comparePlayers(sp, player));
        Assert.assertEquals(140, abcfile.header.keyMetTempo.bpm);
        //sp.play();  
    }
    
    @Test
    public void testSample1_6() throws FileNotFoundException, LexerException, MidiUnavailableException, InvalidMidiDataException, ParseException {
        String file = "sample_abc/piece1-6.abc";
        ABCLexer lexer = new ABCLexer(file);
        ArrayList<ABCLexer.Token> input = lexer.getTokens();
        Parser parser = new Parser();
        ABCFile abcfile = parser.abcFileParser(input);
        
        SequencePlayer sp = abcfile.play();
        
        SequencePlayer player = new SequencePlayer(140, 12);
        
        player.addNote(new Pitch('C').toMidiNote(),  0,    12);
        player.addNote(new Pitch('C').toMidiNote(),  12,   12);
        player.addNote(new Pitch('C').toMidiNote(),  24,   9);
        player.addNote(new Pitch('D').toMidiNote(),  33,   3);
        player.addNote(new Pitch('E').toMidiNote(),  36,   12);
    
        player.addNote(new Pitch('E').toMidiNote(),  48,   9);
        player.addNote(new Pitch('D').toMidiNote(),  57,   3);
        player.addNote(new Pitch('E').toMidiNote(),  60,   9);
        player.addNote(new Pitch('F').toMidiNote(),  69,   3);
        player.addNote(new Pitch('G').toMidiNote(),  72,   24);
    
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(),  96,   4);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(),  100,  4);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(),  104,  4);
        player.addNote(new Pitch('G').toMidiNote(),  108,  4);
        player.addNote(new Pitch('G').toMidiNote(),  112,  4);
        player.addNote(new Pitch('G').toMidiNote(),  116,  4);
        player.addNote(new Pitch('E').toMidiNote(),  120,  4);
        player.addNote(new Pitch('E').toMidiNote(),  124,  4);
        player.addNote(new Pitch('E').toMidiNote(),  128,  4);
        player.addNote(new Pitch('C').toMidiNote(),  132,  4);
        player.addNote(new Pitch('C').toMidiNote(),  136,  4);
        player.addNote(new Pitch('C').toMidiNote(),  140,  4);
    
        player.addNote(new Pitch('C').toMidiNote(),  144,  12);
        player.addNote(new Pitch('C').toMidiNote(),  156,  12);
        player.addNote(new Pitch('C').toMidiNote(),  168,  9);
        player.addNote(new Pitch('D').toMidiNote(),  177,  3);
        player.addNote(new Pitch('E').toMidiNote(),  180,  12);
    
        player.addNote(new Pitch('E').toMidiNote(),  192,    9);
        player.addNote(new Pitch('D').toMidiNote(),  201,    3);
        player.addNote(new Pitch('E').toMidiNote(),  204,    9);
        player.addNote(new Pitch('F').toMidiNote(),  213,    3);
        player.addNote(new Pitch('G').toMidiNote(),  216,    24);
                
        player.addNote(new Pitch('G').toMidiNote(),  240,   9);
        player.addNote(new Pitch('F').toMidiNote(),  249,   3);
        player.addNote(new Pitch('E').toMidiNote(),  252,   9);
        player.addNote(new Pitch('D').toMidiNote(),  261,   3);
        player.addNote(new Pitch('C').toMidiNote(),  264,   24);
        
        System.out.println(abcfile.header.toString());
        Assert.assertTrue(IntegratedPlayerTest.comparePlayers(sp, player));
        Assert.assertEquals(140, abcfile.header.keyMetTempo.bpm);
        //sp.play();  
    }
    
    @Test
    public void restsTests() throws FileNotFoundException, LexerException, MidiUnavailableException, InvalidMidiDataException, ParseException {
        String file = "sample_abc/piece_rests.abc";
        ABCLexer lexer = new ABCLexer(file);
        ArrayList<ABCLexer.Token> input = lexer.getTokens();
        Parser parser = new Parser();
        ABCFile abcfile = parser.abcFileParser(input);
        
        SequencePlayer sp = abcfile.play();
        
        SequencePlayer player = new SequencePlayer(140, 12);
    
        player.addNote(new Pitch('E').toMidiNote(),  48,   9);
        player.addNote(new Pitch('F').toMidiNote(),  69,   3);
    
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(),  96,   4);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(),  100,  4);
        player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(),  104,  4);
        player.addNote(new Pitch('E').toMidiNote(),  120,  4);
        player.addNote(new Pitch('E').toMidiNote(),  124,  4);
        player.addNote(new Pitch('E').toMidiNote(),  128,  4);
        player.addNote(new Pitch('C').toMidiNote(),  132,  4);
        player.addNote(new Pitch('C').toMidiNote(),  136,  4);
        player.addNote(new Pitch('C').toMidiNote(),  140,  4);
    
        player.addNote(new Pitch('G').toMidiNote(),  192,  9);
        player.addNote(new Pitch('F').toMidiNote(),  201,  3);
        player.addNote(new Pitch('E').toMidiNote(),  204,  9);
        player.addNote(new Pitch('D').toMidiNote(),  213,  3);
        player.addNote(new Pitch('C').toMidiNote(),  216,  24);
        
        System.out.println(abcfile.header.toString());
        System.out.println(abcfile.music.getTicksPerUnit());
        
        sp.play(); 
        
        //System.out.println(sp.toString());
        //System.out.println();
        //System.out.println(player.toString());
        //System.out.println();
        Assert.assertTrue(IntegratedPlayerTest.comparePlayers(sp, player));
        
        Assert.assertEquals(140, abcfile.header.keyMetTempo.bpm);
         
    }
    
    //@Test
    public void fureliseTest() throws FileNotFoundException, LexerException, MidiUnavailableException, InvalidMidiDataException, ParseException {
        String file = "sample_abc/fur_elise.abc";
        ABCLexer lexer = new ABCLexer(file);
        ArrayList<ABCLexer.Token> input = lexer.getTokens();
        Parser parser = new Parser();
        ABCFile abcfile = parser.abcFileParser(input);
        
        SequencePlayer sp = abcfile.play();
        sp.play();
    }
    
    //@Test
    public void littlenightmusicTest() throws FileNotFoundException, LexerException, MidiUnavailableException, InvalidMidiDataException, ParseException {
        String file = "sample_abc/little_night_music.abc";
        ABCLexer lexer = new ABCLexer(file);
        ArrayList<ABCLexer.Token> input = lexer.getTokens();
        Parser parser = new Parser();
        ABCFile abcfile = parser.abcFileParser(input);
        
        SequencePlayer sp = abcfile.play();
        sp.play();
    }
    
}
