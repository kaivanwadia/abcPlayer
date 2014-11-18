package sound;

/**
 * The Class Key.
 */
public class Key {
    
    /*
     *  Field = 0byxx
     *      y - SHARP_OFFSET: 0 if flats, 1 if sharps
     *     xx - MAGNITUDE_MASK: 00 if magnitude 0, 01 if magnitude 1, 11 if magnitude 2. 10 is an invalid value.
     *   Mask = right to left in 0b, C field + D field + E field + ... + B field  
     */
    
    /** The mask. */
    private final int mask; 
    private final boolean isMinor;
    
    /** The Constant FIELD_WIDTH. */
    private static final int FIELD_WIDTH = 3;
    
    /** The Constant FIELD_MASK. */
    private static final int FIELD_MASK = (1<<FIELD_WIDTH)-1;
    
    /** The Constant SHARP_OFFSET. */
    private static final int SHARP_OFFSET = FIELD_WIDTH-1;
    
    /** The Constant MAGNITUDE_MASK. */
    private static final int MAGNITUDE_MASK = (1<<SHARP_OFFSET)-1;
    
    /** The Constant SHARP_MASK. */
    private static final int SHARP_MASK = 4;
    
    /** The Constant ONE_MAGNITUDE. */
    private static final int ONE_MAGNITUDE = 1;
    
    /** The Constant TWO_MAGNITUDE. */
    private static final int TWO_MAGNITUDE = 3;
    
    /** The Constant INVALID_MAGNITUDE. */
    private static final int INVALID_MAGNITUDE = 2;
    
    /** The Constant C_OFFSET. */
    private static final int C_OFFSET = 0 * FIELD_WIDTH;
    
    /** The Constant D_OFFSET. */
    private static final int D_OFFSET = 1 * FIELD_WIDTH;
    
    /** The Constant E_OFFSET. */
    private static final int E_OFFSET = 2 * FIELD_WIDTH;
    
    /** The Constant F_OFFSET. */
    private static final int F_OFFSET = 3 * FIELD_WIDTH;
    
    /** The Constant G_OFFSET. */
    private static final int G_OFFSET = 4 * FIELD_WIDTH;
    
    /** The Constant A_OFFSET. */
    private static final int A_OFFSET = 5 * FIELD_WIDTH;
    
    /** The Constant B_OFFSET. */
    private static final int B_OFFSET = 6 * FIELD_WIDTH;
    
    /** The Constant OFFSETS. */
    private static final int[] OFFSETS = {C_OFFSET, D_OFFSET, E_OFFSET, F_OFFSET, G_OFFSET, A_OFFSET, B_OFFSET};
    
    /** The Constant ACCIDENTALS. */
    private static final int[] ACCIDENTALS = {0,ONE_MAGNITUDE,TWO_MAGNITUDE};
    
    /** The Constant VALUES. */
    private static final int[] VALUES = {0,1,Integer.MAX_VALUE,2};
    
    /** The Constant C_MAJOR. */
    public static final Key C_MAJOR = new Key(0,false);
    
    /** The Constant G_MAJOR. */
    public static final Key G_MAJOR = C_MAJOR.addAccidental(NoteName.F, 1);
    
    /** The Constant D_MAJOR. */
    public static final Key D_MAJOR = G_MAJOR.addAccidental(NoteName.C, 1);
    
    /** The Constant A_MAJOR. */
    public static final Key A_MAJOR = D_MAJOR.addAccidental(NoteName.G, 1);
    
    /** The Constant E_MAJOR. */
    public static final Key E_MAJOR = A_MAJOR.addAccidental(NoteName.D, 1);
    
    /** The Constant B_MAJOR. */
    public static final Key B_MAJOR = E_MAJOR.addAccidental(NoteName.A, 1);
    
    /** The Constant F_SHARP_MAJOR. */
    public static final Key F_SHARP_MAJOR = B_MAJOR.addAccidental(NoteName.E, 1);
    
    /** The Constant C_SHARP_MAJOR. */
    public static final Key C_SHARP_MAJOR = F_SHARP_MAJOR.addAccidental(NoteName.B, 1);
    
