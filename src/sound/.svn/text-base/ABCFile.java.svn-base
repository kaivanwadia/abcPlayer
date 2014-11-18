package sound;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;


/**
 * Class to describe ABCFile datatype
 *
 */
public class ABCFile {

    public final ABCMusic music;
    public final ABCHeader header;
    
    /**
     * Constructor to return an instance of ABCFile
     * @param header - the ABCHeader of the ABC File. Must not be null.
     * @param music - the ABCMusic of the ABCFile. Must not be null.
     */
    public ABCFile(ABCHeader header, ABCMusic music) {
        this.header = header;
        this.music = music;
        checkRep();
    }
    
    /**
     * Checks the rep invariant with assertions.
     */
    private void checkRep() {
        assert header != null : "ABCFile.checkRep() - header != null";
        assert music != null  : "ABCFile.checkRep() - music != null";
    }
    
    /**
     * Plays the ABCFile with a SequencePlayer.
     */
    public SequencePlayer play() throws MidiUnavailableException, InvalidMidiDataException {
        SequencePlayer player = new SequencePlayer(header.keyMetTempo.bpm,music.getTicksPerUnit());
        music.play(player, header.keyMetTempo);
        return player;
    }
    
}
