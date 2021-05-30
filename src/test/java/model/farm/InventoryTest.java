package model.farm;

import model.farm.data.item.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class InventoryTest {
    private Item item1;
    private Item item2;
    private Item item3;
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        this.item1 = Mockito.mock(Item.class);
        this.item2 = Mockito.mock(Item.class);
        this.item3 = Mockito.mock(Item.class);
        this.inventory = new Inventory(10);

        this.inventory.storeItem(this.item1, 1);
        this.inventory.storeItem(this.item2, 5);
    }

    @Test
    public void negativeCapacityThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Inventory(-5));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Inventory(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Inventory(-500));
    }

    @Test
    void getAmount() {
        Assertions.assertEquals(1, this.inventory.getAmount(this.item1));
        Assertions.assertEquals(5, this.inventory.getAmount(this.item2));
        Assertions.assertEquals(0, this.inventory.getAmount(this.item3));
    }

    @Test
    void storeItem() {
        this.inventory.storeItem(this.item3, 2);
        Assertions.assertEquals(2, this.inventory.getAmount(this.item3));
        Assertions.assertEquals(8, this.inventory.getOccupied());
        this.inventory.storeItem(this.item3, 1);
        Assertions.assertEquals(3, this.inventory.getAmount(this.item3));
        Assertions.assertEquals(9, this.inventory.getOccupied());
    }

    @Test
    void storeItemFull() {
        this.inventory.storeItem(this.item3, 4);
        Assertions.assertEquals(4, this.inventory.getAmount(this.item3));
        Assertions.assertEquals(10, this.inventory.getOccupied());
        this.inventory.storeItem(this.item3, 1);
        Assertions.assertEquals(4, this.inventory.getAmount(this.item3));
        Assertions.assertEquals(10, this.inventory.getOccupied());
    }

    @Test
    void storeItemExceed() {
        this.inventory.storeItem(this.item3, 10);
        Assertions.assertEquals(4, this.inventory.getAmount(this.item3));
        Assertions.assertEquals(10, this.inventory.getOccupied());
        this.inventory.storeItem(this.item3, 10);
        Assertions.assertEquals(4, this.inventory.getAmount(this.item3));
        Assertions.assertEquals(10, this.inventory.getOccupied());
    }

    @Test
    void removeItem() {
        int rem;
        rem = this.inventory.removeItem(this.item2, 1);
        Assertions.assertEquals(1, rem);
        Assertions.assertEquals(4, this.inventory.getAmount(this.item2));
        Assertions.assertEquals(5, this.inventory.getOccupied());
        rem = this.inventory.removeItem(this.item2, 2);
        Assertions.assertEquals(2, rem);
        Assertions.assertEquals(2, this.inventory.getAmount(this.item2));
        Assertions.assertEquals(3, this.inventory.getOccupied());
    }

    @Test
    void removeItemEmpty() {
        int rem;
        rem = this.inventory.removeItem(this.item3, 1);
        Assertions.assertEquals(0, rem);
        Assertions.assertEquals(0, this.inventory.getAmount(this.item3));
        Assertions.assertEquals(6, this.inventory.getOccupied());
        rem = this.inventory.removeItem(this.item3, 5);
        Assertions.assertEquals(0, rem);
        Assertions.assertEquals(0, this.inventory.getAmount(this.item3));
        Assertions.assertEquals(6, this.inventory.getOccupied());
    }

    @Test
    void removeItemExceedEmpty() {
        int rem;
        rem = this.inventory.removeItem(this.item2, 10);
        Assertions.assertEquals(5, rem);
        Assertions.assertEquals(0, this.inventory.getAmount(this.item3));
        Assertions.assertEquals(1, this.inventory.getOccupied());
        rem = this.inventory.removeItem(this.item3, 1);
        Assertions.assertEquals(0, rem);
        Assertions.assertEquals(0, this.inventory.getAmount(this.item3));
        Assertions.assertEquals(1, this.inventory.getOccupied());
    }
}
