package player;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import player.ABCLexer.LexerException;

public class ABCLexerTest {

    String result;

    /**
     * ABCLexer tests
     * 
     * @throws FileNotFoundException
     * @throws LexerException
     * 
     */

    // Sample piece of music 1 (value)
    @Test
    public void samplePiece1LexerFileTest() throws FileNotFoundException,
            ABCLexer.LexerException {
        String filename = "sample_abc/piece1.abc";
        ABCLexer lexer = new ABCLexer(filename);
        ArrayList<ABCLexer.Token> tokens = lexer.getTokens();
        ArrayList<String> tokensValue = new ArrayList<String>();
        int i;
        for (i = 0; i < tokens.size(); i++) {
            tokensValue.add(tokens.get(i).value);
        }
        String[] arr = { "1", "Piece No.1", "C", "1/4", "140", "C", "", "C",
                "C", "C", "3/4", "D", "/4", "E", "|", "E", "3/4", "D", "/4",
                "E", "3/4", "F", "/4", "G", "2", "|", "3", "c", "/", "c", "/",
                "c", "/", "3", "G", "/", "G", "/", "G", "/", "3", "E", "/",
                "E", "/", "E", "/", "3", "C", "/", "C", "/", "C", "/", "|",
                "G", "3/4", "F", "/4", "E", "3/4", "D", "/4", "C", "2", "|]",
                "" };
        ArrayList<String> array = new ArrayList<String>();
        Collections.addAll(array, arr);
        assertEquals(tokensValue, array);
    }

    // Sample piece of music 2 (value)
    @Test
    public void samplePiece2LexerFileTest() throws FileNotFoundException,
            ABCLexer.LexerException {
        String filename = "sample_abc/piece2.abc";
        ABCLexer lexer = new ABCLexer(filename);
        ArrayList<ABCLexer.Token> tokens = lexer.getTokens();
        ArrayList<String> tokensValue = new ArrayList<String>();
        int i;
        for (i = 0; i < tokens.size(); i++) {
            tokensValue.add(tokens.get(i).value);
        }
        ArrayList<String> array = new ArrayList<String>();
        String[] arr2 = { "2", " Piece No.2", "C", "1/4", "200", "C", "", "[",
                "^", "F", "/", "e", "/", "]", "[", "F", "/", "e", "/", "]",
                "z", "/", "[", "F", "/", "e", "/", "]", "z", "/", "[", "F",
                "/", "c", "/", "]", "[", "F", "e", "]", "|", "[", "G", "B",
                "g", "]", "z", "G", "z", "|", "c", "3/2", "G", "/", "z", "E",
                "|", "E", "/", "A", "B", "_", "B", "/", "A", "|", "3", "G",
                "e", "g", "a", "f", "/", "g", "/", "|", "z", "/", "e", "c",
                "/", "d", "/", "B", "3/4", "|]", "" };
        Collections.addAll(array, arr2);
        assertEquals(tokensValue, array);
    }

