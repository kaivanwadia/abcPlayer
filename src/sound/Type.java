package sound;

/**
 * Token type for ABCLexer. 
 */
public enum Type {
    
    // header
    HEADER_C,       // composer
    HEADER_L,       // length
    HEADER_M,       // meter
    HEADER_Q,       // tempo
    HEADER_V,       // voice
    HEADER_K,       // key
    HEADER_X,       // index
    HEADER_T,       // title
    
    // music
    OCTAVE,         // lowers or raises the octave of a note
    ACCIDENTAL,     // raises or lowers the pitch of a note
    NOTE,           // basic playable unit consisting of a pitch
    REST,           // basic playable unit consisting of no pitch
    START_CHORD,    // denotes the start of a chord of notes
    END_CHORD,      // denotes the end of a chord of notes
    TUPLET,         // a group of notes to be played with a specific defined rhythm
    BAR,            // denotes a grouping of notes
    LENGTH,         // a unit of length for a playable object (NOTE, REST, CHORD, TUPLET)
    START_REPEAT,   // denotes the start a grouping of notes to be repeated
    END_REPEAT,     // denotes the end a grouping of notes to be repeated
    ALT_ENDING,     // denotes an alternative ending to a repeat section
    VOICE,          // denotes a specific voice of music
    
    // general
    COMMENT,        // comment section start 
    EOH,            // end of header
    EOF,            // end of file
    EOL             // end of line

}
