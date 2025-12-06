package lab_4;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє Речення як масив слів та розділових знаків.
 */
public class Sentence {
    private SentenceElement[] elements;

    public Sentence(String sentenceString) {
        parseElements(sentenceString);
    }

    private void parseElements(String text) {
        List<SentenceElement> tempElements = new ArrayList<>();
        StringBuilder wordBuffer = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Punctuation.isPunctuation(c)) {
                // Якщо натрапили на знак, спершу зберігаємо слово з буфера (якщо воно є)
                if (wordBuffer.length() > 0) {
                    tempElements.add(new Word(wordBuffer.toString()));
                    wordBuffer.setLength(0);
                }
                // Додаємо сам знак
                tempElements.add(new Punctuation(c));
            } else if (Character.isWhitespace(c)) {
                // Пробіл означає кінець слова
                if (wordBuffer.length() > 0) {
                    tempElements.add(new Word(wordBuffer.toString()));
                    wordBuffer.setLength(0);
                }
            } else {
                // Накопичуємо літери слова
                wordBuffer.append(c);
            }
        }

        // Додаємо останнє слово, якщо воно залишилось у буфері
        if (wordBuffer.length() > 0) {
            tempElements.add(new Word(wordBuffer.toString()));
        }

        elements = tempElements.toArray(new SentenceElement[0]);
    }

    /**
     * Повертає список тільки слів (без розділових знаків) для зручності пошуку.
     */
    public List<Word> getWords() {
        List<Word> words = new ArrayList<>();
        for (SentenceElement element : elements) {
            if (element instanceof Word) {
                words.add((Word) element);
            }
        }
        return words;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            sb.append(elements[i].toString());
            // Додаємо пробіл, якщо поточний елемент слово, і наступний теж слово
            if (i < elements.length - 1
                    && elements[i] instanceof Word
                    && elements[i+1] instanceof Word) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}