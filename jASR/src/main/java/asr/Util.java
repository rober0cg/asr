package asr;

public final class Util {

    public static final String SEP = "  ";
    
    private Util() {
    // vacio por ser clase estatica de funciones
    }
    
    public static boolean isIniExpr ( char c ) {
        return  c=='(' ;
    }

    public static boolean isFinExpr ( char c ) {
        return c==')' || c==',' || c==';' ;
    }

    public static boolean isOpTerm ( char c ) {
        return c=='+' || c=='-' ; 
    }

    public static boolean isOpFact ( char c ) {
        return c=='*' || c=='/' ; 
    }

    public static boolean isOperador ( char c ) {
        return isOpTerm(c) || isOpFact(c) ;
    }
    
    public static boolean isNumeric ( char c ) {
        return c=='.' || (c>='0' && c<='9') ; 
    }

    public static boolean isAlphaNum ( char c ) { 
        if (c>='a' && c<='z')
            return true ;
        if (c>='A' && c<='Z')
            return true ;
        if (c>='0' && c<='9')
            return true ;
        return false;
    }

    public static boolean isFinVar (char c ) {
        return isFinExpr(c) || isOpTerm(c) || isOpFact(c); 
    }

    public static int longNombreVarOrFunc(String text) {
        int n=0;
        char c=text.charAt(0);
        while ( isAlphaNum(c) ) {
            n++;
            if ( n>=text.length() ) // fin de cadena
                break;
            c=text.charAt(n);
        }
        return n;
    }

    public static char trasNombreVarOrFunc(String text) {
        int n=0;
        char c=text.charAt(0);
        while ( isAlphaNum(c) ) {
            n++;
            if ( n>=text.length() ) { // fin de cadena
                c = ';' ; // simular fin
                break;
            }
            c=text.charAt(n);
        }
        return c;
    }

    
    
}
