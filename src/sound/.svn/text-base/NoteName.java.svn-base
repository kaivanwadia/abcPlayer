package sound;

public enum NoteName {
    C(0),
    D(1),
    E(2),
    F(3),
    G(4),
    A(5),
    B(6);
    
    /** The note id. */
    private final int id;
    
    /**
     * Instantiates a new note name.
     * @param id the id
     */
    NoteName(int id) {
        this.id = id;
    }
    
    /**
     * Gets the note name ID.
     * @return the ID
     */
    public int getID() {
        return id;
    }
    
    /**
     * Creates the NoteName from its char representation.
     * @param c the character note name. Must be in [a-gA-G]
     * @return the NoteName object corresponding to the char
     */
    public static NoteName createFromChar(char c) {
        c = Character.toUpperCase(c);
        switch (c)
        {
        case 'C':
            return NoteName.C;
        case 'D':
            return NoteName.D;
        case 'E':
            return NoteName.E;
        case 'F':
            return NoteName.F;
        case 'G':
            return NoteName.G;
        case 'A':
            return NoteName.A;
        case 'B':
            return NoteName.B;
        default:
            throw new IllegalArgumentException("Value " + c + " is not a valid NoteName."); 
        }            
    }
    
}
