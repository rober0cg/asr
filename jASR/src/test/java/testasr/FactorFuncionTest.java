package testasr;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import asr.FactorFuncion;

public class FactorFuncionTest {
    private static final Logger LOG = Logger.getLogger(FactorFuncionTest.class);

    private static final double DELTA = 0.000001;
    
    @Test
    public final void testFactorFuncion() {
        String str ;
        String funcToString ;
        FactorFuncion f ;
        
        str = null ;
        f = new FactorFuncion(str);
        funcToString = f.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertTrue(str+" = "+funcToString, "null()".equals(funcToString));

        str = ";(2)" ;
        f = new FactorFuncion(str);
        funcToString = f.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertTrue(str+" = "+funcToString, "null()".equals(funcToString));

        str = "-(3)" ;
        f = new FactorFuncion(str);
        funcToString = f.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertTrue(str+" = "+funcToString, "null()".equals(funcToString));

        str = "/(4,5)" ;
        f = new FactorFuncion(str);
        funcToString = f.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertTrue(str+" = "+funcToString, "null()".equals(funcToString));

        str = "rand()" ;
        f = new FactorFuncion(str);
        funcToString = f.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertTrue(str+" = "+funcToString, "rand()".equals(funcToString));

        str = "sin(1-1)" ;
        f = new FactorFuncion(str);
        funcToString = f.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertTrue(str+" = "+funcToString, "sin(1.0-1.0)".equals(funcToString));

        str = "hypot(2,2)" ;
        f = new FactorFuncion(str);
        funcToString = f.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertTrue(str+" = "+funcToString, "hypot(2.0,2.0)".equals(funcToString));
    }

    @Test
    public final void testComido() {
        String str ;
        String funcToString ;
        int funcComido ;
        FactorFuncion f ;

        str = "sin(2-2)" ;
        f = new FactorFuncion(str);
        funcComido = f.comido();
        funcToString = f.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertTrue(str+" (comido) = "+funcToString, 8==funcComido);

        str = "sin(2-2])" ;
        f = new FactorFuncion(str);
        funcComido = f.comido();
        funcToString = f.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertTrue(str+" (comido) = "+funcToString, 9==funcComido);

    }

    @Test
    public final void testEvalua() {
        String str ;
        String funcToString ;
        double funcValue ;
        FactorFuncion f ;

        str = null ;
        f = new FactorFuncion(str);
        funcValue = f.evalua();
        funcToString = f.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertEquals(str+" (value) = "+funcToString, funcValue, 0.0, DELTA);

        str = "sin(3-3)" ;
        f = new FactorFuncion(str);
        funcValue = f.evalua();
        funcToString = f.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertEquals(str+" (value) = "+funcToString, funcValue, 0.0, DELTA);

        str = "cos(1-1)" ;
        f = new FactorFuncion(str);
        funcValue = f.evalua();
        funcToString = f.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertEquals(str+" (value) = "+funcToString, funcValue, 1.0, DELTA);

        str = "hypot(3,4)" ;
        f = new FactorFuncion(str);
        funcValue = f.evalua();
        funcToString = f.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertEquals(str+" (value) = "+funcToString, funcValue, 5.0, DELTA);


    }

    @Test
    public final void testToText() {
        String str ;
        String funcToString ;
        FactorFuncion f ;

        str = "sin(4-4)" ;
        f = new FactorFuncion(str);
        funcToString = f.toText() ;
        f.print("testToText");
        LOG.trace(str+" = "+funcToString);
        assertTrue(str+" (text) = "+funcToString, "sin(4.0-4.0)".equals(funcToString));
    }

}
