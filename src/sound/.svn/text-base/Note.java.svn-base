package sound;
/**
 * Class for Note Datatype
 */
public class Note implements Multinote {
    
    private final NoteName name;
    private final int accidental;
	private final Pitch p;
	private final NoteLength length;
	private final boolean natural;
	
	/**
	 * Basic constructor returning a C Note with length 1/1
	 */
	public Note() {
		this('C', 0, 0, 1, 1, false);
	}
	/**
	 * Constructor to return new instance of Note given the note value.
	 * Sets length by default to 1/1
	 */
	public Note(char c) {
		this(c, 0, 0, 1, 1, false);
	}
	/**
	 * Constructor to return new instance of Note given the note value.
	 * Sets length as per the given parameters
	 */
	public Note(char c, int numerator, int denominator) {
		this(c, 0, 0, numerator, denominator, false);
	}
	/**
	 * Constructor to return new instance of Note given the note value and accidental
	 * Sets length to default Length 1/1
	 */
	public Note(char c, int accidental) {
	    this(c, 0, accidental, 1, 1, false);
	}
	
	/**
     * Constructor to return new instance of Note given the note value and possible naturals
     * Sets length to default Length 1/1
     */
	public Note(char c, boolean hasNatural) {
	    this(c,0,0,1,1,hasNatural);
	}
	
	/**
	 * Constructor to return new instance of Note given the note value and accidental
	 * Sets length as per the given parameters
	 */
	public Note(char c, int accidental, int numerator, int denominator) {
		this(c, 0, accidental, numerator, denominator, false);
	}
	/**
	 * Constructor to return new instance of Note given the note value, octave, accidental and possible naturals
	 * Sets the length as per the given parameters
	 */
	public Note(char c, int octave, int accidental, int numerator, int denominator, boolean hasNatural) {
		if (c>'G') {
			c = (char)((int)c-32);
			octave = octave+1;
		}
		Pitch temp = new Pitch(c);
		temp = temp.accidentalTranspose(accidental);
		this.p = temp.octaveTranspose(octave);
		this.length = new NoteLength(numerator, denominator);
		this.natural = hasNatural;
        this.name = NoteName.createFromChar(c);
        this.accidental = accidental;
		checkRep();
	}
	
	/**
	 * Constructor to return a new instance of Note given a pitch, a length and possible naturals.
	 *
	 * @param p the Pitch of the Note. Must not be null.
	 * @param length the NoteLength of the Note. Must not be null.
	 * @param hasNatural true iff the note has a natural.
	 * @param name the name of the note
	 * @param accidental the number of semitones up above the named note
	 */
	private Note(Pitch p, NoteLength length, boolean hasNatural, NoteName name, int accidental) {
	    this.p = p;
	    this.length = length;
	    this.natural = hasNatural;
	    this.name = name;
	    this.accidental = accidental;
	    checkRep();
	}
	
	/**
	 * Checks the rep invariant.
	 */
	private void checkRep() {
	    assert p != null : "Note.checkRep() - p != null";
	    assert length != null : "Note.checkRep() - length != null";
	    assert (!natural || p.getAccidental()==0) : "Note.checkRep() - if the note has a natural, it must not have accidentals: " + toString();
	}
	
	/**
	 * Method to get the String representation of the Note
	 * @return the String representation of the Note
	 */
	@Override
	public String toString() {
		String note = p.toString()+this.length.toString();
		return note;
	}
	
	/**
	 * Transposes the note a number of semitones, or applies a natural
	 * @param semitonesUp Number of semitones to transpose. Ignored if the note has a natural.
	 * @param applyNatural true to apply a natural, otherwise false
	 * @return a new Note object with the desired transposition
	 */
	public Note transpose(int semitonesUp, boolean applyNatural) {
	    if(applyNatural) {
	        if(p.getAccidental()==0) return new Note(p,length,true,name,0);
	        return new Note(p.transpose(-p.getAccidental()),length,true,name,accidental+semitonesUp);
	    } else {
	        return new Note(p.transpose(semitonesUp),length,false,name,accidental+semitonesUp);
	    }
	}
	
	/**
	 * Gets the name of the note.
	 * @return the name of the note
	 */
	public NoteName getName() {
	    return name;
	}
	
	/**
     * Get the length of the Note object.
     * @return the length of the Note object.
     */
    @Override
    public NoteLength getLength() {
        return length;
    }
    
    /**
     * Gets the accidentals associated with the note.
     * @return the accidental value.
     */
    public int getAccidental() {
        return accidental;
    }
	
    /**
     * Gets the pitch.
     * @return the pitch
     */
    public Pitch getPitch() {
        return p;
    }
    
    /**
     * Gets the note name.
     * @return the note name
     */
    public NoteName getNoteName() {
        return p.getNoteName();
    }
    
    /**
     * Checks if the note has a natural.
     * @return true, if the note has a natural; false otherwise
     */
    public boolean hasNatural() {
        return natural;
    }
    
    /**
     * Plays the Note object.
     * @param player the player to play the Note on
     * @param kmt the key, meter and tempo to play with.
     * @param ticksPerUnit the ticks per unit of the player
     * @param currentTick the current tick number
     * @return a pair of the tick number after the note finishes playing, and the new KeyMeterTempo with the modified key. 
     */
    @Override
    public Pair<Integer,KeyMeterTempo> play(SequencePlayer player, KeyMeterTempo kmt,int ticksPerUnit, int currentTick) {
        int numTicks = this.length.calculateTicks(ticksPerUnit); //multiply(kmt.defaultNote)
        player.addNote(kmt.key.getTransposedNote(this).p.toMidiNote(), currentTick, numTicks);
        //player.addNote(this.p.toMidiNote(), currentTick, numTicks);
        KeyMeterTempo kx = kmt.changeKey(kmt.key.addNoteModifiersToKey(this));
        return new Pair<Integer,KeyMeterTempo>(currentTick+numTicks,kx);
    }
    
    /**
     * Plays the Note object.
     * @param player the player to play the Note on
     * @param kmt the key, meter and tempo to play with.
     * @param ticksPerUnit the ticks per unit of the player
     * @param currentTick the current tick number
     * @param modifier a multipler for the note length (used for tuplets)
     * @return a pair of the tick number after the note finishes playing, and the new KeyMeterTempo with the modified key. 
     */
    @Override
    public Pair<Integer,KeyMeterTempo> play(SequencePlayer player, KeyMeterTempo kmt, int ticksPerUnit, int currentTick, NoteLength modifier) {
        int numTicks = this.length.multiply(modifier).calculateTicks(ticksPerUnit); //multiply(kmt.defaultNote)
        player.addNote(kmt.key.getTransposedNote(this).p.toMidiNote(), currentTick, numTicks);
        return new Pair<Integer,KeyMeterTempo>(currentTick+numTicks,kmt.changeKey(kmt.key.addNoteModifiersToKey(this)));
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