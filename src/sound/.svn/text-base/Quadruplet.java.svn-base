package sound;

/**
 * Class to describe Quadruplet datatype
 *
 */
public class Quadruplet extends Tuplet {
    /**
     * Basic constructor to return an instance of Quadruplet given the four notes
     * @param a The first note of the Quadruplet
     * @param b The second note of the Quadruplet
     * @param c The third note of the Quadruplet
     * @param d The fourth note of the Quadruplet
     */
    public Quadruplet(Multinote a, Multinote b, Multinote c, Multinote d) {
        super(new Multinote[]{a,b,c,d});
    }
    
    /**
     * Returns the default note length modifier of the Quadruplet: 3/4
     * @return Returns the default note length modifier of the Quadruplet: 3/4
     */
    @Override
    public NoteLength getTupletModifier() {
        return new NoteLength(3,4);
    }
}