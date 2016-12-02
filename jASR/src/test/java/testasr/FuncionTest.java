package testasr;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import asr.Funcion;

public class FuncionTest {
    private static final Logger LOG = Logger.getLogger(FuncionTest.class);

    private static final double DELTA = 0.000001;

    private static String[] inStr   = { null,     "",       "random",   "sin",   "hypot",   "sin",   "d", "b", "c", "d", "b", "c", "d",  "a"  } ;
    private static String[] toText  = { "null()", "null()", "random()", "sin()", "hypot()", "sin()", "d()", "b()", "c()", "d()", "b()", "c()", "d()", "a()" };
    private static String[] getName = { "null",   "null",   "random",   "sin",   "hypot",   "sin",   "d", "b", "c", "d", "b", "c", "d",  "a"  };
    private static double[] inVal   = { 0.0,      0.0,      0.0,        0.0,     0.0,       0.0,     0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };

    @Test
    public final void testFuncion() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i];
            Funcion o = new Funcion(str);
            String objToText = o.getName();
            LOG.trace("testFuncion "+str+" = "+objToText);
            assertTrue("testFuncion", getName[i].equals(objToText));
        }
    }

    @Test
    public final void testEvalua() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i];
            Funcion o = new Funcion(str);
            double d = o.evalua(null, null);
            String objToText = o.getName();
            LOG.trace("testFuncion "+str+" = "+objToText);
            if ( str==null || str.equals("random")) {
                assertTrue("testFuncion (evalua)", d>=0.0 && d<1.0);
            }
            else {
                assertEquals("testFuncion (evalua)", inVal[i], d, DELTA);
            }
        }
    }

    @Test
    public final void testGetName() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i];
            Funcion o = new Funcion(str);
            String objToText = o.getName();
            LOG.trace("testFuncion "+str+" = "+objToText);
            assertTrue("testFuncion (getName) ", getName[i].equals(objToText));
        }
    }

    @Test
    public final void testToText() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i];
            Funcion o = new Funcion(str);
            String objToText = o.toText();
            LOG.trace("testFuncion "+str+" = "+objToText);
            assertTrue("testFuncion (toText) ", toText[i].equals(objToText));
        }
    }

    @Test
    public final void testPrint() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i];
            Funcion o = new Funcion(str);
            o.print("prefix");
            String objToText = o.toText();
            LOG.trace("testFuncion "+str+" = "+objToText);
            assertTrue("testFuncion (print) ", toText[i].equals(objToText));
        }
    }

}
