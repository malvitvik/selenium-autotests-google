import cucumber.CucumberRunner;
import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;

public class TestSuite {
    @Test
    public void parallelTest() {
        Class[] cls = {CucumberRunner.class};

        JUnitCore.runClasses(new ParallelComputer(true, true), cls);
    }
}
