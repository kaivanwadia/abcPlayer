package sound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import junit.framework.Assert;

import org.junit.Test;

import player.ParseException;


public class IntegratedPlayerTest {
    
    @Test
    public void warmupTest1() throws ParseException {
        try{
            SequencePlayer player = new SequencePlayer(140, 12);

            // C C C3/4 D/4 E | E3/4 D/4 E3/4 F/4 G2 |
            // (3c/c/c/ (3G/G/G/ (3E/E/E/ (3C/C/C/ | G3/4 F/4 E3/4 D1/4 C2 |]
            
            Voice v = new Voice("piano");
            ArrayList<PlayableElement> arr = new ArrayList<PlayableElement>();
            arr.add(new Note('C'));
            arr.add(new Note('C'));
            arr.add(new Note('C',3,4));
            arr.add(new Note('D',1,4)); //arr.add(new Note('D',3,4));
            arr.add(new Note('E'));
            v.addMeasure(new Measure(arr,true,false,false,false,false));
            
            arr = new ArrayList<PlayableElement>();
            arr.add(new Note('E',3,4));
            arr.add(new Note('D',1,4));
            arr.add(new Note('E',3,4));
            arr.add(new Note('F',1,4));
            arr.add(new Note('G',2,1));
            v.addMeasure(new Measure(arr,false,false,false,false,false));
            
            arr = new ArrayList<PlayableElement>();
            arr.add(new Triplet(new Note('c',1,2),new Note('c',1,2),new Note('c',1,2)));
            arr.add(new Triplet(new Note('G',1,2),new Note('G',1,2),new Note('G',1,2)));
            arr.add(new Triplet(new Note('E',1,2),new Note('E',1,2),new Note('E',1,2)));
            arr.add(new Triplet(new Note('C',1,2),new Note('C',1,2),new Note('C',1,2)));
            v.addMeasure(new Measure(arr,false,false,false,false,false));
            
            arr = new ArrayList<PlayableElement>();
            arr.add(new Note('G',3,4));
            arr.add(new Note('F',1,4));
            arr.add(new Note('E',3,4));
            arr.add(new Note('D',1,4));
            arr.add(new Note('C',2,1));
            v.addMeasure(new Measure(arr,false,true,false,false,false));
            
            HashMap<String, Voice> hs = new HashMap<String, Voice>();
            hs.put(v.getName(),v);
            ArrayList<String> voices = new ArrayList<String>();
            voices.add(v.getName());
            KeyMeterTempo kmt = new KeyMeterTempo(Key.C_MAJOR,new NoteLength(4,4),140,new NoteLength(1,4));
            ABCHeader head = new ABCHeader("Piece No.1",1,"Unknown",kmt,voices);
            ABCMusic music = new ABCMusic(hs,head.keyMetTempo);
            ABCFile file = new ABCFile(head,music);
            
            SequencePlayer abcplay = file.play();
            
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
            
            System.out.println(player);
            System.out.println(abcplay);
            Assert.assertTrue(comparePlayers(abcplay,player));
            
            //player.play();
            
            //abcplay.play();
            
            
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace(); 
            
        }
    }
    
