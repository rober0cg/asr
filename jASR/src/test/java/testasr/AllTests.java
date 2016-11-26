package testasr;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    UtilTest.class,
    VariablesTest.class,
    FuncionTest.class,
    FactorConstanteTest.class,
    FactorVariableTest.class,
    FactorExpresionTest.class,
    FactorFuncionTest.class,
    FactorTest.class,
    TerminoTest.class,
    ExpresionTest.class
})
public class AllTests {

}
