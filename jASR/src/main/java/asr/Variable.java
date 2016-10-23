package asr;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

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
public class Variable {
    private static final Logger LOG = Logger.getLogger(Variable.class);

    int idx; // indice en la lista de Var's
    static ArrayList<Var> listVars = new ArrayList<Var>();
    
    public Variable(String text) {
        this(text,0.0);
    }
    public Variable(String text, double val) {
        LOG.trace("Variable = "+text+","+val);
        Var v = new Var(text,val);
        if ( listVars.contains(v) ) {
            idx = listVars.indexOf(v);
            listVars.get(idx).setVal(val);
        }
        else {
            listVars.add(v);
            idx = listVars.size()-1;
        }
    }

    public void set(double d){
        listVars.get(idx).setVal(d);
    }

    public double evalua() {
        LOG.trace("Variable "+listVars.get(idx).getName()+"("+idx+")"+"="+listVars.get(idx).getVal());
        return listVars.get(idx).getVal();
    }

    public String getName() {
        return listVars.get(idx).getName() ;
    }
    
    public String toText() {
        return listVars.get(idx).getName() + "=" + listVars.get(idx).getVal() ;
    }

    void print(String pre){
        LOG.trace(pre + listVars.get(idx).getName());
    }
    
    @Override
    public String toString() {
        return toText();
    }

    public static Iterator<Variable> getVariables1() {
        ArrayList<Variable> alv = new ArrayList<Variable>();
        Iterator<Var> iv = listVars.iterator();
        while ( iv.hasNext() ) {
            alv.add(new Variable( iv.next().getName()));
        }
        
        return alv.iterator();
    }
    public static Iterator<Variable> getVariables2() {
        ArrayList<Variable> alv = new ArrayList<Variable>();
        for ( int i=0 ; i<listVars.size() ; i++) {
            alv.add(new Variable( listVars.get(i).getName() ));
        }
        return alv.iterator();
    }
    
    private class Var {

        private String name; // nombre de la variable
        private double val; // valor

        public Var(String n, double v) {
            LOG.trace("Var = "+n);
            name=n;
            val=v;
            if ( listVars.contains(this) ) {
                idx = listVars.indexOf(this);
            }
            else {
                listVars.add(this);
                idx = listVars.size()-1;
            }
        }
        public String getName() {
            return name;
        }
        public double getVal() {
            return val;
        }
        public void setVal(double d) {
            val=d;
        }

        @Override
        public boolean equals ( Object o ) {
            Var v = (Var)o;
            return name.equals(v.name);
        }

        @Override
        public int hashCode ( ) {
            return Objects.hash(listVars.get(idx));
        }

    }

    
}
