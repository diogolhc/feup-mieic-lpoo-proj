package model.farm;

import model.farm.data.item.Item;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Negative;
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
        item1 = Mockito.mock(Item.class);
        item2 = Mockito.mock(Item.class);
        item3 = Mockito.mock(Item.class);
        inventory = new Inventory(10);

        inventory.storeItem(item1, 1);
        inventory.storeItem(item2, 5);
    }

    @Test
    public void negativeCapacityThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Inventory(-5));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Inventory(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Inventory(-500));
    }

    @Test
    void getAmount() {
        Assertions.assertEquals(1, inventory.getAmount(item1));
        Assertions.assertEquals(5, inventory.getAmount(item2));
        Assertions.assertEquals(0, inventory.getAmount(item3));
    }

    @Test
    void storeItem() {
        inventory.storeItem(item3, 2);
        Assertions.assertEquals(2, inventory.getAmount(item3));
        Assertions.assertEquals(8, inventory.getOccupied());
        inventory.storeItem(item3, 1);
        Assertions.assertEquals(3, inventory.getAmount(item3));
        Assertions.assertEquals(9, inventory.getOccupied());
    }

    @Test
    void storeItemFull() {
        inventory.storeItem(item3, 4);
        Assertions.assertEquals(4, inventory.getAmount(item3));
        Assertions.assertEquals(10, inventory.getOccupied());
        inventory.storeItem(item3, 1);
        Assertions.assertEquals(4, inventory.getAmount(item3));
        Assertions.assertEquals(10, inventory.getOccupied());
    }

    @Test
    void storeItemExceed() {
        inventory.storeItem(item3, 10);
        Assertions.assertEquals(4, inventory.getAmount(item3));
        Assertions.assertEquals(10, inventory.getOccupied());
        inventory.storeItem(item3, 10);
        Assertions.assertEquals(4, inventory.getAmount(item3));
        Assertions.assertEquals(10, inventory.getOccupied());
    }

    @Test
    void removeItem() {
        int rem;
        rem = inventory.removeItem(item2, 1);
        Assertions.assertEquals(1, rem);
        Assertions.assertEquals(4, inventory.getAmount(item2));
        Assertions.assertEquals(5, inventory.getOccupied());
        rem = inventory.removeItem(item2, 2);
        Assertions.assertEquals(2, rem);
        Assertions.assertEquals(2, inventory.getAmount(item2));
        Assertions.assertEquals(3, inventory.getOccupied());
    }

    @Test
    void removeItemEmpty() {
        int rem;
        rem = inventory.removeItem(item3, 1);
        Assertions.assertEquals(0, rem);
        Assertions.assertEquals(0, inventory.getAmount(item3));
        Assertions.assertEquals(6, inventory.getOccupied());
        rem = inventory.removeItem(item3, 5);
        Assertions.assertEquals(0, rem);
        Assertions.assertEquals(0, inventory.getAmount(item3));
        Assertions.assertEquals(6, inventory.getOccupied());
    }

    @Test
    void removeItemExceedEmpty() {
        int rem;
        rem = inventory.removeItem(item2, 10);
        Assertions.assertEquals(5, rem);
        Assertions.assertEquals(0, inventory.getAmount(item3));
        Assertions.assertEquals(1, inventory.getOccupied());
        rem = inventory.removeItem(item3, 1);
        Assertions.assertEquals(0, rem);
        Assertions.assertEquals(0, inventory.getAmount(item3));
        Assertions.assertEquals(1, inventory.getOccupied());
    }
}
