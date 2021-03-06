package com.codecool;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {

    public Document getDoc(String xmlPath) {

        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
            doc = db.parse(xmlPath);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        doc.getDocumentElement().normalize();

        return doc;
    }

    public List<Card> CardParser(Document doc) {
        List<Card> cardList = new ArrayList<>();
        NodeList nodeList = doc.getElementsByTagName("Hero");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node tempNode = nodeList.item(i);
            Element tempElement = (Element) tempNode;
            Node attributeNode = tempElement.getElementsByTagName("Attribute").item(0);
            Element attributeElement = (Element) attributeNode;
            cardList.add(new Card(attributeElement.getAttribute("name"), attributeElement.getAttribute("game"),
                Integer.parseInt(attributeElement.getAttribute(("strength"))), Integer.parseInt(attributeElement.getAttribute(("endurance"))),
                Integer.parseInt(attributeElement.getAttribute(("intelligence"))), Integer.parseInt(attributeElement.getAttribute(("agility")))));
        }
        return cardList;
    }
}
