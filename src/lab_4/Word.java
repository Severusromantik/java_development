package lab_4;

import java.util.Arrays;

/**
 * Клас, що представляє Слово як масив літер.
 */
public class Word implements SentenceElement {
    private Letter[] letters;

    public Word(String wordString) {
        letters = new Letter[wordString.length()];
        for (int i = 0; i < wordString.length(); i++) {
            letters[i] = new Letter(wordString.charAt(i));
        }
    }

    /**
     * Метод для порівняння слів без урахування регістру.
     */
    public boolean equalsIgnoreCase(Word other) {
        return this.toString().equalsIgnoreCase(other.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Letter letter : letters) {
            sb.append(letter);
        }
        return sb.toString();
    }
}