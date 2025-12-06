package lab_4;

/**
 * Клас, що представляє розділовий знак.
 */
public class Punctuation implements SentenceElement {
    private char symbol;

    public Punctuation(char symbol) {
        this.symbol = symbol;
    }

    public static boolean isPunctuation(char c) {
        // Перелік символів, які вважаємо розділовими знаками
        return ",.!?;:".indexOf(c) != -1;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}