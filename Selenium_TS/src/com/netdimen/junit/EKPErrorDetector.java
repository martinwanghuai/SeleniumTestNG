package com.netdimen.junit;

import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

import com.google.common.base.Throwables;
import com.netdimen.abstractclasses.TestReport;
import com.netdimen.utils.WebDriverUtils;

/**This class will detect ekp error after test case execution
 * 
 * @author martin.wang
 *
 */
public class EKPErrorDetector extends TestReport{

	private WebDriver driver;

    public EKPErrorDetector(WebDriver driver) {
        this.driver =  driver;
    }

    @Override
    protected void failed(Throwable e, Description description) {
   
      SaveFailReportToExcel(Throwables.getStackTraceAsString(e));
    }
    
    
    @Override
    protected void succeeded(Description description){
  
    }
    
    @Override
    protected void finished(Description description) {
    	
    }
    


}
