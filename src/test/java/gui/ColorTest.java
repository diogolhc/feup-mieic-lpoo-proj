package gui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ColorTest {
    @Test
    public void invalidThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Color(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Color("012345"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Color("#01234"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Color("#"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Color("#0123456"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Color("#abcdez"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Color("#ffgfff"));
    }

    @Test
    public void validDoesNotThrow() {
        Assertions.assertDoesNotThrow(() -> new Color("#abcdef"));
        Assertions.assertDoesNotThrow(() -> new Color("#ABCDEF"));
        Assertions.assertDoesNotThrow(() -> new Color("#ab0011"));
        Assertions.assertDoesNotThrow(() -> new Color("#0A1b2C"));
    }
}
