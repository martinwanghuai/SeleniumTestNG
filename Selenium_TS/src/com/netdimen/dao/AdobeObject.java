package com.netdimen.dao;

public class AdobeObject {
	public String getUrl() {
		return url;
	}

	public String[] getKeys() {
		return keys;
	}

	public String[] getExpectedResults() {
		return expectedResults;
	}

	public ChartType getChartType() {
		return chartType;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
	}

	public void setExpectedResults(String[] expectedResults) {
		this.expectedResults = expectedResults;
	}

	public void setChartType(ChartType chartType) {
		this.chartType = chartType;
	}

	private String url;
	private String[] keys;
	private String[] expectedResults;
	private ChartType chartType;

	public AdobeObject(String url, ChartType chartType, String[] keys,
			String[] expectedResults) {
		this.url = url;
		this.chartType = chartType;
		this.keys = keys;
		this.expectedResults = expectedResults;
	}

}
