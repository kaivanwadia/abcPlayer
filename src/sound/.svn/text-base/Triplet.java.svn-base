package sound;

/**
 * Class to describe Triplet datatype
 *
 */
public class Triplet extends Tuplet {
    /**
     * Creates a new Triplet object given three notes.
     * @param a The first note of the Triplet
     * @param b The second note of the Triplet
     * @param c The third note of the Triplet
     */
    public Triplet(Multinote a, Multinote b, Multinote c) {
        super(new Multinote[]{a,b,c});
    }

    /**
     * Returns the default note length modifier of the Triplet: 2/3
     * @return Returns the default note length modifier of the Triplet: 2/3
     */
    @Override
    public NoteLength getTupletModifier() {
        return new NoteLength(2,3);
    }
}