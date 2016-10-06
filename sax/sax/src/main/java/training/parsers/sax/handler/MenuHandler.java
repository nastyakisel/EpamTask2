package training.parsers.sax.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import training.parsers.sax.model.AdditionalDescription;
import training.parsers.sax.model.Description;
import training.parsers.sax.model.Menu;
import training.parsers.sax.model.Snack;

import java.util.Arrays;
import java.util.List;


public class MenuHandler extends DefaultHandler {
    private static final String COLD_SNACK = "Холодные_закуски";
    private static final String HOT_SNACK = "Горячие_закуски";
    private static final String BREAKFAST = "Завтраки";
    private static final String ATTRIBUTE_ID = "id";
    private static final String SNACK_NAME = "Название";
    private static final String SNACK_DESCRIPTION = "Описание";
    private static final String SNACK_ADDITIONAL_DESCRIPTION = "Доп_описание";
    private static final String SNACK_PORTION = "Порция";
    private static final String SNACK_PRICE = "Цена";

    private static final List<String> SNACK_NAMES = Arrays.asList(COLD_SNACK, HOT_SNACK, BREAKFAST);

    private Menu menu;

    private Snack currentSnack;
    private Description currentDescription;
    private AdditionalDescription currentAdditionalDescription;

    private String currentLocalName;

    public MenuHandler(Menu menu) {
        this.menu = menu;
    }

    public void characters(char[] buffer, int start, int length) {
        String value = new String(buffer, start, length).trim();

        if (!value.isEmpty()) {
            switch (currentLocalName) {
                case SNACK_NAME:
                    currentSnack.setName(value);
                    break;
                case SNACK_DESCRIPTION:
                    currentDescription.setValue(value);
                    break;
                case SNACK_ADDITIONAL_DESCRIPTION:
                    currentAdditionalDescription.setValue(value);
                    break;
                case SNACK_PORTION:
                    currentSnack.setPortion(value);
                    break;
                case SNACK_PRICE:
                    currentSnack.setPrice(value);
                    break;
            }
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (isSnackName(localName)) {
            currentSnack = new Snack();
            currentSnack.setId(attributes.getValue(uri, ATTRIBUTE_ID));
        }

        switch (localName) {
            case COLD_SNACK:
                menu.getColdSnacks().add(currentSnack);
                break;
            case HOT_SNACK:
                menu.getHotSnacks().add(currentSnack);
                break;
            case BREAKFAST:
                menu.getBreakfasts().add(currentSnack);
                break;
            case SNACK_DESCRIPTION:
                currentDescription = new Description();
                currentSnack.setDescription(currentDescription);
                break;
            case SNACK_ADDITIONAL_DESCRIPTION:
                currentAdditionalDescription = new AdditionalDescription();
                currentDescription.getAdditionalDescriptions().add(currentAdditionalDescription);
                break;
        }

        currentLocalName = localName;
    }

    private static boolean isSnackName(String localName) {
        return SNACK_NAMES.contains(localName);
    }
}