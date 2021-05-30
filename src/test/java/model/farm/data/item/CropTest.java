package model.farm.data.item;

import gui.Color;
import model.InGameTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CropTest {
    private Crop crop;
    private List<CropGrowthStage> growthStages;

    @BeforeEach
    void setUp() {
        this.crop = new Crop("CROP");

        this.growthStages = new ArrayList<>();
        this.growthStages.add(new CropGrowthStage(new InGameTime(0), 'a', new Color("#000001")));
        this.growthStages.add(new CropGrowthStage(new InGameTime(5), 'b', new Color("#000002")));
        this.growthStages.add(new CropGrowthStage(new InGameTime(6), 'c', new Color("#000003")));
        this.growthStages.add(new CropGrowthStage(new InGameTime(8), 'd', new Color("#000004")));
        this.growthStages.add(new CropGrowthStage(new InGameTime(20), 'e', new Color("#000005")));
    }

    @Test
    void getGrowthTime() {
        this.crop.addGrowthStage(this.growthStages.get(0));
        Assertions.assertEquals(new InGameTime(0), this.crop.getGrowTime());
        this.crop.addGrowthStage(this.growthStages.get(2));
        Assertions.assertEquals(new InGameTime(6), this.crop.getGrowTime());
        this.crop.addGrowthStage(this.growthStages.get(1));
        Assertions.assertEquals(new InGameTime(6), this.crop.getGrowTime());
        this.crop.addGrowthStage(this.growthStages.get(4));
        Assertions.assertEquals(new InGameTime(20), this.crop.getGrowTime());
        this.crop.addGrowthStage(this.growthStages.get(3));
        Assertions.assertEquals(new InGameTime(20), this.crop.getGrowTime());
    }

    @Test
    void getGrowthStage() {
        for (CropGrowthStage growthStage: this.growthStages) {
            this.crop.addGrowthStage(growthStage);
        }

        Assertions.assertSame(this.growthStages.get(0), this.crop.getCurrentGrowthStage(new InGameTime(0)));
        Assertions.assertSame(this.growthStages.get(1), this.crop.getCurrentGrowthStage(new InGameTime(1)));
        Assertions.assertSame(this.growthStages.get(1), this.crop.getCurrentGrowthStage(new InGameTime(3)));
        Assertions.assertSame(this.growthStages.get(1), this.crop.getCurrentGrowthStage(new InGameTime(4)));
        Assertions.assertSame(this.growthStages.get(1), this.crop.getCurrentGrowthStage(new InGameTime(5)));
        Assertions.assertSame(this.growthStages.get(2), this.crop.getCurrentGrowthStage(new InGameTime(6)));
        Assertions.assertSame(this.growthStages.get(3), this.crop.getCurrentGrowthStage(new InGameTime(7)));
        Assertions.assertSame(this.growthStages.get(3), this.crop.getCurrentGrowthStage(new InGameTime(8)));
        Assertions.assertSame(this.growthStages.get(4), this.crop.getCurrentGrowthStage(new InGameTime(15)));
        Assertions.assertSame(this.growthStages.get(4), this.crop.getCurrentGrowthStage(new InGameTime(20)));
        Assertions.assertSame(this.growthStages.get(4), this.crop.getCurrentGrowthStage(new InGameTime(21)));
        Assertions.assertNotSame(this.growthStages.get(3), this.crop.getCurrentGrowthStage(new InGameTime(2)));
    }

    @Test
    void getGrowthShuffled() {
        List<CropGrowthStage> shuffled = new ArrayList<>();
        shuffled.addAll(this.growthStages);
        Collections.shuffle(shuffled);
        for (CropGrowthStage growthStage: shuffled) {
            this.crop.addGrowthStage(growthStage);
        }

        Assertions.assertSame(this.growthStages.get(0), this.crop.getCurrentGrowthStage(new InGameTime(0)));
        Assertions.assertSame(this.growthStages.get(1), this.crop.getCurrentGrowthStage(new InGameTime(1)));
        Assertions.assertSame(this.growthStages.get(1), this.crop.getCurrentGrowthStage(new InGameTime(3)));
        Assertions.assertSame(this.growthStages.get(1), this.crop.getCurrentGrowthStage(new InGameTime(4)));
        Assertions.assertSame(this.growthStages.get(1), this.crop.getCurrentGrowthStage(new InGameTime(5)));
        Assertions.assertSame(this.growthStages.get(2), this.crop.getCurrentGrowthStage(new InGameTime(6)));
        Assertions.assertSame(this.growthStages.get(3), this.crop.getCurrentGrowthStage(new InGameTime(7)));
        Assertions.assertSame(this.growthStages.get(3), this.crop.getCurrentGrowthStage(new InGameTime(8)));
        Assertions.assertSame(this.growthStages.get(4), this.crop.getCurrentGrowthStage(new InGameTime(15)));
        Assertions.assertSame(this.growthStages.get(4), this.crop.getCurrentGrowthStage(new InGameTime(20)));
        Assertions.assertSame(this.growthStages.get(4), this.crop.getCurrentGrowthStage(new InGameTime(21)));
        Assertions.assertNotSame(this.growthStages.get(3), this.crop.getCurrentGrowthStage(new InGameTime(2)));
    }
}
