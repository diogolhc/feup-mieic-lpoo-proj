package model.farm.building.crop_field;

import gui.Color;
import model.InGameTime;
import model.Position;
import model.farm.building.crop_field.crop.Crop;
import model.farm.building.crop_field.crop.GrowthStage;
import model.farm.building.crop_field.crop.NoCrop;
import model.farm.building.crop_field.state.CropFieldState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CropFieldTest {
    private CropField field;

    @BeforeEach
    void setUp() {
        field = new CropField(new Position(4, 5));
    }

//    @Test
//    void isTraversable() {
//        // TODO
//    }

//    @Test
//    void isInInteractiveZone() {
//        // TODO
//    }

    @Test
    void getGrowthStageDefault() {
        Crop crop = new NoCrop();
        Assertions.assertSame(crop.getGrowthStages().get(0), field.getCropGrowthStage());
    }

    @Test
    void getGrowthStage() {
        CropFieldState state = Mockito.mock(CropFieldState.class);
        Crop crop = Mockito.mock(Crop.class);
        GrowthStage growthStage = new GrowthStage(new InGameTime(7), 'c', new Color("#FFFFFF"));
        InGameTime time = new InGameTime(5);

        field.setState(state);

        Mockito.when(state.getRemainingTime()).thenReturn(new InGameTime(5));
        Mockito.when(state.getCrop()).thenReturn(crop);

        Mockito.when(crop.getGrowthStage(time)).thenReturn(growthStage);


        Assertions.assertSame(growthStage, field.getCropGrowthStage());
    }
}
