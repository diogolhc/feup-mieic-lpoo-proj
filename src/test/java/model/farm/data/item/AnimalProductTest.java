package model.farm.data.item;

import gui.Color;
import model.InGameTime;
import model.farm.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnimalProductTest {
    @Test
    public void parseAnimalProduct() {
        AnimalProduct animalProduct = AnimalProduct.parseAnimalProduct("PRODUCT 01:09 5 10");
        Assertions.assertEquals("PRODUCT", animalProduct.getName());
        Assertions.assertEquals(new InGameTime(0, 1, 9), animalProduct.getProductionTime());
        Assertions.assertEquals(5, animalProduct.getBaseProducedAmount());
        Assertions.assertEquals(new Currency(10), animalProduct.getSellPrice());
    }

    @Test
    public void parseAnimalProductInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> AnimalProduct.parseAnimalProduct(
                "PRODUCT 01:09 5 10 11"
        ));

        Assertions.assertThrows(IllegalArgumentException.class, () -> AnimalProduct.parseAnimalProduct(
                "PRODUCT 01:09 5"
        ));

        Assertions.assertThrows(IllegalArgumentException.class, () -> AnimalProduct.parseAnimalProduct(
                "PRODUCT 01:09 5 a 11"
        ));
    }
}
