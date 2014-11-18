package player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sound.Type;

public class ABCLexer {

    private String input; // input taken from file
    private ArrayList<Token> tokens; // list of Tokens to be iterated through
    private int i = 0; // used for finding tokens
    private Type[] types; // array of types to be used in assigning tokens
    private boolean validInput; // used to determine if our input is valid
    private boolean stripWhitespace; // used to determine if value needs to be stripped of whitespace
    private BufferedReader reader; // reader for reading in abc files
    private Matcher matcher; // matcher used to find tokens
    private Pattern pattern; // pattern matcher uses to find tokens

    /**
     * Token in the stream.
     */
    public static class Token {
        public final Type type;
        public final String value;

        public Token(Type type, String value) {
            this.type = type;
            this.value = value;
        }

        public Token(Type type) {
            this(type, null);
        }

        public String toString() {
            return value;
        }
    }

    @SuppressWarnings("serial")
    public static class LexerException extends Exception {
        public LexerException(String msg) {
            super(msg);
        }
    }

    // Header token types
    private static final Type[] HEADER_TYPES = { Type.HEADER_C, // composer
            Type.HEADER_L, // length
            Type.HEADER_M, // meter
            Type.HEADER_Q, // tempo
            Type.HEADER_V, // voice
            Type.HEADER_K, // key
            Type.HEADER_X, // index
            Type.HEADER_T, // title
            Type.COMMENT // comment section start
    };

    // Music token types
    private static final Type[] MUSIC_TYPES = { Type.VOICE, // signifies a
                                                            // specific music
                                                            // voice
            Type.BAR, // signifies a musically sound grouping of notes
            Type.NOTE, // basic playable unit consisting of a pitch
            Type.REST, // basic playable unit consisting of no pitch
            Type.OCTAVE, // lowers or raises the octave of a note
            Type.ACCIDENTAL, // raises or lowers the pitch of a note
            Type.START_CHORD, // signifies the start of a chord of notes
            Type.END_CHORD, // signifies the end of a chord of notes
            Type.TUPLET, // a group of notes to be played with a defined rhythm
            Type.LENGTH, // a unit of length for a playable object (NOTE, REST,
                         // CHORD, TUPLET)
            Type.START_REPEAT, // signifies the start a grouping of notes to be
                               // repeated
            Type.END_REPEAT, // signifies the end a grouping of notes to be
                             // repeated
            Type.ALT_ENDING, // signifies an alternative ending to a repeated
                             // section
            Type.COMMENT // comment section start
    };

    // Regex matching next token in the HEADER section.
    private static final Pattern HEADER_REGEX = Pattern.compile(
    // header
            "(C:.+)|" // HEADER_C
                    + "(L:[0-9]+/[0-9]+|L:[0-9]+|L:[0-9]+/|L:/[0-9]+|L:/)|" // HEADER_L
                    + "(M:[0-9]+/[0-9]+|M:[0-9]+|M:[0-9]+/|M:/[0-9]+|M:/|M:C|M:C\\|)|" // HEADER_M
                    + "(Q:\\d+)|" // HEADER_Q
                    + "(V:\\w+)|" // HEADER_V
                    + "(K:[A-Ga-g][#b ]?m?)|" // HEADER_K
                    + "(X:\\d+)|" // HEADER_X
                    + "(T:[^%]+)|" // HEADER_T
                    + "(%)" // COMMENT

            );

    // Regex matching next token in the MUSIC section.
    private static final Pattern MUSIC_REGEX = Pattern.compile(
    // music
            "(V:\\w+)|" // VOICE
                    + "(\\|(?![\\]\\|:]))|" // BAR
                    + "([A-Ga-g])|" // NOTE
                    + "(z)|" // REST
                    // + "('|,)|" // earlier OCTAVE, changed to clumping octaves
                    // together
                    + "('+|\\,+)|" // OCTAVE
                    + "(_{1,2}|=|\\^{1,2})|" // ACCIDENTAL
                    + "(\\[(?![\\d\\|]))|" // START_CHORD
                    + "(\\])|" // END_CHORD
                    + "(\\([0-9])|" // TUPLET
                    + "([0-9]+/[0-9]+|[0-9]+/|[0-9]+|/[0-9]+|/)|" // LENGTH
                    + "(\\|:(?!\\|)|\\|\\]|\\[\\||\\|\\|)|" // START_REPEAT
                    + "(:\\|)|" // END_REPEAT
                    + "(\\[\\d)|" // ALT_ENDING
                    + "(%)" // COMMENT

            );

    /**
     * ABCLexer: takes ABCFile and tokenizes it for parser Lexes through header
     * and music component of ABCFile separately Whitespace stripped except in
     * Composer and Title (where it is needed) Only specified characters allowed
     * in file
     * 
     * @param fileName
     *            The name of the abc file to tokenize
     * 
     * @throws LexerException
     * @throws FileNotFoundException
     * 
     */

