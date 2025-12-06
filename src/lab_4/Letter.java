package lab_4;

/**
 * Клас, що представляє одну літеру.
 */
public class Letter {
    private char value;

    public Letter(char value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}