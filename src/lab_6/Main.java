package lab_6;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Set на двозв'язному списку ---\n");

        // 1. Конструктор за замовчуванням
        FlowerSet<Flower> set1 = new FlowerSet<>();
        set1.add(new Rose(50.0, 10));
        set1.add(new Tulip(30.0, 8));

        // Спроба додати дублікат (та сама ціна, назва, свіжість)
        boolean added = set1.add(new Rose(50.0, 10));

        System.out.println("Набір 1 (Дублікат додано? " + added + "):");
        printSet(set1);

        // 2. Конструктор з одним елементом
        FlowerSet<Flower> set2 = new FlowerSet<>(new Tulip(100.0, 10));
        System.out.println("\nНабір 2 (1 елемент):");
        printSet(set2);

        // 3. Конструктор зі стандартної колекції
        List<Flower> list = new ArrayList<>();
        list.add(new Rose(40.0, 5));
        list.add(new Rose(40.0, 5)); // Дублікат у вхідному списку
        list.add(new Tulip(25.0, 7));

        FlowerSet<Flower> set3 = new FlowerSet<>(list);
        System.out.println("\nНабір 3 (Створено зі списку з дублікатами):");
        // Дублікат має зникнути
        printSet(set3);

        System.out.println("Розмір набору 3: " + set3.size());
    }

    private static void printSet(FlowerSet<Flower> set) {
        for (Flower f : set) {
            System.out.println(" -> " + f);
        }
    }
}
