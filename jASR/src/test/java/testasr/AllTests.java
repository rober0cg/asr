package testasr;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    FactorVariableTest.class,
    FactorConstanteTest.class,
    FactorExpresionTest.class,
    FactorFuncionTest.class  
})
public class AllTests {

}
