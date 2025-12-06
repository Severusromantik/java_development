package lab_5;

public class Main {
    public static void main(String[] args) {
        try {
            // Створення букета
            Bouquet myBouquet = new Bouquet();

            // Додавання квітів (різні ціни, свіжість, довжина)
            myBouquet.addFlower(new Rose(45.0, 9, 50.5));
            myBouquet.addFlower(new Rose(40.0, 7, 45.0));
            myBouquet.addFlower(new Tulip(25.0, 10, 30.0));
            myBouquet.addFlower(new Chamomile(15.0, 5, 25.0));
            myBouquet.addFlower(new Tulip(20.0, 6, 28.0));

            // Додавання аксесуарів
            myBouquet.addAccessory(new Accessory("Стрічка", 10.0));
            myBouquet.addAccessory(new Accessory("Крафтовий папір", 25.0));

            // Виведення початкового стану та вартості
            myBouquet.printBouquetComposition();
            System.out.println("Загальна вартість букета: " + myBouquet.calculateTotalPrice() + " грн.");

            // Сортування за свіжістю
            System.out.println("\n--- Сортування за свіжістю (від найсвіжіших) ---");
            myBouquet.sortByFreshness();
            myBouquet.printBouquetComposition();

            // Пошук квітки за діапазоном довжини
            double minLen = 25.0;
            double maxLen = 35.0;
            System.out.println("\n--- Пошук квітів довжиною від " + minLen + " до " + maxLen + " см ---");
            var foundFlowers = myBouquet.findFlowersByLength(minLen, maxLen);

            if (foundFlowers.isEmpty()) {
                System.out.println("Квітів у такому діапазоні не знайдено.");
            } else {
                for (Flower f : foundFlowers) {
                    System.out.println(f);
                }
            }


        } catch (IllegalArgumentException e) {
            System.err.println("Помилка при створенні об'єкта: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Непередбачена помилка: " + e.getMessage());
        }
    }
}
