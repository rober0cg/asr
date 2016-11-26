package testasr;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import asr.FactorExpresion;

public class FactorExpresionTest {
    private static final Logger LOG = Logger.getLogger(FactorExpresionTest.class);

    private static final double DELTA = 0.000001;

    private static String[] inStr    = { "8+2/4",         null,     "",       "+32",    "*32",    ",32"    } ;
    private static String[] toTextOK = { "(8.0+2.0/4.0)", "(null)", "(null)", "(null)", "(null)", "(null)" } ;
    private static int[]    comidoOK = {  5,              0,        0,        0,        0,        1        } ;
    private static double[] evaluaOK = {  8.5,            1.0,      1.0,      1.0,      1.0,      1.0      } ;

    
    @Test
    public final void testFactorExpresion() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            FactorExpresion o = new FactorExpresion(str);
            String objToText = o.toText() ;
            LOG.trace("testFactorExpresion "+str+" = "+objToText);
            assertTrue(str+" = "+objToText, toTextOK[i].equals(objToText));
        }
    }

    @Test
    public final void testComido() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            FactorExpresion o = new FactorExpresion(str);
            int objComido = o.comido();
            LOG.trace("testFactorExpresion (comido) "+str+" = "+objComido);
            assertTrue(str, comidoOK[i] == objComido );
        }
    }

    @Test
    public final void testEvalua() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            FactorExpresion o = new FactorExpresion(str);
            double objEvalua = o.evalua();
            LOG.trace("testFactorExpresion (evalua) "+str+" = "+objEvalua);
            assertEquals(str, objEvalua, evaluaOK[i], DELTA);
        }
    }

    @Test
    public final void testToText() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            FactorExpresion o = new FactorExpresion(str);
            String objToText = o.toText() ;
            LOG.trace("testFactorExpresion (toText) "+str+" = "+objToText);
            assertTrue(str, toTextOK[i].equals(objToText));
        }
    }

}
