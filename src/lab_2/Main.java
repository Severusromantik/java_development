package lab_2;
/**
 * Лабораторна робота №2
 * Тема: Рядки в мові програмування Java
 * Варіант: C3 = StringBuffer, C17 = Знайти унікальне слово в першому реченні.
 */

public class Main {

    public static void main(String[] args) {
        // Змінна тексту, що перевірятиметься
        StringBuffer text = new StringBuffer("Римська імперія і Карфаген. Імперія знищила Карфаген і підібрала його сателіти");

        System.out.println("Введений текст:\n" + text);
        System.out.println("--------------------------------------------------");

        try {
            validateText(text);
            StringBuffer result = findUniqueWord(text);

            if (result != null) {
                System.out.println("Результат (Унікальне слово): " + result);
            } else {
                System.out.println("Унікальних слів в першому реченні не знайдено.");
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Основний метод пошуку унікального слова.
     */
    private static StringBuffer findUniqueWord(StringBuffer text) {
        // 1. Кінець першого речення
        int firstSentenceEndIndex = findEndOfSentence(text);

        if (firstSentenceEndIndex == -1 || firstSentenceEndIndex == text.length() - 1) {
            throw new IllegalArgumentException("Текст має складатися щонайменше з 2 речень.");
        }

        // Виділяємо перше речення
        // substring повертає String, тому одразу загортаємо в StringBuffer
        StringBuffer firstSentence = new StringBuffer(text.substring(0, firstSentenceEndIndex));

        // Виділяємо решту тексту (всі наступні речення)
        StringBuffer remainingText = new StringBuffer(text.substring(firstSentenceEndIndex + 1));

        // 2. Розбиваємо перше речення на слова
        // Оскільки StringBuffer не має методу split, реалізуємо простий парсинг
        StringBuffer[] words = splitIntoWords(firstSentence);

        // 3. Перевіряємо кожне слово
        for (StringBuffer word : words) {
            if (word.length() == 0) continue;

            // Для порівняння приводимо до нижнього регістру, щоб ігнорувати регістр літер
            if (!containsWord(remainingText, word)) {
                return word; // Знайшли слово, якого немає далі
            }
        }

        return null;
    }

    /**
     * Метод для пошуку індексу закінчення першого речення (. ! ?)
     */
    private static int findEndOfSentence(StringBuffer text) {
        String delimiters = ".!?";
        for (int i = 0; i < text.length(); i++) {
            if (delimiters.indexOf(text.charAt(i)) != -1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Метод розбиття StringBuffer на масив слів (також StringBuffer)
     */
    private static StringBuffer[] splitIntoWords(StringBuffer sentence) {
        // Конвертуємо в рядок лише для зручності розбиття через regex, 
        // але повертаємо масив StringBuffer
        // Використовуємо пробіли та розділові знаки як роздільники.
        String s = sentence.toString();
        String[] rawWords = s.split("[\\s,;:.!?\"()]+");

        StringBuffer[] bufferWords = new StringBuffer[rawWords.length];
        for (int i = 0; i < rawWords.length; i++) {
            bufferWords[i] = new StringBuffer(rawWords[i]);
        }
        return bufferWords;
    }

    /**
     * Перевіряє, чи містить текст задане слово (цілком).
     */
    private static boolean containsWord(StringBuffer text, StringBuffer word) {
        String searchWord = word.toString().toLowerCase();
        String content = text.toString().toLowerCase();

        int index = 0;
        while ((index = content.indexOf(searchWord, index)) != -1) {
            // Перевіряємо межі слова
            boolean startOk = (index == 0) || !Character.isLetterOrDigit(content.charAt(index - 1));
            boolean endOk = (index + searchWord.length() == content.length()) ||
                    !Character.isLetterOrDigit(content.charAt(index + searchWord.length()));

            if (startOk && endOk) {
                return true;
            }
            index += searchWord.length();
        }
        return false;
    }

    /**
     * Перевірка на валідність вхідних даних
     */
    private static void validateText(StringBuffer text) {
        if (text == null || text.length() == 0) {
            throw new IllegalArgumentException("Введений текст не може бути порожнім.");
        }
    }
}