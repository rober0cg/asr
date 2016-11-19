package asr;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Objects;


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
 */
public class Funcion {
    private static final Logger LOG = Logger.getLogger(Funcion.class);

    int idx; // indice en la lista de Fun's
    static ArrayList<Fun> listFuncs = new ArrayList<>();
   
    public Funcion( String text ) {
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

    public double evalua ( Expresion expr1, Expresion expr2 ) {
        double d;
        Method method = listFuncs.get(idx).getMethod();
        if ( method==null ) {
            LOG.error("Funcion.evalua getMethod ("+listFuncs.get(idx).getName()+") == null ");
            d = 0.0;
        }
        else {

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
                d = 0.0;
            }
        }
        return d;
    }

    String getName() {
        return listFuncs.get(idx).getName();
    }

    public String toText() {
        return listFuncs.get(idx).getName() + "()" ;
    }

    void print(String pre){
        LOG.trace(pre + listFuncs.get(idx).getName()+"()");
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
                    break;
                } catch (NoSuchMethodException | SecurityException e) {
                    LOG.debug("Fun " + name + " (" + i + " args)"+ " NoSuchMethod -> " + e);
                    method = null;
                }
            }
            if ( i<3 ) {
                LOG.info("Fun " + name + " (" + i + " args)"+ " Found OK");
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
            return Objects.hash(listFuncs.get(idx));
        }
    }
    
}
