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

import com.netdimen.config.Config;

public class XMLWebElementManager {

	private Document m_doc;
	private static XMLWebElementManager instance;

	private XMLWebElementManager(String xmlfile) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			File Xml = new File(xmlfile);
			m_doc = builder.parse(new FileInputStream(Xml));
		} catch (IOException | SAXException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static XMLWebElementManager getInstance() {
		// String xmlfile = "./Conf/WebElements.xml";
		String xmlfile = System.getProperty("user.dir") + "\\"
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

	public By getBy(String parentNodeName, String targetChildName) {
		WebElementWrapper webElm = getWebElementWrapper(parentNodeName,
				targetChildName);
		return webElm.getBy();
	}

	private int getChildCount(String parentTag, int parentIndex, String childTag) {
		NodeList list = m_doc.getElementsByTagName(parentTag);
		Element parent = (Element) list.item(parentIndex);
		NodeList childList = parent.getElementsByTagName(childTag);
		return childList.getLength();
	}

	private String getChildValue(String parentTag, int parentIndex,
			String childTag, int childIndex) {
		NodeList list = m_doc.getElementsByTagName(parentTag);
		Element parent = (Element) list.item(parentIndex);
		NodeList childList = parent.getElementsByTagName(childTag);
		Element field = (Element) childList.item(childIndex);
		Node child = field.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}

	private String getChildAttribute(String parentTag, int parentIndex,
			String childTag, int childIndex, String attributeTag) {
		NodeList list = m_doc.getElementsByTagName(parentTag);
		Element parent = (Element) list.item(parentIndex);
		NodeList childList = parent.getElementsByTagName(childTag);
		Element element = (Element) childList.item(childIndex);
		return element.getAttribute(attributeTag);
	}

	private Element getChild(String parentName, String targetChildName) {
		NodeList list = m_doc.getElementsByTagName(parentName);
		if (list.getLength() > 2 || list.getLength() < 1)
			return null; // Error in xml document as there should be only one
							// parent element in xml files
		Element parent = (Element) list.item(0);// get the parent element
		ArrayList<Node> child = new ArrayList<Node>();
		visitRecursively(list.item(0), targetChildName, child);
		if (child.size() == 0) {
			throw new RuntimeException("Cannot find the targer child element="
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
	public WebElementWrapper getWebElementWrapper(String parentNodeName,
			String targetChildName) {
		return createWebElementWrapperFromXMLElement(getChild(parentNodeName,
				targetChildName));
	}

	private void visitRecursively(Node node, String targetChildName,
			ArrayList<Node> child) {

		// get all child nodes
		NodeList list = node.getChildNodes();

		for (int i = 0; i < list.getLength(); i++) {
			// get child node

			Node childNode = list.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE
					&& targetChildName.equals(childNode.getNodeName())) {
				// System.out.println("Found Node: " + childNode.getNodeName()+
				// " - with value: " + childNode.getNodeValue());
				child.add(childNode);
			}
			// visit child node
			visitRecursively(childNode, targetChildName, child);
		}

	}

	private WebElementWrapper createWebElementWrapperFromXMLElement(
			Element element) {
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
			String parentNodeName, String targetChildName) {

		ArrayList<WebElementWrapper> list = new ArrayList<WebElementWrapper>();
		ArrayList<Element> elementsList = new ArrayList<Element>();
		Element elemt = getChild(parentNodeName, targetChildName);
		elementsList.add(elemt);
		do {
			elemt = (Element) elemt.getParentNode();
			elementsList.add(elemt);
		} while (!elemt.getNodeName().equals(parentNodeName));
		Collections.reverse(elementsList);

		for (int i = 0; i < elementsList.size(); i++) {
			Element element = (Element) (elementsList.get(i));
			// if (!element.getNodeName().equals(parentNodeName)){
			list.add(createWebElementWrapperFromXMLElement(element));
			// }
		}
		return list;

	}

	public static void main(String[] args) {
		try {
			// XMLWebElementManager doc = new
			// XMLWebElementManager("./Conf/WebElements.xml");
			XMLWebElementManager doc = XMLWebElementManager.getInstance();
			ArrayList<WebElementWrapper> list = doc.getNavigationPathList(
					"LearningCenter", "OrgUserReview");
			for (WebElementWrapper elem : list) {
				System.out.println(elem.getElementValue());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}