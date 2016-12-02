package testasr;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import asr.Expresion;

public class ExpresionTest {
    private static final Logger LOG = Logger.getLogger(ExpresionTest.class);

    private static final double DELTA = 0.000001;

    private static String[] inStr    = { null,   "",     "+32-3",    "-32-3",     "*32+2",    ",32"  } ;
    private static String[] toTextOK = { "null", "null", "32.0-3.0", "-32.0-3.0", "32.0+2.0", "null" } ;
    private static int[]    comidoOK = { 0,      0,      5,          5,           5,          1      } ;
    private static double[] evaluaOK = { 0.0,    0.0,    29.0,       -35.0,       34.0,       0.0    } ;


    @Test
    public final void testExpresion() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            Expresion o = new Expresion(str);
            String objToText = o.toText() ;
            LOG.trace("testExpresion "+str+" = "+objToText);
            assertTrue(str+" = "+objToText, toTextOK[i].equals(objToText));
        }
    }

    @Test
    public final void testComido() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            Expresion o = new Expresion(str);
            int objComido = o.comido();
            LOG.trace("testExpresion (comido) "+str+" = "+objComido);
            assertTrue(str, comidoOK[i] == objComido );
        }
    }

    @Test
    public final void testEvalua() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            Expresion o = new Expresion(str);
            double objEvalua = o.evalua();
            LOG.trace("testExpresion (evalua) "+str+" = "+objEvalua);
            assertEquals(str, objEvalua, evaluaOK[i], DELTA);
        }
    }

    @Test
    public final void testToText() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            Expresion o = new Expresion(str);
            String objToText = o.toText() ;
            LOG.trace("testExpresion (toText) "+str+" = "+objToText);
            assertTrue(str+" = "+objToText, toTextOK[i].equals(objToText));
        }
    }

    @Test
    public final void testToTextBoolean() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            Expresion o = new Expresion(str);
            String objToText = o.toText(true) ;
            LOG.trace("testExpresion (toTextBoolean(true)) "+str+" = "+objToText);
            assertTrue(str+" = "+objToText, toTextOK[i].equals(objToText));
        }
    }

    @Test
    public final void testTraza() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            Expresion o = new Expresion(str);
            o.traza("testExpresion - testTraza");
            String objToText = o.toText(true) ;
            LOG.trace("testExpresion (toText) "+str+" = "+objToText);
            assertTrue(str+" = "+objToText, toTextOK[i].equals(objToText));
        }
    }

    @Test
    public final void testPrint() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            Expresion o = new Expresion(str);
            o.print();
            String objToText = o.toText(true) ;
            LOG.trace("testExpresion (toText) "+str+" = "+objToText);
            assertTrue(str+" = "+objToText, toTextOK[i].equals(objToText));
        }
    }

    @Test
    public final void testPrintStringBoolean() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            Expresion o = new Expresion(str);
            o.print("prefix",false);
            String objToText = o.toText(true) ;
            LOG.trace("testExpresion (toText) "+str+" = "+objToText);
            assertTrue(str+" = "+objToText, toTextOK[i].equals(objToText));
        }
    }

}
