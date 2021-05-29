package model.farm;

import net.jqwik.api.AfterFailureMode;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CurrencyTest {
    @Test
    public void maxCurrency() {
        Assertions.assertEquals(new Currency(Currency.MAX_COINS), new Currency(1000000));
        Assertions.assertEquals(new Currency(Currency.MAX_COINS), new Currency(Integer.MAX_VALUE));
    }

    @Test
    public void negativeCurrency() {
        Assertions.assertEquals(new Currency(0), new Currency(-1000000));
        Assertions.assertEquals(new Currency(0), new Currency(-1));
    }

    @Property
    public void toStringPadded(@ForAll@Positive@IntRange(max = Currency.MAX_COINS) int coins) {
        Currency currency = new Currency(coins);
        String paddedString = currency.toStringPadded();
        int len = paddedString.length();
        char lastChar = paddedString.charAt(len - 1);
        String numberString = currency.toStringPadded().substring(0, len - 1);

        Assertions.assertEquals(Currency.MAX_STRING_LENGTH, len);
        Assertions.assertEquals('C', lastChar);
        Assertions.assertEquals(coins, Integer.parseInt(numberString));
    }
}
