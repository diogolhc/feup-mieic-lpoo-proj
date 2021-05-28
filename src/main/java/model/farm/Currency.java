package model.farm;

import java.io.Serializable;

public class Currency implements Serializable {
    public static final int MAX_CURRENCY = 999999;
    public static final int MAX_STRING_LENGTH = 7;
    private final int coins;

    public Currency() {
        this(0);
    }

    public Currency(int coins) {
        this.coins = Math.min(coins, MAX_CURRENCY);
    }

    public int getCoins() {
        return this.coins;
    }

    public String toStringPadded() {
        String coinString = this.toString();
        int numberOfZeros = MAX_STRING_LENGTH - coinString.length();
        return "0".repeat(numberOfZeros) + coinString;
    }

    @Override
    public String toString() {
        return this.coins + "C";
    }

    public Currency add(Currency currency) {
        return new Currency(this.coins + currency.coins);
    }

    public Currency subtract(Currency currency) {
        return new Currency(this.coins - currency.coins);
    }

    public Currency multiply(int amount) {
        return new Currency(this.coins * amount);
    }
}
