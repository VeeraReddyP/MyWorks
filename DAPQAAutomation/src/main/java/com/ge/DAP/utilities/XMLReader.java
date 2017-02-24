/**
 * 
 */
package com.ge.DAP.utilities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;

public class XMLReader {

	/**
	 * get all dom elements present in XML corresponding to elementName
	 * 
	 * @param fileName
	 * @param elementName
	 * @return map consisting all dom elements representing elementName
	 */
	public static HashMap<String, String> getDOMElements(String fileName, String elementName) {
		HashMap<String, String> domElements = new HashMap<String, String>();
		try {

			File inputFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getDocumentElement().getChildNodes();

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node parentElementNode = nodeList.item(i);
				if (parentElementNode.getNodeName().equals("element")) {
					String attName = parentElementNode.getAttributes().item(0).getTextContent();

					if (attName.equals(elementName)) {
						NodeList childDomNodes = parentElementNode.getChildNodes();
						for (int j = 0; j < childDomNodes.getLength(); j++) {
							String childNodeName = childDomNodes.item(j).getNodeName();
							String childNodeContent = childDomNodes.item(j).getTextContent();
							if (childNodeName.equals("xpath")) {
								domElements.put("xpath", childNodeContent);
							} else if (childNodeName.equals("id")) {
								domElements.put("id", childNodeContent);
							} else if (childNodeName.equals("css")) {
								domElements.put("css", childNodeContent);
							} else if (childNodeName.equals("class")) {
								domElements.put("class", childNodeContent);
							} else if (childNodeName.equals("tag")) {
								domElements.put("tag", childNodeContent);
							} else if (childNodeName.equals("partialLinkText")) {
								domElements.put("partialLinkText", childNodeContent);
							} else if (childNodeName.equals("linkText")) {
								domElements.put("linkText", childNodeContent);
							} else if (childNodeName.equals("name")) {
								domElements.put("name", childNodeContent);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return domElements;
	}

	public static String getValue(String parentNode, String childNode, String pathOfFile) {

		String val = null;
		try {
			File inputFile = new File((System.getProperty("user.dir") + pathOfFile));
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			;
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName(parentNode);

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					val = (eElement.getElementsByTagName(childNode).item(0).getTextContent());

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;

	}

	public static void main(String[] args) {
		System.out.println(getDOMElements(
				"/Users/madhurichilaka/Documents/automation_Related/MySample/src/main/java/com/testing/MySample/LoginPage.xml",
				"SSOUser"));

		/*
		 * String a = System.getProperty("user.dir")+
		 * "/src/main/java/com/testing/MySample/LoginPage.xml";
		 * 
		 * XMLReader xr = new XMLReader(); HashMap<String, String> hm =
		 * xr.getDOMElements(a,"SSOUser");
		 * System.out.println(hm.get("SSOUser"));
		 */

	}

}
