package training.parsers.sax.model;

import java.util.ArrayList;
import java.util.List;

public class Description {
    private String value;
    private List<AdditionalDescription> additionalDescriptions = new ArrayList<>();

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<AdditionalDescription> getAdditionalDescriptions() {
        return additionalDescriptions;
    }

    public void setAdditionalDescriptions(List<AdditionalDescription> additionalDescriptions) {
        this.additionalDescriptions = additionalDescriptions;
    }

    @Override
    public String toString() {
        return "\n\t\t\t\tDescription:" +
                "\n\t\t\t\t\tvalue: " + value +
                "\n\t\t\t\t\tAdditional Descriptions: " + additionalDescriptions;
    }
}
