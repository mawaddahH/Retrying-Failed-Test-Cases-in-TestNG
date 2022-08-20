/**
 * @author Mawaddah Hanbali
 */
package ass1W10D3;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Test2 {
  @Test(retryAnalyzer = RetryingFailedTestCases.class)
  public void test2() {
	  System.out.println("Test 2 is FAIL");
	  Assert.assertTrue(false);
  }
}