    // Sample accidental test (type and value)
    @Test
    public void AccidentalLexerFileTest() throws FileNotFoundException,
            ABCLexer.LexerException {
        String filename = "test_samples/test_accidentals.abc";
        ABCLexer lexer = new ABCLexer(filename);
        ArrayList<ABCLexer.Token> tokens = lexer.getTokens();
        ArrayList<String> tokensValue = new ArrayList<String>();
        ArrayList<sound.Type> tokensType = new ArrayList<sound.Type>();
        int i;
        for (i = 0; i < tokens.size(); i++) {
            tokensValue.add(tokens.get(i).value);
            tokensType.add(tokens.get(i).type);
        }
        ArrayList<String> array = new ArrayList<String>();
        String[] arrAccidentals = { "1", "Piece No.1", "5/4", "1/4", "140",
                "C", "", "^", "D", "_", "E", "=", "C", "^^", "C", "__", "F",
                "|:", "" };
        Collections.addAll(array, arrAccidentals);
        ArrayList<sound.Type> arrayType = new ArrayList<sound.Type>();
        sound.Type[] arr2 = { sound.Type.HEADER_X, sound.Type.HEADER_T,
                sound.Type.HEADER_M, sound.Type.HEADER_L, sound.Type.HEADER_Q,
                sound.Type.HEADER_K, sound.Type.EOH, sound.Type.ACCIDENTAL,
                sound.Type.NOTE, sound.Type.ACCIDENTAL, sound.Type.NOTE,
                sound.Type.ACCIDENTAL, sound.Type.NOTE, sound.Type.ACCIDENTAL,
                sound.Type.NOTE, sound.Type.ACCIDENTAL, sound.Type.NOTE,
                sound.Type.START_REPEAT, sound.Type.EOF };
        Collections.addAll(arrayType, arr2);
        assertEquals(tokensValue, array);
        assertEquals(tokensType, arrayType);
        /*
         * ArrayList<String> tokensValue = new ArrayList<String>(); int i = 0;
         * ArrayList<sound.Type> tokensType = new ArrayList<sound.Type>(); for
         * (i = 0; i<tokens.size(); i++) { tokensValue.add(tokens.get(i).value);
         * tokensType.add(tokens.get(i).type); //
         * System.out.print(tokens.get(i).type); // System.out.print(" "); }
         * 
         * System.out.println(tokensType); System.out.println(tokensValue);
         */
    }

    // Sample header file test (type and value)
    @Test
    public void HeaderLexerFileTest() throws FileNotFoundException,
            ABCLexer.LexerException {
        String filename = "test_samples/test_header.abc";
        ABCLexer lexer = new ABCLexer(filename);
        ArrayList<ABCLexer.Token> tokens = lexer.getTokens();
        ArrayList<String> tokensValue = new ArrayList<String>();
        ArrayList<sound.Type> tokensType = new ArrayList<sound.Type>();
        int i;
        for (i = 0; i < tokens.size(); i++) {
            tokensValue.add(tokens.get(i).value);
            tokensType.add(tokens.get(i).type);
        }
        ArrayList<String> array = new ArrayList<String>();
        String[] arrAccidentals = { "1", "Bagatelle No.25 in A, WoO.59",
                "Ludwig van Beethoven", "1", "2", "3/8", "1/16", "400", "Am",
                "", "1", "" };
        Collections.addAll(array, arrAccidentals);
        ArrayList<sound.Type> arrayType = new ArrayList<sound.Type>();
        sound.Type[] arr2 = { sound.Type.HEADER_X, sound.Type.HEADER_T,
                sound.Type.HEADER_C, sound.Type.HEADER_V, sound.Type.HEADER_V,
                sound.Type.HEADER_M, sound.Type.HEADER_L, sound.Type.HEADER_Q,
                sound.Type.HEADER_K, sound.Type.EOH, sound.Type.VOICE,
                sound.Type.EOF };
        Collections.addAll(arrayType, arr2);
        assertEquals(tokensValue, array);
        assertEquals(tokensType, arrayType);
    }

    // Sample header file test with comments (type and value)
    @Test
    public void HeaderCommentLexerFileTest() throws FileNotFoundException,
            ABCLexer.LexerException {
        String filename = "test_samples/test_headerComment.abc";
        ABCLexer lexer = new ABCLexer(filename);
        ArrayList<ABCLexer.Token> tokens = lexer.getTokens();
        ArrayList<String> tokensValue = new ArrayList<String>();
        ArrayList<sound.Type> tokensType = new ArrayList<sound.Type>();
        int i;
        for (i = 0; i < tokens.size(); i++) {
            tokensValue.add(tokens.get(i).value);
            tokensType.add(tokens.get(i).type);
        }
        ArrayList<String> arraylistValues = new ArrayList<String>();
        String[] arrayValue = { "1", "Piece No.1", "6/4", "1/4", "140", "C",
                "", "C", "z", "E", "z", "G", "F", "|", "" };
        Collections.addAll(arraylistValues, arrayValue);
        ArrayList<sound.Type> arraylistTypes = new ArrayList<sound.Type>();
        sound.Type[] arrayType = { sound.Type.HEADER_X, sound.Type.HEADER_T,
                sound.Type.HEADER_M, sound.Type.HEADER_L, sound.Type.HEADER_Q,
                sound.Type.HEADER_K, sound.Type.EOH, sound.Type.NOTE,
                sound.Type.REST, sound.Type.NOTE, sound.Type.REST,
                sound.Type.NOTE, sound.Type.NOTE, sound.Type.BAR,
                sound.Type.EOF };
        Collections.addAll(arraylistTypes, arrayType);
        assertEquals(tokensValue, arraylistValues);
        assertEquals(tokensType, arraylistTypes);
    }

