package testasr;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import asr.FactorConstante;

public class FactorConstanteTest {
    private static final Logger LOG = Logger.getLogger(FactorConstanteTest.class);

    private static final double DELTA = 0.000001;

    private static String[] inStr    = { "3,5/b", "3.5/b", null,  "",    "+2",  "*3",  ")+3" } ;
    private static String[] toTextOK = { "3.0",   "3.5",   "1.0", "1.0", "1.0", "1.0", "1.0" } ;
    private static int[]    comidoOK = {  1,       3,      0,     0,     0,     0,     1     } ;
    private static double[] evaluaOK = {  3.0,     3.5,    1.0,   1.0,   1.0,   1.0,   1.0   } ;


    @Test
    public final void testFactorConstante() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            FactorConstante o = new FactorConstante(str);
            String objToText = o.toText();
            LOG.trace("testFactorConstante "+str+" = "+objToText);
            assertTrue(str, toTextOK[i].equals(objToText));
        }
    }

    @Test
    public final void testComido() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            FactorConstante o = new FactorConstante(str);
            int objComido = o.comido() ;
            LOG.trace("testFactorConstante (comido) "+str+" = "+objComido);
            assertTrue(str, comidoOK[i] == objComido );
        }
    }

    @Test
    public final void testEvalua() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            FactorConstante o = new FactorConstante(str);
            double objEvalua = o.evalua();
            LOG.trace("testFactorConstante (evalua) "+str+" = "+objEvalua);
            assertEquals(str, objEvalua, evaluaOK[i], DELTA);
        }
    }

    @Test
    public final void testToText() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            FactorConstante o = new FactorConstante(str);
            String objToText = o.toText();
            LOG.trace("testFactorConstante (toText) "+str+" = "+objToText);
            assertTrue(str, toTextOK[i].equals(objToText));
        }
    }

}
