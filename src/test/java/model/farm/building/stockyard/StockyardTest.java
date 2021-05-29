package model.farm.building.stockyard;


import model.Position;
import model.farm.Currency;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
import model.region.PositionRegion;
import model.region.RectangleRegion;
import model.region.Region;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class StockyardTest {
    private Stockyard stockyard;

    @BeforeEach
    void setUp() {
        Position position = new Position(5,9);
        Livestock livestock = Mockito.mock(Livestock.class);
        Mockito.when(livestock.getStockyardHeight()).thenReturn(4);
        Mockito.when(livestock.getStockyardWidth()).thenReturn(4);
        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(10);
        Mockito.when(livestock.getProducedItem()).thenReturn(Mockito.mock(AnimalProduct.class));
        Mockito.when(livestock.getProducedItem().getBaseProducedAmount()).thenReturn(10);
        Mockito.when(livestock.getRequiredFood()).thenReturn(5);
        stockyard = new Stockyard(position, livestock);
    }

    @Test
    void getDefaultState() {
        Assertions.assertTrue(stockyard.getState() instanceof NotProducing);
        Assertions.assertEquals(
                new RectangleRegion(new Position(7, 10), 1, 2),
                stockyard.getAnimals().getRegion()
        );
    }

    @Test
    void getUntraversableRegion() {
        Region region = stockyard.getUntraversableRegion();
        Assertions.assertTrue(region instanceof RectangleRegion);
        Assertions.assertEquals(new RectangleRegion(new Position(6, 9), 3, 4), region);
        Mockito.when(stockyard.getLivestockType().getStockyardHeight()).thenReturn(8);
        Mockito.when(stockyard.getLivestockType().getStockyardWidth()).thenReturn(5);
        stockyard = new Stockyard(new Position(6, 10), stockyard.getLivestockType());
        region = stockyard.getUntraversableRegion();
        Assertions.assertEquals(new RectangleRegion(new Position(7, 10), 4, 8), region);
    }

    @Test
    void getInteractiveRegion() {
        Region region = stockyard.getInteractiveRegion();
        Assertions.assertTrue(region instanceof PositionRegion);
        Assertions.assertTrue(region.contains(new Position(5, 12)));
        Mockito.when(stockyard.getLivestockType().getStockyardHeight()).thenReturn(8);
        Mockito.when(stockyard.getLivestockType().getStockyardWidth()).thenReturn(5);
        stockyard = new Stockyard(new Position(6, 10), stockyard.getLivestockType());
        region = stockyard.getInteractiveRegion();
        Assertions.assertTrue(region.contains(new Position(6, 13)));
    }

    @Test
    void producedAmountDependsOnNumAnimals() {
        Assertions.assertEquals(0, stockyard.getBaseProducedAmount());
        stockyard.getAnimals().addAnimal(new Position(0, 0));
        Assertions.assertEquals(10, stockyard.getBaseProducedAmount());
        stockyard.getAnimals().addAnimal(new Position(0, 0));
        Assertions.assertEquals(20, stockyard.getBaseProducedAmount());
        stockyard.getAnimals().addAnimal(new Position(0, 0));
        Assertions.assertEquals(30, stockyard.getBaseProducedAmount());
        stockyard.getAnimals().addAnimal(new Position(0, 0));
        Assertions.assertEquals(40, stockyard.getBaseProducedAmount());
    }

    @Test
    void requiredFoodDependsOnNumAnimals() {
        Assertions.assertEquals(0, stockyard.getRequiredFood());
        stockyard.getAnimals().addAnimal(new Position(0, 0));
        Assertions.assertEquals(5, stockyard.getRequiredFood());
        stockyard.getAnimals().addAnimal(new Position(0, 0));
        Assertions.assertEquals(10, stockyard.getRequiredFood());
        stockyard.getAnimals().addAnimal(new Position(0, 0));
        Assertions.assertEquals(15, stockyard.getRequiredFood());
        stockyard.getAnimals().addAnimal(new Position(0, 0));
        Assertions.assertEquals(20, stockyard.getRequiredFood());
    }

    @Test
    void setTopLeftPositionUpdatesAnimals() {
        stockyard.setTopLeftPosition(new Position(9, 9));
        Assertions.assertEquals(
                new RectangleRegion(new Position(11, 10), 1, 2),
                stockyard.getAnimals().getRegion()
        );
    }
}
