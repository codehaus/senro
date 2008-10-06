package ro.siveco.senro.designer.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlHelper
{

    public static Document readDocument(File f) throws XmlException
    {
        Document doc;
        try {
            // Create a builder factory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setAttribute("http://apache.org/xml/features/dom/defer-node-expansion", Boolean.FALSE);

            // Create the builder and parse the file
            doc = factory.newDocumentBuilder().parse(f);
        }
        catch(SAXParseException e) {
            throw new XmlException("While reading the file " + f + "line: " + e.getLineNumber() + ", column: "
                                   + e.getColumnNumber(), e);
        }
        catch(Exception e) {
            throw new XmlException("While reading the file " + f, e);
        }
        return doc;
    }

    /**
     * Returns first child element with name child_name or null if such an element doesn't exists.
     *
     * @param parent the parent element
     * @param child_name the child element name
     * @return first child element with name child_name or null if such an element doesn't exists
     */
    public static Element getChild(Element parent, String child_name)
    {
        if(parent == null) {
            return null;
        }
        Element child = getFirstChildElement(parent);
        while(child != null) {
            if(child.getTagName().equals(child_name)) {
                return child;
            }
            child = getNextSiblingElement(child);
        }
        return null;
    }

    public static String getTextFromChild(Element e, String child_name)
    {
        Element child = getChild(e, child_name);
        return child == null ? null : child.getTextContent();
    }

    public static Element getNextSiblingElement(Element e)
    {
        if(e == null) {
            return null;
        }
        Node sibling = e.getNextSibling();
        while(sibling != null && (sibling.getNodeType() != Element.ELEMENT_NODE)) {
            sibling = sibling.getNextSibling();
        }
        return (Element)sibling;
    }

    public static Element getPreviousSiblingElement(Element e)
    {
        if(e == null) {
            return null;
        }
        Node sibling = e.getPreviousSibling();
        while(sibling != null && (sibling.getNodeType() != Element.ELEMENT_NODE)) {
            sibling = sibling.getPreviousSibling();
        }
        return (Element)sibling;
    }

    public static Element getFirstChildElement(Element e)
    {
        if(e == null) {
            return null;
        }
        Node child = e.getFirstChild();
        while(child != null && (child.getNodeType() != Element.ELEMENT_NODE)) {
            child = child.getNextSibling();
        }
        return (Element)child;
    }

}
