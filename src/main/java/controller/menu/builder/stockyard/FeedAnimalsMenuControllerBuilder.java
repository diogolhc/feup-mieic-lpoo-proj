package controller.menu.builder.stockyard;

import controller.GameController;
import controller.command.*;
import controller.menu.ButtonController;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import model.Position;
import model.farm.Farm;
import model.farm.Inventory;
import model.farm.building.Stockyard;
import model.farm.item.Crop;
import model.menu.Button;
import model.menu.label.Label;

import java.util.List;

public class FeedAnimalsMenuControllerBuilder extends PopupMenuControllerBuilder {
    private Farm farm;
    private Stockyard stockyard;
    private Crop crop;

    public FeedAnimalsMenuControllerBuilder(GameController gameController, Farm farm, Stockyard stockyard, Crop crop) {
        super(gameController);
        this.farm = farm;
        this.stockyard = stockyard;
        this.crop = crop;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();
        int x = 1;
        int y = 10;

        String title = "FEED";

        Button feedAnimalsButton = new Button(new Position(x, y), title);
        Command feedAnimalsCommand;
        if (this.farm.getInventory().getAmount(this.stockyard.getLivestockType().getFoodCrop()) >=
            this.stockyard.getFeedQuantity() && this.stockyard.getAnimals().size() > 0) {

            feedAnimalsCommand = new CompoundCommand()
                    .addCommand(new FeedAnimalsCommand(this.stockyard, this.farm.getInventory(), crop))
                    .addCommand(super.getClosePopupMenuCommand());

        } else if ( this.stockyard.getAnimals().size() == 0 ) {
          feedAnimalsCommand = new OpenPopupMenuCommand(this.controller,
                  new AlertMenuControllerBuilder(this.controller, "THERE ARE NO " +
                          this.stockyard.getLivestockType().getAnimalName()));
        } else {
            feedAnimalsCommand = new OpenPopupMenuCommand(this.controller,
                    new AlertMenuControllerBuilder(this.controller, "NOT ENOUGH " +
                            this.stockyard.getLivestockType().getFoodCrop().getName()));
        }
        buttons.add(new ButtonController(feedAnimalsButton, feedAnimalsCommand));

        y = 5;
        title = "BUY";
        Button buyAnimalButton = new Button(new Position(x, y), title);
        Command buyAnimalCommand;
        if (this.stockyard.getAnimals().size() < this.stockyard.getMaxNumAnimals()) {
            buyAnimalCommand = new CompoundCommand()
                    .addCommand(new BuyAnimalCommand(this.farm, this.stockyard))
                    .addCommand(super.getClosePopupMenuCommand());
        } else {
            buyAnimalCommand = new OpenPopupMenuCommand(this.controller,
                    new AlertMenuControllerBuilder(this.controller, "NOT ENOUGH MONEY"));
        }
        buttons.add(new ButtonController(buyAnimalButton, buyAnimalCommand));

        return buttons;
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();
        int x = 1;
        int y = 3;

        labels.add(new Label(
                new Position(x, y),
                () -> this.stockyard.getLivestockType().getAnimalName() + ": " + this.stockyard.getAnimals().size()
        ));

        x = 6;
        y += 2;
        labels.add(new Label(
                new Position(x, y),
                () -> "COSTS"
        ));
        y += 1;
        labels.add(new Label(
                new Position(x, y),
                () -> String.valueOf(this.stockyard.getLivestockType().getAnimalBuyPrice())
        ));

        x = 1;
        y += 3;
        labels.add(new Label(
                new Position(x, y),
                () -> this.stockyard.getLivestockType().getFoodCrop().getName() + ": " +
                        this.farm.getInventory().getAmount(this.stockyard.getLivestockType().getFoodCrop())
        ));

        x += 7;
        y += 1;
        labels.add(new Label(
                new Position(x, y),
                () ->  this.stockyard.getLivestockType().getFoodCrop().getName()
        ));

        y += 1;
        labels.add(new Label(
                new Position(x, y),
                () -> "NEEDED"
        ));

        y += 1;
        labels.add(new Label(
                new Position(x, y),
                () -> String.valueOf(this.stockyard.getFeedQuantity())
        ));

        x = 1;
        y += 2;
        labels.add(new Label(
                new Position(x, y),
                () -> "PRODUCES: " + this.stockyard.getLivestockType().getProducedItem().getName() + " x" +
                        this.stockyard.getProduceQuantity()
        ));

        return labels;
    }

    @Override
    protected int getHeight() {
        return 15;
    }

    @Override
    protected int getWidth() {
        return 30;
    }

    @Override
    protected String getTitle() {
        return "STOCKYARD";
    }
}
