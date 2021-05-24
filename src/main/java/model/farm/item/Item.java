package model.farm.item;

import java.util.Objects;

public abstract class Item {
    public abstract String getName();
    //public Currency getSellPrice(); TODO implement currency

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
