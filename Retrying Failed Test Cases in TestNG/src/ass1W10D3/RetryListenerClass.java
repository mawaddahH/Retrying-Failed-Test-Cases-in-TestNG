/**
 * @author Mawaddah Hanbali
 */
package ass1W10D3;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class RetryListenerClass implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		Class<? extends IRetryAnalyzer> retry = annotation.getRetryAnalyzerClass();
		if (retry == null) {
			annotation.setRetryAnalyzer(RetryingFailedTestCases.class);
		}
	}
}