    // Sample header music file test with comments (type and value)
    @Test
    public void HeaderMusicCommentLexerFileTest() throws FileNotFoundException,
            ABCLexer.LexerException {
        String filename = "test_samples/test_headerMusicComment.abc";
        ABCLexer lexer = new ABCLexer(filename);
        ArrayList<ABCLexer.Token> tokens = lexer.getTokens();
        ArrayList<String> tokensValue = new ArrayList<String>();
        ArrayList<sound.Type> tokensType = new ArrayList<sound.Type>();
        int i;
        for (i = 0; i < tokens.size(); i++) {
            tokensValue.add(tokens.get(i).value);
            tokensType.add(tokens.get(i).type);
        }
        ArrayList<String> arraylistValues = new ArrayList<String>();
        String[] arrayValue = { "1", "Piece No.1", "6/4", "1/4", "140", "C",
                "", "C", "z", "E", "z", "F", "F", "|", "" };
        Collections.addAll(arraylistValues, arrayValue);
        ArrayList<sound.Type> arraylistTypes = new ArrayList<sound.Type>();
        sound.Type[] arrayType = { sound.Type.HEADER_X, sound.Type.HEADER_T,
                sound.Type.HEADER_M, sound.Type.HEADER_L, sound.Type.HEADER_Q,
                sound.Type.HEADER_K, sound.Type.EOH, sound.Type.NOTE,
                sound.Type.REST, sound.Type.NOTE, sound.Type.REST,
                sound.Type.NOTE, sound.Type.NOTE, sound.Type.BAR,
                sound.Type.EOF };
        Collections.addAll(arraylistTypes, arrayType);
        assertEquals(tokensValue, arraylistValues);
        assertEquals(tokensType, arraylistTypes);
    }

    // Sample one voice file with comments (type and value)
    @Test
    public void singleVoiceFileTest() throws FileNotFoundException,
            ABCLexer.LexerException {
        String filename = "test_samples/test_singleVoice.abc";
        ABCLexer lexer = new ABCLexer(filename);
        ArrayList<ABCLexer.Token> tokens = lexer.getTokens();
        ArrayList<String> tokensValue = new ArrayList<String>();
        ArrayList<sound.Type> tokensType = new ArrayList<sound.Type>();
        int i;
        for (i = 0; i < tokens.size(); i++) {
            tokensValue.add(tokens.get(i).value);
            tokensType.add(tokens.get(i).type);
        }
        ArrayList<String> arraylistValues = new ArrayList<String>();
        String[] arrayValue = { "1", "Piece No.1", "4/4", "1/4", "140", "1",
                "C", "", "1", "F", "F", "F", "F", "|", "F", "G", "C", "2", "|",
                "" };
        Collections.addAll(arraylistValues, arrayValue);
        ArrayList<sound.Type> arraylistTypes = new ArrayList<sound.Type>();
        sound.Type[] arrayType = { sound.Type.HEADER_X, sound.Type.HEADER_T,
                sound.Type.HEADER_M, sound.Type.HEADER_L, sound.Type.HEADER_Q,
                sound.Type.HEADER_V, sound.Type.HEADER_K, sound.Type.EOH,
                sound.Type.VOICE, sound.Type.NOTE, sound.Type.NOTE,
                sound.Type.NOTE, sound.Type.NOTE, sound.Type.BAR,
                sound.Type.NOTE, sound.Type.NOTE, sound.Type.NOTE,
                sound.Type.LENGTH, sound.Type.BAR, sound.Type.EOF };
        Collections.addAll(arraylistTypes, arrayType);
        assertEquals(tokensValue, arraylistValues);
        assertEquals(tokensType, arraylistTypes);
    }

