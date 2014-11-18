package sound;

public class Utils {
    /**
     * Calculates the GCD of two positive integers.
     * @param a The first integer. Must be non-negative.
     * @param b The second integer. Must be non-negative.
     * @return Returns the GCD of the two integers. gcd(0,0) is defined to be 1.
     */
    public static int gcd(int a,int b) {
        if(a<b) return gcd(b,a);
        if(b==0) return a==0 ? 1 : a;
        if(b==1) return 1;
        return gcd(a%b,b);
    }
    
    /**
     * Calculates the LCM of two positive integers.
     * @param a The first integer. Must be non-negative. a/gcd(a,b)*b must not overflow a signed 32bit integer
     * @param b The second integer. Must be non-negative. a/gcd(a,b)*b must not overflow a signed 32bit integer
     * @return Returns the LCM of the two integers. Special cases: lcm(0,0)=1; lcm(a,0)=a; lcm(0,b)=b.
     */
    public static int lcm(int a,int b) {
        if(a==0 && b==0) return 1;
        if(a==0) return b;
        if(b==0) return a;
        return (a/gcd(a,b))*b;
    }
    
}
