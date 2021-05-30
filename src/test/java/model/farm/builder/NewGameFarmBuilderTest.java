package model.farm.builder;

import model.InGameTime;
import model.farm.Currency;
import model.farm.Farm;
import model.farm.data.Weather;
import model.farm.data.item.Crop;
import model.farm.data.item.CropGrowthStage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewGameFarmBuilderTest {
    private List<String> cropsData;
    private List<String> livestocksData;
    private List<String> weathersData;
    private List<Crop> crops;
    private List<Weather> weathers;
    private Weather startingWeather;

    @BeforeEach
    public void setUp() {
        this.cropsData = Arrays.asList(
                "c1 3 10 20 30",
                "00:00 a #000001",
                "00:01 b #000002",
                "00:02 c #000003",
                "c2 2 11 21 31",
                "00:00 d #000004",
                "00:02 e #000005"
        );
        this.crops = new ArrayList<>();
        Crop crop = new Crop("c1");
        crop.setPlantPrice(new Currency(10));
        crop.setBaseHarvestAmount(20);
        crop.setSellPrice(new Currency(30));
        crop.addGrowthStage(CropGrowthStage.parseGrowthStage(this.cropsData.get(1)));
        crop.addGrowthStage(CropGrowthStage.parseGrowthStage(this.cropsData.get(2)));
        crop.addGrowthStage(CropGrowthStage.parseGrowthStage(this.cropsData.get(3)));
        this.crops.add(crop);

        crop = new Crop("c2");
        crop.setPlantPrice(new Currency(11));
        crop.setBaseHarvestAmount(21);
        crop.setSellPrice(new Currency(31));
        crop.addGrowthStage(CropGrowthStage.parseGrowthStage(this.cropsData.get(5)));
        crop.addGrowthStage(CropGrowthStage.parseGrowthStage(this.cropsData.get(6)));
        this.crops.add(crop);

        this.livestocksData = Arrays.asList(
                "a1 A 40 50 60 70 80 90",
                "c1 30",
                "p1 01:01 100 110",
                "a2 B 41 51 61 71 81 91",
                "c2 40",
                "p2 01:02 101 111"
        );

        this.weathersData = Arrays.asList(
                "w1 0.1",
                "1 w2 0.01",
                "w2 0.2",
                "2 w1 0.02 w3 0.03",
                "w3 -0.3",
                "2 w2 0.04 w1 0.05"
        );
        this.weathers = new ArrayList<>();
        Weather weather1 = new Weather("w1", 0.1);
        Weather weather2 = new Weather("w2", 0.2);
        Weather weather3 = new Weather("w3", -0.3);
        weather1.addWeatherChangeProbability(weather2, 0.01);
        weather2.addWeatherChangeProbability(weather1, 0.02);
        weather2.addWeatherChangeProbability(weather3, 0.03);
        weather3.addWeatherChangeProbability(weather2, 0.04);
        weather3.addWeatherChangeProbability(weather1, 0.05);
        this.weathers.add(weather1);
        this.weathers.add(weather2);
        this.weathers.add(weather3);
        this.startingWeather = weather1;
    }

    @Test
    public void buildCropTypes() {
        FarmBuilder builder = new NewGameFarmBuilder(this.cropsData, this.livestocksData, this.weathersData);
        Farm farm = builder.buildFarm();

        for (int i = 0; i < this.crops.size(); i++) {
            Crop expected = this.crops.get(i);
            Crop actual = farm.getCropTypes().get(i);
            Assertions.assertEquals(expected.getName(), actual.getName());
            Assertions.assertEquals(expected.getPlantPrice(), actual.getPlantPrice());
            Assertions.assertEquals(expected.getSellPrice(), actual.getSellPrice());
            for (int j = 0; j < 5; j++) {
                Assertions.assertEquals(
                        expected.getCurrentGrowthStage(new InGameTime(j)),
                        actual.getCurrentGrowthStage(new InGameTime(j))
                );
            }
        }
    }

    @Test
    public void buildLivestockTypes() {
        FarmBuilder builder = new NewGameFarmBuilder(this.cropsData, this.livestocksData, this.weathersData);
        Farm farm = builder.buildFarm();

        Assertions.assertEquals(2, farm.getLivestockTypes().size());
        Assertions.assertEquals("a1", farm.getLivestockTypes().get(0).getAnimalName());
        Assertions.assertEquals("a2", farm.getLivestockTypes().get(1).getAnimalName());
    }

    @Test
    public void buildWeatherTypes() {
        FarmBuilder builder = new NewGameFarmBuilder(this.cropsData, this.livestocksData, this.weathersData);
        Farm farm = builder.buildFarm();

        for (int i = 0; i < this.weathers.size(); i++) {
            Weather expected = this.weathers.get(i);
            Weather actual = farm.getWeatherTypes().get(i);
            Assertions.assertEquals(expected.getName(), actual.getName());
            Assertions.assertEquals(expected.getEffect(new InGameTime(1)), actual.getEffect(new InGameTime(1)));
            Assertions.assertEquals(expected.getWeatherChangeProbabilities(), actual.getWeatherChangeProbabilities());
        }

        Assertions.assertEquals(this.startingWeather, farm.getCurrentWeather());
    }

    @Test
    public void buildInvalidCrops() {
        List<String> invalidCrops1 = Arrays.asList(
                "c1 3 10 20 30",
                "00:00 a #000001",
                "00:01 b #000002",
                "c2 2 11 21 31",
                "00:00 d #000004",
                "00:02 e #000005"
        );
        List<String> invalidCrops2 = Arrays.asList(
                "c1 2 10 20",
                "00:00 a #000001",
                "00:01 b #000002",
                "c2 2 11 21 31",
                "00:00 d #000004",
                "00:02 e #000005"
        );
        List<String> invalidCrops3 = Arrays.asList(
                "c1 2 10 20 30",
                "00:00 a #000001",
                "00:01 b #000002",
                "c2 2 11 21 31 41",
                "00:00 d #000004",
                "00:02 e #000005"
        );
        FarmBuilder builder1 = new NewGameFarmBuilder(invalidCrops1, this.livestocksData, this.weathersData);
        Assertions.assertThrows(IllegalArgumentException.class, () -> builder1.buildFarm());

        FarmBuilder builder2 = new NewGameFarmBuilder(invalidCrops2, this.livestocksData, this.weathersData);
        Assertions.assertThrows(IllegalArgumentException.class, () -> builder2.buildFarm());

        FarmBuilder builder3 = new NewGameFarmBuilder(invalidCrops3, this.livestocksData, this.weathersData);
        Assertions.assertThrows(IllegalArgumentException.class, () -> builder3.buildFarm());
    }

    @Test
    public void buildInvalidWeathers() {
        List<String> invalidWeathers1 = Arrays.asList(
                "w1 0.1",
                "2 w2 0.01",
                "w2 0.2",
                "1 w1 0.02"
        );
        List<String> invalidWeathers2 = Arrays.asList(
                "w1",
                "1 w2 0.01",
                "w2 0.2",
                "1 w1 0.02"
        );
        List<String> invalidWeathers3 = Arrays.asList(
                "w1 0.1",
                "1 w2 0.01",
                "w2 0.2 0.2",
                "1 w1 0.02"
        );

        FarmBuilder builder1 = new NewGameFarmBuilder(this.cropsData, this.livestocksData, invalidWeathers1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> builder1.buildFarm());

        FarmBuilder builder2 = new NewGameFarmBuilder(this.cropsData, this.livestocksData, invalidWeathers2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> builder2.buildFarm());

        FarmBuilder builder3 = new NewGameFarmBuilder(this.cropsData, this.livestocksData, invalidWeathers3);
        Assertions.assertThrows(IllegalArgumentException.class, () -> builder3.buildFarm());
    }
}
