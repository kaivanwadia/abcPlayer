package sound;

import java.util.ArrayList;
import java.util.HashMap;

import player.ABCLexer;
import player.ParseException;
import player.Parser;
import player.ABCLexer.Token;



/**
 * Class for ABCMusic datatype
 */
public class ABCMusic {    
    
    private final HashMap<String, Voice> voices;
    private final int ticksPerUnit;
    /**
     * Constructor returning an instance of ABCMusic given the necessary parameters
     * @param voices a HashMap of Strings to their respective voices in the track. Must not be null or empty.
     *               No Voice may be null. Each Voice must have the same number of Measures. The first and
     *               last measure of each Voice must either be of type RegularMeasures or must sum up to a
     *               full measure. 
     * @throws ParseException if the measures in the Voice are not of proper length.
     */
    public ABCMusic(HashMap<String, Voice> voices, KeyMeterTempo kmt) throws ParseException {
        int tpu = 1;
        this.voices = voices;
        try {
            for(Voice v : voices.values()) {
                v.forceRegularMeasures(kmt);
                for(int i=0;i<v.getNumberOfMeasures();i++) {
                    tpu = Utils.lcm(tpu,v.getMeasureAt(i).getLCMDenominator());
                }
                ensureFirstLastMeasureLength(v,kmt);
            }
        } catch (IllegalStateException ex) {
            throw new ParseException("Parsing failed due to wrong measure length.");
        }
        this.ticksPerUnit = tpu;
        checkRep(kmt);
    }
    
    /**
     * Ensures that the first and last measure of the voice have the proper length.     *
     * @param v the voice to check
     * @param kmt the key, meter and tempo to use.
     * @throws ParseException
     */
    private void ensureFirstLastMeasureLength(Voice v, KeyMeterTempo kmt) throws ParseException {
        NoteLength l1,l2;
        l1 = v.getMeasureAt(0).getMeasureLength(kmt.defaultNote);
        l2 = v.getMeasureAt(v.getNumberOfMeasures()-1).getMeasureLength(kmt.defaultNote);
        if(!((l1.equals(kmt.meter) && l2.equals(kmt.meter)) || (l1.add(l2)).equals(kmt.meter))) {
            throw new ParseException("Parsing failed due to wrong first-last measure length.");
        }
    }
    
    /**
     * Gets the voice with the specified name.
     * @param name the name of the voice to get
     * @return the requested voice
     */
    public Voice getVoice(String name) {
        return voices.get(name);
    }
    
    /**
     * Gets the number of ticks per unit.
     * @return the number of ticks per unit
     */
    public int getTicksPerUnit() {
        return ticksPerUnit;
    }
    
    /**
     * Parses a symbol stream to produce an ABCMusic object.
     * @param input the lexed input stream
     * @param startIndex the start index in the input stream
     * @param parser object of type parser to use to parse different datatypes
     * @return a pair containing the ABCMusic object and the index one beyond the end of the ABCMusic object in the stream
     * @throws ParseException 
     */
    public static Pair<ABCMusic, Integer> parse(ArrayList<ABCLexer.Token> input, int startIndex, Parser parser, ABCHeader header) throws ParseException {
        ABCMusic music;
        HashMap<String, Voice> voices = new HashMap<String, Voice>();
        ABCLexer.Token currentToken = input.get(startIndex);
        if (!currentToken.type.equals(Type.VOICE)) {
            Pair<Voice, Integer> v = parser.voiceParser(input, startIndex, new Voice("1"));
            voices.put("1", v.getVal1());
            startIndex = v.getVal2();
            music = new ABCMusic(voices, header.keyMetTempo);
            Pair<ABCMusic, Integer> parsedMusic = new Pair<ABCMusic, Integer>(music, startIndex);
            return parsedMusic;
        } else {
            while (currentToken.type.equals(Type.VOICE)) {
                if (!voices.containsKey(currentToken.value)) {
                    Voice voice = new Voice(currentToken.value);
                    voices.put(currentToken.value, voice);
                    
                }
                Voice voice = voices.get(currentToken.value);
                Pair<Voice, Integer> v = parser.voiceParser(input, startIndex, voice);
                Voice voice2 = v.getVal1();
                startIndex = v.getVal2();
                for (int i=0; i<voice2.getNumberOfMeasures(); i++) {
                    voice.addMeasure(voice2.getMeasureAt(i));
                }
                voices.put(voice.getName(), voice);
                currentToken = input.get(startIndex);
            }
            if (!currentToken.type.equals(Type.EOF)) throw new AssertionError("Invalid Format, should be end of file");
            music = new ABCMusic(voices, header.keyMetTempo);
            Pair<ABCMusic, Integer> parsedMusic = new Pair<ABCMusic, Integer>(music, startIndex);
            return parsedMusic;
        }
    }
    
    /**
     * Plays the ABCMusic.
     * @param player the player to play the music on.
     * @param kmt the key, meter and tempo to play with
     */
    public void play(SequencePlayer player, KeyMeterTempo kmt) {
        for(Voice v:voices.values()) {
            v.play(player, kmt, ticksPerUnit);
        }
    }
    
    /**
     * Checks the rep invariant with assertions.
     */
    private void checkRep(KeyMeterTempo kmt) {
        assert voices != null : "ABCMusic.checkRep() - voices != null";
        assert !voices.isEmpty() : "ABCMusic.checkRep() - !voices.isEmpty()";
        assert ticksPerUnit > 0 : "KeyMeterTempo.checkRep() - ticksPerUnit > 0";
        int numMeasures = voices.values().iterator().next().getNumberOfMeasures();
        for(Voice v : voices.values()) {
            assert v != null : "ABCMusic.checkRep() - v!=null";
            assert numMeasures == v.getNumberOfMeasures() : "ABCMusic.checkRep() - numMeasures == v.getNumberOfMeasures()";
            NoteLength l1 = v.getMeasureAt(0).getMeasureLength(kmt.defaultNote);
            NoteLength l2 = v.getMeasureAt(v.getNumberOfMeasures()-1).getMeasureLength(kmt.defaultNote);
            assert ((l1.equals(kmt.meter) && l2.equals(kmt.meter)) || (l1.add(l2)).equals(kmt.meter)) : "ABCMusic.checkRep() - first, last measure length";
            
            for(int i=1;i<v.getNumberOfMeasures();i++) {
                if(v.getMeasureAt(i-1).isMajorEnd) {
                    assert v.getMeasureAt(i).isMajorStart : "ABCMusic.checkRep() - v.getMeasureAt(i).isMajorStart";
                }
            }
            
        }
    }
    

    /**
     * Method to add a voice to the ABCMusic instance
     * @param name key value of the voice
     * @param voice the Voice to be added
     * @throws ParseException if the voice of that name already exists
     */
    @SuppressWarnings("unchecked")
    public ABCMusic addVoice(String name, Voice voice, KeyMeterTempo kmt) throws ParseException {
        if (this.voices.containsKey(name)) {
            //This should be an exception, but I don't think it should be a ParseException (Predrag)
            throw new ParseException("Already contains the voice with String: "+name);
        }
        HashMap<String, Voice> voi = (HashMap<String, Voice>) this.voices.clone();
        voi.put(name, voice);
        return new ABCMusic(voi, kmt);
    }
    
    public String toString() {
        String music = "";
        for (Voice v : this.voices.values()) {
            music = music+"V:"+v.getName()+"\n"+v.toString();
        }
        return music;
    }
}