    /** The Constant F_MAJOR. */
    public static final Key F_MAJOR = C_MAJOR.addAccidental(NoteName.B, -1);
    
    /** The Constant B_FLAT_MAJOR. */
    public static final Key B_FLAT_MAJOR = F_MAJOR.addAccidental(NoteName.E, -1);
    
    /** The Constant E_FLAT_MAJOR. */
    public static final Key E_FLAT_MAJOR = B_FLAT_MAJOR.addAccidental(NoteName.A, -1);
    
    /** The Constant A_FLAT_MAJOR. */
    public static final Key A_FLAT_MAJOR = E_FLAT_MAJOR.addAccidental(NoteName.D, -1);
    
    /** The Constant D_FLAT_MAJOR. */
    public static final Key D_FLAT_MAJOR = A_FLAT_MAJOR.addAccidental(NoteName.G, -1);
    
    /** The Constant G_FLAT_MAJOR. */
    public static final Key G_FLAT_MAJOR = D_FLAT_MAJOR.addAccidental(NoteName.C, -1);
    
    /** The Constant C_FLAT_MAJOR. */
    public static final Key C_FLAT_MAJOR = G_FLAT_MAJOR.addAccidental(NoteName.F, -1);
    
    /** The Constant A_MINOR. */
    public static final Key A_MINOR = C_MAJOR.toggleMinor();
    
    /** The Constant E_MINOR. */
    public static final Key E_MINOR = G_MAJOR.toggleMinor();
    
    /** The Constant B_MINOR. */
    public static final Key B_MINOR = D_MAJOR.toggleMinor();
    
    /** The Constant F_SHARP_MINOR. */
    public static final Key F_SHARP_MINOR = A_MAJOR.toggleMinor();
    
    /** The Constant C_SHARP_MINOR. */
    public static final Key C_SHARP_MINOR = E_MAJOR.toggleMinor();
    
    /** The Constant G_SHARP_MINOR. */
    public static final Key G_SHARP_MINOR = B_MAJOR.toggleMinor();
    
    /** The Constant D_SHARP_MINOR. */
    public static final Key D_SHARP_MINOR = F_SHARP_MAJOR.toggleMinor();
    
    /** The Constant A_SHARP_MINOR. */
    public static final Key A_SHARP_MINOR = C_SHARP_MAJOR.toggleMinor();
    
    /** The Constant D_MINOR. */
    public static final Key D_MINOR = F_MAJOR.toggleMinor();
    
    /** The Constant G_MINOR. */
    public static final Key G_MINOR = B_FLAT_MAJOR.toggleMinor();
    
    /** The Constant C_MINOR. */
    public static final Key C_MINOR = E_FLAT_MAJOR.toggleMinor();
    
    /** The Constant F_MINOR. */
    public static final Key F_MINOR = A_FLAT_MAJOR.toggleMinor();
    
    /** The Constant B_FLAT_MINOR. */
    public static final Key B_FLAT_MINOR = D_FLAT_MAJOR.toggleMinor();
    
    /** The Constant E_FLAT_MINOR. */
    public static final Key E_FLAT_MINOR = G_FLAT_MAJOR.toggleMinor();

    /** The Constant A_FLAT_MINOR. */
    public static final Key A_FLAT_MINOR = C_FLAT_MAJOR.toggleMinor();
    
    /**
     * Instantiates a new blank key (C major).
     */
    public Key() {
        this(0,false);
    }
    
    /**
     * Instantiates a new key from a given bitmask.
     * @param m the bitmask of the key
     * @param minor true if the key is minor
     */
    private Key(int m,boolean minor) {
        mask = m;
        isMinor = minor;
        checkRep();
    }
    
    /**
     * Checks the rep invariant.
     */
    private void checkRep() {
        for(int i=0;i<OFFSETS.length;i++) {
            assert (mask & (MAGNITUDE_MASK<<OFFSETS[i]))!=INVALID_MAGNITUDE : "Key.checkRep() - Magnitude at index " + i + " is not valid: " + mask;
        }
    }
    
