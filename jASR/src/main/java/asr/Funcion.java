package asr;

import org.apache.log4j.Logger;

import java.util.ArrayList;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
 * Implementa la lista de funciones y el índice de la lista de la propia funcion
 * 
 */
public class Funcion {
    private static final Logger LOG = Logger.getLogger(Funcion.class);

    int idx; // indice en la lista de Fun's
    static ArrayList<Fun> listFuncs = new ArrayList<>();
   
    public Funcion( String text ) {
        if ( text==null || text.isEmpty() ) {
            LOG.error("Funcion null - ERROR");
            idx = -1;
        }
        else {
            LOG.trace("Funcion = "+text);
            Fun f = new Fun(text);
            if ( listFuncs.contains(f)) {
                idx = listFuncs.indexOf(f);
            }
            else {
                listFuncs.add(f);
                idx = listFuncs.size()-1;
            }
        }
    }

    public double evalua ( Expresion expr1, Expresion expr2 ) {
        double d=0.0;
        if ( idx<0 ) {
            LOG.error("Funcion.evalua null - ERROR");
        }
        else {
            Method method = listFuncs.get(idx).getMethod();
            if ( method==null ) {
                LOG.error("Funcion.evalua getMethod ("+listFuncs.get(idx).getName()+") == null ");
            }
            else {
                d = evaluaFunc ( method, expr1, expr2 ) ;
            }
        }
        return d;
    }
    private double evaluaFunc ( Method method, Expresion expr1, Expresion expr2 ) {
        double d=0.0;
        try {
            if ( expr1==null ) { // 0 argumentos -> func()
                d = (double)method.invoke(Math.class);
            }
            else if ( expr2==null) {  // 1 argumentos -> func(expr1)
                double eval1 = expr1.evalua();
                d = (double)method.invoke(Math.class, eval1);
            }
            else { // 2 argumentos -> func(expr1,expr2)
                double eval1 = expr1.evalua();
                double eval2 = expr2.evalua();
                d = (double)method.invoke(Math.class, eval1, eval2);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            LOG.error(e);
            LOG.error("Funcion " + listFuncs.get(idx).getName() + " ERROR IllegalArgument");
        }
        return d;
    }
    
    public String getName() {
        String str;
        if ( idx<0 ) {
            LOG.error("Funcion.getName null - ERROR");
            str = "null";
        }
        else {
            str = listFuncs.get(idx).getName();
        }
        return str ;
    }

    public String toText() {
        return getName() + "()" ;
    }

    public void print(String pre){
        LOG.trace(pre + toText());
    }


    private class Fun {

        String name; // nombre de la funcion
        Method method; // metodo de la clase Math

        public Fun( String text ) {
            name = text;
            method = null;
            int i;
            for ( i=0; i<3 && method==null; i++) {
                try {
                    if ( i==0 )
                        method = Math.class.getMethod(name);
                    if ( i==1 )
                        method = Math.class.getMethod(name,double.class);
                    if ( i==2 )
                        method = Math.class.getMethod(name,double.class,double.class);
                } catch (NoSuchMethodException | SecurityException e) {
                    LOG.debug("Fun " + name + " (with " + i + " args)"+ " NoSuchMethod -> " + e);
                    method = null;
                }
            }
            if ( i>=3 ) {
                LOG.error("Fun " + name + " NOT FOUND in Math");
            }
            else {
                LOG.info("Fun " + name + " (with " + i + " args)"+ " FOUND OK in Math");
            }
        }
        public String getName(){
            return name;
        }
        public Method getMethod(){
            return method;
        }

        @Override
        public boolean equals ( Object o ) {
            if ( o==null )
                return false ;
            if ( o.getClass() != this.getClass() )
                return false ;
            Fun f = (Fun)o;
            return name.equals(f.name);
        }
        @Override
        public int hashCode ( ) {
            return name.hashCode();
        }
    }
}
