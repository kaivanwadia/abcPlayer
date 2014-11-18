package player;
import java.util.ArrayList;
import java.util.regex.Pattern;

import sound.ABCFile;
import sound.ABCHeader;
import sound.ABCMusic;
import sound.Chord;
import sound.Duplet;
import sound.Key;
import sound.KeyMeterTempo;
import sound.KeySignatures;
import sound.Measure;
import sound.Multinote;
import sound.Note;
import sound.Pair;
import sound.PlayableElement;
import sound.Quadruplet;
import sound.Rest;
import sound.Triplet;
import sound.Tuplet;
import sound.Type;
import sound.Voice;

/**
 * Class handling the parsing of the abc-music file to abc-datatype
 */
public class Parser {
    public static int ticksCalculation = 0;
    
    /**
     * Method to parse the complete abc-file
     * @param input - a String array of input tokens
     * @return an ABCFile instance given the String of tokens
     * @throws ParseException 
     */
    public ABCFile abcFileParser(ArrayList<ABCLexer.Token> input) throws ParseException {
        Pair<ABCHeader, Integer> header = this.abcHeaderParser(input, 0);
        int startIndex = header.getVal2();
        Pair<ABCMusic, Integer> music = this.abcMusicParser(input, startIndex, header.getVal1());
        ABCFile file = new ABCFile(header.getVal1(), music.getVal1());
        return file;
    }
    /**
     * Method to parse the abc-header
     * @param input - a String array of input tokens
     * @param startIndex - Starting index of the abc-header to be parsed
     * @return an ABCHeader instance given the String of tokens
     * @throws ParseException 
     */
    public Pair<ABCHeader,Integer> abcHeaderParser(ArrayList<ABCLexer.Token> input, int startIndex) throws ParseException {
        KeyMeterTempo kmt;
        ABCLexer.Token currentToken = input.get(startIndex);
        if (!currentToken.type.equals(Type.HEADER_X)) throw new ParseException("First field not X");
        int pieceNumber = Integer.parseInt(currentToken.value);
        startIndex++;
        currentToken = input.get(startIndex);
        if (!currentToken.type.equals(Type.HEADER_T)) throw new ParseException("Second field not T");
        String title = currentToken.value;
        ABCHeader header = new ABCHeader(pieceNumber, title);
        startIndex++;
        currentToken = input.get(startIndex);
        while (!currentToken.type.equals(Type.HEADER_K)) {
            if (currentToken.type.equals(Type.HEADER_C)) {
                header = header.setComposer(currentToken.value);
            } else if (currentToken.type.equals(Type.HEADER_L)) {
                String[] len = currentToken.value.split("/");
                int numerator = Integer.parseInt(len[0]);
                int denominator = Integer.parseInt(len[1]);
                kmt = new KeyMeterTempo(header.keyMetTempo.key, header.keyMetTempo.meter.realNumerator, header.keyMetTempo.meter.realDenominator, header.keyMetTempo.bpm, numerator, denominator);
                header = header.setKeyMeterTempo(kmt);
            } else if (currentToken.type.equals(Type.HEADER_Q)) {
                int bpm = Integer.parseInt(currentToken.value);
                kmt = new KeyMeterTempo(header.keyMetTempo.key, header.keyMetTempo.meter.realNumerator, header.keyMetTempo.meter.realDenominator, bpm, header.keyMetTempo.defaultNote.realNumerator, header.keyMetTempo.defaultNote.realDenominator);
                header = header.setKeyMeterTempo(kmt);
            } else if (currentToken.type.equals(Type.HEADER_M)) {
                if (currentToken.value.contains("C")) {
                    int numerator = 4;
                    int denominator = 4;
                    kmt = new KeyMeterTempo(header.keyMetTempo.key, numerator, denominator, header.keyMetTempo.bpm, header.keyMetTempo.defaultNote.realNumerator, header.keyMetTempo.defaultNote.realDenominator);
                    header = header.setKeyMeterTempo(kmt);
                } else if (currentToken.value.contains("C|")) {
                    int numerator = 2;
                    int denominator = 2;
                    kmt = new KeyMeterTempo(header.keyMetTempo.key, numerator, denominator, header.keyMetTempo.bpm, header.keyMetTempo.defaultNote.realNumerator, header.keyMetTempo.defaultNote.realDenominator);
                    header = header.setKeyMeterTempo(kmt);
                } else {
                    String[] len = currentToken.value.split("/");
                    int numerator = Integer.parseInt(len[0]);
                    int denominator = Integer.parseInt(len[1]);
                    kmt = new KeyMeterTempo(header.keyMetTempo.key, numerator, denominator, header.keyMetTempo.bpm, header.keyMetTempo.defaultNote.realNumerator, header.keyMetTempo.defaultNote.realDenominator);
                    header = header.setKeyMeterTempo(kmt);
                }
            } else if (currentToken.type.equals(Type.HEADER_V)) {
                header = header.addVoice(currentToken.value);
            } else {
                throw new ParseException("Invalid header field");
            }
            startIndex++;
            currentToken = input.get(startIndex);
        }
        KeySignatures keySig = KeySignatures.getKeySignatures();
        Key key = keySig.getKeyFromString(currentToken.value);
        kmt = new KeyMeterTempo(key, header.keyMetTempo.meter.realNumerator, header.keyMetTempo.meter.realDenominator, header.keyMetTempo.bpm, header.keyMetTempo.defaultNote.realNumerator, header.keyMetTempo.defaultNote.realDenominator);
        header = header.setKeyMeterTempo(kmt);
        startIndex++;
        currentToken = input.get(startIndex);
        if (!currentToken.type.equals(Type.EOH)) throw new ParseException("Key not last in header");
        startIndex++;
        Pair<ABCHeader, Integer> parsedHeader = new Pair<ABCHeader, Integer>(header, startIndex);
        return parsedHeader;
    }
    /**
     * Method to parse the abc-music
     * @param input - a String array of input tokens
     * @param startIndex - Starting index of the abc-music to be parsed
     * @return an ABCMusic instance given the String of tokens
     * @throws ParseException 
     */
    public Pair<ABCMusic,Integer> abcMusicParser(ArrayList<ABCLexer.Token> input, int startIndex, ABCHeader header) throws ParseException {
        return ABCMusic.parse(input, startIndex, this, header);
    }
    
