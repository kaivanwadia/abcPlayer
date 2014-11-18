package sound;

/**
 * Class to describe Chord Datatype
 */
public class Chord implements Multinote {
    private final ImList<Note> notes;
    
    /**
     * Constructor to return an instance of Chord given a single note.
     * @param note A note to add to the Chord. Must not be null.
     */
    public Chord(Note note) {
        this.notes = new Cons<Note>(note);
        checkRep();
    }
    
    /**
     * Constructor to create an instance of Chord given a list of notes.
     * @param notes An ImList of notes. Must not be null or empty.
     */
    public Chord(ImList<Note> notes) {
        this.notes = notes;
        checkRep();
    }
    
    /**
     * Checks the rep invariant.
     */
    private void checkRep() {
        assert this.notes != null : "Chord.checkRep() - this.notes != null";
        assert !this.notes.isEmpty() : "Chord.checkRep() - !this.notes.isEmpty()";
        //ImList guarantees that all notes are non-null.
    }
    
    /**
     * Method to add a note to the existing chord
     * @param Note to be added. Cannot be null
     * @return New chord with note added to the beginning of the list
     */
    public Chord addNote(Note n1) {
        return new Chord(this.notes.addToFront(n1));
    }
    
    /**
     * Gets the length of the chord. In case there are multiple note durations, returns the shortest one.
     * @return Returns the length of the chord. In case there are multiple note durations, returns the shortest one.
     */
    @Override
    public NoteLength getLength() {
        if(notes.isEmpty()) return new NoteLength();
        NoteLength res = notes.first().getLength();
        ImList<Note> nt = notes.rest();
        while(!nt.isEmpty()) {
            if(res.compareTo(nt.first().getLength())==1) {
                res = nt.first().getLength();
            }
            nt=nt.rest();
        }
        return res;
    }
    
    /**
     * Method to give the String form of a Chord
     * @return The String representation of the Chord.
     */
    @Override
    public String toString() {
        String chord = "[";
        for (Note note: notes) {
            chord += note.toString();
        }
        chord = chord+"]";
        return chord;
    }
    
    /**
     * Plays the Chord object.
     * @param player the player to play the Chord on
     * @param kmt the key, meter and tempo to play with.
     * @param ticksPerUnit the ticks per unit of the player
     * @param currentTick the current tick number
     * @return a pair of the tick number after the chord finishes playing, and the new KeyMeterTempo with the modified key. 
     */
    @Override
    public Pair<Integer,KeyMeterTempo> play(SequencePlayer player, KeyMeterTempo kmt, int ticksPerUnit, int currentTick) {
        Pair<Integer,KeyMeterTempo> tmp = new Pair<Integer,KeyMeterTempo>(currentTick,kmt);
        int res = Integer.MAX_VALUE;
        for(Note n : notes) {
            tmp = n.play(player, tmp.getVal2(), ticksPerUnit, currentTick);
            res = Math.min(res, tmp.getVal1());
        }
        return new Pair<Integer,KeyMeterTempo>(res,tmp.getVal2());
    }
    
    /**
     * Plays the Chord object.
     * @param player the player to play the Chord on
     * @param kmt the key, meter and tempo to play with.
     * @param ticksPerUnit the ticks per unit of the player
     * @param currentTick the current tick number
     * @param modifier a multipler for the note length (used for tuplets)
     * @return a pair of the tick number after the note finishes playing, and the new KeyMeterTempo with the modified key. 
     */
    @Override
    public Pair<Integer,KeyMeterTempo> play(SequencePlayer player, KeyMeterTempo kmt, int ticksPerUnit, int currentTick, NoteLength modifier) {
        Pair<Integer,KeyMeterTempo> tmp = new Pair<Integer,KeyMeterTempo>(currentTick,kmt);
        int res = Integer.MAX_VALUE;
        for(Note n : notes) {
            tmp = n.play(player, tmp.getVal2(), ticksPerUnit, currentTick, modifier);
            res = Math.min(res, tmp.getVal1());
        }
        return new Pair<Integer,KeyMeterTempo>(res,tmp.getVal2());
    }
    
    /**
     * Gets the lowest common multiple of the denominators of the notes and rests within.
     * @return the LCM of the denominators
     */
    @Override
    public int getLCMDenominator() {
        int res = 1;
        for(Note n : notes) {
            res = Utils.lcm(res, n.getLCMDenominator());
        }
        return res;
    }
    
}
