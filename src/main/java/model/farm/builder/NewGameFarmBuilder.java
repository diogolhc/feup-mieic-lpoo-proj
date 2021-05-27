package model.farm.builder;

import gui.Color;
import model.InGameTime;
import model.Position;
import model.farm.*;
import model.farm.Currency;
import model.farm.building.*;
import model.farm.item.AnimalProduct;
import model.farm.item.Crop;
import model.farm.item.CropGrowthStage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

// TODO after implementing loading save from file, this class
//      may be substituted with loading from a file in resources
public class NewGameFarmBuilder extends FarmBuilder {
    private final List<String> cropsLines;
    private final List<String> livestockLines;
    private final List<String> weatherLines;
    private final List<String> stockyardLines;

    // TODO check invalid formats?
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

        resource = NewGameFarmBuilder.class.getResource("/game/stockyard.data");
        br = new BufferedReader(new FileReader(resource.toURI().getPath()));
        this.stockyardLines = this.readLines(br);
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
    protected List<Weather> getWeatherStates() {
        List<Weather> weathers = new ArrayList<>();
        if (this.weatherLines.isEmpty()) return weathers;

        int numWeatherStates = Integer.parseInt(this.weatherLines.get(0));

        int line = 1;
        for (int i = 0; i < numWeatherStates; i++) {
            String name = this.weatherLines.get(line++);
            double weatherEffect = Double.parseDouble(this.weatherLines.get(line++));
            Weather weather = new Weather(name, weatherEffect);

            int index;
            if ((index = weathers.indexOf(weather)) != -1) {
                weather = weathers.get(index);
                weather.setWeatherEffect(weatherEffect);
            } else {
                weathers.add(weather);
            }

            int numChanges = Integer.parseInt(this.weatherLines.get(line++));
            for (int j = 0; j < numChanges; j++) {
                String name2 = this.weatherLines.get(line++);
                double probability = Double.parseDouble(this.weatherLines.get(line++));

                Weather weather2 = new Weather(name2);

                if ((index = weathers.indexOf(weather2)) != -1) {
                    weather2 = weathers.get(index);
                } else {
                    weathers.add(weather2);
                }

                weather.addWeatherChangePossibility(weather2, probability);
            }

        }

        return weathers;
    }

    @Override
    protected Farmer getFarmer() {
        return new Farmer(new Position(3, 7));
    }

    @Override
    protected Inventory getInventory() {
        return new Inventory(650);
    }

    @Override
    protected List<Livestock> getLivestockTypes() {
        List<Livestock> livestockTypes = new ArrayList<>();
        if (this.livestockLines.isEmpty()) return livestockTypes;

        int currentLine = 0;
        while (currentLine < this.livestockLines.size()) {
            Livestock livestock = new Livestock();
            String[] tokens = this.livestockLines.get(currentLine).split(" ");
            livestock.setAnimalName(tokens[0]);
            livestock.setAnimalChar(tokens[1].charAt(0));
            livestock.setStockyardWidth(Integer.parseInt(tokens[2]));
            livestock.setStockyardHeight(Integer.parseInt(tokens[3]));
            livestock.setMaxNumAnimals(Integer.parseInt(tokens[4]));
            livestock.setBuildPrice(new Currency(Integer.parseInt(tokens[5])));
            livestock.setAnimalBuyPrice(new Currency(Integer.parseInt(tokens[6])));
            livestock.setAnimalSellPrice(new Currency(Integer.parseInt(tokens[7])));

            currentLine++;
            tokens = this.livestockLines.get(currentLine).split(" ");
            livestock.setFoodCrop(new Crop(tokens[0]));
            livestock.setRequiredFood(Integer.parseInt(tokens[1]));

            currentLine++;
            tokens = this.livestockLines.get(currentLine).split(" ");
            AnimalProduct product = new AnimalProduct(
                    tokens[0],
                    parseTimeString(tokens[1]),
                    Integer.parseInt(tokens[2]),
                    new Currency(Integer.parseInt(tokens[3])));

            livestock.setProducedItem(product);
            livestockTypes.add(livestock);
            currentLine++;
        }

        return livestockTypes;
    }

    @Override
    protected List<Stockyard> getStockyards() {
        List<Stockyard> stockyards = new ArrayList<>();
        if (this.stockyardLines.isEmpty()) return stockyards;

        int currentLine = 0;
        while (currentLine < this.stockyardLines.size()) {
            String[] tokens = this.stockyardLines.get(currentLine).split(" ");
            Position posTopLeft = new Position(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
            currentLine++;
            tokens = this.stockyardLines.get(currentLine).split(" ");

            String livestockName = tokens[0];
            Livestock livestock = new Livestock();
            boolean found = false;
            for (Livestock l : this.getLivestockTypes()) {
                if (l.getAnimalName().equals(livestockName)) {
                    livestock = l;
                    found = true;
                    break;
                }
            }
            if (!found) {       // Invalid Stockyard
                currentLine++;
                continue;
            }

            Stockyard stockyard = new Stockyard(posTopLeft, livestock);
            stockyards.add(stockyard);
            currentLine++;
        }
        return stockyards;
    }

    @Override
    protected Currency getCurrency() {
        return new Currency(999999);
    }

    @Override
    protected Warehouse getWarehouse() {
        return new Warehouse(new Position(18, 1));
    }

    @Override
    protected Market getMarket() {
        return new Market(new Position(10, 1));
    }

    @Override
    protected InGameTime getTime() {
        return new InGameTime(1, 8, 0);
    }

    @Override
    protected House getHouse() {
        return new House(new Position(2, 1));
    }

    @Override
    protected Set<CropField> getCropFields() {
        Set<CropField> cropFields = new HashSet<>();
        cropFields.add(new CropField(new Position(2, 10)));
        return cropFields;
    }

    @Override
    protected List<Crop> getCrops() {
        List<Crop> crops = new ArrayList<>();
        if (this.cropsLines.isEmpty()) return crops;

        int currentLine = 0;
        while (currentLine < this.cropsLines.size()) {
            String[] tokens = this.cropsLines.get(currentLine).split(" ");
            String cropName = tokens[0];
            InGameTime cropGrowthTime = parseTimeString(tokens[1]);
            int numberOfStages = Integer.parseInt(tokens[2]);
            Currency plantPrice = new Currency(Integer.parseInt(tokens[3]));
            int baseHarvestAmount = Integer.parseInt(tokens[4]);
            Currency sellPrice = new Currency(Integer.parseInt(tokens[5]));

            List<CropGrowthStage> growthStages = new ArrayList<>();
            currentLine++;
            for (int i = 0; i < numberOfStages; i++) {
                growthStages.add(parseGrowthStage(this.cropsLines.get(currentLine)));
                currentLine++;
            }
            crops.add(new Crop(cropName, cropGrowthTime, growthStages, baseHarvestAmount, plantPrice, sellPrice));
        }

        return crops;
    }

    // TODO is this supposed to be here or in respective class?
    private static CropGrowthStage parseGrowthStage(String s) {
        String tokens[] = s.split(" ");
        InGameTime stageTime = parseTimeString(tokens[0]);
        char stageChar = tokens[1].charAt(0);
        Color stageColor = new Color(tokens[2]);
        return new CropGrowthStage(stageTime, stageChar, stageColor);
    }

    // TODO is this supposed to be here or in respective class?
    private static InGameTime parseTimeString(String s) {
        String[] tokens = s.split(":");
        return new InGameTime(0, Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
    }
}
