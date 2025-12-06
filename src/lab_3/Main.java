package lab_3;

/**
 * Лабораторна робота №3
 * Тема: Класи в мові програмування Java
 * Варіант: 9 - Визначити клас NPC
 */

import java.util.Arrays;
import java.util.Comparator;

/**
 * Клас, що описує Неігрового Персонажа (NPC).
 * Містить дані про ім'я, роль, рівень, здоров'я та фракцію.
 */
class NPC {
    // 5 полів класу (Fields)
    private String name;
    private String role;
    private int level;      // Поле для первинного сортування (за зростанням)
    private int health;     // Поле для вторинного сортування (за спаданням)
    private String faction;

    /**
     * Конструктор для створення об'єкта NPC.
     *
     * @param name    Ім'я персонажа
     * @param role    Роль (наприклад, "Торговець", "Воїн")
     * @param level   Рівень персонажа
     * @param health  Кількість здоров'я
     * @param faction Приналежність до фракції
     */
    public NPC(String name, String role, int level, int health, String faction) {
        this.name = name;
        this.role = role;
        this.level = level;
        this.health = health;
        this.faction = faction;
    }

    // Геттери (Getters) для доступу до приватних полів
    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    /**
     * Перевизначення методу toString для зручного виводу об'єкта в консоль.
     */
    @Override
    public String toString() {
        return "NPC{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", level=" + level +
                ", health=" + health +
                ", faction='" + faction + '\'' +
                '}';
    }

    /**
     * Перевизначення методу equals для коректного порівняння об'єктів.
     * Необхідно для пошуку ідентичного об'єкта в масиві.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NPC npc = (NPC) o;

        if (level != npc.level) return false;
        if (health != npc.health) return false;
        if (!name.equals(npc.name)) return false;
        if (!role.equals(npc.role)) return false;
        return faction.equals(npc.faction);
    }
}

/**
 * Головний клас для виконання.
 */
public class Main {

    /**
     * Виконавчий метод (entry point).
     */
    public static void main(String[] args) {
        // 1. Створення масиву об'єктів NPC
        NPC[] npcs = {
                new NPC("Gorlag", "Warrior", 5, 100, "Orcs"),
                new NPC("Elara", "Healer", 5, 80, "Elves"),
                new NPC("Gendalf", "Mage", 10, 60, "Humans"),
                new NPC("Zix", "Rogue", 1, 40, "Goblins"),
                new NPC("Bromb", "Blacksmith", 5, 120, "Dwarves")
        };

        System.out.println("--- Початковий масив ---");
        printArray(npcs);

        // 2. Сортування масиву стандартними засобами (Arrays.sort)
        // Сортуємо за Level (зростання) -> якщо Level рівний, то за Health (спадання)
        Arrays.sort(npcs, new Comparator<NPC>() {
            @Override
            public int compare(NPC n1, NPC n2) {
                // Порівняння першого поля (рівень)
                int levelCompare = Integer.compare(n1.getLevel(), n2.getLevel());

                // Якщо рівні не однакові, повертаємо результат порівняння рівнів
                if (levelCompare != 0) {
                    return levelCompare;
                }

                // Якщо рівні однакові, порівнюємо здоров'я
                return Integer.compare(n2.getHealth(), n1.getHealth());
            }
        });

        System.out.println("\n--- Відсортований масив (Level: ASC, Health: DESC) ---");
        printArray(npcs);

        // 3. Пошук ідентичного об'єкта
        // Створюємо об'єкт, який ми хочемо знайти
        NPC searchTarget = new NPC("Elara", "Healer", 5, 80, "Elves");
        boolean found = false;

        System.out.println("\n--- Результат пошуку: " + searchTarget.getName() + " ---");

        // Проходимо по масиву для пошуку
        for (int i = 0; i < npcs.length; i++) {
            if (npcs[i].equals(searchTarget)) {
                System.out.println("Об'єкт знайдено під індексом: " + i);
                System.out.println("Дані об'єкта: " + npcs[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Об'єкт не знайдено.");
        }
    }

    /**
     * Допоміжний метод для виводу масиву в консоль.
     *
     * @param array масив NPC
     */
    private static void printArray(NPC[] array) {
        for (NPC npc : array) {
            System.out.println(npc);
        }
    }
}