    /**
     * Method to parse the voice
     * @param input - a String array of input tokens
     * @param startIndex - Starting index of the abc-music to be parsed
     * @param previousVoice - The previous instance of the same voice
     * @return a Voice instance given the String of tokens
     */
    public Pair<Voice, Integer> voiceParser(ArrayList<ABCLexer.Token> input, int startIndex, Voice previousVoice) {
        ABCLexer.Token currentToken = input.get(startIndex);
        if(currentToken.type.equals(Type.VOICE) || currentToken.type.equals(Type.BAR)) {
            startIndex++;
            currentToken = input.get(startIndex);
        }
        if (currentToken.type.equals(Type.START_REPEAT)) {
            previousVoice.replaceLastMeasure();
            startIndex++;
            currentToken = input.get(startIndex);
        }        
        currentToken = input.get(startIndex);
        
        Measure previousMeasure;
        if (previousVoice.getNumberOfMeasures()==0) {
            previousMeasure = null;
        } else {
            previousMeasure = previousVoice.getMeasureAt(previousVoice.getNumberOfMeasures()-1);
        }
        
        Voice newVoice = new Voice(previousVoice.getName());
        while (!currentToken.type.equals(Type.VOICE) && !currentToken.type.equals(Type.EOF)) {
            Pair<Measure, Integer> m = this.measureParser(input, startIndex, previousMeasure);
            startIndex = m.getVal2();
            newVoice.addMeasure(m.getVal1());
            previousMeasure = m.getVal1();
            currentToken = input.get(startIndex);
        }
        Pair<Voice, Integer> parsedVoice = new Pair<Voice, Integer>(newVoice, startIndex);
        return parsedVoice;
    }
    
