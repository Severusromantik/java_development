package lab_6;

import java.util.Objects;

/**
 * Абстрактний базовий клас, що описує загальні властивості будь-якої квітки.
 * Визначає поля: назва, ціна, рівень свіжості.
 */
public abstract class Flower {
    private String name;
    private double price;
    private int freshnessLevel;

    /**
     * Конструктор для створення об'єкта квітки.
     * @param name Назва квітки
     * @param price Ціна квітки
     * @param freshnessLevel Рівень свіжості (ціле число, наприклад, від 1 до 10)
     */
    public Flower(String name, double price, int freshnessLevel) {
        this.name = name;
        this.price = price;
        this.freshnessLevel = freshnessLevel;
    }

    // Цей метод повертає назву квітки.
    public String getName() { return name; }

    // Цей метод повертає ціну квітки.
    public double getPrice() { return price; }

    @Override
    // Цей метод формує та повертає текстовий опис квітки (для виводу в консоль).
    public String toString() {
        return String.format("%s (%.2f грн, св: %d)", name, price, freshnessLevel);
    }

    @Override
    // Цей метод перевіряє, чи є два об'єкти "логічно" однаковими (по вмісту, а не по посиланню).
    // Це потрібно для роботи Set: якщо цей метод поверне true, колекція вважатиме об'єкт дублікатом.
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flower)) return false;
        Flower flower = (Flower) o;
        // Квіти вважаються однаковими, якщо співпадає назва, ціна і свіжість
        return Double.compare(flower.price, price) == 0 &&
                freshnessLevel == flower.freshnessLevel &&
                Objects.equals(name, flower.name);
    }

    @Override
    // Цей метод генерує унікальний числовий код на основі полів об'єкта.
    // Він працює в парі з equals() і необхідний для коректної роботи хеш-колекцій.
    public int hashCode() {
        return Objects.hash(name, price, freshnessLevel);
    }
}