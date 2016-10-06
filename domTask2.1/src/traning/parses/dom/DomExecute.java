package traning.parses.dom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomExecute {

	public static void main(String[] args) throws SAXException, IOException {
		DOMParser parser = new DOMParser(); 
		parser.parse("src/res/Menu.xml");
		Document document = parser.getDocument();
		
		Element root = document.getDocumentElement();
		
		Menu menu = new Menu();
		
		NodeList coldSnacks = root.getElementsByTagName("Холодные_закуски");
		
		Snack snack = null;
		
		for (int i = 0; i < coldSnacks.getLength(); i++) {
			snack = new Snack();
			Element coldSnacksElement = (Element) coldSnacks.item(i);
			snack.setId(coldSnacksElement.getAttribute("id"));
			
			//snack.setName(getSingleChild(coldSnacksElement, "name").getTextContent().trim());
			menu.getColdSnacks().add(snack);
		}
		
		System.out.println("Menu:\n" + menu);
		
	}
	
	private static Element getSingleChild(Element element, String childName){
	    NodeList nlist = element.getElementsByTagName(childName);
	    Element child = (Element) nlist.item(0);
	    return child;
	}
}