    /**
     * Method to parse a measure given the input and starting index
     * @param input - a String array of input tokens
     * @param startIndex - the starting index of the Measure
     * @return - A measure instance of the  measure to be parsed
     */
    public Pair<Measure,Integer> measureParser(ArrayList<ABCLexer.Token> input, int startIndex, Measure previousMeasure) {
        boolean isMS;
        boolean isME;
        boolean isFR;
        boolean isSR;
        boolean isRE;
        ArrayList<PlayableElement> elems = new ArrayList<PlayableElement>();
        if (previousMeasure==null || previousMeasure.isMajorEnd==true) {
            isMS = true;
        } else {
            isMS = false;
        }
        ABCLexer.Token currentToken = input.get(startIndex);
        if (currentToken.type.equals(Type.ALT_ENDING)) {
            if (currentToken.value.contains("1")) {
                isFR = true;
                isSR = false;
            } else {
                isFR = false;
                isSR = true;
            }
            startIndex++;
        } else {
            isFR = false;
            isSR = false;
        }
        currentToken = input.get(startIndex);
        while (!currentToken.type.equals(Type.BAR) && !currentToken.type.equals(Type.START_REPEAT) && !currentToken.type.equals(Type.END_REPEAT) && !currentToken.type.equals(Type.EOF)) {
            Pair<PlayableElement, Integer> pl = this.playableElementParser(input, startIndex);
            PlayableElement el = pl.getVal1();
            startIndex = pl.getVal2();
            elems.add(el);
            currentToken = input.get(startIndex);
        }
        if (currentToken.type.equals(Type.BAR)) {
            isME = false;
            isRE = false;
        } else if (currentToken.type.equals(Type.START_REPEAT)) {
            isME = true;
            isRE = false;
        } else {
            isME = true;
            isRE = true;
        }
        if (currentToken.type.equals(Type.EOF)) {
            isME = true;
            isRE = false;
            startIndex--;
        }
        startIndex++;
        Measure m = new Measure(elems, isMS, isME, isRE, isFR, isSR);
        Pair<Measure, Integer> parsedMeasure = new Pair<Measure, Integer>(m, startIndex);
        return parsedMeasure;
    }
    
    /**
     * Method to parse a PlayableElement given the input and starting index
     * @param input - a String array of input tokens
     * @param startIndex - the starting index of the PlayableElement
     * @return A PlayableElement
     */
    public Pair<PlayableElement,Integer> playableElementParser(ArrayList<ABCLexer.Token> input, int startIndex) {
        ABCLexer.Token currentToken = input.get(startIndex);
        PlayableElement element = null;
        if (currentToken.type.equals(Type.TUPLET)) {
            Pair<Tuplet, Integer> temp = this.tupletParser(input, startIndex);
            startIndex = temp.getVal2();
            element = temp.getVal1();
            Pair<PlayableElement, Integer> playable = new Pair<PlayableElement, Integer>(element, startIndex);
            return playable;
        } else if (currentToken.type.equals(Type.START_CHORD)) {
            Pair<Chord, Integer> temp = this.chordParser(input, startIndex);
            startIndex = temp.getVal2();
            element = temp.getVal1();
            Pair<PlayableElement, Integer> playable = new Pair<PlayableElement, Integer>(element, startIndex);
            return playable;
        } else if (currentToken.type.equals(Type.REST)) {
            Pair<Rest,Integer> temp = this.restParser(input, startIndex);
            startIndex = temp.getVal2();
            element = temp.getVal1();
            Pair<PlayableElement, Integer> playable = new Pair<PlayableElement, Integer>(element, startIndex);
            return playable;
        } else {
            Pair<Note, Integer> temp = this.noteParser(input, startIndex);
            startIndex = temp.getVal2();
            element = temp.getVal1();
            Pair<PlayableElement, Integer> playable = new Pair<PlayableElement, Integer>(element, startIndex);
            return playable;
        }
    }
    
