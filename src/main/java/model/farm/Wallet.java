package model.farm;

public class Wallet {
    Currency currency;

    public Wallet(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void spend(Currency currency) {
        this.currency = this.currency.subtract(currency);
    }

    public void receive(Currency currency) {
        this.currency = this.currency.add(currency);
    }

    public boolean canBuy(Currency currency) {
        return this.currency.getCoins() >= currency.getCoins();
    }
}