    /**
     * Gets the transposition of the note specified by the key.
     * @param name the name of the note
     * @return the transposition of the note in semitones. Positive is sharps, negative is flats
     */
    private int calculateTransposition(NoteName name) {
        //int res = VALUES[mask & (MAGNITUDE_MASK<<OFFSETS[name.getID()])];
        
        int res = VALUES[(mask>>OFFSETS[name.getID()]) & MAGNITUDE_MASK];
        
        if((mask & (1<<(OFFSETS[name.getID()]+SHARP_OFFSET)))==0) {
            res=-res;
        }
        return res;
    }
    
    /**
     * Gets the transposed note as specified by the key. If the argument already has accidentals,
     * or if it has a natural, returns the same (untransposed) note.
     * @param note the note to transpose. If it already has accidentals, it is returned 'as is'
     * @return the transposed note
     */
    public Note getTransposedNote(Note note) {
        if(note.getAccidental()!=0 || note.hasNatural()) return note;
        return note.transpose(calculateTransposition(note.getNoteName()), false);
    }
    
    /**
     * Calculates the mask for the current key with a natural at the specified note.
     * @param name the name of the note to which a natural is being added
     * @return the mask of the resulting key
     */
    private int applyNatural(NoteName name) {
        return mask & (~(FIELD_MASK<<OFFSETS[name.getID()]));
    }
    
    /**
     * Calculates the mask for the current key with the specified accidental at the given note.
     * @param name the name of the note to which the accidental is being added
     * @param semitones the number of the semitones to shift down by. Must be non-negative.
     * @return the mask of the resulting key
     */
    private int applyFlats(NoteName name, int semitones) {
        return applyNatural(name) | (ACCIDENTALS[semitones] << OFFSETS[name.getID()]);
    }
    
    /**
     * Calculates the mask for the current key with the specified accidental at the given note.
     * @param name the name of the note to which the accidental is being added
     * @param semitones the number of the semitones to shift up by. Must be non-negative.
     * @return the mask of the resulting key
     */
    private int applySharps(NoteName name, int semitones) {
        return applyNatural(name) | ((SHARP_MASK | ACCIDENTALS[semitones]) << OFFSETS[name.getID()]);
    }
    

    /**
     * Adds a natural to the specified note and returns the Key.
     * @param name the name of the note to which the natural is applied
     * @return the resulting key with the added natural
     */
    public Key addNatural(NoteName name) {
        return new Key(applyNatural(name),isMinor);
    }
    
    
    /**
     * Toggle between the minor and major versions of the key.
     * @return the key with the minor flag changed
     */
    public Key toggleMinor() {
        return new Key(mask,!isMinor);
    }
    
    /**
     * Adds a accidentals to the specified note and returns the Key.
     *
     * @param name the name of the note to which the accidentals are applied
     * @param semitonesUp the number of semitones to shift up by. Will shift down if this number is negative.
     * @return the key
     */
    public Key addAccidental(NoteName name, int semitonesUp) {
        if(semitonesUp==0) return this;
        if(semitonesUp>0) return new Key(applySharps(name,semitonesUp),isMinor);
        return new Key(applyFlats(name,-semitonesUp),isMinor);
    }
    
    /**
     * Adds the note modifiers to the key.
     * @param note the note to read modifiers from
     * @return the resulting key with modifiers
     */
    public Key addNoteModifiersToKey(Note note) {
        if(note.hasNatural()) return addNatural(note.getNoteName());
        if(note.getAccidental()!=0) return addAccidental(note.getNoteName(), note.getAccidental());
        return this;
    }
    
    /**
     * Gets the String representation of the key.
     * @return the String representation of the key.
     */
    public String toString() {
        return KeySignatures.getKeySignatures().getStringForKey(this);
    }
    
    /**
     * Generate a hash code for the key.
     * @return the hash code of the key
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (isMinor ? 1231 : 1237);
        result = prime * result + mask;
        return result;
    }

    /**
     * Check if a given object equals the Key.
     * @return true iff the object equals this Key. 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Key other = (Key) obj;
        if (isMinor != other.isMinor)
            return false;
        if (mask != other.mask)
            return false;
        return true;
    }
    
}