    // Sample multiple voice file test with comments (type and value)
    @Test
    public void multipleVoiceFileTest() throws FileNotFoundException,
            ABCLexer.LexerException {
        String filename = "test_samples/test_multipleVoices.abc";
        ABCLexer lexer = new ABCLexer(filename);
        ArrayList<ABCLexer.Token> tokens = lexer.getTokens();
        ArrayList<String> tokensValue = new ArrayList<String>();
        ArrayList<sound.Type> tokensType = new ArrayList<sound.Type>();
        int i;
        for (i = 0; i < tokens.size(); i++) {
            tokensValue.add(tokens.get(i).value);
            tokensType.add(tokens.get(i).type);
        }
        ArrayList<String> arraylistValues = new ArrayList<String>();
        String[] arrayValue = { "1", "Piece No.1", "4/4", "1/4", "140", "1",
                "3", "2", "C", "", "3", "C", "A", "F", "E", "|", "A", "b", "c",
                "2", "|", "2", "F", "E", "E", "D", "|", "F", "G", "C", "2",
                "|", "1", "F", "A", "C", "E", "|", "c", "d", "e", "2", "|", "" };
        Collections.addAll(arraylistValues, arrayValue);
        ArrayList<sound.Type> arraylistTypes = new ArrayList<sound.Type>();
        sound.Type[] arrayType = { sound.Type.HEADER_X, sound.Type.HEADER_T,
                sound.Type.HEADER_M, sound.Type.HEADER_L, sound.Type.HEADER_Q,
                sound.Type.HEADER_V, sound.Type.HEADER_V, sound.Type.HEADER_V,
                sound.Type.HEADER_K, sound.Type.EOH, sound.Type.VOICE,
                sound.Type.NOTE, sound.Type.NOTE, sound.Type.NOTE,
                sound.Type.NOTE, sound.Type.BAR, sound.Type.NOTE,
                sound.Type.NOTE, sound.Type.NOTE, sound.Type.LENGTH,
                sound.Type.BAR, sound.Type.VOICE, sound.Type.NOTE,
                sound.Type.NOTE, sound.Type.NOTE, sound.Type.NOTE,
                sound.Type.BAR, sound.Type.NOTE, sound.Type.NOTE,
                sound.Type.NOTE, sound.Type.LENGTH, sound.Type.BAR,
                sound.Type.VOICE, sound.Type.NOTE, sound.Type.NOTE,
                sound.Type.NOTE, sound.Type.NOTE, sound.Type.BAR,
                sound.Type.NOTE, sound.Type.NOTE, sound.Type.NOTE,
                sound.Type.LENGTH, sound.Type.BAR, sound.Type.EOF };
        Collections.addAll(arraylistTypes, arrayType);
        assertEquals(tokensValue, arraylistValues);
        assertEquals(tokensType, arraylistTypes);
    }

    // Sample repeat file test with comments (type and value)
    @Test
    public void repeatFileTest() throws FileNotFoundException,
            ABCLexer.LexerException {
        String filename = "test_samples/test_repeat.abc";
        ABCLexer lexer = new ABCLexer(filename);
        ArrayList<ABCLexer.Token> tokens = lexer.getTokens();
        ArrayList<String> tokensValue = new ArrayList<String>();
        ArrayList<sound.Type> tokensType = new ArrayList<sound.Type>();
        int i;
        for (i = 0; i < tokens.size(); i++) {
            tokensValue.add(tokens.get(i).value);
            tokensType.add(tokens.get(i).type);
        }
        ArrayList<String> arraylistValues = new ArrayList<String>();
        String[] arrayValue = { "1", "Piece No.1", "4/4", "1/4", "140", "C",
                "", "|:", "B", "A", "B", "E", "|", "C", "A", "F", "E", ":|" };
        Collections.addAll(arraylistValues, arrayValue);
        ArrayList<sound.Type> arraylistTypes = new ArrayList<sound.Type>();
        sound.Type[] arrayType = { sound.Type.HEADER_X, sound.Type.HEADER_T,
                sound.Type.HEADER_M, sound.Type.HEADER_L, sound.Type.HEADER_Q,
                sound.Type.HEADER_K, sound.Type.EOH, sound.Type.START_REPEAT,
                sound.Type.NOTE, sound.Type.NOTE, sound.Type.NOTE,
                sound.Type.NOTE, sound.Type.BAR, sound.Type.NOTE,
                sound.Type.NOTE, sound.Type.NOTE, sound.Type.NOTE,
                sound.Type.END_REPEAT };
        Collections.addAll(arraylistTypes, arrayType);
        // assertEquals(tokensValue, arraylistValues);
        // assertEquals(tokensType, arraylistTypes);
    }

