package training.parsers.sax.model;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Snack> coldSnacks = new ArrayList<>();
    private List<Snack> hotSnacks = new ArrayList<>();
    private List<Snack> breakfasts = new ArrayList<>();

    public List<Snack> getColdSnacks() {
        return coldSnacks;
    }

    public void setColdSnacks(List<Snack> coldSnacks) {
        this.coldSnacks = coldSnacks;
    }

    public List<Snack> getHotSnacks() {
        return hotSnacks;
    }

    public void setHotSnacks(List<Snack> hotSnacks) {
        this.hotSnacks = hotSnacks;
    }

    public List<Snack> getBreakfasts() {
        return breakfasts;
    }

    public void setBreakfasts(List<Snack> breakfasts) {
        this.breakfasts = breakfasts;
    }

    @Override
    public String toString() {
        return "Menu:" +
                "\n\tCold Snacks:" + coldSnacks +
                "\n\tHot Snacks:" + hotSnacks +
                "\n\tBreakfasts:" + breakfasts;
    }
}
