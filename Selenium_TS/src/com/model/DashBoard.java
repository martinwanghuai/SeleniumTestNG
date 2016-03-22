package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.config.Config;
import com.dao.AdobeObject;
import com.dao.ChartType;
import com.utils.CriteriaParser;
import com.utils.DataUtils;
import com.utils.WebDriverUtils;
import com.view.FunctionUI;
import com.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class DashBoard extends com.abstractclasses.TestObject {
	
	public String ChartNameAndType;
	
	public String getChartNameAndType() {
		return ChartNameAndType;
	}

	public void setChartNameAndType(String chartNameAndType) {
		ChartNameAndType = chartNameAndType;
	}

	public boolean equals(com.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public DashBoard() {
		super();
	}

	
	public void runCheckDashboard(WebDriver driver) {
		Navigator.navigate(driver, Navigator.xmlWebElmtMgr.getNavigationPathList(
				"LearningCenter", "MyCharts"));
		
		//HashMap<String, ArrayList<String>> chart_values = UIFunctionUtils.parseParticipants(this.getExpectedResult());
		
		HashMap<String, ArrayList<String>> chartNameAndTypes = CriteriaParser.parseKeyValueList(">", "@", this.getExpectedResult());
		Iterator<String> chartNames = chartNameAndTypes.keySet().iterator();
	
		ArrayList<AdobeObject> objs = new ArrayList();
		
		while(chartNames.hasNext()){
			String chartName = chartNames.next(); //Performance Goal Completion
			String strChartType = chartNameAndTypes.get(chartName).get(0);//Table
			String strChartCheckingValues = chartNameAndTypes.get(chartName).get(1);//GCat1=8.333334;GCat2=8;Performance=8.333334;Uncategorized=8.333334;
			HashMap<String, ArrayList<String>> chartKeysAndValueList = CriteriaParser.parseKeyValueList("=", null, ";",strChartCheckingValues);
			Iterator<String> chartKeys = chartKeysAndValueList.keySet().iterator();

				int size= chartKeysAndValueList.size();
				String[] keys=new String[size];
				String[] expectedResults=new String[size];
				int counter=0;
				while(chartKeys.hasNext()){
					keys[counter]=chartKeys.next();;
					expectedResults[counter]= chartKeysAndValueList.get(keys[counter]).get(0);
					counter++;
				}
				
				By srcBy = By.xpath("//table/tbody[descendant::tr/td/div/div/span/h3[text()='" + chartName 
						+"']]/tr/td/div/object/param[@name='FlashVars']");
				
				String temp = DataUtils.decodeURL(WebDriverUtils.getAttribute(driver,
						srcBy, "value"));
				int index = temp.indexOf("=");
				String dataURL = temp.substring(index + 1);
				String url = "http://" + Config.getInstance().getProperty("IP") + ":"
						+ Config.getInstance().getProperty("port") + dataURL;
				
				ChartType chartType = null ;
				if(strChartType.contains("Pie")){
					chartType = ChartType.Pie;
				}else if(strChartType.contains("Histogram")){
					chartType = ChartType.Histogram;
				}else if(strChartType.contains("Table")){
					chartType = ChartType.Table;
				}
				
				AdobeObject obj = new AdobeObject(url, chartType, keys,expectedResults);
				objs.add(obj);
		
		}
		
		for(AdobeObject obj: objs){
			WebDriverUtils.checkAdobeFlashResults(driver, obj.getChartType(), obj.getUrl(), obj.getKeys(), obj.getExpectedResults());
		}
	}

}