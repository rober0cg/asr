package testasr;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import asr.FactorFuncion;

public class FactorFuncionTest {
    private static final Logger LOG = Logger.getLogger(FactorFuncionTest.class);

    private static final double DELTA = 0.000001;
    
    private static String[] inStr    = { null,     "",       ";(2)",   "-(3)",   "/(4.5)", "random()", "sin(1-1)",     "hypot(3,4)",     "multi(1,2,3)",   "raro(1;)"  };
    private static String[] toTextOK = { "null()", "null()", "null()", "null()", "null()", "random()", "sin(1.0-1.0)", "hypot(3.0,4.0)", "multi(1.0,2.0)", "raro(1.0)" };
    private static int[]    comidoOK = {  0,       0,        1,        0,        0,        8,          8,              10,               10,               8           };
    private static double[] evaluaOK = {  0.0,     0.0,      0.0,      0.0,      0.0,      1.0,        0.0,            5.0,              0.0,              0.0         };

    
    @Test
    public final void testFactorFuncion() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            FactorFuncion f = new FactorFuncion(str);
            String objToText = f.toText();
            LOG.trace("testFactorFuncion "+str+" = "+objToText);
            assertTrue(str, toTextOK[i].equals(objToText));
        }
    }

    @Test
    public final void testComido() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            FactorFuncion f = new FactorFuncion(str);
            int objComido = f.comido() ;
            LOG.trace("testFactorFuncion "+str+" = "+objComido);
            assertTrue(str, comidoOK[i] == objComido );
        }
    }

    @Test
    public final void testEvalua() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            FactorFuncion f = new FactorFuncion(str);
            double objEvalua = f.evalua();
            LOG.trace("testFactorFuncion (evalua) "+str+" = "+objEvalua);
            if ( str=="random()" ) {
                assertTrue(str, objEvalua>=0.0 && objEvalua<1.0 );
            }
            else {
                assertEquals(str, objEvalua, evaluaOK[i], DELTA);
            }
        }
    }

    @Test
    public final void testToText() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            FactorFuncion o = new FactorFuncion(str);
            String objToText = o.toText();
            LOG.trace("testFactorFuncion (toText) "+str+" = "+objToText);
            assertTrue(str, toTextOK[i].equals(objToText));
        }
    }

    @Test
    public final void testPrint() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            FactorFuncion o = new FactorFuncion(str);
            o.print("prefix");
            String objToText = o.toText();
            LOG.trace("testFactorFuncion (print) "+str+" = "+objToText);
            assertTrue(str, toTextOK[i].equals(objToText));
        }
    }

}