    @Test
    public void smallRepeatTest1() throws ParseException {
        try{
            SequencePlayer player = new SequencePlayer(140, 6);

            // (3[c/e/g/C/][d/f/a/][e/g/b] (3e/g/c/ [gce] g :|
            
            Voice v = new Voice("piano");
            ArrayList<PlayableElement> arr = new ArrayList<PlayableElement>();
            Note c = new Note('c',1,2);
            Note C = new Note('C',1,2);
            Note d = new Note('d',1,2);
            Note e = new Note('e',1,2);
            Note f = new Note('f',1,2);
            Note g = new Note('g',1,2);
            Note a = new Note('a',1,2);
            Note b = new Note('b',1,2);
            Chord ch1 = new Chord(c);
            ch1 = ch1.addNote(e);
            ch1 = ch1.addNote(g);
            ch1 = ch1.addNote(C);
            Chord ch2 = new Chord(d);
            ch2 = ch2.addNote(f);
            ch2 = ch2.addNote(a);
            Chord ch3 = new Chord(e);
            ch3 = ch3.addNote(g);
            ch3 = ch3.addNote(b);
            Tuplet tup = new Triplet(ch1,ch2,ch3);
            arr.add(tup);
            arr.add(new Triplet(e,g,c));
            arr.add(new Chord(new Note('g')).addNote(new Note('c')).addNote(new Note('e')));
            arr.add(new Note('g'));
            v.addMeasure(new Measure(arr,true,true,true,false,false));
            
            HashMap<String, Voice> hs = new HashMap<String, Voice>();
            hs.put(v.getName(),v);
            ArrayList<String> voices = new ArrayList<String>();
            voices.add(v.getName());
            KeyMeterTempo kmt = new KeyMeterTempo(Key.C_MAJOR,new NoteLength(4,4),140,new NoteLength(1,4));
            ABCHeader head = new ABCHeader("Piece No.1",1,"Unknown",kmt,voices);
            ABCMusic music = new ABCMusic(hs,head.keyMetTempo);
            ABCFile file = new ABCFile(head,music);
            
            SequencePlayer abcplay = file.play();
            
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

            
            System.out.println(player);
            System.out.println(abcplay);
            Assert.assertTrue(comparePlayers(abcplay,player));
            
            //player.play();
            
            //abcplay.play();
            
            
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace(); 
            
        }
    }
    
    @Test
    public void diversityTest1() throws ParseException {
        try{
            SequencePlayer player = new SequencePlayer(140, 6);

            // (3[c/e/g/C/][d/f/a/][e/g/b] (3e/g/c/ [gce] g |
            
            Voice v = new Voice("piano");
            ArrayList<PlayableElement> arr = new ArrayList<PlayableElement>();
            Note c = new Note('c',1,2);
            Note C = new Note('C',1,2);
            Note d = new Note('d',1,2);
            Note e = new Note('e',1,2);
            Note f = new Note('f',1,2);
            Note g = new Note('g',1,2);
            Note a = new Note('a',1,2);
            Note b = new Note('b',1,2);
            Chord ch1 = new Chord(c);
            ch1 = ch1.addNote(e);
            ch1 = ch1.addNote(g);
            ch1 = ch1.addNote(C);
            Chord ch2 = new Chord(d);
            ch2 = ch2.addNote(f);
            ch2 = ch2.addNote(a);
            Chord ch3 = new Chord(e);
            ch3 = ch3.addNote(g);
            ch3 = ch3.addNote(b);
            Tuplet tup = new Triplet(ch1,ch2,ch3);
            arr.add(tup);
            arr.add(new Triplet(e,g,c));
            arr.add(new Chord(new Note('g')).addNote(new Note('c')).addNote(new Note('e')));
            arr.add(new Note('g'));
            v.addMeasure(new Measure(arr,true,false,false,false,false));
            
            HashMap<String, Voice> hs = new HashMap<String, Voice>();
            hs.put(v.getName(),v);
            ArrayList<String> voices = new ArrayList<String>();
            voices.add(v.getName());
            KeyMeterTempo kmt = new KeyMeterTempo(Key.C_MAJOR,new NoteLength(4,4),140,new NoteLength(1,4));
            ABCHeader head = new ABCHeader("Piece No.1",1,"Unknown",kmt,voices);
            ABCMusic music = new ABCMusic(hs,head.keyMetTempo);
            ABCFile file = new ABCFile(head,music);
            
            SequencePlayer abcplay = file.play();
            
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
            
            System.out.println(player);
            System.out.println(abcplay);
            Assert.assertTrue(comparePlayers(abcplay,player));
            
            //player.play();
            
            //abcplay.play();
            
            
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace(); 
            
        }
    }
    