    public ABCLexer(String filename) throws LexerException,
            FileNotFoundException {
        this.tokens = new ArrayList<Token>();
        this.reader = new BufferedReader(new FileReader(filename));
        this.pattern = HEADER_REGEX;
        this.types = HEADER_TYPES;
        String in;

        try {
            // preliminary check for valid tokens
            // strip all spaces, comments, valid tokens and then see if
            // something remains in the string
            // if string != "", then must be an invalidInput
            while ((in = reader.readLine()) != null) {
                // ignore blank lines
                if (in.equals(""))
                    continue;
                this.input = in;
                char c = in.charAt(0);
                // if first character is C or T, i.e. if composer or title,
                // don't strip white spaces
                if (c == 'C' || c == 'T')
                    this.stripWhitespace = false;
                else
                    this.stripWhitespace = true;
                // replace all spaces with empty string
                String inWithoutValid = this.input.replaceAll("\\s+", "");
                // remove comments
                if (inWithoutValid.contains("%"))
                    inWithoutValid = inWithoutValid.substring(0,inWithoutValid.indexOf('%'));
                inWithoutValid = inWithoutValid.replaceAll(
                        pattern.toString(), "");
                // at the end, inputMinusValidTokens should be an empty string
                // ==> validInput
                if (inWithoutValid.equals(""))
                    validInput = true;
                else
                    validInput = false;
                // for sections of the file where white spaces need to be
                // stripped
                if (stripWhitespace)
                    in = in.replaceAll("\\s+", "");
                i = 0;
                this.matcher = pattern.matcher(in);
                // lex each line at a time
                Token t = lexLine(in);
                while (t.type != Type.EOL && t.type != Type.COMMENT) {
                    // add tokens lexed so far
                    tokens.add(t);
                    // if reached end of header, then change pattern and types
                    // to those for music
                    if (t.type == Type.HEADER_K) {
                        tokens.add(new Token(Type.EOH, ""));
                        types = MUSIC_TYPES;
                        pattern = MUSIC_REGEX;
                        break;
                    }
                    t = lexLine(in);
                }
            }

        } catch (Exception e) {
            throw new LexerException(e.getMessage());
        }
        // add something here EOF
        Token EOFToken = new Token(Type.EOF, "");
        tokens.add(EOFToken);
    }

    /**
     * lexLine: Given a String of characters, finds the next Token in the String
     * and returns it. If it reaches the end of a line, it returns the
     * corresponding Token (Type.EOL).
     * 
     * @param String
     *            line - a string of characters that will be lexed through for
     *            the next Token
     * @return the matched Token, or endOfLine Token (Type.EOL)
     * @throws LexerException
     *             if the input is not valid else if no Token is found
     */
    private Token lexLine(String line) throws LexerException {

        // Check for reaching end of line
        if (i >= line.length())
            return new Token(Type.EOL, "");

        // Check for valid input
        if (!validInput) {
            throw new LexerException("Invalid character(s) in file.");
        }

        // If no token found
        if (!matcher.find(i)) {
            throw new LexerException("Token mismatch at " + line.substring(i));
        }

        // Obtain the part of the string that matches the regex
        String value = matcher.group(0);
        // Advance state
        i = matcher.end();

        // Each set of () in TOKEN_REGEX is a "capturing group",
        // thus the regex matcher remembers where it found a match and returns
        // it
        // with the method group(i), where i=1 is the first set of parens.
        // as group(0) is reserved to denote the entire pattern
        // Maximum one of the groups can match, so look for a non-null group.
        for (int i = 1; i < matcher.groupCount() + 1; i++) {
            if (matcher.group(i) != null) {
                // since i is 1-based, use i-1 to obtain token type for given
                // pattern
                Type t = types[i - 1];
                // For Tuplet, obtain the value to be the number of notes in the
                // tuplet.
                // For Alt_Ending, obtain the value to be which alternate ending
                // we are at
                if (t.equals(Type.TUPLET) || t.equals(Type.ALT_ENDING))
                    value = value.substring(1);
                // Strip value of header tags, like C:, L:, etc.
                else if (t.equals(Type.HEADER_C)
                        || t.equals(Type.HEADER_Q)
                        || t.equals(Type.HEADER_L)
                        || t.equals(Type.HEADER_M)
                        || t.equals(Type.HEADER_V)
                        || t.equals(Type.HEADER_K)
                        || t.equals(Type.HEADER_T)
                        || t.equals(Type.HEADER_X)
                        || t.equals(Type.VOICE))
                    value = value.substring(2);
                // else return token with type and value as seen
                return (new Token(t, value));
            }
        }

        // Matcher matched the expression, but not the parens => this part of
        // code should not be reached
        throw new AssertionError("Something is wrong: this part of the code mustn't be reached");

    }

    /**
     * getTokens: Getter method for lexed tokens
     * 
     * @return an arrayList of tokens
     */
    public ArrayList<Token> getTokens() {
        return this.tokens;
    }

}