    /**
     * Method to parse a tuplet given the input and starting index
     * @param input - a String array of input tokens
     * @param startIndex - the starting index of the Tuplet
     * @return - A tuplet instance of the tuplet to be parsed
     */
    public Pair<Tuplet,Integer> tupletParser(ArrayList<ABCLexer.Token> input, int startIndex) {
        ABCLexer.Token currentToken = input.get(startIndex);
        if (currentToken.value.contains("2")) {
            Multinote[] notes = new Multinote[2];
            startIndex++;
            for (int i=0;i<2;i++) {
                Pair<Multinote, Integer> temp = this.multinoteParser(input, startIndex);
                startIndex = temp.getVal2();
                notes[i] = temp.getVal1();
            }
            Duplet tuplet = new Duplet(notes[0], notes[1]);
            Pair<Tuplet, Integer> parsedTuplet = new Pair<Tuplet, Integer>(tuplet, startIndex);
            return parsedTuplet;
        } else if (currentToken.value.contains("3")) {
            Multinote[] notes = new Multinote[3];
            startIndex++;
            for (int i=0;i<3;i++) {
                Pair<Multinote, Integer> temp = this.multinoteParser(input, startIndex);
                startIndex = temp.getVal2();
                notes[i] = temp.getVal1();
            }
            Triplet tuplet = new Triplet(notes[0], notes[1], notes[2]);
            Pair<Tuplet, Integer> parsedTuplet = new Pair<Tuplet, Integer>(tuplet, startIndex);
            return parsedTuplet;
        } else {
            Multinote[] notes = new Multinote[4];
            startIndex++;
            for (int i=0;i<4;i++) {
                Pair<Multinote, Integer> temp = this.multinoteParser(input, startIndex);
                startIndex = temp.getVal2();
                notes[i] = temp.getVal1();
            }
            Quadruplet tuplet = new Quadruplet(notes[0], notes[1], notes[2], notes[3]);
            Pair<Tuplet, Integer> parsedTuplet = new Pair<Tuplet, Integer>(tuplet, startIndex);
            return parsedTuplet;
        }
    }
    
    /**
     * Method to parse a PlayableElement given the input and starting index
     * @param input - a String array of input tokens
     * @param startIndex - the starting index of the PlayableElement
     * @return A PlayableElement
     */
    public Pair<Multinote,Integer> multinoteParser(ArrayList<ABCLexer.Token> input, int startIndex) {
        ABCLexer.Token currentToken = input.get(startIndex);
        Multinote element = null;
        if (currentToken.type.equals(Type.START_CHORD)) {
            Pair<Chord, Integer> temp = this.chordParser(input, startIndex);
            startIndex = temp.getVal2();
            element = temp.getVal1();
            Pair<Multinote, Integer> playable = new Pair<Multinote, Integer>(element, startIndex);
            return playable;
        } else {
            Pair<Note, Integer> temp = this.noteParser(input, startIndex);
            startIndex = temp.getVal2();
            element = temp.getVal1();
            Pair<Multinote, Integer> playable = new Pair<Multinote, Integer>(element, startIndex);
            return playable;
        }
    }
    
    /**
     * Method to parse a chord given the input and starting index
     * @param input - a String array of input tokens
     * @param startIndex - the starting index of the Chord
     * @return - A chord instance of the chord to be parsed
     */
    public Pair<Chord,Integer> chordParser(ArrayList<ABCLexer.Token> input, int startIndex) {
        ABCLexer.Token currentToken = input.get(startIndex);
        Note note;
        Chord chord = null;
        boolean firstNote = true;
        if (currentToken.type.equals(Type.START_CHORD)) {
            startIndex++;
        }
        currentToken = input.get(startIndex);
        while (!currentToken.type.equals(Type.END_CHORD)) {
            Pair<Note, Integer> temp = this.noteParser(input, startIndex);
            startIndex = temp.getVal2();
            note = temp.getVal1();
            if (firstNote) {
                chord = new Chord(note);
                firstNote = false;
            } else {
                chord = chord.addNote(note);
            }
            currentToken = input.get(startIndex);
        }
        startIndex++;
        Pair<Chord, Integer> parsedChord = new Pair<Chord, Integer>(chord, startIndex);
        return parsedChord;
    }
    
