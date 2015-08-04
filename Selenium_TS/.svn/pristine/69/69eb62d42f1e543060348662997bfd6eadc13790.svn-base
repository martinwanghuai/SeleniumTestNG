package com.netdimen.junit;


import java.util.List;

import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;

import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

import com.google.common.base.Throwables;

/**This class will detect JavaScript error after test case execution.
 * 
 * @author martin.wang
 *
 */
public class JavaScriptErrorDetector extends TestReport {
	private WebDriver driver;

    public JavaScriptErrorDetector(WebDriver driver) {
    	super(driver);
        this.driver =  driver;
    }

    @Override
    protected void failed(Throwable e, Description description) {
      this.checkJSError();
     //SaveFailReportToExcel(Throwables.getStackTraceAsString(e));
      super.failed(e, description);
    }
    
    
    @Override
    protected void succeeded(Description description){
    	this.checkJSError();
    }
    
    @Override
    protected void finished(Description description) {
    	this.checkJSError();
    }
    
    private void checkJSError(){
    	List<JavaScriptError> jsErrors = JavaScriptError.readErrors(driver);
    	for(JavaScriptError jsError: jsErrors){
    		System.out.println("JS errors occured:" + jsError);
    	}
    	
    	JUnitAssert.assertTrue(jsErrors.isEmpty(),"JS error is found");
    }

}	
