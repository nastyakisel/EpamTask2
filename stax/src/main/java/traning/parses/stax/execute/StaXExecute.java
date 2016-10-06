package traning.parses.stax.execute;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import traning.parses.stax.model.AdditionalDescription;
import traning.parses.stax.model.Description;
import traning.parses.stax.model.Menu;
import traning.parses.stax.model.Snack;

public class StaXExecute {

	private static final String COLD_SNACK = "Холодные_закуски";
	private static final String HOT_SNACK = "Горячие_закуски";
	private static final String BREAKFAST = "Завтраки";
	private static final String ATTRIBUTE_ID = "id";
	private static final String SNACK_NAME = "Название";
	private static final String SNACK_DESCRIPTION = "Описание";
	private static final String SNACK_ADDITIONAL_DESCRIPTION = "Доп_описание";
	private static final String SNACK_PORTION = "Порция";
	private static final String SNACK_PRICE = "Цена";

	private static final List<String> SNACK_NAMES = Arrays.asList(COLD_SNACK,
			HOT_SNACK, BREAKFAST);

	private Menu menu = new Menu();

	private Snack currentSnack;
	private Description currentDescription;
	private AdditionalDescription currentAdditionalDescription;

	private String currentLocalName;

	private List<Snack> snackList = new ArrayList<Snack>();
	
	private Menu readMenu(XMLStreamReader reader) throws XMLStreamException {

		while (reader.hasNext()) {

			int type = reader.next();

			switch (type) {

			case XMLStreamConstants.CHARACTERS:
				String text = reader.getText().trim();
				if (text.isEmpty()) {
					break;
				}
				switch (currentLocalName) {
				case SNACK_NAME:
					currentSnack.setName(text);
					break;
				case SNACK_DESCRIPTION:
					currentDescription.setValue(text);
					break;
				case SNACK_ADDITIONAL_DESCRIPTION:
					currentAdditionalDescription.setValue(text);
					break;
				case SNACK_PORTION:
					currentSnack.setPortion(text);
					break;
				case SNACK_PRICE:
					currentSnack.setPrice(text);
					break;
				}
				break;
			case XMLStreamConstants.START_ELEMENT:
				String localName = reader.getLocalName();
				if (isSnackName(localName)) {
					currentSnack = new Snack();
					String id = reader.getAttributeValue(null, ATTRIBUTE_ID);
					currentSnack.setId(id);
					snackList.add(currentSnack);
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
					currentDescription.getAdditionalDescriptions().add(
							currentAdditionalDescription);
					break;
				}
				currentLocalName = localName;
			}
		}
		return menu;
	}

	private static boolean isSnackName(String localName) {
		return SNACK_NAMES.contains(localName);
	}

	public static void main(String[] args) {

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		try {
			InputStream input = new FileInputStream(
					"src/main/resources/Menu.xml");

			XMLStreamReader reader = inputFactory.createXMLStreamReader(input);

			final StaXExecute exe = new StaXExecute();

			final Menu menu = exe.readMenu(reader);

			System.out.println("Menu:\n" + menu);

		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}
}
