package lab_4;

/**
 * Лабораторна робота №4
 * Тема: Відношення між класами в мові програмування Java.
 * Завдання: модифікація лабораторної 2
 */


import java.util.List;

/**
 * Головний клас.
 * Виконує завдання: Знайти таке слово в першому реченні, якого немає в жодному з наступних.
 */
public class Main {
    public static void main(String[] args) {
        // Вхідний текст
        String rawText = "Римська імперія і Карфаген. Імперія знищила Карфаген і підібрала його сателіти.";

        try {
            // Створення об'єкта Текст (автоматично прибирає зайві пробіли і парсить структуру)
            Text text = new Text(rawText);

            System.out.println("Введений текст (після обробки):\n" + text);
            System.out.println("--------------------------------------------------");

            Word uniqueWord = findUniqueWord(text);

            if (uniqueWord != null) {
                System.out.println("Результат (Унікальне слово): " + uniqueWord);
            } else {
                System.out.println("Унікальних слів в першому реченні не знайдено.");
            }

        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Алгоритм пошуку унікального слова
     */
    private static Word findUniqueWord(Text text) {
        Sentence[] sentences = text.getSentences();

        if (sentences.length < 2) {
            System.out.println("Текст повинен містити хоча б два речення.");
            return null;
        }

        // Отримуємо список слів першого речення
        List<Word> wordsInFirstSentence = sentences[0].getWords();

        // Перебираємо кожне слово з першого речення
        for (Word candidateWord : wordsInFirstSentence) {
            boolean foundInOtherSentences = false;

            // Перевіряємо всі наступні речення
            for (int i = 1; i < sentences.length; i++) {
                List<Word> wordsInOtherSentence = sentences[i].getWords();

                for (Word otherWord : wordsInOtherSentence) {
                    // Порівняння слів (ігноруючи регістр)
                    if (candidateWord.equalsIgnoreCase(otherWord)) {
                        foundInOtherSentences = true;
                        break;
                    }
                }
                if (foundInOtherSentences) break; // Якщо знайшли, далі не шукаємо
            }

            // Якщо слово не знайдено в інших реченнях — це наш результат
            if (!foundInOtherSentences) {
                return candidateWord;
            }
        }
        return null;
    }
}