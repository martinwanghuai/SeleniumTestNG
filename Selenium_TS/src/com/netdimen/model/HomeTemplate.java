package com.netdimen.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.config.Labels;
import com.netdimen.utils.CriteriaParser;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.FunctionUI;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class HomeTemplate extends com.netdimen.abstractclasses.TestObject {
	private String TemplateName = "", Modules = "", Sections = "", Columns = "";

	public String getSections() {
		return Sections;
	}

	public String getColumns() {
		return Columns;
	}

	public void setSections(String sections) {
		Sections = sections;
	}

	public void setColumns(String columns) {
		Columns = columns;
	}

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public HomeTemplate() {
		super();
	}

	public String getTemplateName() {
		return TemplateName;
	}

	public String getModules() {
		return Modules;
	}

	public void setTemplateName(String templatename) {
		TemplateName = templatename;
	}

	public void setModules(String modules) {
		Modules = modules;
	}

	public void setTemplateName_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("templateName");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	/**append modules into different sections and columns
	 * 
	 * @param driver
	 * @param str: Section0:Column1:My Team;Course Status;
	 */
	public void setModules_UI(WebDriver driver, String str) {
		if (!str.equals("")) { //
			HashMap<String, ArrayList<String>> section_column_values =CriteriaParser.parseKeyValueList(">", null, str); //section0>Column1:My Team;Course Status;
			Iterator<String> ite_section = section_column_values.keySet().iterator();
			while(ite_section.hasNext()){
				String section = ite_section.next();
				
				By target = null, src = null, by = null;
				String target_str = "";
				if(section.contains("1")){
					target_str = "//div[@class='template-section-widgets' and @data-section-index='0']/";
				}else if(section.contains("2")){
					target_str = "//div[@class='template-section-widgets' and @data-section-index='1']/";
				}else if(section.contains("3")){
					target_str = "//div[@class='template-section-widgets' and @data-section-index='2']/";
				}
				
				ArrayList<String> column_values = section_column_values.get(section); //Column1:My Team;Course Status;
				for(String column_value: column_values){
					HashMap<String, ArrayList<String>> column_values_map =CriteriaParser.parseKeyValueList(":", ";", column_value); //column1:My Team;Course Status;
					Iterator<String> ite_column = column_values_map.keySet().iterator();
					while(ite_column.hasNext()){
						String column = ite_column.next();
						
						StringBuilder target_str_build = new StringBuilder(target_str);
						if(column.contains("1")){
							target_str_build.append("div[@data-column-index='0']/ul"); 
						}else if(column.contains("2")){
							target_str_build.append("div[@data-column-index='1']/ul");
						}else if(column.contains("3")){
							target_str_build.append("div[@data-column-index='2']/ul");
						}
						target = By.xpath(target_str_build.toString()); 						
						int size;// = WebDriverUtils.getHowManyByPresntInPage(target);
						WebDriverUtils.scrollWindowToElement(driver, target);
						ArrayList<String> modules = column_values_map.get(column);
						for(String module: modules){
							src = By.xpath("//div[@id='widgetLibraryDiv']/ul/li[contains(text(),'"+ module +"')]");
							size = WebDriverUtils.getHowManyByPresntInPage(driver,src, false);
							WebDriverUtils.dragAndDrop(driver, src, target);	
							
							by = By.xpath("//div[@class='ui-dialog-buttonset']");
							size = WebDriverUtils.getHowManyByPresntInPage(driver,by, false);
							if(size > 0){
								by = By.xpath("//div[@class='ui-dialog-buttonset']/button/span[text()='OK']");
								WebDriverUtils.clickButton(driver, by);
							}
						}
					}
				}
			}
		}
	}
	
	public void setSections_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("templateRows");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}
	
	public void setColumns_UI(WebDriver driver, String str){
		if(!str.equals("")){
			HashMap<String, ArrayList<String>> section_columns =CriteriaParser.parseKeyValueList(":", null, str);  //section0 -> 1 Column;
			Iterator<String> ite_section = section_columns.keySet().iterator();
			while(ite_section.hasNext()){
				String section = ite_section.next();
				String column = section_columns.get(section).get(0);
				
				String target_str = "";
				if(section.contains("1")){
					target_str = "//div[@class='template-section display-block' and @data-section-index='0']/";
				}else if(section.contains("2")){
					target_str = "//div[@class='template-section display-block' and @data-section-index='1']/";
				}else if(section.contains("3")){
					target_str = "//div[@class='template-section display-block' and @data-section-index='2']/";
				}
				
				By by = By.xpath(target_str + "dl/select");
				WebDriverUtils.select_selector(driver, by, column);				
			}
		}
	}

	public void runCreate(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"ManageCenter", "HomePageManager"));
		
		By by = By.xpath("//button[@title='"+Labels.Btn_Create_HomeTempl+"']");
		WebDriverUtils.clickButton(driver, by);
		
		this.setTemplateName_UI(driver, this.getTemplateName());
		this.setSections_UI(driver, this.getSections());
		this.setColumns_UI(driver, this.getColumns());
		this.setModules_UI(driver, this.getModules());
	    
		by = By.xpath("//input[@value='Save']");
		WebDriverUtils.clickButton(driver, by);
  }
}