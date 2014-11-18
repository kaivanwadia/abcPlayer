package sound;

/**
 * Class to describe Duplet datatype
 *
 */
public class Duplet extends Tuplet {
    /**
     * Basic constructor to return an instance of Duplet given two notes 
     * @param a First note in the Duplet
     * @param b Second note in the Duplet
     */
    public Duplet(Multinote a, Multinote b) {
        super(new Multinote[]{a,b});
    }

    /**
     * Returns the default note length modifier of the Duplet: 3/2
     * @return Returns the default note length modifier of the Duplet: 3/2
     */
    @Override
    public NoteLength getTupletModifier() {
        return new NoteLength(3,2);
    }

}
