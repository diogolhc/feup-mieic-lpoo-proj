package model.farm.data;

import model.farm.Currency;
import model.farm.data.item.AnimalProduct;
import model.farm.data.item.Crop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LivestockTest {
    private List<Crop> crops;

    @BeforeEach
    public void setUp() {
        this.crops = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.crops.add(new Crop("c" + i));
        }
    }

    @Test
    public void parseLivestockType() {
        List<String> lines = Arrays.asList(
                "ANIMAL a 11 12 3 4 5 6",
                "c1 7",
                "PRODUCT 01:23 8 9"
        );

        Livestock livestock = Livestock.parseLivestockType(this.crops, lines);
        Assertions.assertEquals("ANIMAL", livestock.getAnimalName());
        Assertions.assertEquals('a', livestock.getAnimalChar());
        Assertions.assertEquals(11, livestock.getStockyardWidth());
        Assertions.assertEquals(12, livestock.getStockyardHeight());
        Assertions.assertEquals(3, livestock.getMaxNumAnimals());
        Assertions.assertEquals(new Currency(4), livestock.getBuildPrice());
        Assertions.assertEquals(new Currency(5), livestock.getAnimalBuyPrice());
        Assertions.assertEquals(new Currency(6), livestock.getAnimalSellPrice());
        Assertions.assertEquals(this.crops.get(1), livestock.getFoodCrop());
        Assertions.assertEquals(7, livestock.getRequiredFood());
        Assertions.assertEquals(new AnimalProduct("PRODUCT"), livestock.getProducedItem());
    }

    @Test
    public void parseLivestockTypeInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Livestock.parseLivestockType(this.crops, Arrays.asList(
                "ANIMAL a 1 2 3 4 5 6",
                "c1 7"
        )));

        Assertions.assertThrows(IllegalArgumentException.class, () -> Livestock.parseLivestockType(this.crops, Arrays.asList(
                "ANIMAL ab 1 2 3 4 5 6",
                "c1 7",
                "PRODUCT 01:23 8 9"
        )));

        Assertions.assertThrows(IllegalArgumentException.class, () -> Livestock.parseLivestockType(this.crops, Arrays.asList(
                "ANIMAL ab 1 2 3 4 5",
                "c1 7",
                "PRODUCT 01:23 8 9"
        )));

        Assertions.assertThrows(IllegalArgumentException.class, () -> Livestock.parseLivestockType(this.crops, Arrays.asList(
                "ANIMAL ab 1 2 3 4 5 6 7",
                "c1 7",
                "PRODUCT 01:23 8 9"
        )));

        Assertions.assertThrows(IllegalArgumentException.class, () -> Livestock.parseLivestockType(this.crops, Arrays.asList(
                "ANIMAL ab 1 2 3 4 5 6",
                "unknown 7",
                "PRODUCT 01:23 8 9"
        )));

        Assertions.assertThrows(IllegalArgumentException.class, () -> Livestock.parseLivestockType(this.crops, Arrays.asList(
                "ANIMAL ab 1 2 3 4 5 6",
                "",
                "PRODUCT 01:23 8 9"
        )));

        Assertions.assertThrows(IllegalArgumentException.class, () -> Livestock.parseLivestockType(this.crops, Arrays.asList(
                "ANIMAL ab 1 2 3 4 5 6",
                "c2 7 8",
                "PRODUCT 01:23 8 9"
        )));

        Assertions.assertThrows(IllegalArgumentException.class, () -> Livestock.parseLivestockType(this.crops, Arrays.asList(
                "ANIMAL ab 1 2 3 4 5 6",
                "c3",
                "PRODUCT 01:23 8 9"
        )));
    }
}