    // Sample alternate ending test file with comments (type and value)
    @Test
    public void repeatAlternateEndingsFileTest() throws FileNotFoundException,
            ABCLexer.LexerException {
        String filename = "test_samples/test_alternateEnding.abc";
        ABCLexer lexer = new ABCLexer(filename);
        ArrayList<ABCLexer.Token> tokens = lexer.getTokens();
        ArrayList<String> tokensValue = new ArrayList<String>();
        ArrayList<sound.Type> tokensType = new ArrayList<sound.Type>();
        int i;
        for (i = 0; i < tokens.size(); i++) {
            tokensValue.add(tokens.get(i).value);
            tokensType.add(tokens.get(i).type);
        }
        ArrayList<String> arraylistValues = new ArrayList<String>();
        String[] arrayValue = { "1", "Piece No.4", "4/4", "1/4", "140", "F",
                "", "|:", "C", "D", "F", "C", "|", "1", "F", "G", "G", "2",
                ":|", "2", "C", "F", "C", "2", "|", "" };
        Collections.addAll(arraylistValues, arrayValue);
        ArrayList<sound.Type> arraylistTypes = new ArrayList<sound.Type>();
        sound.Type[] arrayType = { sound.Type.HEADER_X, sound.Type.HEADER_T,
                sound.Type.HEADER_M, sound.Type.HEADER_L, sound.Type.HEADER_Q,
                sound.Type.HEADER_K, sound.Type.EOH, sound.Type.START_REPEAT,
                sound.Type.NOTE, sound.Type.NOTE, sound.Type.NOTE,
                sound.Type.NOTE, sound.Type.BAR, sound.Type.ALT_ENDING,
                sound.Type.NOTE, sound.Type.NOTE, sound.Type.NOTE,
                sound.Type.LENGTH, sound.Type.END_REPEAT,
                sound.Type.ALT_ENDING, sound.Type.NOTE, sound.Type.NOTE,
                sound.Type.NOTE, sound.Type.LENGTH, sound.Type.BAR,
                sound.Type.EOF };
        Collections.addAll(arraylistTypes, arrayType);
        assertEquals(tokensValue, arraylistValues);
        assertEquals(tokensType, arraylistTypes);
    }

    // Sample music comment test file with comments (type and value)
    @Test
    public void musicCommentFileTest() throws FileNotFoundException,
            ABCLexer.LexerException {
        String filename = "test_samples/test_musicComment.abc";
        ABCLexer lexer = new ABCLexer(filename);
        ArrayList<ABCLexer.Token> tokens = lexer.getTokens();
        ArrayList<String> tokensValue = new ArrayList<String>();
        ArrayList<sound.Type> tokensType = new ArrayList<sound.Type>();
        int i;
        for (i = 0; i < tokens.size(); i++) {
            tokensValue.add(tokens.get(i).value);
            tokensType.add(tokens.get(i).type);
        }
        ArrayList<String> arraylistValues = new ArrayList<String>();
        String[] arrayValue = { "1", "Piece No.1", "6/4", "1/4", "140", "D",
                "", "C", "z", "F", "z", "F", "E", "|", "" };
        Collections.addAll(arraylistValues, arrayValue);
        ArrayList<sound.Type> arraylistTypes = new ArrayList<sound.Type>();
        sound.Type[] arrayType = { sound.Type.HEADER_X, sound.Type.HEADER_T,
                sound.Type.HEADER_M, sound.Type.HEADER_L, sound.Type.HEADER_Q,
                sound.Type.HEADER_K, sound.Type.EOH, sound.Type.NOTE,
                sound.Type.REST, sound.Type.NOTE, sound.Type.REST,
                sound.Type.NOTE, sound.Type.NOTE, sound.Type.BAR,
                sound.Type.EOF };
        Collections.addAll(arraylistTypes, arrayType);
        assertEquals(tokensValue, arraylistValues);
        assertEquals(tokensType, arraylistTypes);
    }

