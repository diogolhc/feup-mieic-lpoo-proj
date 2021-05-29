package model.farm.data.item;

import gui.Color;
import model.InGameTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CropGrowthStageTest {
    @Test
    public void parseGrowthStage() {
        Assertions.assertEquals(
                new CropGrowthStage(new InGameTime(0, 1, 23), 'a', new Color("#012345")),
                CropGrowthStage.parseGrowthStage("01:23 a #012345")
        );
    }

    @Test
    public void parseGrowthStageInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> CropGrowthStage.parseGrowthStage(
                "01:23 bb #012345"
        ));

        Assertions.assertThrows(IllegalArgumentException.class, () -> CropGrowthStage.parseGrowthStage(
                "01:23 a 012345"
        ));

        Assertions.assertThrows(IllegalArgumentException.class, () -> CropGrowthStage.parseGrowthStage(
                "01:23 a #012345 b"
        ));

        Assertions.assertThrows(IllegalArgumentException.class, () -> CropGrowthStage.parseGrowthStage(
                "01:23 a"
        ));
    }
}
