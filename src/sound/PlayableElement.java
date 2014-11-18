package sound;

public interface PlayableElement {
    
    /**
     * Gets the length of the PlayableElement.
     * @return the length (duration) of the element.
     */
    NoteLength getLength();
    
    /**
     * Gets the String representation of the PlayableElement.
     * @return the String representation of the PlayableElement
     */
    String toString();
    
    /**
     * Plays the PlayableElement object.
     * @param player the player to play the PlayableElement on
     * @param kmt the key, meter and tempo to play with.
     * @param ticksPerUnit the ticks per unit of the player
     * @param currentTick the current tick number
     * @return a pair of the tick number after the PlayableElement finishes playing, and the new KeyMeterTempo with the modified key. 
     */
    Pair<Integer,KeyMeterTempo> play(SequencePlayer player, KeyMeterTempo kmt, int ticksPerUnit, int currentTick);
    
    /**
     * Gets the lowest common multiple of the denominators of the notes and rests within.
     * @return the LCM of the denominators
     */
    int getLCMDenominator();
}