    // Sample chord test file with comments (type and value)
    @Test
    public void chordFileTest() throws FileNotFoundException,
            ABCLexer.LexerException {
        String filename = "test_samples/test_chord.abc";
        ABCLexer lexer = new ABCLexer(filename);
        ArrayList<ABCLexer.Token> tokens = lexer.getTokens();
        ArrayList<String> tokensValue = new ArrayList<String>();
        ArrayList<sound.Type> tokensType = new ArrayList<sound.Type>();
        int i;
        for (i = 0; i < tokens.size(); i++) {
            tokensValue.add(tokens.get(i).value);
            tokensType.add(tokens.get(i).type);
        }
        ArrayList<String> arraylistValues = new ArrayList<String>();
        String[] arrayValue = { "1", "Piece No.1", "1/4", "1/4", "140", "C",
                "", "[", "F", "F", "F", "]", "|]", "" };
        Collections.addAll(arraylistValues, arrayValue);
        ArrayList<sound.Type> arraylistTypes = new ArrayList<sound.Type>();
        sound.Type[] arrayType = { sound.Type.HEADER_X, sound.Type.HEADER_T,
                sound.Type.HEADER_M, sound.Type.HEADER_L, sound.Type.HEADER_Q,
                sound.Type.HEADER_K, sound.Type.EOH, sound.Type.START_CHORD,
                sound.Type.NOTE, sound.Type.NOTE, sound.Type.NOTE,
                sound.Type.END_CHORD, sound.Type.START_REPEAT, sound.Type.EOF };
        Collections.addAll(arraylistTypes, arrayType);
        assertEquals(tokensValue, arraylistValues);
        assertEquals(tokensType, arraylistTypes);
    }

    // Sample rest test file with comments (type and value)
    @Test
    public void restFileTest() throws FileNotFoundException,
            ABCLexer.LexerException {
        String filename = "test_samples/test_rest.abc";
        ABCLexer lexer = new ABCLexer(filename);
        ArrayList<ABCLexer.Token> tokens = lexer.getTokens();
        ArrayList<String> tokensValue = new ArrayList<String>();
        ArrayList<sound.Type> tokensType = new ArrayList<sound.Type>();
        int i;
        for (i = 0; i < tokens.size(); i++) {
            tokensValue.add(tokens.get(i).value);
            tokensType.add(tokens.get(i).type);
        }
        ArrayList<String> arraylistValues = new ArrayList<String>();
        String[] arrayValue = { "1", "Piece No.1", "6/4", "1/4", "140", "C",
                "", "C", "z", "E", "z", "F", "F", "|", "" };
        Collections.addAll(arraylistValues, arrayValue);
        ArrayList<sound.Type> arraylistTypes = new ArrayList<sound.Type>();
        sound.Type[] arrayType = { sound.Type.HEADER_X, sound.Type.HEADER_T,
                sound.Type.HEADER_M, sound.Type.HEADER_L, sound.Type.HEADER_Q,
                sound.Type.HEADER_K, sound.Type.EOH, sound.Type.NOTE,
                sound.Type.REST, sound.Type.NOTE, sound.Type.REST,
                sound.Type.NOTE, sound.Type.NOTE, sound.Type.BAR,
                sound.Type.EOF };
        Collections.addAll(arraylistTypes, arrayType);
        assertEquals(tokensValue, arraylistValues);
        assertEquals(tokensType, arraylistTypes);
    }

