package com.dao;

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

	public void setUrl(final String url) {
		this.url = url;
	}

	public void setKeys(final String[] keys) {
		this.keys = keys;
	}

	public void setExpectedResults(final String[] expectedResults) {
		this.expectedResults = expectedResults;
	}

	public void setChartType(final ChartType chartType) {
		this.chartType = chartType;
	}

	private String url;
	private String[] keys;
	private String[] expectedResults;
	private ChartType chartType;

	public AdobeObject(final String url, final ChartType chartType, final String[] keys,
			final String[] expectedResults) {
		this.url = url;
		this.chartType = chartType;
		this.keys = keys;
		this.expectedResults = expectedResults;
	}

}
