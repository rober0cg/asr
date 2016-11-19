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
 * FACT = CONST | VAR | FUNC() | (EXPR)
 * 
 */
public class Expresion {
    private static final Logger LOG = Logger.getLogger(Expresion.class);

    int leidos=0;  // longitud caracteres leídos
    Termino term;   // primer término
    Expresion next=null;  // seguido de otra expresión

    static Variables vars ; // lista de variables

    public Expresion(String text) {
        LOG.trace("Expresion= "+text);
        // analizar si ya hemos llegado al final de la expresión ( ')' o null )
        if ( text==null || text.length()==0 ){
            term=null;
            next=null;
            leidos=0;
        }
        else {
            char c=text.charAt(0);
            if (Util.isFinExpr(c)) {// final de expresion, avanzamos 1 caracter
                term=null;
                next=null;
                leidos=1;
            }
            else {// sacamos el termino, avanzamos y buscamos otra expresion
                term = new Termino(text.substring(leidos));
                leidos += term.comido();

                leidos += nextExpresion(text.substring(leidos));
            }
        }
    }
    private int nextExpresion(String text) {
        if ( text==null || text.length()==0 ){
            next = null;
            return 0;
        }
        else {
            char c=text.charAt(0);
            if ( Util.isOpTerm(c) ) {
                next = new Expresion(text);
                return next.comido();
            }
            else {
                next = null;
                return 0;
            }
        }
    }
    
    int comido(){
        return leidos;
    }

    public double evalua(){
        double d;
        if ( term==null ) {
            LOG.error("Expresion.evalua null ("+term+")");
            d=0.0;
        }
        else {
            d = term.evalua();
        }
        if ( next!=null)
            d += next.evalua();
        return d;
    }

    public String toText(){
        return toText(true);
    }
    public String toText(boolean isFirst){
        String str= "";
        if ( isFirst && term.oper=='-')
            str += term.oper;
        str += term.toText();
        if ( next!=null) {
            str += next.term.oper;
            str += next.toText(false);
        }
        return str;
    }

    public void traza(String text){
        LOG.trace("Expresion "+text+" ==>");
        this.print(Util.SEP,true);
    }

    public void print(){
        print(Util.SEP, true);
    }
    public void print(String pre, boolean isFirst){
        if (isFirst && term.oper=='-')
            LOG.trace(pre + term.oper);
        term.print(pre + Util.SEP);
        if ( next!=null) {
            LOG.trace(pre + Util.SEP + next.term.oper);
            next.print(pre, false);
        }
    }

}
