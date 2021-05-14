package model.farm.builder;

import gui.Color;
import model.InGameTime;
import model.Position;
import model.farm.Farmer;
import model.farm.building.House;
import model.farm.building.Market;
import model.farm.building.Warehouse;
import model.farm.building.crop_field.CropField;
import model.farm.crop.Crop;
import model.farm.crop.GrowthStage;
import model.farm.Weather;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

// TODO after implementing loading save from file, this class
//      may be substituted with loading from a file in resources
public class NewGameFarmBuilder extends FarmBuilder {
    private final List<String> cropsLines;
    private final List<String> weatherLines;

    // TODO check invalid formats?
    public NewGameFarmBuilder() throws IOException, URISyntaxException {
        URL resource = NewGameFarmBuilder.class.getResource("/game/crops.data");
        BufferedReader br = new BufferedReader(new FileReader(resource.toURI().getPath()));
        this.cropsLines = this.readLines(br);

        resource = NewGameFarmBuilder.class.getResource("/game/weather.data");
        br = new BufferedReader(new FileReader(resource.toURI().getPath()));
        this.weatherLines = this.readLines(br);
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
            double humidity = Double.parseDouble(this.weatherLines.get(line++));

            Weather weather = new Weather(name, humidity);

            int index;
            if ((index = weathers.indexOf(weather)) != -1) {
                weather = weathers.get(index);
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
            List<GrowthStage> growthStages = new ArrayList<>();
            currentLine++;
            for (int i = 0; i < numberOfStages; i++) {
                growthStages.add(parseGrowthStage(this.cropsLines.get(currentLine)));
                currentLine++;
            }
            crops.add(new Crop(cropName, cropGrowthTime, growthStages));
        }

        return crops;
    }

    // TODO is this supposed to be here or in respective class?
    private static GrowthStage parseGrowthStage(String s) {
        String tokens[] = s.split(" ");
        InGameTime stageTime = parseTimeString(tokens[0]);
        char stageChar = tokens[1].charAt(0);
        Color stageColor = new Color(tokens[2]);
        return new GrowthStage(stageTime, stageChar, stageColor);
    }

    // TODO is this supposed to be here or in respective class?
    private static InGameTime parseTimeString(String s) {
        String[] tokens = s.split(":");
        return new InGameTime(0, Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
    }
}
