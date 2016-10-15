package asr;

import org.apache.log4j.Logger;

/*
 * ASR
 * Analizador
 *  Sintáctico
 *   Recursivo
 *
 * EXPR = TERM [ + EXPR ]
 * 
 * TERM = FACT [ * TERM ]
 *
 * FACT = CONST | VAR | FUNC() | (EXPR)
 * 
 */
public class Termino {
    private static final Logger LOG = Logger.getLogger(Termino.class);

    int leidos=0; // caracteres analizados
    char oper='+'; // operaciones '+' y '-', y '+' por defecto
    Factor fact;
    Termino next=null;
    
    public Termino(String text) {
        LOG.trace("Termino= "+text);
        // analizar si ya hemos llegado al final del termino ('+' ó '-') o la expresión ( ')' o null )
        if ( text==null || text.length()==0 ){
            fact=null;
            next=null;
            leidos=0;
        }
        else {
            char c=text.charAt(0);
            if ( Util.isFinExpr(c) ) { // final de termino y expresion, avanzamos 1
                fact=null;
                next=null;
                leidos=1;
            }
            else {
                if (  Util.isOpTerm(c) ) { // el signo de este termino
                    oper=c;
                    leidos=1;
                }
                // sacamos el factor, avanzamos y buscamos otro termino
                fact = new Factor(text.substring(leidos));
                leidos += fact.comido();
                
                leidos += nextTermino(text.substring(leidos));
            }
        }
    }
    private int nextTermino (String text) {
        if ( text==null || text.length()==0 ){
            next=null;
            return 0;
        }
        else {
            char c=text.charAt(0);
            if ( Util.isOpFact(c) ) {
                next = new Termino(text.substring(0));
                return next.comido();
            }
            else {
                next = null ;
                return 0;
            }
        }
    }

    double evalua() {
        double d ;
        if ( fact==null ) {
            LOG.error("Termino.evalua null ("+fact+")");
            d=1.0;
        }
        else {
            d = fact.evalua();
        }
        if ( next!=null)
            d *= next.evalua();
        return ( oper=='+' ) ?  d : -d;
    }

    int comido(){
        return leidos;
    }

    public String toText(){
        String str = "";
        str += fact.toText();
        if ( next!=null) {
            str += next.fact.oper ;
            str += next.toText();
        }
        return str;
    }

    void print(String pre){
        fact.print(pre + Util.SEP);
        if ( next!=null) {
            LOG.trace(pre + Util.SEP + next.fact.oper);
            next.print(pre);
        }
    }
}