    /**
     * Method to parse a note
     * @param input - List of tokens from the parser
     * @param startIndex - Starting index of the note to be parsed
     * @param noOfNotes - No. of notes to be parsed from the starting index
     * @return - A note instance of the note  to be parsed
     */
    public Pair<Note,Integer> noteParser(ArrayList<ABCLexer.Token> input, int startIndex) {
        ABCLexer.Token currentToken = input.get(startIndex);
        boolean hasNatural = false;
        int accidental = 0;
        int octave = 0;
        int numerator = 1;
        int denominator = 1;
        char noteLetter = 'q';
        Note note = new Note();
        if (currentToken.type.equals(Type.ACCIDENTAL)) {
            startIndex++;
            if (currentToken.value.contains("^")) {
                accidental = accidental + currentToken.value.length();
            } else if (currentToken.value.contains("_")) {
                accidental = accidental - currentToken.value.length();
            } else if (currentToken.value.contains("=")) {
                accidental = 0;
                hasNatural = true;
            }
        }
        currentToken = input.get(startIndex);
        if (currentToken.type.equals(Type.NOTE)) {
            startIndex++;
            noteLetter = currentToken.value.charAt(0);
        }
        currentToken = input.get(startIndex);
        if (currentToken.type.equals(Type.OCTAVE)) {
            startIndex++;
            if (currentToken.value.contains("'")) {
                octave = octave + currentToken.value.length();
            } else if (currentToken.value.contains(",")) {
                octave = octave - currentToken.value.length();
            }
        }
        currentToken = input.get(startIndex);
        if (currentToken.type.equals(Type.LENGTH)) {
            startIndex++;
            String len = currentToken.value;
            Pattern p = Pattern.compile("/{1}");
            String[] items = p.split(len);
            if (items.length==0) {
                numerator = 1;
                denominator = 2;
            } else if (items.length==1) {
                numerator = Integer.parseInt(items[0]);
                if (len.contains("/")) {
                    denominator = 2;
                }
            } else if (items.length==2) {
                if (items[0].length()>0) {
                    numerator = Integer.parseInt(items[0]);
                }
                denominator = Integer.parseInt(items[1]);
            }
        }
        note = new Note(noteLetter, octave, accidental, numerator, denominator, hasNatural);
        Pair<Note, Integer> parsedNote = new Pair<Note, Integer>(note, startIndex);
        return parsedNote;
    }
    
    /**
     * Method to parse a tuplet given the input and starting index
     * @param input - a String array of input tokens
     * @param startIndex - the starting index of the Tuplet
     * @return - A rest instance of the rest to be parsed
     */
    public Pair<Rest,Integer> restParser(ArrayList<ABCLexer.Token> input, int startIndex) {
        Rest rest = new Rest();
        ABCLexer.Token currentToken = input.get(startIndex);
        int numerator = 1;
        int denominator = 1;
        if (currentToken.type.equals(Type.REST)) {
            startIndex++;
        }
        currentToken = input.get(startIndex);
        if (currentToken.type.equals(Type.LENGTH)) {
            startIndex++;
            String len = currentToken.value;
            Pattern p = Pattern.compile("/{1}");
            String[] items = p.split(len);
            if (items.length==0) {
                numerator = 1;
                denominator = 2;
            } else if (items.length==1) {
                numerator = Integer.parseInt(items[0]);
                if (len.contains("/")) {
                    denominator = 2;
                }
            } else if (items.length==2) {
                if (items[0].length()>0) {
                    numerator = Integer.parseInt(items[0]);
                }
                denominator = Integer.parseInt(items[1]);
            }
        }
        rest = new Rest(numerator, denominator);
        Pair<Rest, Integer> parsedRest = new Pair<Rest, Integer>(rest, startIndex);
        return parsedRest;
    }
}