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
public class FactorConstante implements FactorBase {
    private static final Logger LOG = Logger.getLogger(FactorConstante.class);

    int leidos=0;  // longitud caracteres leídos
    double cons;

    public FactorConstante(String text) {
        LOG.trace("FactorConstante= "+text);
        if ( text==null || text.length()==0 ){
            cons=1.0;
            leidos=0;
        }
        else {
            char c=text.charAt(0);
            if ( Util.isFinExpr(c) ) { // si [),;] avanzamos el final de expresion
                cons=1.0;
                leidos=1; // avanzamos el final de expresion
            }
            else if ( Util.isOpTerm(c) ) { // si [+-] viene termino
                cons=1.0;
                leidos=0;
            }
            else if ( Util.isOpFact(c) ) {// si [*/] viene factor
                cons=1.0;
                leidos=0;
            }
            else { // calcular K y avanzar l
                leidos = factConst(text);
            }
        }
    }
    private int factConst (String text) {
        int n=0;
        char c=text.charAt(0);
        while ( Util.isNumeric(c) ) {
            n++;
            if (n>=text.length())
                break;
            c=text.charAt(n);
        }
        cons = Double.parseDouble(text.substring(0,n));
        return n;
    }

    @Override
    public int comido(){
        return leidos;
    }
    
    @Override
    public double evalua(){
        return cons ;
    }
    
    @Override
    public String toText(){
        return String.valueOf(cons);
    }

    @Override
    public void print(String pre){
        LOG.trace(pre + cons);
    }

}
