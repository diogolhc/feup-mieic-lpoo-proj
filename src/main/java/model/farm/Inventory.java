package model.farm;

import model.farm.data.item.Item;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Inventory implements Serializable {
    private final Map<Item, Integer> items;
    private int capacity;
    private int occupied;

    public Inventory() {
        this(0);
    }

    public Inventory(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Negative capacity");
        this.capacity = capacity;
        this.occupied = 0;
        this.items = new HashMap<>();
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getOccupied() {
        return this.occupied;
    }

    public int getAmount(Item item) {
        Integer amount = this.items.get(item);
        if (amount == null) {
            return 0;
        } else {
            return amount;
        }
    }

    public void storeItem(Item item, int amount) {
        // Discards items that can't be stored
        if (this.occupied + amount > this.capacity) {
            amount = this.capacity - this.occupied;
        }

        this.occupied += amount;
        this.items.put(item, getAmount(item) + amount);
    }

    public int removeItem(Item item, int amount) {
        int currentAmount = getAmount(item);
        if (amount >= currentAmount) {
            amount = currentAmount;
            this.items.remove(item);
        } else {
            this.items.put(item, currentAmount - amount);
        }

        this.occupied -= amount;
        return amount;
    }
}
