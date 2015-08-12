package com.netdimen.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.common.collect.Lists;
import com.netdimen.config.Config;

public class XMLWebElementManager {

	private Document m_doc;
	private static XMLWebElementManager instance;

	private XMLWebElementManager(final String xmlfile) {
		
		try {
			final DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final File Xml = new File(xmlfile);
			m_doc = builder.parse(new FileInputStream(Xml));
		} catch (IOException | SAXException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static XMLWebElementManager getInstance() {
		
		final String xmlfile = System.getProperty("user.dir") + "\\"
				+ Config.getInstance().getProperty("XMLWebElements");
		if (instance == null) {
			synchronized (XMLWebElementManager.class) { // Add a synch block
				if (instance == null) { // verify some other synch block didn't
										// create a WebElementManager yet...
					instance = new XMLWebElementManager(xmlfile);
				}
			}
		}
		return instance;
	}

	public By getBy(final String parentNodeName, final String targetChildName) {
		final WebElementWrapper webElm = getWebElementWrapper(parentNodeName,
				targetChildName);
		return webElm.getBy();
	}

	private int getChildCount(final String parentTag, final int parentIndex, final String childTag) {
		final NodeList list = m_doc.getElementsByTagName(parentTag);
		final Element parent = (Element) list.item(parentIndex);
		final NodeList childList = parent.getElementsByTagName(childTag);
		return childList.getLength();
	}

	private String getChildValue(final String parentTag, final int parentIndex,
			final String childTag, final int childIndex) {
		final NodeList list = m_doc.getElementsByTagName(parentTag);
		final Element parent = (Element) list.item(parentIndex);
		final NodeList childList = parent.getElementsByTagName(childTag);
		final Element field = (Element) childList.item(childIndex);
		final Node child = field.getFirstChild();
		if (child instanceof CharacterData) {
			final CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}

	private String getChildAttribute(final String parentTag, final int parentIndex,
			final String childTag, final int childIndex, final String attributeTag) {
		final NodeList list = m_doc.getElementsByTagName(parentTag);
		final Element parent = (Element) list.item(parentIndex);
		final NodeList childList = parent.getElementsByTagName(childTag);
		final Element element = (Element) childList.item(childIndex);
		return element.getAttribute(attributeTag);
	}

	private Element getChild(final String parentName, final String targetChildName) {
		final NodeList list = m_doc.getElementsByTagName(parentName);
		if (list.getLength() > 2 || list.getLength() < 1){
			return null; // Error in xml document as there should be only one
			// parent element in xml files
		}

		final ArrayList<Node> child = Lists.newArrayList();
		visitRecursively(list.item(0), targetChildName, child);
		if (child.size() == 0) {
			throw new AssertionError("Cannot find the targer child element="
					+ targetChildName);
		}
		return (Element) child.get(0);
	}

	/**
	 * 
	 * @param parentNodeName
	 * @param targetChildName
	 * @return
	 */
	public WebElementWrapper getWebElementWrapper(final String parentNodeName,
			final String targetChildName) {
		return createWebElementWrapperFromXMLElement(getChild(parentNodeName,
				targetChildName));
	}

	private void visitRecursively(final Node node, final String targetChildName,
			final ArrayList<Node> child) {

		// get all child nodes
		final NodeList list = node.getChildNodes();

		for (int i = 0; i < list.getLength(); i++) {
			// get child node

			final Node childNode = list.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE
					&& targetChildName.equals(childNode.getNodeName())) {
				child.add(childNode);
			}
			// visit child node
			visitRecursively(childNode, targetChildName, child);
		}

	}

	private WebElementWrapper createWebElementWrapperFromXMLElement(
			final Element element) {
		String type = "", id, value = "";
		String parameter = "";
		id = element.getNodeName();
		parameter = element.getAttribute(WebElementWrapper.Parameter);
		boolean topMenu = false;
		if (element.getAttribute("TopMenu").equals(WebElementWrapper.IsTopMenu)) {
			topMenu = true;
		}
		if (element.hasAttribute("XPath")) {
			type = WebElementWrapper.ByXPath;
			value = element.getAttribute("XPath");
		}
		if (element.hasAttribute("ClassName")) {
			type = WebElementWrapper.ByClassName;
			value = element.getAttribute("ClassName");
		}
		if (element.hasAttribute("CssSelector")) {
			type = WebElementWrapper.ByCssSelector;
			value = element.getAttribute("CssSelector");
		}
		if (element.hasAttribute("Id")) {
			type = WebElementWrapper.ById;
			value = element.getAttribute("Id");
		}
		if (element.hasAttribute("LinkText")) {
			type = WebElementWrapper.ByLinkText;
			value = element.getAttribute("LinkText");
		}
		if (element.hasAttribute("Name")) {
			type = WebElementWrapper.ByName;
			value = element.getAttribute("Name");
		}
		if (element.hasAttribute("TagName")) {
			type = WebElementWrapper.ByTagName;
			value = element.getAttribute("TagName");
		}
		if (element.hasAttribute("PartialLinkText")) {
			type = WebElementWrapper.ByPartialLinkText;
			value = element.getAttribute("PartialLinkText");
		}
		return new WebElementWrapper(id, type, value, parameter, topMenu,
				element.getParentNode().getLocalName());

	}

	public ArrayList<WebElementWrapper> getNavigationPathList(
			final String parentNodeName, final String targetChildName) {

		final ArrayList<WebElementWrapper> list = new ArrayList<WebElementWrapper>();
		final ArrayList<Element> elementsList = new ArrayList<Element>();
		Element elemt = getChild(parentNodeName, targetChildName);
		elementsList.add(elemt);
		do {
			elemt = (Element) elemt.getParentNode();
			elementsList.add(elemt);
		} while (!elemt.getNodeName().equals(parentNodeName));
		Collections.reverse(elementsList);

		for (int i = 0; i < elementsList.size(); i++) {
			final Element element = (elementsList.get(i));
			list.add(createWebElementWrapperFromXMLElement(element));
		}
		return list;

	}
}