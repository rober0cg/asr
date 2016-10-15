package asr;

import org.apache.log4j.Logger;

import java.util.ArrayList;
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
    static ArrayList<Var> lst = new ArrayList<Var>();
    
    public Variable(String text) {
        LOG.trace("Variable = "+text);
        Var v = new Var(text,0.0);
        if ( lst.contains(v) ) {
            idx = lst.indexOf(v);
        }
        else {
            lst.add(v);
            idx = lst.size()-1;
        }
    }
    public Variable(String text, double val) {
        LOG.trace("Variable = "+text+","+val);
        Var v = new Var(text,val);
        if ( lst.contains(v) ) {
            idx = lst.indexOf(v);
            lst.get(idx).setVal(val);
        }
        else {
            lst.add(v);
            idx = lst.size()-1;
        }
    }

    public void set(double d){
        lst.get(idx).setVal(d);
    }

    public double evalua() {
        LOG.trace("Variable "+lst.get(idx).getName()+"("+idx+")"+"="+lst.get(idx).getVal());
        return lst.get(idx).getVal();
    }

    public String getName() {
        return lst.get(idx).getName() ;
    }
    
    public String toText() {
        return lst.get(idx).getName() + "=" + lst.get(idx).getVal() ;
    }

    void print(String pre){
        LOG.trace(pre + lst.get(idx).getName());
    }
    
    @Override
    public String toString() {
        return toText();
    }

    private class Var {

        private String name; // nombre de la variable
        private double val; // valor

        public Var(String n, double v) {
            LOG.trace("Var = "+n);
            name=n;
            val=v;
            if ( lst.contains(this) ) {
                idx = lst.indexOf(this);
            }
            else {
                lst.add(this);
                idx = lst.size()-1;
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
            return Objects.hash(lst.get(idx));
        }

    }

    
}
