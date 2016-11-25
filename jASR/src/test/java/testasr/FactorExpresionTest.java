package testasr;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import asr.FactorExpresion;

public class FactorExpresionTest {
    private static final Logger LOG = Logger.getLogger(FactorExpresionTest.class);

    private static final double DELTA = 0.000001;

    @Test
    public final void testFactorExpresion() {
        String str ;
        String exprToString ;
        FactorExpresion e ;
        
        str = "8+2/4" ;
        e = new FactorExpresion(str);
        exprToString = e.toText() ;
        LOG.trace(str+" = "+exprToString);
        assertTrue(str+" = "+exprToString, "(8.0+2.0/4.0)".equals(exprToString));

        str = null ;
        e = new FactorExpresion(str);
        exprToString = e.toText() ;
        LOG.trace(str+" = "+exprToString);
        assertTrue(str+" = "+exprToString, "(null)".equals(exprToString));

        str = "" ;
        e = new FactorExpresion(str);
        exprToString = e.toText() ;
        LOG.trace(str+" = "+exprToString);
        assertTrue(str+" = "+exprToString, "(null)".equals(exprToString));

        str = "+32" ;
        e = new FactorExpresion(str);
        exprToString = e.toText() ;
        LOG.trace(str+" = "+exprToString);
        assertTrue(str+" = "+exprToString, "(null)".equals(exprToString));

        str = "*32" ;
        e = new FactorExpresion(str);
        exprToString = e.toText() ;
        LOG.trace(str+" = "+exprToString);
        assertTrue(str+" = "+exprToString, "(null)".equals(exprToString));

        str = ",32" ;
        e = new FactorExpresion(str);
        exprToString = e.toText() ;
        LOG.trace(str+" = "+exprToString);
        assertTrue(str+" = "+exprToString, "(null)".equals(exprToString));

    }

    @Test
    public final void testComido() {
        String str ;
        String exprToString ;
        int exprComido ;
        FactorExpresion e ;
        
        str = "6+2/4" ;
        e = new FactorExpresion(str);
        exprComido = e.comido() ;
        exprToString = e.toText() ;
        LOG.trace(str+" (comido) = "+exprComido);
        assertTrue(str+" = "+exprToString, 5 == exprComido);

    }

    @Test
    public final void testEvalua() {
        String str ;
        String exprToString ;
        double exprValue ;
        FactorExpresion e ;

        str = null ;
        e = new FactorExpresion(str);
        exprToString = e.toText() ;
        exprValue = e.evalua() ;
        LOG.trace(str+" (value) = "+exprValue);
        assertEquals(str+" = "+exprToString, exprValue, 1.0, DELTA);

        str = "2+2/4" ;
        e = new FactorExpresion(str);
        exprToString = e.toText() ;
        exprValue = e.evalua() ;
        LOG.trace(str+" (value) = "+exprValue);
        assertEquals(str+" = "+exprToString, exprValue, 2.5, DELTA);

        str = "(2+2)/4" ;
        e = new FactorExpresion(str);
        exprToString = e.toText() ;
        exprValue = e.evalua() ;
        LOG.trace(str+" (value) = "+exprValue);
        assertEquals(str+" = "+exprToString, exprValue, 1.0, DELTA);

    }

    @Test
    public final void testToText() {
        String str ;
        String exprToString ;
        FactorExpresion e ;

        str = null ;
        e = new FactorExpresion(str);
        exprToString = e.toText() ;
        e.print("testToText");
        LOG.trace(str+" = "+exprToString);
        assertTrue(str+" = "+exprToString, "(null)".equals(exprToString));

        str = "x+2/4" ;
        e = new FactorExpresion(str);
        exprToString = e.toText() ;
        e.print("testToText");
        LOG.trace(str+" = "+exprToString);
        assertTrue(str+" = "+exprToString, "(x+2.0/4.0)".equals(exprToString));
    }

}
