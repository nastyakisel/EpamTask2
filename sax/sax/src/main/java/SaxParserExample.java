import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import training.parsers.sax.handler.MenuHandler;
import training.parsers.sax.model.Menu;

import java.io.IOException;


public class SaxParserExample {

    public static final String MENU_FILE = "Menu.xml";

    public Menu readMenu(String menuFile) throws SAXException, IOException {
        Menu menu = new Menu();

        XMLReader menuReader = XMLReaderFactory.createXMLReader();
        menuReader.setContentHandler(new MenuHandler(menu));
        menuReader.parse(new InputSource(getClass().getResourceAsStream(menuFile)));

        return menu;
    }

    public static void main(String[] args) throws Exception {
        final SaxParserExample saxParserExample = new SaxParserExample();
        final Menu menu = saxParserExample.readMenu(MENU_FILE);

        System.out.println("Menu:\n" + menu);
    }
}
