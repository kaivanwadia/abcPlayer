package sound;
import java.util.HashMap;
import java.util.Map.Entry;

import player.ParseException;


public class KeySignatures {
    private HashMap<String, Key> keySigs = new HashMap<String, Key>();
    private HashMap<Key,String> reverseKeySigs = new HashMap<Key,String>();
    
    private static KeySignatures sig;
    
    /**
     * Gets the singleton KeySignatures object.
     * @return the key signatures object
     */
    public static KeySignatures getKeySignatures() {
        if(sig==null) {
            sig = new KeySignatures();
        }
        return sig;
    }
    
    /**
     * Gets the string representation of the given key.
     * @param k The key whose representation we want. If the key is non-standard, returns "Unknown key"
     * @return the string representation of the given key
     */
    public String getStringForKey(Key k) {
        if(reverseKeySigs.containsKey(k)) {
            return reverseKeySigs.get(k);
        }
        return "Unknown key";
    }
    
    /**
     * Gets the key from its string representation.
     * @param str the string form of the key
     * @return the key represented by the string
     * @throws ParseException if the key string is not recognized
     */
    public Key getKeyFromString(String str) throws ParseException {
        if(keySigs.containsKey(str)) {
            return keySigs.get(str);
        }
        throw new ParseException("Unknown key string");
    }
    
    /**
     * Instantiates a new key signatures object.
     */
    private KeySignatures() {
        keySigs.put("Ab", Key.A_FLAT_MAJOR);
        keySigs.put("Abm", Key.A_FLAT_MINOR);
        keySigs.put("A", Key.A_MAJOR);
        keySigs.put("Am", Key.A_MINOR);
        keySigs.put("A#", Key.A_SHARP_MINOR);
        keySigs.put("Bb", Key.B_FLAT_MAJOR);
        keySigs.put("Bbm", Key.B_FLAT_MINOR);
        keySigs.put("B", Key.B_MAJOR);
        keySigs.put("Bm", Key.B_MINOR);
        keySigs.put("Cb", Key.C_FLAT_MAJOR);
        keySigs.put("C", Key.C_MAJOR);
        keySigs.put("Cm", Key.C_MINOR);
        keySigs.put("C#", Key.C_SHARP_MAJOR);
        keySigs.put("C#m", Key.C_SHARP_MINOR);
        keySigs.put("Db", Key.D_FLAT_MAJOR);
        keySigs.put("D", Key.D_MAJOR);
        keySigs.put("Dm", Key.D_MINOR);
        keySigs.put("D#m", Key.D_SHARP_MINOR);
        keySigs.put("Eb", Key.E_FLAT_MAJOR);
        keySigs.put("Ebm", Key.E_FLAT_MINOR);
        keySigs.put("E", Key.E_MAJOR);
        keySigs.put("Em", Key.E_MINOR);
        keySigs.put("F", Key.F_MAJOR);
        keySigs.put("Fm", Key.F_MINOR);
        keySigs.put("F#", Key.F_SHARP_MAJOR);
        keySigs.put("F#m", Key.F_SHARP_MINOR);
        keySigs.put("Gb", Key.G_FLAT_MAJOR);
        keySigs.put("G", Key.G_MAJOR);
        keySigs.put("Gm", Key.G_MINOR);
        keySigs.put("G#m", Key.G_SHARP_MINOR);
        
        for(Entry<String,Key> x : keySigs.entrySet()) {
            reverseKeySigs.put(x.getValue(), x.getKey());
        }
        
    }
    
}
