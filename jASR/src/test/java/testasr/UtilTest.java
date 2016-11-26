package testasr;

import static org.junit.Assert.*;

import org.junit.Test;

import asr.Util;

public class UtilTest {

    @Test
    public final void testIsIniExpr() {
        assertTrue("testIsIniExpr", Util.isIniExpr('('));
        assertFalse("testIsIniExpr", Util.isIniExpr(')'));
    }

    @Test
    public final void testIsFinExpr() {
        assertTrue("testIsIniExpr", Util.isFinExpr(')'));
        assertTrue("testIsIniExpr", Util.isFinExpr(','));
        assertTrue("testIsIniExpr", Util.isFinExpr(';'));
        assertFalse("testIsIniExpr", Util.isFinExpr('('));
    }

    @Test
    public final void testIsOpTerm() {
        assertTrue("testIsIniExpr", Util.isOpTerm('+'));
        assertTrue("testIsIniExpr", Util.isOpTerm('-'));
        assertFalse("testIsIniExpr", Util.isOpTerm('*'));
    }

    @Test
    public final void testIsOpFact() {
        assertTrue("testIsIniExpr", Util.isOpFact('*'));
        assertTrue("testIsIniExpr", Util.isOpFact('/'));
        assertFalse("testIsIniExpr", Util.isOpFact('+'));
    }

    @Test
    public final void testIsOperador() {
        assertTrue("testIsIniExpr", Util.isOperador('+'));
        assertTrue("testIsIniExpr", Util.isOperador('-'));
        assertTrue("testIsIniExpr", Util.isOperador('*'));
        assertTrue("testIsIniExpr", Util.isOperador('/'));
        assertFalse("testIsIniExpr", Util.isOperador('('));
    }

    @Test
    public final void testIsNumeric() {
        assertTrue("testIsIniExpr", Util.isNumeric('0'));
        assertTrue("testIsIniExpr", Util.isNumeric('1'));
        assertTrue("testIsIniExpr", Util.isNumeric('9'));
        assertTrue("testIsIniExpr", Util.isNumeric('.'));
        assertFalse("testIsIniExpr", Util.isNumeric('('));
        assertFalse("testIsIniExpr", Util.isNumeric('+'));
        assertFalse("testIsIniExpr", Util.isNumeric(','));
    }

    @Test
    public final void testIsAlphaNum() {
        assertTrue("testIsIniExpr", Util.isAlphaNum('a'));
        assertTrue("testIsIniExpr", Util.isAlphaNum('b'));
        assertTrue("testIsIniExpr", Util.isAlphaNum('z'));
        assertTrue("testIsIniExpr", Util.isAlphaNum('A'));
        assertTrue("testIsIniExpr", Util.isAlphaNum('Y'));
        assertTrue("testIsIniExpr", Util.isAlphaNum('Z'));
        assertTrue("testIsIniExpr", Util.isAlphaNum('0'));
        assertTrue("testIsIniExpr", Util.isAlphaNum('5'));
        assertTrue("testIsIniExpr", Util.isAlphaNum('9'));
        assertFalse("testIsIniExpr", Util.isAlphaNum('('));
        assertFalse("testIsIniExpr", Util.isAlphaNum('+'));
        assertFalse("testIsIniExpr", Util.isAlphaNum(','));
        assertFalse("testIsIniExpr", Util.isAlphaNum('.'));
    }

    @Test
    public final void testIsFinVar() {
        assertTrue("testIsIniExpr", Util.isFinVar(')'));
        assertTrue("testIsIniExpr", Util.isFinVar(','));
        assertTrue("testIsIniExpr", Util.isFinVar(';'));
        assertFalse("testIsIniExpr", Util.isFinVar('a'));
        assertFalse("testIsIniExpr", Util.isFinVar('A'));
        assertFalse("testIsIniExpr", Util.isFinVar('0'));
    }

    @Test
    public final void testLongNombreVarOrFunc() {
        assertTrue("testIsIniExpr", Util.longNombreVarOrFunc("a+b")==1);
        assertTrue("testIsIniExpr", Util.longNombreVarOrFunc("sin(3.14)")==3);
        assertTrue("testIsIniExpr", Util.longNombreVarOrFunc("hypot(3,4)")==5);
    }

    @Test
    public final void testTrasNombreVarOrFunc() {
        assertTrue("testIsIniExpr", Util.trasNombreVarOrFunc("a+b")=='+');
        assertTrue("testIsIniExpr", Util.trasNombreVarOrFunc("sin(3.14)")=='(');
        assertTrue("testIsIniExpr", Util.trasNombreVarOrFunc("hypot(3,4)")=='(');
    }

}
