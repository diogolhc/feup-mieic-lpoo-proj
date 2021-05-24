package model.farm;

import model.farm.item.Item;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Item, Integer> items;
    private int capacity;
    private int occupied;

    public Inventory() {
        this(0);
    }

    public Inventory(int capacity) {
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
        Integer amount = items.get(item);
        if (amount == null) {
            return 0;
        } else {
            return amount;
        }
    }

    public void storeItem(Item item, int amount) {
        // Discard items that can't be stored
        if (occupied + amount > capacity) amount = capacity - occupied;

        occupied += amount;
        items.put(item, getAmount(item) + amount);
    }

    public int removeItem(Item item, int amount) {
        int currentAmount = getAmount(item);
        if (amount >= currentAmount) {
            amount = currentAmount;
            items.remove(item);
        } else {
            items.put(item, currentAmount - amount);
        }

        occupied -= amount;
        return amount;
    }
}
