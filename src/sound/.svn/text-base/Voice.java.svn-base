package sound;

import java.util.ArrayList;


/**
 * Class to describe Voice datatype
 *
 */
public class Voice {
    private final String name;
    private final ArrayList<Measure> measures;
    
    /**
     * Instantiates a new Voice.
     * @param name the name of the voice
     */
    public Voice(String name) {
        this.name = name;
        this.measures = new ArrayList<Measure>();
    }
    
    /**
     * The state of player.
     */
    private enum PlayState {
        Playing,
        Repeating,
        HitFirst,
        SkippingFirst,
        ExpectingAlternate;
    }
    
    /**
     * Plays the Voice object.
     * @param player the player to play the Voice on
     * @param kmt the key, meter and tempo to play with.
     * @param ticksPerUnit the ticks per unit of the player
     */
    public void play(SequencePlayer player, KeyMeterTempo kmt, int ticksPerUnit) {
        int position = 0;
        int startRepeat = 0;
        int currentTick = 0;
        Measure current;
        PlayState state = PlayState.Playing;
        
        while(position<measures.size()) {
            current = measures.get(position);
            if(current.isMajorStart) {
                startRepeat = position;
            }
            
            if(state==PlayState.ExpectingAlternate) {
                if(current.isFirstRepeat) {
                    state=PlayState.SkippingFirst;
                    position++;
                    continue;
                }
            } else if(state==PlayState.SkippingFirst) {
                if(current.isSecondRepeat) {
                    state = PlayState.Playing;
                } else {
                    position++;
                    continue;
                }
            }
            
            currentTick = current.play(player,kmt,ticksPerUnit,currentTick);
            
            if(state == PlayState.Playing) {
                if(current.isFirstRepeat) {
                    if(current.isRepeatEnd) {
                        position = startRepeat;
                        state = PlayState.ExpectingAlternate;
                    } else {
                        position++;
                        state = PlayState.HitFirst;
                    }
                } else if(current.isRepeatEnd) {
                    position = startRepeat;
                    state = PlayState.Repeating;
                } else {                   
                    position++;
                }
            } else if(state == PlayState.Repeating) { 
                if(current.isRepeatEnd) {
                    state = PlayState.Playing;
                }
                position++;
            } else if(state==PlayState.HitFirst) { //state == PlayState.HitFirst
                if(current.isRepeatEnd) {
                    position = startRepeat;
                    state = PlayState.ExpectingAlternate;
                } else {
                    position++;
                }
            } else if(state==PlayState.ExpectingAlternate) {
                position++;
                /*if(measures.get(position).isFirstRepeat) {
                    position++;
                    while(!measures.get(position).isSecondRepeat) {
                        position++;
                    }
                    state = PlayState.Playing;
                } else {
                    position++;
                }*/
            }            
        }
    }
    
    /**
     * Adds a Measure to the list of measures in the Voice. 
     * @param msr the Measure to add
     */
    public void addMeasure(Measure msr) {
        measures.add(msr);
    }
    
    /**
     * Gets the number of measures in the voice.
     * @return the number of measures in the voice
     */
    public int getNumberOfMeasures() {
        return measures.size();
    }
    
    /**
     * Gets the measure at the specified index.
     * @param index the index of the measure to get. Must be less than getNumberOfMeasures().
     * @return the measure at the specified index.
     */
    public Measure getMeasureAt(int index) {
        return measures.get(index);
    }
    
    /**
     * Replaces the last measure in the voice
     * @param msr - the measure to replace the old measure
     */
    public void replaceLastMeasure() {
        if (getNumberOfMeasures()>0) {
            Measure old = getMeasureAt(getNumberOfMeasures()-1);
            Measure n = new Measure(old.elements, old.isMajorStart, true, old.isRepeatEnd, old.isFirstRepeat, old.isSecondRepeat);
            measures.set(getNumberOfMeasures()-1, n);
        } else {
            return;
        }
    }
    
    /**
     * Forces measures at the start and end to be of regular length.
     * @param kmt the key, meter and tempo of the voice. Only the meter is used.
     */
    public void forceRegularMeasures(KeyMeterTempo kmt) {
        for(int i=1;i<measures.size()-1;i++) {
            measures.get(i).makeRegular(kmt);
        }
    }
    
    /**
     * Gets the name of the Voice.
     * @return the name of the Voice
     */
    public String getName() {
        return name;
    }
    
    /**
     * Method to return the String form of the Voice
     */
    public String toString() {
        String voice = "";
        for (int i=0; i<this.getNumberOfMeasures(); i++) {
            voice = voice + this.getMeasureAt(i).toString();
        }
        voice=voice+"]";
        return voice;
    }
    
}
