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
public class Factor {
    private static final Logger LOG = Logger.getLogger(Factor.class);

    int leidos=0;  // longitud caracteres leídos
    char oper='*'; // operaciones '*' y '/', y '*' por defecto
    FactorBase fact;

    public Factor(String text) {
        LOG.trace("Factor= "+text);
        // analizar si ya hemos llegado al final de la expresión ( ')' o null )
        if ( text==null || text.length()==0 ){
            fact=null;
            leidos=0;
        }
        else {
            char c=text.charAt(0);
            if ( Util.isFinExpr(c) ) { // fin de expresion
                fact=null;
                leidos=1;
            }
            else if ( Util.isOpTerm(c) ) { // fin de termino
                fact=null;
                leidos=0;
            }
            else {  // hay factor
                if ( Util.isOpFact(c) ) {
                    oper=c;
                    leidos=1;
                }
                leidos += nextFactor(text.substring(leidos));
            }
        }
    }
    private int nextFactor (String text){
        char c=text.charAt(0);
        if ( Util.isIniExpr(c) ) { // "(" -> Expresion
            return nextFactorExpr(text);
        }
        else if ( Util.isNumeric(c) ) { // "[0-9.]" -> Constante
            return nextFactorConst(text);
        }
        else { // puede ser variable o funcion: "txt(" -> funcion ; "txt[+-*/] -> variable
            return nextFactorVarOrFunc(text);
        }
    }
    private int nextFactorExpr (String text){
        int n=1;
        fact = new FactorExpresion(text.substring(1));
        n += fact.comido();
        if ( n<text.length() && Util.isFinExpr(text.charAt(n)) )
            n += 1;
        return n ;
    }
    private int nextFactorConst (String text){
        fact = new FactorConstante(text);
        return fact.comido();
    }
    private int nextFactorVarOrFunc (String text){
        char c = Util.trasNombreVarOrFunc(text);
        if ( Util.isFinVar(c) ) { // Variable
            fact = new FactorVariable(text);
            return fact.comido();
        }
        else if ( Util.isIniExpr(c) ) { // funcion
            fact = new FactorFuncion(text);
            return fact.comido();
        }
        else {
            LOG.error("Factor DESCONOCIDO");
            fact=null;
            return 1;
        }
    }
    
    public double evalua() {
        double d ;
        if (fact==null) {
            LOG.error("Factor.evalua null ("+fact+")");
            d=1.0;
        }
        else {
            d = fact.evalua();
        }
        return ( oper=='*' ) ? d : 1.0/d ;
    }

    public int comido() {
        return leidos;
    }

    public String toText() {
        String str="";
        if ( fact==null ) {
            str = "(null)" ;
        }
        else {
            str += fact.toText() ;
        }
        return str;
    }

    public void print(String pre){
        if ( fact==null ) {
            LOG.trace(pre + "(null)");
        }
        else {
            fact.print(pre + Util.SEP);
        }
    }
}