    @Test
    public void warmupTest1Repeats() throws ParseException {
        try{
            SequencePlayer player = new SequencePlayer(140, 12);

            // C C C3/4 D/4 E | E3/4 D/4 E3/4 F/4 G2 |
            // (3c/c/c/ (3G/G/G/ (3E/E/E/ (3C/C/C/ | G3/4 F/4 E3/4 D1/4 C2 |]
            
            Voice v = new Voice("piano");
            ArrayList<PlayableElement> arr = new ArrayList<PlayableElement>();
            arr.add(new Note('C'));
            arr.add(new Note('C'));
            arr.add(new Note('C',3,4));
            arr.add(new Note('D',1,4)); //arr.add(new Note('D',3,4));
            arr.add(new Note('E'));
            v.addMeasure(new Measure(arr,true,false,false,false,false));
            
            arr = new ArrayList<PlayableElement>();
            arr.add(new Note('E',3,4));
            arr.add(new Note('D',1,4));
            arr.add(new Note('E',3,4));
            arr.add(new Note('F',1,4));
            arr.add(new Note('G',2,1));
            v.addMeasure(new Measure(arr,false,true,true,false,false));
            
            arr = new ArrayList<PlayableElement>();
            arr.add(new Triplet(new Note('c',1,2),new Note('c',1,2),new Note('c',1,2)));
            arr.add(new Triplet(new Note('G',1,2),new Note('G',1,2),new Note('G',1,2)));
            arr.add(new Triplet(new Note('E',1,2),new Note('E',1,2),new Note('E',1,2)));
            arr.add(new Triplet(new Note('C',1,2),new Note('C',1,2),new Note('C',1,2)));
            v.addMeasure(new Measure(arr,true,false,false,false,false));
            
            arr = new ArrayList<PlayableElement>();
            arr.add(new Note('G',3,4));
            arr.add(new Note('F',1,4));
            arr.add(new Note('E',3,4));
            arr.add(new Note('D',1,4));
            arr.add(new Note('C',2,1));
            v.addMeasure(new Measure(arr,false,true,false,false,false));
            
            HashMap<String, Voice> hs = new HashMap<String, Voice>();
            hs.put(v.getName(),v);
            ArrayList<String> voices = new ArrayList<String>();
            voices.add(v.getName());
            KeyMeterTempo kmt = new KeyMeterTempo(Key.C_MAJOR,new NoteLength(4,4),140,new NoteLength(1,4));
            ABCHeader head = new ABCHeader("Piece No.1",1,"Unknown",kmt,voices);
            ABCMusic music = new ABCMusic(hs,head.keyMetTempo);
            ABCFile file = new ABCFile(head,music);
            
            SequencePlayer abcplay = file.play();
            
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
            
            player.addNote(new Pitch('C').toMidiNote(),  96,   12);
            player.addNote(new Pitch('C').toMidiNote(),  108,  12);
            player.addNote(new Pitch('C').toMidiNote(),  120,  9);
            player.addNote(new Pitch('D').toMidiNote(),  129,  3);
            player.addNote(new Pitch('E').toMidiNote(),  132,  12);
            player.addNote(new Pitch('E').toMidiNote(),  144,  9);
            player.addNote(new Pitch('D').toMidiNote(),  153,  3);
            player.addNote(new Pitch('E').toMidiNote(),  156,  9);
            player.addNote(new Pitch('F').toMidiNote(),  165,  3);
            player.addNote(new Pitch('G').toMidiNote(),  168,  24);
                    
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(),  192,  4);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(),  196,  4);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(),  200,  4);
            player.addNote(new Pitch('G').toMidiNote(),  204,  4);
            player.addNote(new Pitch('G').toMidiNote(),  208,  4);
            player.addNote(new Pitch('G').toMidiNote(),  212,  4);
            player.addNote(new Pitch('E').toMidiNote(),  216,  4);
            player.addNote(new Pitch('E').toMidiNote(),  220,  4);
            player.addNote(new Pitch('E').toMidiNote(),  224,  4);
            player.addNote(new Pitch('C').toMidiNote(),  228,  4);
            player.addNote(new Pitch('C').toMidiNote(),  232,  4);
            player.addNote(new Pitch('C').toMidiNote(),  236,  4);
            player.addNote(new Pitch('G').toMidiNote(),  240,  9);
            player.addNote(new Pitch('F').toMidiNote(),  249,  3);
            player.addNote(new Pitch('E').toMidiNote(),  252,  9);
            player.addNote(new Pitch('D').toMidiNote(),  261,  3);
            player.addNote(new Pitch('C').toMidiNote(),  264,  24);

            
            System.out.println(player);
            System.out.println(abcplay);
            Assert.assertTrue(comparePlayers(abcplay,player));
            
            //player.play();
            
            //abcplay.play();
            
            
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace(); 
            
        }
    }
    
    @Test
    public void warmupTest1RepeatAlternates() throws ParseException {
        try{
            SequencePlayer player = new SequencePlayer(140, 12);

            // C C C3/4 D/4 E |[1 E3/4 D/4 E3/4 F/4 G2 |
            // (3c/c/c/ (3G/G/G/ (3E/E/E/ (3C/C/C/ :|[2 G3/4 F/4 E3/4 D1/4 C2 |]
            
            Voice v = new Voice("piano");
            ArrayList<PlayableElement> arr = new ArrayList<PlayableElement>();
            arr.add(new Note('C'));
            arr.add(new Note('C'));
            arr.add(new Note('C',3,4));
            arr.add(new Note('D',1,4)); //arr.add(new Note('D',3,4));
            arr.add(new Note('E'));
            v.addMeasure(new Measure(arr,true,false,false,false,false));
            
            arr = new ArrayList<PlayableElement>();
            arr.add(new Note('E',3,4));
            arr.add(new Note('D',1,4));
            arr.add(new Note('E',3,4));
            arr.add(new Note('F',1,4));
            arr.add(new Note('G',2,1));
            v.addMeasure(new Measure(arr,false,false,false,true,false));
            
            arr = new ArrayList<PlayableElement>();
            arr.add(new Triplet(new Note('c',1,2),new Note('c',1,2),new Note('c',1,2)));
            arr.add(new Triplet(new Note('G',1,2),new Note('G',1,2),new Note('G',1,2)));
            arr.add(new Triplet(new Note('E',1,2),new Note('E',1,2),new Note('E',1,2)));
            arr.add(new Triplet(new Note('C',1,2),new Note('C',1,2),new Note('C',1,2)));
            v.addMeasure(new Measure(arr,false,false,true,false,false));
            
            arr = new ArrayList<PlayableElement>();
            arr.add(new Note('G',3,4));
            arr.add(new Note('F',1,4));
            arr.add(new Note('E',3,4));
            arr.add(new Note('D',1,4));
            arr.add(new Note('C',2,1));
            v.addMeasure(new Measure(arr,false,true,false,false,true));
            
            HashMap<String, Voice> hs = new HashMap<String, Voice>();
            hs.put(v.getName(),v);
            ArrayList<String> voices = new ArrayList<String>();
            voices.add(v.getName());
            KeyMeterTempo kmt = new KeyMeterTempo(Key.C_MAJOR,new NoteLength(4,4),140,new NoteLength(1,4));
            ABCHeader head = new ABCHeader("Piece No.1",1,"Unknown",kmt,voices);
            ABCMusic music = new ABCMusic(hs,head.keyMetTempo);
            ABCFile file = new ABCFile(head,music);
            
            SequencePlayer abcplay = file.play();
            
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
            
            System.out.println(player);
            System.out.println(abcplay);
            Assert.assertTrue(comparePlayers(abcplay,player));
            
            //player.play();
            
            //abcplay.play();
            
            
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace(); 
            
        }
    }
    

    public static boolean comparePlayers(SequencePlayer a, SequencePlayer b) {
        List<String> as = Arrays.asList(a.toString().split("\n"));
        List<String> bs = Arrays.asList(b.toString().split("\n"));
        boolean res = true,r=true;
        for(String x : as) {
            if(!bs.contains(x)) {
                if(res) {
                    System.out.println("As in B");
                }
                System.out.println("Difference: "+x);
                res = false;
            }
        }
        
        for(String x : bs) {
            if(!as.contains(x)) {
                if(r) {
                    System.out.println("Bs in A");
                }
                r=false;
                System.out.println("Difference: "+x);
                res = false;
            }
        }
        return res;
    }
    
}
