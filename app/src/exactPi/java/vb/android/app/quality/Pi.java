package vb.android.app.quality;

import java.math.BigInteger ;

import vb.android.app.quality.pi.PiGenerator;

/**
 * Very slow class to compute PI.
 */
public final class Pi implements PiGenerator {
    private static final BigInteger TWO = BigInteger.valueOf(2) ;
    private static final BigInteger THREE = BigInteger.valueOf(3) ;
    private static final BigInteger FOUR = BigInteger.valueOf(4) ;
    private static final BigInteger SEVEN = BigInteger.valueOf(7) ;

    public Pi() {

    }

    @Override
    public double calcPiDigits(int maxDigits){
        BigInteger q = BigInteger.ONE ;
        BigInteger r = BigInteger.ZERO ;
        BigInteger t = BigInteger.ONE ;
        BigInteger k = BigInteger.ONE ;
        BigInteger n = BigInteger.valueOf(3) ;
        BigInteger l = BigInteger.valueOf(3) ;
        BigInteger nn, nr;
        double pi = 0;
        boolean first = true;
        int digits = 0;
        BigInteger divider = BigInteger.ONE;
        while(digits < maxDigits){
            if(FOUR.multiply(q).add(r).subtract(t).compareTo(n.multiply(t)) == -1){
                pi += n.doubleValue() / divider.doubleValue();
                divider = divider.multiply(new BigInteger("10"));
                if(first){System.out.print(".") ; first = false ;}
                nr = BigInteger.TEN.multiply(r.subtract(n.multiply(t))) ;
                n = BigInteger.TEN.multiply(THREE.multiply(q).add(r)).divide(t).subtract(BigInteger.TEN.multiply(n)) ;
                q = q.multiply(BigInteger.TEN) ;
                r = nr ;
                digits++;
            }else{
                nr = TWO.multiply(q).add(r).multiply(l) ;
                nn = q.multiply((SEVEN.multiply(k))).add(TWO).add(r.multiply(l)).divide(t.multiply(l)) ;
                q = q.multiply(k) ;
                t = t.multiply(l) ;
                l = l.add(TWO) ;
                k = k.add(BigInteger.ONE) ;
                n = nn ;
                r = nr ;
            }
        }
        return pi;
    }
}