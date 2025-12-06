package lab_5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Клас, що описує букет квітів з аксесуарами.
 */
public class Bouquet {
    private List<Flower> flowers;
    private List<Accessory> accessories;

    public Bouquet() {
        flowers = new ArrayList<>();
        accessories = new ArrayList<>();
    }

    public void addFlower(Flower flower) {
        flowers.add(flower);
    }

    public void addAccessory(Accessory accessory) {
        accessories.add(accessory);
    }

    /**
     * Розраховує повну вартість букета.
     * @return сума цін квітів та аксесуарів.
     */
    public double calculateTotalPrice() {
        double total = 0;
        for (Flower f : flowers) {
            total += f.getPrice();
        }
        for (Accessory a : accessories) {
            total += a.getPrice();
        }
        return total;
    }

    /**
     * Сортує квіти в букеті за рівнем свіжості.
     * Використовує Comparator.
     */
    public void sortByFreshness() {
        // Сортуємо від найсвіжіших (10) до менш свіжих (1)
        flowers.sort(Comparator.comparingInt(Flower::getFreshnessLevel).reversed());
    }

    /**
     * Знаходить квіти, довжина стебла яких потрапляє в заданий діапазон.
     * @param minLength мінімальна довжина
     * @param maxLength максимальна довжина
     * @return список знайдених квітів
     */
    public List<Flower> findFlowersByLength(double minLength, double maxLength) {
        List<Flower> result = new ArrayList<>();
        for (Flower f : flowers) {
            if (f.getStemLength() >= minLength && f.getStemLength() <= maxLength) {
                result.add(f);
            }
        }
        return result;
    }

    public void printBouquetComposition() {
        System.out.println("--- Склад букета ---");
        for (Flower f : flowers) System.out.println(f);
        for (Accessory a : accessories) System.out.println("Аксесуар: " + a);
    }
}