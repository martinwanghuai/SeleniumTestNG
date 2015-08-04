package com.netdimen.interfaces;

import org.openqa.selenium.WebDriver;

/**Implemented by all testing objects (Implicitly implemented since all testing objects in "com.netdimen.model" package 
 * need to extend com.netdimen.abstractclasses.TestObject, while TestObject implements ITestObject interface)
 * 
 * @author martin.wang
 *
 */
public interface ITestObject {
	
	public void run(WebDriver driver);
	
	public String toString();
}
