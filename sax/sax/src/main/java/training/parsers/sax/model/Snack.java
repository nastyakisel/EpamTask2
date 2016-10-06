package training.parsers.sax.model;

public class Snack {
    private String id;
    private String name;
    private Description description;
    private String portion;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "\n\t\tSnack:" +
                "\n\t\t\tid: " + id +
                "\n\t\t\tname: " + name +
                "\n\t\t\tdescription: " + description +
                "\n\t\t\tportion: " + portion +
                "\n\t\t\tprice: " + price;
    }
}
