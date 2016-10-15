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
public class FactorFuncion implements FactorBase {
    private static final Logger LOG = Logger.getLogger(FactorFuncion.class);

    int leidos=0;  // longitud caracteres leídos
    Funcion func;
    Expresion expr1, expr2; // para funciones con 0, 1 o 2 argumentos
    
    public FactorFuncion(String text) {
        LOG.trace("FactorFuncion= "+text);
        if ( text==null || text.length()==0 ){
            leidos=0;
            func=null;
            expr1=expr2=null;
        }
        else {
            char c=text.charAt(0);
            if ( Util.isFinExpr(c) ) { // si [),;] avanzamos el final de expresion
                func=null;
                expr1=expr2=null;
                leidos=1; // avanzamos el final de expresion
            }
            else if ( Util.isOpTerm(c) ) { // si [+-] viene termino
                func=null;
                expr1=expr2=null;
                leidos=0;
            }
            else if ( Util.isOpFact(c) ) { // si [*/] viene factor
                func=null;
                expr1=expr2=null;
                leidos=0;
            }
            else { // localizamos nombre funcion
                leidos = factFunc(text);
            }
        }
    }
    private int factFunc (String text) {
        int n;
        char c;

        n=Util.longNombreVarOrFunc(text);
        func = new Funcion(text.substring(0,n));
        n++; // saltar '('

        c=text.charAt(n);
        if (c==')') { // funcion sin argumentos f()
            expr1=expr2=null;
        }
        else { // funcion tiene al menos un argumento f(expr1...)
            expr1 = new Expresion(text.substring(n));
            n += expr1.comido();

            c=text.charAt(n);
            if (c==')') { // funcion con 1 argumento f(expr1)
                expr2=null;
            }
            else if (c==',' ){
                n++; // saltar ','
                expr2 = new Expresion(text.substring(n));
                n += expr2.comido();
            }
            else {
                LOG.error("FactorFuncion.factFunc ("+text+")");
                n++; //saltamos posicion desconocida
            }
        }

        n ++; // saltar ')'

        return n;
        
    }

    @Override
    public int comido() {
        return leidos;
    }

    @Override
    public double evalua() {
        double d;
        if ( func==null ) {
            LOG.error("FactorFuncion.evalua("+func.toText()+") -> null ");
            d=0.0;
        }
        else {
            d = func.evalua(expr1,expr2);
        }
        return d ;
    }

    @Override
    public String toText(){
        String str = func.getName()+ "(";
        if ( expr1!=null ) { // func(expr1...)
            str += expr1.toText() ;
        }
        if ( expr2!=null ) { // func(expr1,expr2)
            str += "," + expr2.toText() ;
        }
        str += ")";
        return str;
    }

    @Override
    public void print(String pre){
        func.print(pre);
        LOG.trace(pre + "(");
        if ( expr1!=null ) {
            expr1.print(pre, true);
        }
        if ( expr2!=null ) {
            LOG.trace(pre + ",");
            expr2.print(pre, true);
        }
        LOG.trace(pre + ")");
    }
}
