package testasr;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import asr.Termino;

public class TerminoTest {
    private static final Logger LOG = Logger.getLogger(TerminoTest.class);

    private static final double DELTA = 0.000001;

    private static String[] inStr    = { null,   "",     "+32+2",  "-32+2",  "*32*3",     ",32"   } ;
    private static String[] toTextOK = { "null", "null", "32.0",   "32.0",   "32.0*3.0",  "null"  } ;
    private static int[]    comidoOK = { 0,      0,      3,        3,        5,           1       } ;
    private static double[] evaluaOK = { 1.0,    1.0,    32.0,     -32.0,    96.0,        1.0     } ;


    @Test
    public final void testTermino() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            Termino o = new Termino(str);
            String objToText = o.toText() ;
            LOG.trace("testTermino "+str+" = "+objToText);
            assertTrue(str+" = "+objToText, toTextOK[i].equals(objToText));
        }
    }

    @Test
    public final void testComido() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            Termino o = new Termino(str);
            int objComido = o.comido();
            LOG.trace("testTermino (comido) "+str+" = "+objComido);
            assertTrue(str, comidoOK[i] == objComido );
        }
    }

    @Test
    public final void testEvalua() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            Termino o = new Termino(str);
            double objEvalua = o.evalua();
            LOG.trace("testTermino (evalua) "+str+" = "+objEvalua);
            assertEquals(str, objEvalua, evaluaOK[i], DELTA);
        }
    }

    @Test
    public final void testToText() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            Termino o = new Termino(str);
            String objToText = o.toText() ;
            LOG.trace("testTermino (toText) "+str+" = "+objToText);
            assertTrue(str+" = "+objToText, toTextOK[i].equals(objToText));
        }
    }

    @Test
    public final void testPrint() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            Termino o = new Termino(str);
            o.print("prefix") ;
            String objToText = o.toText() ;
            LOG.trace("testTermino (print) "+str+" = "+objToText);
            assertTrue(str+" = "+objToText, toTextOK[i].equals(objToText));
        }
    }

}
