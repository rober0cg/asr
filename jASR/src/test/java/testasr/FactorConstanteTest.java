package testasr;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import asr.FactorConstante;

public class FactorConstanteTest {
    private static final Logger LOG = Logger.getLogger(FactorConstanteTest.class);

    private static final double DELTA = 0.000001;

    @Test
    public final void testFactorConstante() {
        String str ;
        String consToString ;
        
        str = "3,5/b" ;
        FactorConstante c1 = new FactorConstante(str);
        consToString = c1.toText();
        LOG.trace(consToString);
        assertTrue(consToString, "3.0".equals(consToString));

        str = "3.5/b" ;
        FactorConstante c2 = new FactorConstante(str);
        consToString = c2.toText();
        LOG.trace(consToString);
        assertTrue(consToString, "3.5".equals(consToString));
    }

    @Test
    public final void testComido() {
        String str ;
        int consComido ;

        str = "3,5/b";
        FactorConstante c1 = new FactorConstante(str);
        consComido = c1.comido() ;
        assertTrue(str+" (comido) = "+consComido, 1 == consComido );

        str = "3.5/b";
        FactorConstante c2 = new FactorConstante(str);
        consComido = c2.comido() ;
        assertTrue(str+" (comido) = "+consComido, 3 == consComido );
    }

    @Test
    public final void testEvalua() {
        String str ;
        String consToString ;
        double consEvalua ;

        str = "3,5/b0";
        FactorConstante c1 = new FactorConstante(str);
        consEvalua = c1.evalua() ;
        consToString = c1.toText();
        assertEquals(str+" (value) = "+consToString, consEvalua, 3.0, DELTA);

        str = "3.5/b1";
        FactorConstante c2 = new FactorConstante(str);
        consEvalua = c2.evalua() ;
        consToString = c2.toText();
        assertEquals(str+" (value) = "+consToString, consEvalua, 3.5, DELTA);
    }

    @Test
    public final void testToText() {
        String str ;
        String consToString ;
        
        str = "3,5/cc" ;
        FactorConstante c1 = new FactorConstante(str);
        consToString = c1.toText();
        LOG.trace(consToString);
        assertTrue(consToString+" (text) ", "3.0".equals(consToString));

        str = "3.5/cc" ;
        FactorConstante c2 = new FactorConstante(str);
        consToString = c2.toText();
        LOG.trace(consToString);
        assertTrue(consToString+" (text) ", "3.5".equals(consToString));
    }

}
