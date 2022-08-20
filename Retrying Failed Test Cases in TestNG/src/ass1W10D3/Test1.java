/**
 * @author Mawaddah Hanbali
 */
package ass1W10D3;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 {
	@Test(retryAnalyzer = RetryingFailedTestCases.class)
	public void test1() {
		System.out.println("Test 1 is PASS");
		Assert.assertTrue(true);
	}
}
