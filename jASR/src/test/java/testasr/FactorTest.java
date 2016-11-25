package testasr;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import asr.Factor;

public class FactorTest {
    private static final Logger LOG = Logger.getLogger(FactorTest.class);

    private static final double DELTA = 0.000001;

    @Test
    public final void testFactor() {
        String str;
        String factToString;
        Factor f;
        
        str = "8+2/4" ;
        f = new Factor(str);
        factToString = f.toText() ;
        LOG.trace(str+" = "+factToString);
        assertTrue(str+" = "+factToString, "8.0".equals(factToString));

        str = null ;
        f = new Factor(str);
        factToString = f.toText() ;
        LOG.trace(str+" = "+factToString);
        assertTrue(str+" = "+factToString, "(null)".equals(factToString));

        str = "" ;
        f = new Factor(str);
        factToString = f.toText() ;
        LOG.trace(str+" = "+factToString);
        assertTrue(str+" = "+factToString, "(null)".equals(factToString));

        str = "+32" ;
        f = new Factor(str);
        factToString = f.toText() ;
        LOG.trace(str+" = "+factToString);
        assertTrue(str+" = "+factToString, "(null)".equals(factToString));

        str = "*32" ;
        f = new Factor(str);
        factToString = f.toText() ;
        LOG.trace(str+" = "+factToString);
        assertTrue(str+" = "+factToString, "32.0".equals(factToString));

        str = ",32" ;
        f = new Factor(str);
        factToString = f.toText() ;
        LOG.trace(str+" = "+factToString);
        assertTrue(str+" = "+factToString, "(null)".equals(factToString));

    }

    @Test
    public final void testEvalua() {
        String str ;
        String factToString ;
        double factValue ;
        Factor f ;

        str = null ;
        f = new Factor(str);
        factToString = f.toText() ;
        factValue = f.evalua() ;
        LOG.trace(str+" (value) = "+factValue);
        assertEquals(str+" = "+factToString, factValue, 1.0, DELTA);

        str = "3,5+2,5" ;
        f = new Factor(str);
        factToString = f.toText() ;
        factValue = f.evalua() ;
        LOG.trace(str+" (value) = "+factValue);
        assertEquals(str+" = "+factToString, factValue, 3.0, DELTA);

        str = "3.5+2.5" ;
        f = new Factor(str);
        factToString = f.toText() ;
        factValue = f.evalua() ;
        LOG.trace(str+" (value) = "+factValue);
        assertEquals(str+" = "+factToString, factValue, 3.5, DELTA);
    }

    @Test
    public final void testComido() {
        String str ;
        String factToString ;
        int factComido ;
        Factor f ;
        
        str = "6+2/4" ;
        f = new Factor(str);
        factComido = f.comido() ;
        factToString = f.toText() ;
        LOG.trace(str+" (comido) = "+factComido);
        assertTrue(str+" = "+factToString, 1 == factComido);

        str = "6.666+2/4" ;
        f = new Factor(str);
        factComido = f.comido() ;
        factToString = f.toText() ;
        LOG.trace(str+" (comido) = "+factComido);
        assertTrue(str+" = "+factToString, 5 == factComido);
    }

    @Test
    public final void testToText() {
        String str;
        String factToString;
        Factor f;
        
        str = "8+2/4" ;
        f = new Factor(str);
        factToString = f.toText() ;
        LOG.trace(str+" = "+factToString);
        assertTrue(str+" = "+factToString, "8.0".equals(factToString));

        str = null ;
        f = new Factor(str);
        factToString = f.toText() ;
        LOG.trace(str+" = "+factToString);
        assertTrue(str+" = "+factToString, "(null)".equals(factToString));

        str = "" ;
        f = new Factor(str);
        factToString = f.toText() ;
        LOG.trace(str+" = "+factToString);
        assertTrue(str+" = "+factToString, "(null)".equals(factToString));
    }

}
