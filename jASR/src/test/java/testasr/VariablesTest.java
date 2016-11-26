package testasr;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.junit.Test;

import asr.Variables;
import asr.Variables.Var;

public class VariablesTest {
    private static final Logger LOG = Logger.getLogger(VariablesTest.class);

    private static final double DELTA = 0.000001;

    private static String[] inStr   = { null,   "",     "a",     "b", "c", "d", "b", "c", "d", "b", "c", "d",  "a"  } ;
    private static String[] toText  = { "null", "null", "a=0.0", "b=0.0", "c=0.0", "d=0.0", "b=0.0", "c=0.0", "d=0.0", "b=0.0", "c=0.0", "d=0.0",  "a=0.0" };
    private static String[] getName = { "null", "null", "a",     "b", "c", "d", "b", "c", "d", "b", "c", "d",  "a"  };
    private static double[] inVal   = { 0.0,    0.0,    1.0,     2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0 };


    @Test
    public final void testAddVar() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i];
            int idx = Variables.addVar(str);
            String objToText = Variables.getName(idx);
            LOG.trace("testAddVar "+str+" = "+objToText+"("+idx+")");
            assertTrue("testAddVar", getName[i].equals(objToText));
        }
    }

    @Test
    public final void testSetStringDouble() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i];
            int idx = Variables.addVar(str);
            Variables.set(str, inVal[i]);
            double d = Variables.evalua(str);
            String objToText = Variables.toText(idx);
            LOG.trace("testSetStringDouble "+str+" = "+objToText+"("+idx+")");
            assertEquals("testSetStringDouble",  inVal[i], d, DELTA);
        }
    }

    @Test
    public final void testSetIntDouble() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i];
            int idx = Variables.addVar(str);
            Variables.set(idx, inVal[i]);
            double d = Variables.evalua(idx);
            String objToText = Variables.toText(idx);
            LOG.trace("testSetIntDouble "+str+" = "+objToText+"("+idx+")");
            assertEquals("testSetIntDouble",  inVal[i], d, DELTA);
        }
    }

    @Test
    public final void testEvaluaString() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i];
            int idx = Variables.addVar(str);
            Variables.set(str, inVal[i]);
            double d = Variables.evalua(str);
            String objToText = Variables.toText(idx);
            LOG.trace("testEvaluaString "+str+" = "+objToText+"("+idx+")");
            assertEquals("testEvaluaString",  inVal[i], d, DELTA);
        }
    }

    @Test
    public final void testEvaluaInt() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i];
            int idx = Variables.addVar(str);
            Variables.set(idx, inVal[i]);
            double d = Variables.evalua(idx);
            String objToText = Variables.toText(idx);
            LOG.trace("testEvaluaInt "+str+" = "+objToText+"("+idx+")");
            assertEquals("testEvaluaInt",  inVal[i], d, DELTA);
        }
    }

    @Test
    public final void testGetName() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i];
            int idx = Variables.addVar(str);
            String objToText = Variables.getName(idx);
            LOG.trace("testGetName "+str+" = "+objToText+"("+idx+")");
            assertTrue("testGetName", getName[i].equals(objToText));
        }
    }

    @Test
    public final void testPrint() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i];
            int idx = Variables.addVar(str);
            Variables.print("prefix",idx);
            String objToText = Variables.getName(idx);
            LOG.trace("testPrint "+str+" = "+objToText+"("+idx+")");
            assertTrue("testPrint", getName[i].equals(objToText));
        }
    }

    @Test
    public final void testToStringInt() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i];
            int idx = Variables.addVar(str);
            String objToText = Variables.toString(idx);
            LOG.trace("testToStringInt "+str+" = "+objToText+"("+idx+")");
            assertTrue("testToStringInt", toText[i].equals(objToText));
        }
    }

    @Test
    public final void testToTextInt() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i];
            int idx = Variables.addVar(str);
// pte implementar la validación del método toText(int)
            String objToText = Variables.getName(idx);
            LOG.trace("testToTextInt "+str+" = "+objToText+"("+idx+")");
            assertTrue("testToTextInt", getName[i].equals(objToText));
        }
    }

    @Test
    public final void testToTextString() {
        for ( int i=0 ; i< inStr.length ; i++ ) {
            String str = inStr[i];
            int idx = Variables.addVar(str);
// pte implementar la validación del método toText(String)
            String objToText = Variables.getName(idx);
            LOG.trace("testToTextString "+str+" = "+objToText+"("+idx+")");
            assertTrue("testToTextString", getName[i].equals(objToText));
        }
    }

    @Test
    public final void testGetVariables() {
        Iterator<Var> iv ;
        iv = Variables.getVariables();
        int i = 0 ;
        while ( iv.hasNext()) {
            String str = iv.next().getName();
            LOG.info( "testGetVariables = " + str );
            assertTrue("testGetVariables", str.equals(Variables.getName(i)));
            i++;
        }
    }

}
