package testasr;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import asr.Factor;

public class FactorTest {
    private static final Logger LOG = Logger.getLogger(FactorTest.class);

    private static final double DELTA = 0.000001;

    private static String[] inStr    = { "8+2/4",  "",       null,     "+32",    "*32",  ",32",    "6.666+2/4", "(2)-2", "sin(0)+2", "v+2", "y[]"    } ;
    private static String[] toTextOK = { "8.0",    "(null)", "(null)", "(null)", "32.0", "(null)", "6.666",     "(2.0)", "sin(0.0)", "v",   "(null)" } ;
    private static int[]    comidoOK = {  1,       0,        0,        0,        3,      1,        5,           3,       6,          1,     1        } ;
    private static double[] evaluaOK = {  8.0,     1.0,      1.0,      1.0,      32.0,   1.0,      6.666,       2.0,     0.0,        0.0,   1.0      } ;


    @Test
    public final void testFactor() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            Factor o = new Factor(str);
            String objToText = o.toText();
            LOG.trace("testFactor "+str+" = "+objToText);
            assertTrue(str, toTextOK[i].equals(objToText));
        }
    }

    @Test
    public final void testComido() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            Factor o = new Factor(str);
            int objComido = o.comido() ;
            LOG.trace("testFactor (comido) "+str+" = "+objComido);
            assertTrue(str, comidoOK[i] == objComido );
        }
    }

    @Test
    public final void testEvalua() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            Factor o = new Factor(str);
            double objEvalua = o.evalua() ;
            LOG.trace("testFactor (evalua) "+str+" = "+objEvalua);
            assertEquals(str, objEvalua, evaluaOK[i], DELTA);
        }
    }

    @Test
    public final void testToText() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            Factor o = new Factor(str);
            String objToText = o.toText();
            LOG.trace("testFactor (toText) "+str+" = "+objToText);
            assertTrue(str, toTextOK[i].equals(objToText));
        }
    }
    
    @Test
    public final void testPrint() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            Factor o = new Factor(str);
            o.print("prefix");
            String objToText = o.toText() ;
            LOG.trace("testExpresion (toText) "+str+" = "+objToText);
            assertTrue(str+" = "+objToText, toTextOK[i].equals(objToText));
        }
    }


}
