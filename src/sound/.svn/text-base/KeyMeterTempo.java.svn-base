package sound;
/**
 * Class to describe KeyMeterTempo datatype
 * Contains information about the Key, Meter and Tempo of the track
 */
public class KeyMeterTempo {
    
    public final Key key;
    public final NoteLength meter;
    public final int bpm;
    public final NoteLength defaultNote;
    
    /**
     * Constructor returning an instance of KeyMeterTempo
     * @param key - an instance of Key Class containing the key. Must not be null.
     * @param meterNumerator - Numerator of the default Meter (M); must be positive.
     * @param meterDenominator - Denominator of the default Meter(M); must be positive. 
     * @param bpm - Tempo of the track (Q); must be positive.
     * @param defaultNoteNumerator - Numerator of the default Note Length (L); must be positive.
     * @param defaultNoteDenominator - Denominator of the default Note Length (L); must be positive.
     */
    public KeyMeterTempo(Key key, int meterNumerator, int meterDenominator, int bpm, int defaultNoteNumerator, int defaultNoteDenominator) {
        this(key,new NoteLength(meterNumerator,meterDenominator),bpm,new NoteLength(defaultNoteNumerator,defaultNoteDenominator));
    }
  
    /**
     * Instantiates a new KeyMeterTempo instance.
     * @param key - an instance of Key Class containing the key. Must not be null.
     * @param meter - the default Meter (M); must not be null or have a zero numerator.
     * @param bpm - Tempo of the track (Q); must be positive.
     * @param defaultNote - default Note Length (L); must not be null or have a zero numerator.
     */
    public KeyMeterTempo(Key key, NoteLength meter, int bpm, NoteLength defaultNote) {
        this.key = key;
        this.meter = meter;
        this.bpm = bpm;
        //this.ticksPerUnit = ticksPerUnit;
        this.defaultNote = defaultNote;
        checkRep();
    }
    
    /**
     * Change the key of the KeyMeterTempo.
     * @param newKey the new key
     * @return the key meter tempo with changed key
     */
    public KeyMeterTempo changeKey(Key newKey) {
        return new KeyMeterTempo(newKey, meter, bpm, defaultNote);
    }
    
    /**
     * Checks the rep invariant.
     */
    private void checkRep() {
        assert bpm > 0 : "KeyMeterTempo.checkRep() - bpm > 0";
        assert key != null : "KeyMeterTempo.checkRep() - key != null";
        assert meter != null : "KeyMeterTempo.checkRep() - meter != null";
        assert defaultNote != null : "KeyMeterTempo.checkRep() - defaultNote != null";
        assert meter.numerator != 0 : "KeyMeterTempo.checkRep() - meter.numerator != 0";
        assert defaultNote.numerator != 0 : "KeyMeterTempo.checkRep() - defaultNote.numerator != 0";
    }
    
}
