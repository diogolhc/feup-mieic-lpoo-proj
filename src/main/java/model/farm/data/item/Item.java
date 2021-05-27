package model.farm.data.item;

import model.farm.Currency;

import java.io.Serializable;
import java.util.Objects;

public abstract class Item implements Serializable {
    public abstract String getName();
    public abstract Currency getSellPrice();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(this.getName(), item.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }
}
