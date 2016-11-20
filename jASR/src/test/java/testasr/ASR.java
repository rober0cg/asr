package testasr;

import java.util.Iterator;

import org.apache.log4j.Logger;

import asr.Expresion;
import asr.Variables;
import asr.Variables.Var;


public class ASR {
    private static final Logger LOG = Logger.getLogger(ASR.class);

    private ASR() {
    }

    public static void main(String[] args) {

        final String vx = "x";
        final String vy = "y";
        final String vz = "z";
        String text ;
        double d;
        
// ej1     (3-2)-3+5*(2+2)-2 
// ej2     -cos(x+y)+1-sin(-2*x/2) 
// ej3     100+50*sin(x/100)
// ej4     100+50*hypot(x/100,y)
// ej5     100+50*hypot(x/100,y)+80*sin(z)

        text = "(3-2)-3+5*(2+2)-2" ;

        Expresion expr = new Expresion(text);
        LOG.info("Expresion creada: " + text);

        expr.traza("expr e");

        Iterator<Var> iv ;
        
        iv = Variables.getVariables();
        while ( iv.hasNext()) {
            LOG.info( "getVariables = " + iv.next().getName() );
        }

        Variables.set(vx, 1.55);
        LOG.info("Variable x creada: " + Variables.toText(vx) );

        Variables.set(vy, 1.55);
        LOG.info("Variable y creada: " + Variables.toText(vy) );
      
        Variables.set(vy, -7.55);
        LOG.info("Variable y creada: " + Variables.toText(vz) );

        d = expr.evalua();
        LOG.info("main 1: " + expr.toText() + " = " + d );

        Variables.set(vx, 0.71);
        d = expr.evalua();
        LOG.info("main 2: " + text + " = " + d );

        Variables.set(vx, 0);
        d = expr.evalua();
        LOG.info("main 3: " + text + " = " + d );

        iv = Variables.getVariables();
        while ( iv.hasNext()) {
            LOG.info( "getVariables = " + iv.next().getName() );
        }
        
        return;
    }

}
