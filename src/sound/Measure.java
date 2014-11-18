package sound;

import java.util.ArrayList;

import player.ParseException;

/**
 * The Measure class -- represents a possibly incomplete measure.
 */
public class Measure {
    public final ArrayList<PlayableElement> elements;
    private boolean isRegular = false;
    public final boolean isRepeatEnd;
    public final boolean isMajorStart;
    public final boolean isMajorEnd;
    public final boolean isFirstRepeat;
    public final boolean isSecondRepeat;
    
    /**
     * Constructor to create an instance of Measure given the necessary parameters.
     * @param elements An immutable list of PlayableElement objects that are part of this measure. Must not be empty.
     * @param measureID This measure's unique identifier
     * @param isMajorStart true if this measure is the start of a major section
     * @param isMajorEnd true if this measure is the end of a major section
     * @param isRepeatEnd true if this measure ends with a repeat symbol
     * @param isFirstRepeat true if this measure is marked with a [1. Must not be true if isMajorStart or isSecondRepeat is true.
     * @param isSecondRepeat true if this measure is marked with a [2. Must not be true if isMajorStart or isFirstRepeat is true.
     */
    public Measure(ArrayList<PlayableElement> elements, boolean isMajorStart, boolean isMajorEnd, boolean isRepeatEnd, boolean isFirstRepeat, boolean isSecondRepeat) {
        this.elements = elements;
        this.isMajorStart = isMajorStart;
        this.isMajorEnd = isMajorEnd;
        this.isRepeatEnd = isRepeatEnd;
        this.isFirstRepeat = isFirstRepeat;
        this.isSecondRepeat = isSecondRepeat;
        checkRep();
    }
    
    /**
     * Gets the total length of the measure.
     * @return the measure length
     */
    public NoteLength getMeasureLength(NoteLength defaultNote) {
        NoteLength res = this.elements.get(0).getLength();
        for (int i=1; i<this.elements.size(); i++) {
            res = res.add(this.elements.get(i).getLength());
        }
        return res.multiply(defaultNote);
    }
    
    /**
     * Gets the lowest common multiple of the denominator of the notes within.
     * @return the largest denominator
     */
    public int getLCMDenominator() {
        int res = 1;
        for(PlayableElement p : elements) {
            res = Utils.lcm(res, p.getLCMDenominator());
        }
        return res;
    }
    
    /**
     * Checks the rep invariant.
     */
    private void checkRep() {
        assert elements != null : "Measure.checkRep() - elements != null";
        assert !elements.isEmpty() : "Measure.checkRep() - elements is empty.";
        assert !isMajorStart || !isFirstRepeat || !isSecondRepeat : "Measure.checkRep() - !isMajorStart || !isFirstRepeat || !isSecondRepeat";
        //ImList guarantees that elements does not contain null values
    }
    
    /**
     * Checks the regular measure rep.
     * @param kmt the key, meter and tempo to use.
     * @throws ParseException if the measure is not complete
     */
    private void checkRegularRep(KeyMeterTempo kmt) throws IllegalStateException {
        if(!getMeasureLength(kmt.defaultNote).equals(kmt.meter)) {
            throw new IllegalStateException("Measure marked as regular is incomplete.");
        }
        //assert getMeasureLength(kmt.defaultNote).equals(kmt.meter) : "RegularMeasure.checkRep() - measure is incomplete: " + getMeasureLength(kmt.defaultNote).toString();
    }
    
    public void makeRegular(KeyMeterTempo kmt) throws IllegalStateException {
        if(!isRegular) {
            isRegular = true;
            checkRegularRep(kmt);
        }
    }
    
    /**
     * Plays the Measure object.
     * @param player the player to play the Measure on
     * @param kmt the key, meter and tempo to play with.
     * @param ticksPerUnit the ticks per unit of the player
     * @param currentTick the current tick number
     * @return the tick number after the Measure finishes playing 
     */
    public int play(SequencePlayer player, KeyMeterTempo kmt, int ticksPerUnit, int currentTick) {
        Pair<Integer,KeyMeterTempo> tmp = new Pair<Integer,KeyMeterTempo>(currentTick,kmt);
        for(PlayableElement p : elements) {
            tmp = p.play(player, tmp.getVal2(), ticksPerUnit, tmp.getVal1());
        }
        return tmp.getVal1();
    }
    
    /**
     * Method to return String form of Measure
     */
    public String toString() {
        String measure = "\n";
        for (PlayableElement pl: this.elements) {
            measure = measure+" "+pl.toString();
        }
        measure = measure+"|";
        measure = measure+"\n "+isMajorStart+" "+isMajorEnd+" "+isRepeatEnd+" "+isFirstRepeat+" "+isSecondRepeat;
        return measure;
    }
}
