package sound;
import org.junit.Test;
import junit.framework.Assert;

public class UtilsTest {
    
    @Test
    public void GCDTest() {
        Assert.assertEquals(5, Utils.gcd(15, 35));
        Assert.assertEquals(1, Utils.gcd(5, 7)); 
        Assert.assertEquals(10, Utils.gcd(10, 0));
    }
    
    @Test
    public void LCMTest() {
        Assert.assertEquals(105, Utils.lcm(15, 35));
        Assert.assertEquals(5, Utils.lcm(5,0));
        Assert.assertEquals(35, Utils.lcm(5, 7));
        Assert.assertEquals(5, Utils.lcm(5,1));
    }
}
