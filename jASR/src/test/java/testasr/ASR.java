package testasr;

import org.apache.log4j.Logger;

import asr.*;


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
        
//        "(3-2)-3+5*(2+2)-2" 
//        "-cos(x+y)+1-sin(-2*x/2)" 
//        "100+50*sin(x/100)"
//      "100+50*hypot(x/100,y)"

        text = "100+50*hypot(x/100,y)+80*sin(z)" ;

        Expresion expr = new Expresion(text);
        LOG.info("Expresion creada: " + text);

        expr.traza("expr e");

        Variable x = new Variable(vx,1.55);
        LOG.info("Variable x creada: " + x.toText() );

        Variable y = new Variable(vy);
        y.set(1.55);
        LOG.info("Variable y creada: " + y.toText() );
      
        Variable z = new Variable(vz);
        z.set(-7.55);
        LOG.info("Variable y creada: " + z.toText() );

        d = expr.evalua();
        LOG.info("main 1: " + expr.toText() + " = " + d );

        x.set(0.71);
        d = expr.evalua();
        LOG.info("main 2: " + text + " = " + d );

        x.set(0);
        d = expr.evalua();
        LOG.info("main 3: " + text + " = " + d );

        return;
    }

}
