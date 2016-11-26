package testasr;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

public class FuncionTest {
    private static final Logger LOG = Logger.getLogger(FuncionTest.class);

    private static final double DELTA = 0.000001;

    private static String[] inStr   = { null,   "",     "a",     "b", "c", "d", "b", "c", "d", "b", "c", "d",  "a"  } ;
    private static String[] toText  = { "null", "null", "a=0.0", "b=0.0", "c=0.0", "d=0.0", "b=0.0", "c=0.0", "d=0.0", "b=0.0", "c=0.0", "d=0.0",  "a=0.0" };
    private static String[] getName = { "null", "null", "a",     "b", "c", "d", "b", "c", "d", "b", "c", "d",  "a"  };
    private static double[] inVal   = { 0.0,    0.0,    1.0,     2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0 };

    @Test
    public final void testFuncion() {
        assertTrue("testFuncion", true);
    }

    @Test
    public final void testEvalua() {
        assertTrue("testEvalua", true);
    }

    @Test
    public final void testGetName() {
        assertTrue("testGetName", true);
    }

    @Test
    public final void testToText() {
        assertTrue("testToText", true);
    }

    @Test
    public final void testPrint() {
        assertTrue("testPrint", true);
    }

}
