package training.parsers.sax.model;

public class AdditionalDescription {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "\n\t\t\t\t\t\tAdditionalDescription:" +
                "\n\t\t\t\t\t\t\tvalue: " + value;
    }
}
