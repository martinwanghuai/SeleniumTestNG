package com.view;

import org.openqa.selenium.By;

import com.config.Config;

/**
 * 
 * @author lester.li This is a wrapper class for WebElement
 */
public class WebElementWrapper {
	private String ElementType;
	private String ElementValue;
	private String id;
	private By by;
	private String parentId;
	public static final String ROOT = "ROOT";
	public static final String ByClassName = "CLASSNAME";
	public static final String ByCssSelector = "CSSSELECTOR";
	public static final String ById = "ID";
	public static final String ByLinkText = "LINKTEXT";
	public static final String ByName = "NAME";
	public static final String ByPartialLinkText = "PARTIALLINKTEXT";
	public static final String ByTagName = "TAGNAME";
	public static final String ByXPath = "XPATH";
	public static final String LABLEKEY = "LabelKey";
	public static final String IsTopMenu = "True";
	public static final String Parameter = "Parameter";

	private String parameter = "";

	public String getParameter() {
		return parameter;
	}

	public void setParameter(final String parameter) {
		this.parameter = parameter;
	}

	public boolean isTopMenu() {
		return topMenu;
	}

	public void setTopMenu(final boolean topMenu) {
		this.topMenu = topMenu;
	}

	private boolean topMenu = false;

	/**
	 * 
	 * @param id
	 *            map to ID column in sheet
	 * @param type
	 *            map to type column in sheet
	 * @param value
	 *            map to value column in sheet
	 * @param parentId
	 *            map to parentId column in sheet
	 * @param sheet
	 *            : the sheet hold the WebElement
	 * @throws Exception
	 *             if type is not supported
	 */
	public WebElementWrapper(final String id, final String type, final String value,
			final String parameter, final boolean topMenu, final String parentId) {
		this.setId(id);
		this.setElementType(type);
		this.setElementValue(value);
		this.setParentId(parentId);
		this.setParameter(parameter);
		this.setTopMenu(topMenu);
		this.setBy(this.constructBy(type, value, parameter, topMenu));
	}

	private By constructBy(final String type, String value, final String parameter,
			final boolean topMenu) {
		By by = null;
		switch (type.toUpperCase()) {
		case ByClassName:
			by = By.className(value);
			break;
		case ByCssSelector:
			by = By.cssSelector(value);
			break;
		case ById:
			by = By.id(value);
			break;
		case ByLinkText:
			if (topMenu)
				by = By.linkText(Config.getInstance().getProperty(value)
						.toUpperCase());
			else
				by = By.linkText(Config.getInstance().getProperty(value));
			break;
		case ByName:
			by = By.name(value);
			break;
		case ByPartialLinkText:
			if (parameter.equals(WebElementWrapper.LABLEKEY))
				by = By.partialLinkText(Config.getInstance().getProperty(value));
			else
				By.partialLinkText(value);
			break;
		case ByTagName:
			by = By.tagName(value);
			break;
		case ByXPath:// self customized xpath
			if (parameter.equals(WebElementWrapper.LABLEKEY)) {
				final String labelKey = value.split("'")[1]; // example:
														// xpath=//a[descendant::span[text()='menu.org.user.review']]
				final String label_text = Config.getInstance().getProperty(labelKey);
				value = value.replace(labelKey, label_text);
				by = By.xpath(value);
			} else {
				by = By.xpath(value);
			}
			break;
		default:
			throw new AssertionError("WebElementWrapper does not support such WebElement type="
					+ this.ElementType);
		}

		return by;
	}

	public String getElementType() {
		return ElementType;
	}

	public void setElementType(final String elementType) {
		ElementType = elementType;
	}

	public String getElementValue() {
		if (ElementValue.contains("?")) {
			throw new AssertionError(
					"Error>>WebElementWrapper:paramters is not set yet "
							+ ElementValue
							+ "Pls use setParamter(String[] parameters)  to replace ? before call getElementValue");
		} else {
			return ElementValue;
		}
	}

	public void setElementValue(final String elementValue) {
		ElementValue = elementValue;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public By getBy() {
		return by;
	}

	public void setBy(final By by) {
		this.by = by;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(final String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 
	 * @param parameters
	 *            to replace the "?" in the element value
	 */
	public void setParamters(final String[] parameters) {
		// check if value has "?" to replace
		if (this.ElementValue.contains("?")) {
			final String[] splited = this.ElementValue.split("[?]");
			if (splited.length == parameters.length + 1) {
				this.ElementValue = "";
				for (int i = 0; i < splited.length - 1; i++) {
					this.ElementValue = this.ElementValue
							+ splited[i].concat(parameters[i]);
				}
				this.ElementValue = this.ElementValue
						+ splited[splited.length - 1];
			} else {
				throw new AssertionError(
						"Error>>WebElementWrapper: setParamter: parameters size is not matched");
			}
			by = By.xpath(this.ElementValue);
		} else {
			throw new AssertionError(
					"Error>>WebElementWrapper: setParamter: parameters not existed");
		}

	}

	public WebElementWrapper Clone() {
		return new WebElementWrapper(this.getId(), this.getElementType(),
				this.ElementValue, this.getParameter(), this.isTopMenu(),
				this.getParentId());
	}

}
