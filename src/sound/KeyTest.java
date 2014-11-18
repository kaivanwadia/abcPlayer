package sound;
import org.junit.Test;
import junit.framework.Assert;

public class KeyTest {
    
    public char[] notes = new char[] {'c','d','e','f','g','a','b'};
    
    @Test
    public void addAllSharpsKeyTest() {
        int i,j,acc;
        
        for(acc=1;acc<3;acc++) {
            for(i=0;i<notes.length;i++) {
                Key k = Key.C_MAJOR.addAccidental(NoteName.createFromChar(notes[i]), acc);
                
                for(j=0;j<notes.length;j++) {
                    //System.out.println(acc+" "+i+" "+j);
                    Note n = k.getTransposedNote(new Note(notes[j]));
                    if(i==j) {
                        Assert.assertEquals(acc,n.getAccidental());
                    } else {
                        Assert.assertEquals(0,n.getAccidental());
                    }
                }
            }
        }
    }
    
    @Test
    public void addAllNaturalsFromSharpsKeyTest() {
        int i,j;
        
        for(i=0;i<notes.length;i++) {
            Key k = Key.C_SHARP_MAJOR.addNatural(NoteName.createFromChar(notes[i]));
            
            for(j=0;j<notes.length;j++) {
                //System.out.println(" "+i+" "+j);
                Note n = k.getTransposedNote(new Note(notes[j]));
                if(i==j) {
                    Assert.assertEquals(0,n.getAccidental());
                } else {
                    Assert.assertEquals(1,n.getAccidental());
                }
            }
        }
    }
    
    @Test
    public void addAllNaturalsFromFlatsKeyTest() {
        int i,j;
        
        for(i=0;i<notes.length;i++) {
            Key k = Key.C_FLAT_MAJOR.addNatural(NoteName.createFromChar(notes[i]));
            
            for(j=0;j<notes.length;j++) {
                //System.out.println(" "+i+" "+j);
                Note n = k.getTransposedNote(new Note(notes[j]));
                if(i==j) {
                    Assert.assertEquals(0,n.getAccidental());
                } else {
                    Assert.assertEquals(-1,n.getAccidental());
                }
            }
        }
    }
    
    @Test
    public void addAllFlatsKeyTest() {
        int i,j,acc;
        for(acc=-1;acc>-3;acc--) {
            for(i=0;i<notes.length;i++) {
                Key k = Key.C_MAJOR.addAccidental(NoteName.createFromChar(notes[i]), acc);
                
                for(j=0;j<notes.length;j++) {
                    //System.out.println(acc+" "+i+" "+j);
                    Note n = k.getTransposedNote(new Note(notes[j]));
                    if(i==j) {
                        Assert.assertEquals(acc,n.getAccidental());
                    } else {
                        Assert.assertEquals(0,n.getAccidental());
                    }
                }
            }
        }
    }
    
    @Test
    public void addAllAccidentalsViaNoteKeyTest() {
        int i,j,acc;
        
        for(acc=1;acc<3;acc++) {
            for(i=0;i<notes.length;i++) {
                Key k = Key.C_MAJOR;
                
                for(j=0;j<notes.length;j++) {
                    //System.out.println(i+" "+j);
                    Note t = new Note(notes[i],acc);
                    Note n = k.addNoteModifiersToKey(t).getTransposedNote(new Note(notes[j]));
                    if(i==j) {
                        Assert.assertEquals(acc,n.getAccidental());
                    } else {
                        Assert.assertEquals(0,n.getAccidental());
                    }
                }
            }   
        }
        
        for(acc=-1;acc>-3;acc--) {
            for(i=0;i<notes.length;i++) {
                Key k = Key.C_MAJOR;
                
                for(j=0;j<notes.length;j++) {
                    //System.out.println(i+" "+j);
                    Note t = new Note(notes[i],acc);
                    Note n = k.addNoteModifiersToKey(t).getTransposedNote(new Note(notes[j]));
                    if(i==j) {
                        Assert.assertEquals(acc,n.getAccidental());
                    } else {
                        Assert.assertEquals(0,n.getAccidental());
                    }
                }
            }   
        }
        
    }
    
    @Test
    public void addAllNaturalsFromFlatsViaNoteKeyTest() {
        int i,j;
        for(i=0;i<notes.length;i++) {
            Key k = Key.C_FLAT_MAJOR;
            
            for(j=0;j<notes.length;j++) {
                //System.out.println(i+" "+j);
                Note t = new Note(notes[i],true);
                Note n = k.addNoteModifiersToKey(t).getTransposedNote(new Note(notes[j]));
                if(i==j) {
                    Assert.assertEquals(0,n.getAccidental());
                } else {
                    Assert.assertEquals(-1,n.getAccidental());
                }
            }
        }
    }
    
    @Test
    public void addAllNaturalsFromSharpsViaNoteKeyTest() {
        int i,j;
        
        for(i=0;i<notes.length;i++) {
            Key k = Key.C_SHARP_MAJOR;
            
            for(j=0;j<notes.length;j++) {
                //System.out.println(i+" "+j);
                Note t = new Note(notes[i],true);
                Note n = k.addNoteModifiersToKey(t).getTransposedNote(new Note(notes[j]));
                if(i==j) {
                    Assert.assertEquals(0,n.getAccidental());
                } else {
                    Assert.assertEquals(1,n.getAccidental());
                }
            }
        }
    }
    
    @Test
    public void addESharpKeyTest() {
        Key k = Key.C_MAJOR.addAccidental(NoteName.E, 1);
        Note n = k.getTransposedNote(new Note(notes[2]));
        Assert.assertEquals(1, n.getAccidental());
    }
    
}
