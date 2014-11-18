package sound;

/**
 * Class for NoteLength datatype
 */
public class NoteLength implements Comparable<NoteLength>{
	public final int numerator;
	public final int denominator;
	
	public final int realNumerator, realDenominator;
	
	/**
	 * Basic constructor to return a new instance of NoteLength with a numerator of 0 and denominator of 1.
	 */
	public NoteLength() {
		this.numerator = 0;
		this.denominator = 1;
		this.realNumerator = numerator;
		this.realDenominator = denominator;
	}
	
	/**
	 * Constructor to return a new instance of NoteLength with the given numerator and denominator,
	 * reduced to mutually prime numbers.
	 * @param numerator Must be greater than or equal to 0.
	 * @param denominator Must be greater than 0.
	 */
	public NoteLength(int numerator, int denominator) {
	    this.realNumerator = numerator;
	    this.realDenominator = denominator;
	    int gcd = Utils.gcd(numerator, denominator);
		this.numerator = numerator/gcd;
		this.denominator = denominator/gcd;
        checkRep();
	}
	
	/**
	 * Checks the rep invariant.
	 */
	private void checkRep() {
        assert numerator>=0 : "NoteLength.numerator must be greater than or equal to 0. It is currently " + numerator;
        assert denominator>=1 : "NoteLength.denominator must be greater than 1. It is currently " + denominator;
        assert Utils.gcd(numerator, denominator)==1 : "NoteLength.checkRep() - gcd is not 1: " + Utils.gcd(numerator, denominator);
        int gcd = Utils.gcd(realNumerator, realDenominator);
        assert numerator*gcd == realNumerator : "NoteLength.numerator mismatch";
        assert denominator*gcd == realDenominator : "NoteLength.numerator mismatch";
    }
	
	/**
	 * Method to print the value of NoteLength
	 */
	public String toString() {
		String noteLength = this.realNumerator+"/"+this.realDenominator;
		return noteLength;
	}
	
	/* *
	 * Calculate the number of ticks that this NoteLength represents
	 * @param kmt The KeyMeterTempo that defines the number of ticks per unit beat
	 * @return The number of ticks that this NoteLength represents
	 * /
	public int calculateTicks(KeyMeterTempo kmt) {
	    return calculateTicks(kmt.ticksPerUnit);
	}*/
	
	/**
     * Calculate the number of ticks that this NoteLength represents
     * @param kmt The number of ticks per unit beat
     * @return The number of ticks that this NoteLength represents
     */
	public int calculateTicks(int ticksPerUnit) {
	    return (ticksPerUnit / denominator) * numerator;
	}
	
	/**
	 * Multiply the values of two NoteLength objects.
	 * @param mult The NoteLength object to multiply the current instance by.
	 * @return Returns the result of the multiplication of the two NoteLengths.
	 */
	public NoteLength multiply(NoteLength arg) {
	    return new NoteLength(numerator*arg.numerator,denominator*arg.denominator);
	}
	
	/**
	 * Get the reciprocal of this NoteLength. The numerator of this instance must be positive. 
	 * @return the reciprocal of this NoteLength
	 */
	public NoteLength reciprocal() {
	    return new NoteLength(denominator,numerator);
	}
	
	/**
     * Add the values of two NoteLength objects.
     * @param mult The NoteLength object to add to the current instance.
     * @return Returns the result of the addition of the two NoteLengths.
     */
	public NoteLength add(NoteLength arg) {
	    int lcm = Utils.lcm(denominator, arg.denominator);
        int a,b;
        a=numerator*(lcm/denominator);
        b=arg.numerator*(lcm/arg.denominator);
        return new NoteLength(a+b,lcm);
	}
	
	/**
	 * Auto-generated hashCode function.
	 * @return Returns a hash code for the NoteLength object.
	 */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + denominator;
        result = prime * result + numerator;
        return result;
    }
    
    /**
     * Auto-generated equals function.
     * @return Returns true if the argument matches this NoteLength object. Otherwise, returns false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return equals((NoteLength) obj);
    }
   
    /**
     * Auto-generated equals function.
     * @return Returns true if the argument matches this NoteLength object. Otherwise, returns false.
     */
    public boolean equals(NoteLength other) {
        if (denominator != other.denominator)
            return false;
        if (numerator != other.numerator)
            return false;
        return true;
    }
    
    /**
     * Auto-generated compareTo method for Comparable
     * @param other The NoteLength to compare to
     * @return -1 if this object is less than the argument, 1 if it is greater than the argument, 0 if they are equal
     */
    @Override
    public int compareTo(NoteLength other) {
        int lcm = Utils.lcm(denominator, other.denominator);
        Integer a,b;
        a=numerator*(lcm/other.denominator);
        b=other.numerator*(lcm/denominator);
        return a.compareTo(b);
    }
    
}
