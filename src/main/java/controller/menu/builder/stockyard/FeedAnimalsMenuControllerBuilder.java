package controller.menu.builder.stockyard;

import controller.GameController;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.FeedAnimalsCommand;
import controller.command.OpenPopupMenuCommand;
import controller.menu.ButtonController;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import model.Position;
import model.farm.Inventory;
import model.farm.building.Stockyard;
import model.farm.item.Crop;
import model.menu.Button;
import model.menu.label.Label;

import java.util.List;

public class FeedAnimalsMenuControllerBuilder extends PopupMenuControllerBuilder {
    private Inventory inventory;
    private Stockyard stockyard;
    private Crop crop;

    public FeedAnimalsMenuControllerBuilder(GameController gameController, Inventory inventory, Stockyard stockyard, Crop crop) {
        super(gameController);
        this.inventory = inventory;
        this.stockyard = stockyard;
        this.crop = crop;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();
        int x = 1;
        int y = 9;

        String title = "FEED";

        Button feedAnimalsButton = new Button(new Position(x, y), title);
        Command feedAnimalsCommand;
        if (this.inventory.getAmount(this.stockyard.getLivestockType().getFoodCrop()) >=
            this.stockyard.getFeedQuantity()) {

            feedAnimalsCommand = new CompoundCommand()
                    .addCommand(new FeedAnimalsCommand(this.stockyard, inventory, crop))
                    .addCommand(super.getClosePopupMenuCommand());

        } else {
            feedAnimalsCommand = new OpenPopupMenuCommand(this.controller,
                    new AlertMenuControllerBuilder(this.controller, "NOT ENOUGH " +
                            this.stockyard.getLivestockType().getFoodCrop().getName()));
        }
        buttons.add(new ButtonController(feedAnimalsButton, feedAnimalsCommand));

        title = "BUY";
        Button buyAnimalButton = new Button(new Position(x, y), title);
        Command buyAnimalCommand;
        if (this.stockyard.getAnimals().size() < this.stockyard.getMaxNumAnimals()) {

        }

        return buttons;
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();
        int x = 1;
        int y = 3;

        labels.add(new Label(
                new Position(x, y),
                () -> stockyard.getLivestockType().getAnimalName() + ": " + stockyard.getAnimals().size()
        ));

        y += 4;
        labels.add(new Label(
                new Position(x, y),
                () -> stockyard.getLivestockType().getFoodCrop().getName() + ": " +
                        inventory.getAmount(stockyard.getLivestockType().getFoodCrop())
        ));

        x += 7;
        y += 2;
        labels.add(new Label(
                new Position(x, y),
                () ->  stockyard.getLivestockType().getFoodCrop().getName()
        ));

        y += 1;
        labels.add(new Label(
                new Position(x, y),
                () -> "NEEDED"
        ));

        y += 1;
        labels.add(new Label(
                new Position(x, y),
                () -> String.valueOf(stockyard.getLivestockType().getRequiredFood() * stockyard.getAnimals().size())
        ));

        x = 1;
        y += 2;
        labels.add(new Label(
                new Position(x, y),
                () -> "PRODUCES: " + stockyard.getLivestockType().getProducedItem().getName() + " x" + stockyard.getProduceQuantity()
        ));

        return labels;
    }

    @Override
    protected int getHeight() {
        return 15;
    }

    @Override
    protected int getWidth() {
        return 25;
    }

    @Override
    protected String getTitle() {
        return "STOCKYARD";
    }
}
