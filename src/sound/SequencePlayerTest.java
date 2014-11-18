package sound;

import static org.junit.Assert.*;
import org.junit.Test;

import javax.sound.midi.InvalidMidiDataException; 
import javax.sound.midi.MidiUnavailableException; 

public class SequencePlayerTest {
    @Test
	public void warmupTest1() {
        try{
            SequencePlayer player = new SequencePlayer(140, 12);

            // C C C3/4 D/4 E | E3/4 D/4 E3/4 F/4 G2 |
            // (3c/c/c/ (3G/G/G/ (3E/E/E/ (3C/C/C/ | G3/4 F/4 E3/4 D1/4 C2 |]
            
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
            

            player.play();
            
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace(); 
            
        }
    }
    
   // [^F/e/] [F/e/] z/ [F/e/] z/ [F/c/] [Fe] | [GBg] z G z | c3/2 G/ z E | E/ A B _B/ A | 
   // (3Geg a f/ g/ | z/ e c/ d/ B3/4 |]
   
   // Test cases to be written 
   
    @Test
    public void warmupTest2() {
        try{
            SequencePlayer player = new SequencePlayer(200, 6);
            
            // [^Fe]/2 [^Fe]/2 z/2 [^Fe]/2 z/2 [^Fc]/2 [^Fe]/2 | [GBg] z G z | c3/2 G/2 z E |
            // E/2 A B _B/2 A | (3G2e2g2 a f/2 g/2 | z/2 e c/2 d/2 B3/2 |]
            
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), 0, 3);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 0, 3);
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), 3, 3);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 3, 3);
            //player.addNote(new Pitch('').toMidiNote(), 6, 3);
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), 9, 3);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 9, 3);
            //player.addNote(new Pitch('').toMidiNote(), 12, 3);
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), 15, 3);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 15, 3);
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), 18, 6);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 18, 6);
            
            player.addNote(new Pitch('G').toMidiNote(), 24, 6);
            player.addNote(new Pitch('B').toMidiNote(), 24, 6);
            player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 24, 6);
            //player.addNote(new Pitch('').toMidiNote(), 30, 6);
            player.addNote(new Pitch('G').toMidiNote(), 36, 6);
            //player.addNote(new Pitch('').toMidiNote(), 42, 6);
            
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 48, 9);
            player.addNote(new Pitch('G').toMidiNote(), 57, 3);
            //player.addNote(new Pitch('').toMidiNote(), 60, 6);
            player.addNote(new Pitch('E').toMidiNote(), 66, 6);
            
            player.addNote(new Pitch('E').toMidiNote(), 72, 3);
            player.addNote(new Pitch('A').toMidiNote(), 75, 6);
            player.addNote(new Pitch('B').toMidiNote(), 81, 6);
            player.addNote(new Pitch('B').transpose(-1).toMidiNote(), 87, 3);
            player.addNote(new Pitch('A').toMidiNote(), 90, 6);
            
            player.addNote(new Pitch('G').toMidiNote(), 96, 4);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 100, 4);
            player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 104, 4);
            player.addNote(new Pitch('A').transpose(Pitch.OCTAVE).toMidiNote(), 108, 6);
            player.addNote(new Pitch('F').transpose(Pitch.OCTAVE).toMidiNote(), 114, 3);
            player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 117, 3);
            
            //player.addNote(new Pitch('').toMidiNote(), 120, 3);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 123, 6);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 129, 3);
            player.addNote(new Pitch('D').transpose(Pitch.OCTAVE).toMidiNote(), 132, 3);
            player.addNote(new Pitch('B').toMidiNote(), 135, 9);


            System.out.println(player);
            

            player.play();
            
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace(); 
            
        }
    }

}
