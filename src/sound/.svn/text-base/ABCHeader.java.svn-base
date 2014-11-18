package sound;

import java.util.ArrayList;

/**
 * Class to describe ABCHeader datatype
 *
 */
public class ABCHeader {
    
    public final String title;
    public final int pieceNumber;
    public final String composer;
    public final KeyMeterTempo keyMetTempo;
    public final ArrayList<String> voices;
    
    public static final int DEFAULT_TEMPO = 100;
    public static final NoteLength DEFAULT_NOTELENGTH = new NoteLength(1,8);
    public static final NoteLength DEFAULT_METER = new NoteLength(4,4);
    public static final String DEFAULT_COMPOSER = "Unknown Composer";
    
    public ABCHeader(int pieceNumber, String title) {
        this(title,pieceNumber,DEFAULT_COMPOSER,new KeyMeterTempo(new Key(),DEFAULT_METER,DEFAULT_TEMPO,DEFAULT_NOTELENGTH), new ArrayList<String>());
    }
    /**
     * Basic constructor returning and instance of ABCHeader by setting everything to default values:
     * Composer:        Unknown Composer
     * Tempo:           100
     * Default note:    1/8
     * Meter:           4/4
     * @param pieceNumber - Index Number of the track (X)
     * @param title - title of the piece (T). Must not be null or empty.
     * @param key - the Key of the piece (K). Must not be null.
     */
    public ABCHeader(int pieceNumber,String title,Key key) {
        this(title,pieceNumber,DEFAULT_COMPOSER,new KeyMeterTempo(key,DEFAULT_METER,DEFAULT_TEMPO,DEFAULT_NOTELENGTH), new ArrayList<String>());
    }
    /**
     * Basic constructor returning an instance of ABCHeader the necessary parameters
     * @param title - title of the piece (T)
     * @param pieceNumber - Index Number of the track (X)
     * @param composer - name of the composer (C). Must not be null or empty.
     * @param keyMetTempo - key, meter and tempo of the music piece. Must not be null.
     */
    public ABCHeader(String title, int pieceNumber, String composer, KeyMeterTempo keyMetTempo, ArrayList<String> voice) {
        this.title = title;
        this.pieceNumber = pieceNumber;
        this.composer = composer;
        this.keyMetTempo = keyMetTempo;
        this.voices = voice;
        checkRep();
    }
    
    /**
     * Checks the rep invariant with assertions.
     */
    private void checkRep() {
        assert title != null : "ABCHeader.checkRep() - title != null";
        assert title != "" : "ABCHeader.checkRep() - title != empty string";
        assert composer != null : "ABCHeader.checkRep() - composer != null";
        assert composer != "" : "ABCHeader.checkRep() - composer != empty string"; 
        assert keyMetTempo != null : "ABCHeader.checkRep() - keyMetTempo != null";
    }
    
    /**
     * Method to set the title of an instance of ABCHeader
     * @param title - Title of the music to be assigned to the ABCHeader (T) 
     * @return Returns a new instance of ABCHeader with the changed title
     */
    public ABCHeader setTitle(String title) {
        return new ABCHeader(title, pieceNumber, composer, keyMetTempo, voices);
    }
    /**
     * Method to set the pieceNumber of an instance of ABCHeader
     * @param pieceNumber - Piece Number of the music to be assigned to the ABCHeader (X)
     * @return Returns a new instance of ABCHeader with the changed piece number
     */
    public ABCHeader setPieceNumber(int pieceNumber) {
        return new ABCHeader(title, pieceNumber, composer, keyMetTempo, voices);
    }
    /**
     * Method to set the name of the composer in ABCHeader
     * @param composer - Name of the composer (C)
     * @return Returns a new instance of ABCHeader with the changed composer
     */
    public ABCHeader setComposer(String composer) {
        return new ABCHeader(title, pieceNumber, composer, keyMetTempo, voices);
    }
    /**
     * Method to set the keyMetTempo of the ABCHeader
     * @param keyMetTempo - Key, Meter and Tempo of the ABCFile
     * @return Returns a new instance of ABCHeader with a changed KeyMeterTempo
     */
    public ABCHeader setKeyMeterTempo(KeyMeterTempo keyMetTempo) {
        return new ABCHeader(title, pieceNumber, composer, keyMetTempo, voices);
    }
    
    public ABCHeader addVoice(String voice) {
        ArrayList<String> v = this.voices;
        v.add(voice);
        return new ABCHeader(title, pieceNumber, composer, keyMetTempo, v);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Track number: ");
        sb.append(pieceNumber);
        sb.append("\nTitle: ");
        sb.append(title);
        sb.append("\nComposer: ");
        sb.append(composer);
        sb.append("\nMeter: ");
        sb.append(keyMetTempo.meter.toString());
        sb.append("\nTempo: ");
        sb.append(keyMetTempo.bpm);
        sb.append("\nKey: ");
        sb.append(keyMetTempo.key);
        sb.append("\nDefault note length: ");
        sb.append(keyMetTempo.defaultNote.toString());
        return sb.toString();
    }
}
