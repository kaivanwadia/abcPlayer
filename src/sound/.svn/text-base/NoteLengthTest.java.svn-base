package sound;
import org.junit.Assert;
import org.junit.Test;

public class NoteLengthTest {

    @Test
    public void gcdTest() {
        NoteLength l = new NoteLength(4,8);
        Assert.assertEquals(1,l.numerator);
        Assert.assertEquals(2,l.denominator);
    }
    
    @Test
    public void addTest() {
        NoteLength l1 = new NoteLength(1,6);
        NoteLength l2 = new NoteLength(1,3);
        NoteLength res = l1.add(l2);
        Assert.assertEquals(1,res.numerator);
        Assert.assertEquals(2,res.denominator);
        
        res = l2.add(l1);
        Assert.assertEquals(1,res.numerator);
        Assert.assertEquals(2,res.denominator);
    }
    
    @Test
    public void addTestComplex() {
        NoteLength l1 = new NoteLength(3,5);
        NoteLength l2 = new NoteLength(7,19);
        NoteLength res = l1.add(l2);
        Assert.assertEquals(92,res.numerator);
        Assert.assertEquals(95,res.denominator);
        
        res = l2.add(l1);
        Assert.assertEquals(92,res.numerator);
        Assert.assertEquals(95,res.denominator);
    }
    
    @Test
    public void invertTest() {
        NoteLength l = new NoteLength(3,5);
        l = l.reciprocal();
        Assert.assertEquals(5, l.numerator);
        Assert.assertEquals(3, l.denominator);
    }
    
    @Test
    public void addTestLCM() {
        NoteLength l1 = new NoteLength(3,5);
        NoteLength l2 = new NoteLength(7,15);
        NoteLength res = l1.add(l2);
        Assert.assertEquals(16,res.numerator);
        Assert.assertEquals(15,res.denominator);
        
        res = l2.add(l1);
        Assert.assertEquals(16,res.numerator);
        Assert.assertEquals(15,res.denominator);
    }
    
    @Test
    public void multiplyTestGCD() {
        NoteLength l1 = new NoteLength(3,5);
        NoteLength l2 = new NoteLength(7,15);
        NoteLength res = l1.multiply(l2);
        Assert.assertEquals(7,res.numerator);
        Assert.assertEquals(25,res.denominator);
        
        res = l2.multiply(l1);
        Assert.assertEquals(7,res.numerator);
        Assert.assertEquals(25,res.denominator);
    }
    
    @Test
    public void addZeroTest() {
        NoteLength l1 = new NoteLength(1,6);
        NoteLength l2 = new NoteLength();
        NoteLength res = l1.add(l2);
        Assert.assertEquals(1,res.numerator);
        Assert.assertEquals(6,res.denominator);
        
        res = l2.add(l1);
        Assert.assertEquals(1,res.numerator);
        Assert.assertEquals(6,res.denominator);
    }
    
    @Test
    public void add2ZeroTest() {
        NoteLength l1 = new NoteLength();
        NoteLength l2 = new NoteLength();
        NoteLength res = l1.add(l2);
        Assert.assertEquals(0,res.numerator);
        Assert.assertEquals(1,res.denominator);
        
        res = l2.add(l1);
        Assert.assertEquals(0,res.numerator);
        Assert.assertEquals(1,res.denominator);
    }
    
    @Test
    public void equalsTest() {
        NoteLength l1 = new NoteLength(1,4);
        NoteLength l2 = new NoteLength(2,8);
        Assert.assertTrue(l1.equals(l2));
        Assert.assertTrue(l2.equals(l1));
    }
    
    @Test
    public void comparableTestEqual() {
        NoteLength l1 = new NoteLength(1,4);
        NoteLength l2 = new NoteLength(2,8);
        Assert.assertEquals(0,l1.compareTo(l2));
        Assert.assertEquals(0,l2.compareTo(l1));
    }
    
    @Test
    public void comparableTestNotEqual() {
        NoteLength l1 = new NoteLength(2,3);
        NoteLength l2 = new NoteLength(5,8);
        Assert.assertEquals(-1,l1.compareTo(l2));
        Assert.assertEquals(1,l2.compareTo(l1));
    }
    
    
    
}
