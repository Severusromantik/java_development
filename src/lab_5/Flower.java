package lab_5;

/**
 * Абстрактний клас, що описує загальні властивості квітки.
 */
public abstract class Flower implements Comparable<Flower> {
    private String name;
    private double price;
    private int freshnessLevel;
    private double stemLength;

    /**
     * Конструктор квітки.
     * @param name Назва квітки
     * @param price Ціна
     * @param freshnessLevel Рівень свіжості (1-10)
     * @param stemLength Довжина стебла
     * @throws IllegalArgumentException якщо параметри некоректні
     */
    public Flower(String name, double price, int freshnessLevel, double stemLength) {
        if (price < 0 || stemLength < 0 || freshnessLevel < 1 || freshnessLevel > 10) {
            throw new IllegalArgumentException("Некоректні параметри для створення квітки.");
        }
        this.name = name;
        this.price = price;
        this.freshnessLevel = freshnessLevel;
        this.stemLength = stemLength;
    }

    public double getPrice() { return price; }
    public int getFreshnessLevel() { return freshnessLevel; }
    public double getStemLength() { return stemLength; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return String.format("%s (Ціна: %.2f, Свіжість: %d/10, Довжина: %.1f см)",
                name, price, freshnessLevel, stemLength);
    }

    // Реалізація Comparable для дефолтного сортування (наприклад, за свіжістю)
    @Override
    public int compareTo(Flower other) {
        return Integer.compare(this.freshnessLevel, other.freshnessLevel);
    }
}