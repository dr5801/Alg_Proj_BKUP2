import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Runs all the tests
 * @author Drew Rife
 */
@RunWith(Suite.class)
@SuiteClasses(
		{
			TestQuickSorter.class,
			TestMergeSorter.class,
			TestHeapSorter.class,
			TestPartition.class
		})
public class RunAllTests {}
