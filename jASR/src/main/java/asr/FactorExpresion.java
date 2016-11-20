package asr;

import org.apache.log4j.Logger;

/*
 * ASR
 * Analizador
 *  Sintáctico
 *   Recursivo
 *
 * EXPR = TERM [ + EXPR ]
 * TERM = FACT [ * TERM ]
 *
 * FACT = CONST | VAR | FUNC() | (EXPR)
 * 
 */
public class FactorExpresion implements FactorBase {
    private static final Logger LOG = Logger.getLogger(FactorExpresion.class);

    int leidos=0;  // longitud caracteres leídos
    Expresion expr;
    
    public FactorExpresion(String text) {
        LOG.trace("FactorExpresion= "+text);
        if ( text==null || text.length()==0 ){
            expr=null;
            leidos=0;
        }
        else {
            char c=text.charAt(0);
            if ( Util.isFinExpr(c) ) { // si [),;] avanzamos el final de expresion
                expr=null;
                leidos=1;
            }
            else if ( Util.isOpTerm(c) ) { // si [+-] viene termino
                leidos=0;
                expr=null;
            }
            else if ( Util.isOpFact(c) ) { // si [*/] viene factor
                leidos=0;
                expr=null;
            }
            else {
                expr = new Expresion(text.substring(0)); // 0 -> desde la primera posición
                leidos = expr.comido();
            }
        }
    }

    @Override
    public int comido() {
        return leidos;
    }
 
    @Override
    public double evalua() {
        double d;
        if ( expr == null ) {
            LOG.error("FactorExpresion.evalua null ("+expr+")");
            d = 1.0;
        }
        else {
            d = expr.evalua();
        }
        return d ;
    }

    @Override
    public String toText(){
        return "(" + expr.toText() + ")";
    }

    @Override
    public void print(String pre){
        LOG.trace(pre + "(");
        expr.print(pre, true);
        LOG.trace(pre + ")" );
    }
}
