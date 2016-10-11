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
			
			snack.setName(getSingleChild(coldSnacksElement, "Название").getTextContent().trim());
			snack.setPortion(getSingleChild(coldSnacksElement, "Порция").getTextContent().trim());
			snack.setPrice(getSingleChild(coldSnacksElement, "Цена").getTextContent().trim());
			
			String description = getSingleChild(coldSnacksElement, "Описание").getTextContent().trim();
			Description descr = new Description();
			descr.setValue(description);
			snack.setDescription(descr);
			
			menu.getColdSnacks().add(snack);
		}
		
		NodeList hotSnacks = root.getElementsByTagName("Горячие_закуски");
		
		for (int i = 0; i < hotSnacks.getLength(); i++) {
			snack = new Snack();
			Element hotSnacksElement = (Element) hotSnacks.item(i);
			snack.setId(hotSnacksElement.getAttribute("id"));
			
			snack.setName(getSingleChild(hotSnacksElement, "Название").getTextContent().trim());
			snack.setPortion(getSingleChild(hotSnacksElement, "Порция").getTextContent().trim());
			snack.setPrice(getSingleChild(hotSnacksElement, "Цена").getTextContent().trim());
			
			String description = getSingleChild(hotSnacksElement, "Описание").getTextContent().trim();
			Description descr = new Description();
			descr.setValue(description);
			snack.setDescription(descr);
			
			menu.getHotSnacks().add(snack);
		}
		
		NodeList breakfast = root.getElementsByTagName("Завтраки");
		
		for (int i = 0; i < breakfast.getLength(); i++) {
			snack = new Snack();
			Element breakfastElement = (Element) breakfast.item(i);
			snack.setId(breakfastElement.getAttribute("id"));
			
			snack.setName(getSingleChild(breakfastElement, "Название").getTextContent().trim());
			snack.setPortion(getSingleChild(breakfastElement, "Порция").getTextContent().trim());
			snack.setPrice(getSingleChild(breakfastElement, "Цена").getTextContent().trim());
			
			String description = getSingleChild(breakfastElement, "Описание").getTextContent().trim();
			Description descr = new Description();
			descr.setValue(description);
			snack.setDescription(descr);
			
			menu.getBreakfasts().add(snack);
		}
		System.out.println(menu);
		
	}
	
	private static Element getSingleChild(Element element, String childName){
	    NodeList nlist = element.getElementsByTagName(childName);
	    Element child = (Element) nlist.item(0);
	    return child;
	}
}
