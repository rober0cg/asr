package testasr;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import asr.Variable;

public class VariableTest {
    private static final Logger LOG = Logger.getLogger(VariableTest.class);

    private static final String X ="x";
    private static final String XX2 = "xx2+3";
    private static final double DELTA = 0.000001;

    @Test
    public final void testVariableString() {
        String str;
        String strToString;

        str=X;
        Variable x = new Variable(str);
        strToString = x.toString();
        LOG.trace(strToString);
        LOG.trace(strToString.substring(0, 5));
        assertTrue(strToString, strToString.substring(0, 1).equals(X));

        str=XX2;
        Variable xx2 = new Variable(str);
        strToString = xx2.toString();
        LOG.trace(strToString);
        LOG.trace(strToString.substring(0, 5));
        assertTrue(strToString, strToString.substring(0, 5).equals(XX2));

    }

    @Test
    public final void testVariableStringDouble() {
        String str;
        String strToString;
        double dEvalua;

        str=X;
        Variable x = new Variable(str,33.0);
        strToString = x.toString();
        dEvalua = x.evalua();
        assertEquals(strToString, dEvalua, 33.0, DELTA);

        str=XX2;
        Variable xx2 = new Variable(str,-123);
        strToString = xx2.toString();
        dEvalua = xx2.evalua();
        assertEquals(strToString, dEvalua, -123.0, DELTA);
    }

    @Test
    public final void testSet() {
        String str;
        String strToString;
        double dEvalua;

        str=X;
        Variable x = new Variable(str);
        strToString = x.toString();
        x.set(33.0);
        dEvalua = x.evalua();
        assertEquals(strToString, dEvalua, 33.0, DELTA);

        str=XX2;
        Variable xx2 = new Variable(str);
        strToString = xx2.toString();
        xx2.set(-123.0);
        dEvalua = xx2.evalua();
        assertEquals(strToString, dEvalua, -123.0, DELTA);

    }

    @Test
    public final void testEvalua() {
        String str;
        String strToString;
        double dEvalua;

        str=X;
        Variable x = new Variable(str);
        x.set(33.0);
        strToString = x.toString();
        dEvalua = x.evalua();
        assertEquals(strToString, dEvalua, 33.0, DELTA);

        str=XX2;
        Variable xx2 = new Variable(str);
        xx2.set(-123.0);
        strToString = xx2.toString();
        dEvalua = xx2.evalua();
        assertEquals(strToString, dEvalua, -123.0, DELTA);

    }
}
