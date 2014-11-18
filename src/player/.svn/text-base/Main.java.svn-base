package player;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import player.ABCLexer.LexerException;

import sound.ABCFile;
import sound.Pitch;
import sound.SequencePlayer;

/**
 * Main entry point of your application.
 */
public class Main {

	/**
	 * Plays the input file using Java MIDI API and displays
	 * header information to the standard output stream.
	 * 
	 * <p>Your code <b>should not</b> exit the application abnormally using
	 * System.exit()</p>
	 * 
	 * @param file the name of input abc file
	 * @throws LexerException 
	 * @throws FileNotFoundException 
	 * @throws ParseException 
	 * @throws InvalidMidiDataException 
	 * @throws MidiUnavailableException 
	 */
	public static void play(String file) throws FileNotFoundException, LexerException, ParseException, MidiUnavailableException, InvalidMidiDataException {
		// YOUR CODE HERE
	    //read file, lex, parse, get ABCFile from parser, call ABCFile.play() to get SequencePlayer
	    //print ABCFile.header.toString() to standard out
	    ABCLexer lexer = new ABCLexer(file);
	    ArrayList<ABCLexer.Token> input = lexer.getTokens();
	    Parser parser = new Parser();
	    ABCFile abcfile = parser.abcFileParser(input);
	    System.out.println(abcfile.header.toString());
	    abcfile.play().play();
	}
}
