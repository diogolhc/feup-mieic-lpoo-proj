package model.farm.builder;

import model.InGameTime;
import model.Position;
import model.farm.*;
import model.farm.Currency;
import model.farm.building.*;
import model.farm.building.crop_field.CropField;
import model.farm.data.Livestock;
import model.farm.data.Weather;
import model.farm.entity.Entity;
import model.farm.data.item.Crop;
import model.farm.data.item.CropGrowthStage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class NewGameFarmBuilder extends FarmBuilder {
    private final List<String> cropsLines;
    private final List<String> livestockLines;
    private final List<String> weatherLines;

    public NewGameFarmBuilder(List<String> crops, List<String> livestocks, List<String> weathers) {
        this.cropsLines = crops;
        this.livestockLines = livestocks;
        this.weatherLines = weathers;
    }

    public NewGameFarmBuilder() throws IOException, URISyntaxException {
        URL resource = NewGameFarmBuilder.class.getResource("/game/crops.data");
        BufferedReader br = new BufferedReader(new FileReader(resource.toURI().getPath()));
        this.cropsLines = this.readLines(br);

        resource = NewGameFarmBuilder.class.getResource("/game/weather.data");
        br = new BufferedReader(new FileReader(resource.toURI().getPath()));
        this.weatherLines = this.readLines(br);

        resource = NewGameFarmBuilder.class.getResource("/game/livestock.data");
        br = new BufferedReader(new FileReader(resource.toURI().getPath()));
        this.livestockLines = this.readLines(br);
    }

    private static List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            lines.add(line);
        return lines;
    }

    @Override
    protected int getHeight() {
        return 20;
    }

    @Override
    protected int getWidth() {
        return 40;
    }

    @Override
    protected BuildingSet getBuildings() {
        BuildingSet buildingSet = new BuildingSet();

        buildingSet.setHousePosition(new Position(2, 1));
        buildingSet.setMarketPosition(new Position(10, 1));
        buildingSet.setWarehousePosition(new Position(18, 1));

        buildingSet.addCropField(new CropField(new Position(2, 10)));

        return buildingSet;
    }

    @Override
    protected Entity getFarmer() {
        return new Entity(new Position(3, 7));
    }

    @Override
    protected Inventory getInventory() {
        return new Inventory(850);
    }

    @Override
    protected Wallet getWallet() {
        return new Wallet(new Currency(0));
    }

    @Override
    protected InGameTime getTime() {
        return new InGameTime(1, 8, 0);
    }

    @Override
    protected List<Weather> getWeatherStates() {
        List<Weather> weathers = new ArrayList<>();
        if (this.weatherLines.isEmpty()) return weathers;

        int currentLine = 0;
        while (currentLine < this.weatherLines.size()){
            String[] tokens = this.weatherLines.get(currentLine++).split(" ");
            if (tokens.length != 2) {
                throw new IllegalArgumentException("Line must have exactly 2 values; got " + tokens.length);
            }

            String name = tokens[0];
            double weatherEffect = Double.parseDouble(tokens[1]);
            Weather weather = new Weather(name, weatherEffect);

            int index;
            if ((index = weathers.indexOf(weather)) != -1) {
                weather = weathers.get(index);
                weather.setEffect(weatherEffect);
            } else {
                weathers.add(weather);
            }

            tokens = this.weatherLines.get(currentLine++).split(" ");
            if (tokens.length < 1) {
                throw new IllegalArgumentException("Line must have at least one value; got " + tokens.length);
            }
            int numChanges = Integer.parseInt(tokens[0]);
            int expectedTokens = numChanges * 2 + 1;
            if (tokens.length != expectedTokens) {
                throw new IllegalArgumentException("Line must have exactly "
                        + expectedTokens + " values; got " + tokens.length);
            }

            for (int j = 0; j < numChanges; j++) {
                String name2 = tokens[2*j + 1];
                double probability = Double.parseDouble(tokens[2*j + 2]);

                Weather weather2 = new Weather(name2);

                if ((index = weathers.indexOf(weather2)) != -1) {
                    weather2 = weathers.get(index);
                } else {
                    weathers.add(weather2);
                }

                weather.addWeatherChangeProbability(weather2, probability);
            }
        }

        return weathers;
    }

    @Override
    protected List<Livestock> getLivestockTypes(List<Crop> cropTypes) {
        List<Livestock> livestockTypes = new ArrayList<>();
        if (this.livestockLines.isEmpty()) return livestockTypes;

        int currentLine = 0;
        while (currentLine < this.livestockLines.size()) {
            List<String> lines = this.livestockLines.subList(currentLine, currentLine + 3);
            currentLine += 3;
            livestockTypes.add(Livestock.parseLivestockType(cropTypes, lines));
        }

        return livestockTypes;
    }

    @Override
    protected List<Crop> getCropTypes() {
        List<Crop> crops = new ArrayList<>();
        if (this.cropsLines.isEmpty()) return crops;

        int currentLine = 0;
        while (currentLine < this.cropsLines.size()) {
            String[] tokens = this.cropsLines.get(currentLine++).split(" ");
            if (tokens.length != 5) {
                throw new IllegalArgumentException("Line must have exactly 5 values; got " + tokens.length);
            }

            Crop crop = new Crop(tokens[0]);
            int numberOfStages = Integer.parseInt(tokens[1]);

            crop.setPlantPrice(new Currency(Integer.parseInt(tokens[2])));
            crop.setBaseHarvestAmount(Integer.parseInt(tokens[3]));
            crop.setSellPrice(new Currency(Integer.parseInt(tokens[4])));

            for (int i = 0; i < numberOfStages; i++) {
                crop.addGrowthStage(CropGrowthStage.parseGrowthStage(this.cropsLines.get(currentLine++)));
            }
            crops.add(crop);
        }

        return crops;
    }
}
