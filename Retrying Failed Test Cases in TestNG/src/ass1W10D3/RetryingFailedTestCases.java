/**
 * @author Mawaddah Hanbali
 */
package ass1W10D3;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryingFailedTestCases implements IRetryAnalyzer {

	private int retryCnt = 0;

	// You could mention maxRetryCnt (Maximian Retry Count) as per your requirement.
	// Here I took 2, If any failed test cases then it runs two times.
	private int maxRetryCnt = 2;

	// This method will be called everytime a test fails. It wil1 return TRUE if a
	// test fails and need to be retried, else it returns FALSE
	@Override
	public boolean retry(ITestResult result) {
		if (retryCnt < maxRetryCnt) {
			System.out.println("Retrying " + result.getName() + " again and the count is " + (retryCnt + 1));
			retryCnt++;
			return true;
		}
		return false;
	}

}
