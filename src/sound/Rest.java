package sound;

/** 
 * Class to describe Rest Datatype and its functions and constructors
 */
public class Rest implements PlayableElement {
    
	public static final char NAME = 'z';
    private final NoteLength length;
	
	/** Basic constructor which initializes a new Rest with length as 1/1 */
	public Rest() {
        this.length = new NoteLength(1,1);
	}
	/**
	 * Constructor which initializes a new Rest with length as given in the parameters
	 * 
	 * @param numerator
	 * @param denominator
	 */
	public Rest(int numerator, int denominator) {
        this.length = new NoteLength(numerator, denominator);
	}
	
	/**
	 * Method to get the String representation of the Rest object
	 */
	@Override
	public String toString() {
		String rest = NAME+this.length.toString();
		return rest;
	}
	
	/**
	 * Get the length of the Rest object.
	 * @return Returns the length of the Rest object.
	 */
	@Override
	public NoteLength getLength() {
	    return length;
	}
	
	/**
     * Plays the Rest object.
     * @param player the player to play the Rest on
     * @param kmt the key, meter and tempo to play with.
     * @param ticksPerUnit the ticks per unit of the player
     * @param currentTick the current tick number
     * @return a pair of the tick number after the rest finishes playing, and the new KeyMeterTempo with the modified key. 
     */
    @Override
    public Pair<Integer,KeyMeterTempo> play(SequencePlayer player, KeyMeterTempo kmt,int ticksPerUnit, int currentTick) {
        int numTicks = this.length.calculateTicks(ticksPerUnit);
        return new Pair<Integer,KeyMeterTempo>(currentTick+numTicks,kmt);
    }
    
    /**
     * Gets the lowest common multiple of the denominators of the notes and rests within.
     * @return the LCM of the denominators
     */
    @Override
    public int getLCMDenominator() {
        return length.denominator;
    }

}
