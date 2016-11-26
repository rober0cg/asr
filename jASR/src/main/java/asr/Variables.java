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
        int idx;
        if (text==null || text.isEmpty()) {
            idx = -1 ;
        }
        else {
            Var v = new Var(text);
            if ( listVars.contains(v) ) {
                idx = listVars.indexOf(v);
            }
            else {
                listVars.add(v);
                idx = listVars.size()-1;
            }
        }
        return idx;
    }

    public static void set(String text, double d){
        if ( text==null || text.isEmpty() ) {
            LOG.error("Variable.set null - ERROR");
        }
        else {
            LOG.trace("Variable.set "+text);
            Var v = new Var(text);
            if ( listVars.contains(v) ) {
                int idx = listVars.indexOf(v);
                v.setVal(d);
                listVars.set(idx,v);
            }
        }
    }
    public static void set(int idx, double d){
        if ( idx<0 ) {
            LOG.error("Variable.set idx<0 - ERROR");
        }
        else {
            LOG.trace("Variable.set "+idx);
            listVars.get(idx).setVal(d);
        }
    }

    public static double evalua(String text) {
        double d=0.0;
        if ( text==null || text.isEmpty() ) {
            LOG.error("Variable.evalua null - ERROR");
        }
        else {
            LOG.trace("Variable.evalua "+text);
            Var v = new Var(text);
            if ( listVars.contains(v) ) {
                int idx = listVars.indexOf(v);
                d = listVars.get(idx).getVal();
            }
        }
        return d;
    }
    public static double evalua(int idx) {
        double d=0.0;
        if ( idx<0 ) {
            LOG.error("Variable.evalua idx<0 - ERROR");
        }
        else {
            LOG.trace("Variable.evalua "+idx);
            d = listVars.get(idx).getVal();
        }
        return d;
    }

    public static String getName(int idx) {
        String str;
        if ( idx<0 ) {
            LOG.error("Variable.getName idx<0 - ERROR");
            str = "null";
        }
        else {
            str = listVars.get(idx).getName() ;
        }
        return str;
    }

    public static void print(String pre, int idx){
        if ( idx<0 ) {
            LOG.error("Variable.print idx<0 - ERROR");
        }
        else {
            LOG.trace(pre + listVars.get(idx).getName());
        }
    }

    public static String toString(int idx) {
        return toText(idx);
    }
    public static String toText(int idx) {
        String str;
        if ( idx<0 ) {
            LOG.error("Variable.toString idx<0 - ERROR");
            str="null";
        }
        else {
            str = listVars.get(idx).getName() + "=" + listVars.get(idx).getVal();
        }
        return str;
    }
    public static String toText(String text) {
        String str;
        if ( text==null || text.isEmpty() ) {
            str = "null";
        }
        else {
            Var v = new Var(text);
            int idx = listVars.indexOf(v);
            str = toText(idx);
        }
        return str;
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
