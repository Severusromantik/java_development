package lab_4;

/**
 * Клас, що представляє Текст як масив речень.
 */
public class Text {
    private Sentence[] sentences;

    public Text(String rawText) {
        // Замінюємо послідовність табуляцій та пробілів одним пробілом
        String cleanedText = rawText.replaceAll("[\\s\\t]+", " ").trim();
        parseSentences(cleanedText);
    }

    private void parseSentences(String text) {
        // Розбиваємо текст на речення за розділовими знаками (. ! ?)
        // (?<=[.!?]) — це lookbehind, щоб залишити розділовий знак у реченні
        String[] rawSentences = text.split("(?<=[.!?])\\s*");

        sentences = new Sentence[rawSentences.length];
        for (int i = 0; i < rawSentences.length; i++) {
            sentences[i] = new Sentence(rawSentences[i]);
        }
    }

    public Sentence[] getSentences() {
        return sentences;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Sentence s : sentences) {
            sb.append(s).append(" ");
        }
        return sb.toString().trim();
    }
}