package lab_5;


/**
 * Клас, що описує аксесуари квітів.
 */
public class Accessory {
    private String name;
    private double price;

    public Accessory(String name, double price) {
        if (price < 0) throw new IllegalArgumentException("Ціна не може бути від'ємною");
        this.name = name;
        this.price = price;
    }

    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " (" + price + ")";
    }
}