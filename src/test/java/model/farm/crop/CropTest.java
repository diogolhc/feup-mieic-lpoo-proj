package model.farm.crop;

import gui.Color;
import model.InGameTime;
import model.farm.data.item.Crop;
import model.farm.data.item.CropGrowthStage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class CropTest {
    private Crop crop;
    private List<CropGrowthStage> growthStages;

    @BeforeEach
    void setUp() {
        growthStages = new ArrayList<>();
        crop = Mockito.mock(Crop.class);
        Mockito.when(crop.getCurrentGrowthStage(Mockito.any())).thenCallRealMethod();
        Mockito.when(crop.getGrowthStages()).thenReturn(growthStages);

        growthStages.add(new CropGrowthStage(new InGameTime(0), 'a', new Color("#000001")));
        growthStages.add(new CropGrowthStage(new InGameTime(5), 'b', new Color("#000002")));
        growthStages.add(new CropGrowthStage(new InGameTime(6), 'c', new Color("#000003")));
        growthStages.add(new CropGrowthStage(new InGameTime(8), 'd', new Color("#000004")));
        growthStages.add(new CropGrowthStage(new InGameTime(20), 'e', new Color("#000005")));
    }

    @Test
    void getGrowthStage() {
        Assertions.assertSame(growthStages.get(0), crop.getCurrentGrowthStage(new InGameTime(0)));
        Assertions.assertSame(growthStages.get(1), crop.getCurrentGrowthStage(new InGameTime(1)));
        Assertions.assertSame(growthStages.get(1), crop.getCurrentGrowthStage(new InGameTime(3)));
        Assertions.assertSame(growthStages.get(1), crop.getCurrentGrowthStage(new InGameTime(4)));
        Assertions.assertSame(growthStages.get(1), crop.getCurrentGrowthStage(new InGameTime(5)));
        Assertions.assertSame(growthStages.get(2), crop.getCurrentGrowthStage(new InGameTime(6)));
        Assertions.assertSame(growthStages.get(3), crop.getCurrentGrowthStage(new InGameTime(7)));
        Assertions.assertSame(growthStages.get(3), crop.getCurrentGrowthStage(new InGameTime(8)));
        Assertions.assertSame(growthStages.get(4), crop.getCurrentGrowthStage(new InGameTime(15)));
        Assertions.assertSame(growthStages.get(4), crop.getCurrentGrowthStage(new InGameTime(20)));
        Assertions.assertSame(growthStages.get(4), crop.getCurrentGrowthStage(new InGameTime(21)));
        Assertions.assertNotSame(growthStages.get(3), crop.getCurrentGrowthStage(new InGameTime(2)));
    }
}
