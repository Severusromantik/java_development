package lab_1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    private static final Random RANDOM = new Random();

    // Функція виведення матриці на екран
    public static void printMatrix(String name, int[][] matrix) {
        System.out.println("\n--- Матриця " + name + " ---");
        if (matrix.length == 0 || matrix[0].length == 0) {
            System.out.println("Матриця порожня.");
            return;
        }
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("Розмір: " + matrix.length + "x" + matrix[0].length);
    }

    // Функція генерації матриці випадковими числами
    public static int[][] generateMatrix(int rows, int cols, int maxVal) {
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Генеруємо ціле число від 1 до maxVal
                matrix[i][j] = RANDOM.nextInt(maxVal) + 1;
            }
        }
        return matrix;
    }

    // Функція для виконання матричного добутку
    public static int[][] multiplyMatrices(int[][] A, int[][] B) throws IllegalArgumentException {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;

        int[][] C = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    // Функція знаходження середнього значення елементів кожного стовпчика
    public static void findColumnAverages(int[][] C) {
        if (C.length == 0 || C[0].length == 0) {
            System.out.println("\nМатриця C порожня. Неможливо обчислити середні значення.");
            return;
        }

        int rowsC = C.length;
        int colsC = C[0].length;

        System.out.println("\n--- Результат другої дії: Середнє значення кожного стовпчика матриці C ---");

        for (int j = 0; j < colsC; j++) {
            long sum = 0;
            for (int i = 0; i < rowsC; i++) {
                sum += C[i][j];
            }

            double average = (double) sum / rowsC;

            System.out.printf("Середнє значення стовпчика %d: %.2f%n", j, average);
        }
    }

    // Виконавчий метод - main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rowsA = 0, colsA = 0, rowsB = 0, colsB = 0;
        int commonDim = 0;

        System.out.println("=== Введення розмірів матриць ===");

        try {
            System.out.println("Матриця A (M x K)");
            System.out.println("Матриця B (K x N)");
            System.out.print("Введіть кількість рядків матриці A (M): ");
            rowsA = scanner.nextInt();

            System.out.print("Введіть кількість стовпців матриці A і кількість рядків матриці B (K): ");
            commonDim = scanner.nextInt();
            colsA = commonDim;
            rowsB = commonDim;

            System.out.print("Введіть кількість стовпців матриці B (N): ");
            colsB = scanner.nextInt();

            if (rowsA <= 0 || commonDim <= 0 || colsB <= 0) {
                throw new IllegalArgumentException("Розміри матриць повинні бути додатніми числами.");
            }

            int MAX_VALUE = 100;

            int[][] A = generateMatrix(rowsA, colsA, MAX_VALUE);
            int[][] B = generateMatrix(rowsB, colsB, MAX_VALUE);
            int[][] C = null;

            // Дія 1: C = A * B (Матричний добуток)
            System.out.println("\n========================================================");
            System.out.println("=== Дія 1: матричний добуток C = A * B ===");
            printMatrix("A", A);
            printMatrix("B", B);

            C = multiplyMatrices(A, B);

            System.out.print("\nМатриця C:");
            printMatrix("C", C);

            // Дія 2: Знайти середнє значення елементів кожного стовпчика C
            System.out.println("\n========================================================");
            System.out.println("=== Дія 2: знаходження середнього значення в стовпчиках C ===");
            findColumnAverages(C);

        } catch (InputMismatchException e) {
            // Обробка, якщо користувач ввів не те число
            System.err.println("\n!!! ПОМИЛКА: Некоректне введення. Будь ласка, вводьте лише цілі числа.");
        } catch (IllegalArgumentException e) {
            // Обробка, якщо користувач ввів недопустимий розмір (0 або негативний)
            System.err.println("\n!!! ПОМИЛКА: " + e.getMessage());
        } catch (Exception e) {
            // Обробка будь-яких інших непередбачених винятків
            System.err.println("\n!!! СИСТЕМНА ПОМИЛКА: Виникла невідома виняткова ситуація.");
            System.err.println("Деталі: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}