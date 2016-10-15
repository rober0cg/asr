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
public class FactorVariable implements FactorBase {
    private static final Logger LOG = Logger.getLogger(FactorVariable.class);

    int leidos=0;  // longitud caracteres leídos
    Variable var;
    
    public FactorVariable(String text) {
        LOG.trace("FactorVariable= "+text);
        if ( text==null || text.length()==0 ){
            leidos=0;
            var=null;
        }
        else {
            char c=text.charAt(0);
            if ( Util.isFinExpr(c) ) { // si [),;] avanzamos el final de expresion
                leidos=1; // avanzamos el final de expresion
                var=null;
            }
            else if ( Util.isOpTerm(c) ) { // si [+-] viene termino
                leidos=0;
                var=null;
            }
            else if ( Util.isOpFact(c) ) { // si [*/] viene factor
                leidos=0;
                var=null;
            }
            else { // localizamos nombre variable y la creamos
                leidos = factVar(text);
            }
        }
    }
    private int factVar (String text) {
        int n=Util.longNombreVarOrFunc(text);
        var = new Variable(text.substring(0,n));
        return n;
    }
    
    @Override
    public int comido() {
        return leidos;
    }

    @Override
    public double evalua() {
        double d ;
        if ( var==null ) {
            LOG.error("FactorVariable.evalua null ("+var+")");
            d=0.0;
        }
        else {
            d = var.evalua();
        }
        return d ;
    }

    public Variable getVariable(){
        return var;
    }
    
    @Override
    public String toText(){
        String str="";
        str = var.getName();
        return str;
    }

    @Override
    public void print(String pre){
        var.print(pre);
    }
}
