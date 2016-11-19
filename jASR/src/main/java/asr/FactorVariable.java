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
    int idx; // indice en la lista de Var's
    static Variables vars ; // lista de variables

    public FactorVariable(String text) {
        LOG.trace("FactorVariable= "+text);
        if ( text==null || text.length()==0 ){
            leidos=0;
            idx=-1;
        }
        else {
            char c=text.charAt(0);
            if ( Util.isFinExpr(c) ) { // si [),;] avanzamos el final de expresion
                leidos=1; // avanzamos el final de expresion
                idx=-1;
            }
            else if ( Util.isOpTerm(c) ) { // si [+-] viene termino
                leidos=0;
                idx=-1;
            }
            else if ( Util.isOpFact(c) ) { // si [*/] viene factor
                leidos=0;
                idx=-1;
            }
            else { // localizamos nombre variable y la creamos
                leidos = factVar(text);
            }
        }
    }
    private int factVar (String text) {
        int n=Util.longNombreVarOrFunc(text);
        idx = Variables.addVar(text.substring(0,n));
        return n;
    }
    
    @Override
    public int comido() {
        return leidos;
    }

    @Override
    public double evalua() {
        double d=0.0 ;
        if ( idx==-1 ) {
            LOG.error("FactorVariable.evalua null ("+idx+")");
        }
        else {
            d = Variables.evalua(idx);
        }
        return d ;
    }

    @Override
    public String toText(){
        return Variables.getName(idx);
    }

    @Override
    public void print(String pre){
        LOG.trace(pre + Variables.getName(idx));
    }
}
