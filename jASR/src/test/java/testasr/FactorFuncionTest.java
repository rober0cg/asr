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

        str = "rand()" ;
        FactorFuncion f0 = new FactorFuncion(str);
        funcToString = f0.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertTrue(str+" = "+funcToString, "rand()".equals(funcToString));

        str = "sin(1-1)" ;
        FactorFuncion f1 = new FactorFuncion(str);
        funcToString = f1.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertTrue(str+" = "+funcToString, "sin(1.0-1.0)".equals(funcToString));

        str = "hypot(2,2)" ;
        FactorFuncion f2 = new FactorFuncion(str);
        funcToString = f2.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertTrue(str+" = "+funcToString, "hypot(2.0,2.0)".equals(funcToString));
    }

    @Test
    public final void testComido() {
        String str ;
        String funcToString ;
        int funcComido ;

        str = "sin(2-2)" ;
        FactorFuncion f1 = new FactorFuncion(str);
        funcComido = f1.comido();
        funcToString = f1.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertTrue(str+" (comido) = "+funcToString, 8==funcComido);

    }

    @Test
    public final void testEvalua() {
        String str ;
        String funcToString ;
        double funcValue ;

        str = "sin(3-3)" ;
        FactorFuncion f1 = new FactorFuncion(str);
        funcValue = f1.evalua();
        funcToString = f1.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertEquals(str+" (value) = "+funcToString, funcValue, 0.0, DELTA);

        str = "cos(1-1)" ;
        FactorFuncion f2 = new FactorFuncion(str);
        funcValue = f2.evalua();
        funcToString = f2.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertEquals(str+" (value) = "+funcToString, funcValue, 1.0, DELTA);

        str = "hypot(3,4)" ;
        FactorFuncion f3 = new FactorFuncion(str);
        funcValue = f3.evalua();
        funcToString = f3.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertEquals(str+" (value) = "+funcToString, funcValue, 5.0, DELTA);


    }

    @Test
    public final void testToText() {
        String str ;
        String funcToString ;

        str = "sin(4-4)" ;
        FactorFuncion f1 = new FactorFuncion(str);
        funcToString = f1.toText() ;
        LOG.trace(str+" = "+funcToString);
        assertTrue(str+" (text) = "+funcToString, "sin(4.0-4.0)".equals(funcToString));
    }

}
