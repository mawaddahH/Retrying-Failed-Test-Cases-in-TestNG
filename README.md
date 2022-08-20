# Retrying-Failed-Test-Cases-in-TestNG
Assignment 1 W10D3 - SDA - Software QA Bootcamp



# Table of contents
* [Question](#question)
* [Answer](#answer)
  * [Set up](#set-up)
  * [Method 1](#method-1)
  * [Method 2](#method-2)

---
# Question
Create brief documentation for Retrying failed Testcases in TestNG.
- STEP 1: Create a test case using TestNG.
- STEP 2: Execute the Testcase.
- STEP 3: Verify the Failed Testcases from the test-output folder
- STEP 4: Rerun the Failed TestCase (2 Methods).

---

# Answer
## Set up
Before running the code, there are some steps that need to take considered:

### First:
Setup:
- [JDK](https://www.oracle.com/java/technologies/downloads/) (Lastest)
- [Eclipse](https://www.eclipse.org/) (Lastest)

Donwload the necessary jar files:
- [Selenium](https://www.selenium.dev/downloads/) (Lastest).
- [TestNG](http://www.java2s.com/Code/Jar/t/Downloadtestng685jar.htm) (Lastest).

### Second:
Add them as a library in the classpath of the project
- _click-reight on the file project >Build path > configure Bild path > Java Build Path > Libraries > classpath > add external JARs > Apply and close_.

---

## Method 1
Execute failed test cases using TestNG in Selenium – By using “testng-failed.xml”
- Step 1: After the first run of an automated test run. 
- Step 2: Right click on Project – Click on Refresh.
- Step 3: A folder will be generated named “test-output” folder
- Step 4: Inside “test-output” folder, you could find “testng-failed.xml”
- Step 5: Run “testng-failed.xml” to execute the failed test cases again.

### Output Screenshots
<p align="center">

https://user-images.githubusercontent.com/48597284/185726024-a691801f-c963-485f-8cca-8ca46f8bb252.mp4

</p>

---

## Method 2
Execute failed test cases using TestNG in Selenium – By Implementing TestNG `IRetryAnalyzer`
- Step 1: Create a class to implement IRetryAnalyzer.

```md
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
```

- Step 2: Create another class `RetryListenerClass` by Implementing `IAnnotationTransaformer` interface transform method is called for every test during test run. A simple implementation of this `IAnnotationTransformer` interface can help us set the `setRetryAnalyzer` for ‘ITestAnnotation.

```md
public class RetryListenerClass implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		Class<? extends IRetryAnalyzer> retry = annotation.getRetryAnalyzerClass();
		if (retry == null) {
			annotation.setRetryAnalyzer(RetryingFailedTestCases.class);
		}
	}
}
```

- Step 3: Sample Testcases 1 , and Sample Testcases 2

```md
public class Test1 {
	@Test(retryAnalyzer = RetryingFailedTestCases.class)
	public void test1() {
		System.out.println("Test 1 is PASS");
		Assert.assertTrue(true);
	}
}
```

```md
public class Test2 {
  @Test(retryAnalyzer = RetryingFailedTestCases.class)
  public void test2() {
	  System.out.println("Test 2 is FAIL");
	  Assert.assertTrue(false);
  }
}
```

- Step 4: Include below mentioned Listener to `testng.xml` file.
```md
new WebDriverWait(driver, Duration.ofSeconds(10));
```

- Step 5: `Testng.xml` file.
```md
    <listeners>
        <listener class-name="ass1W10D3.RetryListenerClass"/>
    </listeners>
```

- Step 6: Execute the `testng.xml` 
```md
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Suite">
    <listeners>
        <listener class-name="ass1W10D3.RetryListenerClass"/>
    </listeners>
    <test name="TestNG Sample Test1">
        <classes>
            <class name="ass1W10D3.Test1"/>
        </classes>
    </test>  <!-- Test1 -->
        <test name="TestNG Sample Test2">
        <classes>
            <class name="ass1W10D3.Test2"/>
        </classes>
    </test>  <!-- Test2 -->
</suite> <!-- Suite -->

```
### Output Screenshots

<p align="center">
<img src="https://user-images.githubusercontent.com/48597284/185727119-6216f84c-8a05-413c-84fc-fb9eed96103e.png" width=80% height=80%>

https://user-images.githubusercontent.com/48597284/185727185-d67b7ac9-9702-446e-bda5-aec1fc839ede.mp4

</p>





