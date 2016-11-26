package testasr;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.junit.Test;

import asr.FactorVariable;
import asr.Variables;
import asr.Variables.Var;

public class FactorVariableTest {
    private static final Logger LOG = Logger.getLogger(FactorVariableTest.class);

    private static final double DELTA = 0.000001;
    
    private static String[] inStr    = { null,   "",     ";v+2", "+v3",  "/v",   "x", "xx2+3", "x3,x2"  } ;
    private static String[] toTextOK = { "null", "null", "null", "null", "null", "x", "xx2",   "x3"     } ;
    private static int[]    comidoOK = {  0,     0,      1,      0,      0,      1,   3,       2        } ;
    private static double[] inEvalua = {  3.0,   3.5,    9.9,    4.5,    6.7,    1.1, 2.2,    -1.2345   } ;
    private static double[] evaluaOK = {  0.0,   0.0,    0.0,    0.0,    0.0,    1.1, 2.2,    -1.2345   } ;
    
    @Test
    public final void testFactorVariable() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            FactorVariable v = new FactorVariable(str);
            String objToText = v.toText();
            LOG.trace("testFactorVariable "+str+" = "+objToText);
            assertTrue(str, toTextOK[i].equals(objToText));
        }
    }

    @Test
    public final void testComido() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            FactorVariable v = new FactorVariable(str);
            int objComido = v.comido() ;
            LOG.trace("testFactorVariable (comido) "+str+" = "+objComido);
            assertTrue(str, comidoOK[i] == objComido );
        }
    }

    @Test
    public final void testEvalua() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            FactorVariable v = new FactorVariable(str);
            Variables.set(toTextOK[i], inEvalua[i]);
            double objEvalua = v.evalua();
            LOG.trace("testFactorVariable (evalua) "+str+" = "+objEvalua);
            assertEquals(str, objEvalua, evaluaOK[i], DELTA);
        }
    }

    @Test
    public final void testToText() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i] ;
            FactorVariable v = new FactorVariable(str);
            String objToText = v.toText();
            LOG.trace("testFactorVariable (toText) "+str+" = "+objToText);
            assertTrue(str, toTextOK[i].equals(objToText));
        }
    }

    @Test
    public final void testVariables() {
        String[] masVars = {"a", "b", "c", "d", "b", "c", "d", "b", "c", "d", "a" } ;
        for ( String str : masVars ) {
            FactorVariable v;
            v = new FactorVariable(str);
            String objToText = v.toText();
            LOG.trace("testVariables "+str+" = "+objToText);
        }

        Iterator<Var> iv ;
        iv = Variables.getVariables();
        int i = 0 ;
        while ( iv.hasNext()) {
            String str = iv.next().getName();
            LOG.info( "getVariables = " + str );
            assertTrue("testGetVariables", str.equals(Variables.getName(i)));
            i++;
        }
    }
    
}
