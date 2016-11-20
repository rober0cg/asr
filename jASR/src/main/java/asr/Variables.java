package asr;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/*
 * ASR
 * Clase estática para mantener las distintas variables de la expresion
 * Utilizada por Expresión y FactorVariable
 * 
 */
public class Variables {
    private static final Logger LOG = Logger.getLogger(Variables.class);

//  ArrayList con las variables (subclase Var con nombre y valor)
    private static ArrayList<Var> listVars = new ArrayList<>();
    
    private Variables() {
    }

    public static int addVar (String text) {
        Var v = new Var(text);
        int idx;
        if ( listVars.contains(v) ) {
            idx = listVars.indexOf(v);
        }
        else {
            listVars.add(v);
            idx = listVars.size()-1;
        }
        return idx;
    }

    public static void set(String text, double d){
        LOG.trace("Variable.set "+text);
        Var v = new Var(text);
        if ( listVars.contains(v) ) {
            int idx = listVars.indexOf(v);
            v.setVal(d);
            listVars.set(idx,v);
        }
    }
    public static void set(int idx, double d){
        LOG.trace("Variable.set "+idx);
        listVars.get(idx).setVal(d);
    }

    public static double evalua(String text) {
        LOG.trace("Variable.evalua "+text);
        Var v = new Var(text);
        double d=0.0;
        if ( listVars.contains(v) ) {
            int idx = listVars.indexOf(v);
            d = listVars.get(idx).getVal();
        }
        return d;
    }
    public static double evalua(int idx) {
        LOG.trace("Variable.evalua "+idx);
        return listVars.get(idx).getVal();
    }

    public static String getName(int idx) {
        return listVars.get(idx).getName() ;
    }


    void print(String pre, int idx){
        LOG.trace(pre + listVars.get(idx).getName());
    }

    public String toString(int idx) {
        return toText(idx);
    }
    public static String toText(int idx) {
        return listVars.get(idx).getName() + "=" + listVars.get(idx).getVal() ;
    }
    public static String toText(String text) {
        Var v = new Var(text);
        int idx = listVars.indexOf(v);
        if ( idx >= 0 ) {
            return listVars.get(idx).getName() + "=" + listVars.get(idx).getVal() ;
        }
        else {
            return text + " --> IS NOT A VAR" ;
        }
    }

    public static Iterator<Var> getVariables() {
        return listVars.iterator();
    }
    
    public static class Var {

        private String name; // nombre de la variable
        private double val; // valor

        public Var(String n) {
            this(n,0.0);
        }
        public Var(String n, double v) {
            LOG.trace("Var ("+n+","+v+")");
            name=n;
            val=v;
        }
        public String getName() {
            LOG.trace("Var.getName ("+name+")");
            return name;
        }
        public double getVal() {
            LOG.trace("Var.getVal ("+name+","+val+")");
            return val;
        }
        public void setVal(double d) {
            LOG.trace("Var.setVal ("+name+","+d+")");
            val=d;
        }

        @Override
        public boolean equals ( Object o ) {
            if ( o==null )
                return false ;
            if ( o.getClass() != this.getClass() )
                return false ;
            Var v = (Var)o;
            return name.equals(v.name);
        }

        @Override
        public int hashCode ( ) {
            return Objects.hash(name);
        }

    }

    
}
