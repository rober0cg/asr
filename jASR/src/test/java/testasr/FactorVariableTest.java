package testasr;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import asr.FactorVariable;

public class FactorVariableTest {
    private static final Logger LOG = Logger.getLogger(FactorVariableTest.class);

    private static final double DELTA = 0.000001;
    
    @Test
    public final void testFactorVariable() {
        String str;
        String varToString;

        str="x";
        FactorVariable x = new FactorVariable(str);
        varToString = x.toText();
        LOG.trace(varToString);
        assertTrue(varToString, "x".equals(varToString));

        str="xx2+3";
        FactorVariable xx2 = new FactorVariable(str);
        varToString = xx2.toText();
        LOG.trace(varToString);
        assertTrue(varToString, "xx2".equals(varToString));

        str="xxx3,xx2";
        FactorVariable xxx3 = new FactorVariable(str);
        varToString = xxx3.toText();
        LOG.trace(varToString);
        assertTrue(varToString, "xxx3".equals(varToString));

    }

    @Test
    public final void testComido() {
        String str;
        int varComido;

        str="x/5";
        FactorVariable x = new FactorVariable(str);
        varComido = x.comido();
        LOG.trace("x.comido()=="+varComido);
        assertTrue(str, varComido==1);

        str="xx2*23";
        FactorVariable xx2 = new FactorVariable(str);
        varComido = xx2.comido();
        LOG.trace("xx2.comido()=="+varComido);
        assertTrue(str, varComido==3);

        str="xxx3*(xx2)";
        FactorVariable xxx3 = new FactorVariable(str);
        varComido = xxx3.comido();
        LOG.trace("xxx3.comido()=="+varComido);
        assertTrue(str, varComido==4);

    }

    @Test
    public final void testEvalua() {
        String str;
        double varEvalua;

        str="x+13";
        FactorVariable x = new FactorVariable(str);
        x.getVariable().set(123.0);
        varEvalua = x.evalua();
        LOG.trace("x.evalua()=="+varEvalua);
        assertEquals(str, varEvalua, 123.0, DELTA);

        str="xx2+33";
        FactorVariable xx2 = new FactorVariable(str);
        xx2.getVariable().set(-0.00001);
        varEvalua = xx2.evalua();
        LOG.trace("xx2.evalua()=="+varEvalua);
        assertEquals(str, varEvalua, -0.00001, DELTA);

        str="xxx3,xx2";
        FactorVariable xxx3 = new FactorVariable(str);
        xxx3.getVariable().set(-12345678.00001);
        varEvalua = xxx3.evalua();
        LOG.trace("xxx3.evalua()=="+varEvalua);
        assertEquals(str, varEvalua, -12345678.00001, DELTA);

    }

}