    // Sample tuplet test file with comments (type and value)
    @Test
    public void tupletFileTest() throws FileNotFoundException,
            ABCLexer.LexerException {
        String filename = "test_samples/test_tuplet.abc";
        ABCLexer lexer = new ABCLexer(filename);
        ArrayList<ABCLexer.Token> tokens = lexer.getTokens();
        ArrayList<String> tokensValue = new ArrayList<String>();
        ArrayList<sound.Type> tokensType = new ArrayList<sound.Type>();
        int i;
        for (i = 0; i < tokens.size(); i++) {
            tokensValue.add(tokens.get(i).value);
            tokensType.add(tokens.get(i).type);
        }
        ArrayList<String> arraylistValues = new ArrayList<String>();
        String[] arrayValue = { "1", "Piece No.1", "4/4", "1/4", "140", "C",
                "", "3", "C", "/", "C", "/", "C", "/", "2", "C", "/", "C", "/",
                "4", "C", "/", "C", "/", "C", "/", "C", "/", "3", "C", "/",
                "C", "/", "C", "/", "" };
        Collections.addAll(arraylistValues, arrayValue);
        ArrayList<sound.Type> arraylistTypes = new ArrayList<sound.Type>();
        sound.Type[] arrayType = { sound.Type.HEADER_X, sound.Type.HEADER_T,
                sound.Type.HEADER_M, sound.Type.HEADER_L, sound.Type.HEADER_Q,
                sound.Type.HEADER_K, sound.Type.EOH, sound.Type.TUPLET,
                sound.Type.NOTE, sound.Type.LENGTH, sound.Type.NOTE,
                sound.Type.LENGTH, sound.Type.NOTE, sound.Type.LENGTH,
                sound.Type.TUPLET, sound.Type.NOTE, sound.Type.LENGTH,
                sound.Type.NOTE, sound.Type.LENGTH, sound.Type.TUPLET,
                sound.Type.NOTE, sound.Type.LENGTH, sound.Type.NOTE,
                sound.Type.LENGTH, sound.Type.NOTE, sound.Type.LENGTH,
                sound.Type.NOTE, sound.Type.LENGTH, sound.Type.TUPLET,
                sound.Type.NOTE, sound.Type.LENGTH, sound.Type.NOTE,
                sound.Type.LENGTH, sound.Type.NOTE, sound.Type.LENGTH,
                sound.Type.EOF };
        Collections.addAll(arraylistTypes, arrayType);
        assertEquals(tokensValue, arraylistValues);
        assertEquals(tokensType, arraylistTypes);
    }

    // Sample octave file test with comments (type and value)

    @Test
    public void octaveFileTest() throws FileNotFoundException,
            ABCLexer.LexerException {
        String filename = "test_samples/test_octave.abc";
        ABCLexer lexer = new ABCLexer(filename);
        ArrayList<ABCLexer.Token> tokens = lexer.getTokens();
        ArrayList<String> tokensValue = new ArrayList<String>();
        ArrayList<sound.Type> tokensType = new ArrayList<sound.Type>();
        int i;
        for (i = 0; i < tokens.size(); i++) {
            tokensValue.add(tokens.get(i).value);
            tokensType.add(tokens.get(i).type);
        }
        ArrayList<String> arraylistValues = new ArrayList<String>();
        String[] arrayValue = { "1", "Piece No.1", "4/4", "1/4", "140", "C",
                "", "F", ",", "f", "'", "F", ",,", "f", "''", "" };
        Collections.addAll(arraylistValues, arrayValue);
        ArrayList<sound.Type> arraylistTypes = new ArrayList<sound.Type>();
        sound.Type[] arrayType = { sound.Type.HEADER_X, sound.Type.HEADER_T,
                sound.Type.HEADER_M, sound.Type.HEADER_L, sound.Type.HEADER_Q,
                sound.Type.HEADER_K, sound.Type.EOH, sound.Type.NOTE,
                sound.Type.OCTAVE, sound.Type.NOTE, sound.Type.OCTAVE,
                sound.Type.NOTE, sound.Type.OCTAVE, sound.Type.NOTE,
                sound.Type.OCTAVE, sound.Type.EOF };
        Collections.addAll(arraylistTypes, arrayType);
        assertEquals(tokensValue, arraylistValues);
        assertEquals(tokensType, arraylistTypes);
    }
}
