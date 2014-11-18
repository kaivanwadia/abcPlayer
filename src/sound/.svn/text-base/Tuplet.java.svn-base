package sound;

/**
 * Abstract class for Tuplets
 * Inherited by Duplet, Triplet and Quadruplet
 *
 */
public abstract class Tuplet implements PlayableElement{
    
    protected final Multinote[] notes;
    
    /**
     * Creates a new Tuplet instance.
     * @param notes The array of notes that are part of this Tuplet. The array must have at least 2 elements.
     */
    public Tuplet(Multinote[] notes) {
        this.notes = notes;
        checkRep();
    }
    
    /**
     * Checks the rep invariant.
     */
    private void checkRep() {
        assert notes != null : "Tuplet.checkRep() - notes != null";
        assert notes.length > 1 : "Tuplet.checkRep() - notes.length > 1";
    }
    
    /**
     * Gets the String representation of the Tuplet object
     * @return Returns the String representation of the Tuplet object
     */
    @Override
    public String toString() {
        String tuplet = "("+notes.length;
        for(int i=0;i<notes.length;i++) {
            tuplet += notes[i].toString();
        }
        return tuplet;
    }
    
    /**
     * Plays the Tuplet object.
     * @param player the player to play the Tuplet on
     * @param kmt the key, meter and tempo to play with.
     * @param ticksPerUnit the ticks per unit of the player
     * @param currentTick the current tick number
     * @return a pair of the tick number after the tuplet finishes playing, and the new KeyMeterTempo with the modified key. 
     */
    @Override
    public Pair<Integer,KeyMeterTempo> play(SequencePlayer player, KeyMeterTempo kmt, int ticksPerUnit, int currentTick) {
        Pair<Integer,KeyMeterTempo> tmp = new Pair<Integer,KeyMeterTempo>(currentTick,kmt);
        for(Multinote n : notes) {
            tmp = n.play(player, tmp.getVal2(), ticksPerUnit, tmp.getVal1(), getTupletModifier());
        }
        return tmp;
    }
    
    /**
     * Returns the default note length modifier of the Tuplet
     * @return Returns the default note length modifier of the Tuplet
     */
    public abstract NoteLength getTupletModifier();
    
    /**
     * Gets the total length of the Tuple object.
     * @return Returns the total length of the Tuple object.
     */
    @Override
    public NoteLength getLength() {
        NoteLength res = notes[0].getLength();
        for(int i=1;i<notes.length;i++) {
            res = res.add(notes[i].getLength());
        }
        return res.multiply(getTupletModifier());
    }
    
    /**
     * Gets the lowest common multiple of the denominators of the notes and rests within.
     * @return the LCM of the denominators
     */
    @Override
    public int getLCMDenominator() {
        int res = 1;
        for(Multinote n : notes) {
            res = Utils.lcm(res, n.getLength().denominator*getTupletModifier().denominator);
        }
        return res;
    }
    
}
