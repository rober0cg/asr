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
    
    @Test
    public final void testFactorVariable() {
        String str;
        String varToString;
        FactorVariable v;

        str=null;
        v = new FactorVariable(str);
        varToString = v.toText();
        LOG.trace(varToString);
        assertTrue(str+" = "+varToString, "null".equals(varToString));

        str="";
        v = new FactorVariable(str);
        varToString = v.toText();
        LOG.trace(varToString);
        assertTrue(str+" = "+varToString, "null".equals(varToString));

        str= ";v";
        v = new FactorVariable(str);
        varToString = v.toText();
        LOG.trace(varToString);
        assertTrue(str+" = "+varToString, "null".equals(varToString));

        str="+v";
        v = new FactorVariable(str);
        varToString = v.toText();
        LOG.trace(varToString);
        assertTrue(str+" = "+varToString, "null".equals(varToString));

        str= "/v";
        v = new FactorVariable(str);
        varToString = v.toText();
        LOG.trace(varToString);
        assertTrue(str+" = "+varToString, "null".equals(varToString));

        str="x";
        v = new FactorVariable(str);
        varToString = v.toText();
        LOG.trace(varToString);
        assertTrue(str+" = "+varToString, "x".equals(varToString));

        str="xx2+3";
        v = new FactorVariable(str);
        varToString = v.toText();
        LOG.trace(varToString);
        assertTrue(str+" = "+varToString, "xx2".equals(varToString));

        str="xxx3,xx2";
        v = new FactorVariable(str);
        varToString = v.toText();
        LOG.trace(varToString);
        assertTrue(str+" = "+varToString, "xxx3".equals(varToString));

    }

    @Test
    public final void testComido() {
        String str;
        int varComido;
        FactorVariable v;

        str="x/5";
        v = new FactorVariable(str);
        varComido = v.comido();
        LOG.trace("x.comido()=="+varComido);
        assertTrue(str+" (comido1) = ", varComido==1);

        str="xx2*23";
        v = new FactorVariable(str);
        varComido = v.comido();
        LOG.trace("xx2.comido()=="+varComido);
        assertTrue(str+" (comido2) = ", varComido==3);

        str="xxx3*(xx2)";
        v = new FactorVariable(str);
        varComido = v.comido();
        LOG.trace("xxx3.comido()=="+varComido);
        assertTrue(str+" (comido3) = ", varComido==4);

    }

    @Test
    public final void testEvalua() {
        String str;
        double varEvalua;
        FactorVariable v;

        str=null;
        v = new FactorVariable(str);
        Variables.set("x", 123.0);
        varEvalua = v.evalua();
        LOG.trace("x.evalua()=="+varEvalua);
        assertEquals(str+" (value1) = ", varEvalua, 0.0, DELTA);

        str="x+13";
        v = new FactorVariable(str);
        Variables.set("x", 123.0);
        varEvalua = v.evalua();
        LOG.trace("x.evalua()=="+varEvalua);
        assertEquals(str+" (value1) = ", varEvalua, 123.0, DELTA);

        str="xx2+33";
        v = new FactorVariable(str);
        Variables.set("xx2", -0.00001);
        varEvalua = v.evalua();
        LOG.trace("xx2.evalua()=="+varEvalua);
        assertEquals(str+" (value2) = ", varEvalua, -0.00001, DELTA);

        str="xxx3,xx2";
        v = new FactorVariable(str);
        Variables.set("xxx3",-12345678.00001);
        varEvalua = v.evalua();
        LOG.trace("xxx3.evalua()=="+varEvalua);
        assertEquals(str+" (value3) = ", varEvalua, -12345678.00001, DELTA);

        v = new FactorVariable("a");
        v = new FactorVariable("b");
        v = new FactorVariable("c");
        v = new FactorVariable("b");
        v = new FactorVariable("c");

        Iterator<Var> iv ;
        iv = Variables.getVariables();
        while ( iv.hasNext()) {
            LOG.info( "getVariables = " + iv.next().getName() );
        }
        
    }

    @Test
    public final void testToText() {
        String str ;
        String varToString ;
        FactorVariable v ;

        str = null ;
        v = new FactorVariable(str);
        varToString = v.toText() ;
        v.print("testToText");
        LOG.trace(str+" = "+varToString);
        assertTrue(str+" (text) = "+varToString, "null".equals(varToString));

        str = "sin(4-4)" ;
        v = new FactorVariable(str);
        varToString = v.toText() ;
        v.print("testToText");
        LOG.trace(str+" = "+varToString);
        assertTrue(str+" (text) = "+varToString, "sin".equals(varToString));
    }

    
}
