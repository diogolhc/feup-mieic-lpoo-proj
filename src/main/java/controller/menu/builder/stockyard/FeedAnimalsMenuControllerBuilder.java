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

        Button feedAnimalsButton = new Button(new Position(x, y), "FEED");
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
        Button buyAnimalButton = new Button(new Position(x, y), "BUY");
        Command buyAnimalCommand;
        if (this.stockyard.canAddAnimal() &&
                this.farm.getCurrency().canBuy(this.stockyard.getLivestockType().getAnimalBuyPrice())) {

            buyAnimalCommand = new BuyAnimalCommand(this.farm, this.stockyard);

        } else if (this.stockyard.canAddAnimal())  {
            buyAnimalCommand = new OpenPopupMenuCommand(this.controller,
                    new AlertMenuControllerBuilder(this.controller, "NOT ENOUGH MONEY"));
        } else {
            buyAnimalCommand = new OpenPopupMenuCommand(this.controller,
                    new AlertMenuControllerBuilder(this.controller, "STOCKYARD IS FULL"));
        }
        buttons.add(new ButtonController(buyAnimalButton, buyAnimalCommand));

        x = 15;
        Button sellAnimalButton = new Button(new Position(x, y), "SELL");
        Command sellAnimalCommand;
        if (this.stockyard.canRemoveAnimal()) {
            sellAnimalCommand = new SellAnimalCommand(this.farm, this.stockyard);
        } else {
            sellAnimalCommand = new OpenPopupMenuCommand(this.controller,
                    new AlertMenuControllerBuilder(this.controller, "THERE ARE NO " +
                    this.stockyard.getLivestockType().getAnimalName() + " TO SELL"));
        }
        buttons.add(new ButtonController(sellAnimalButton, sellAnimalCommand));
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

        x = 21;
        labels.add(new Label(
                new Position(x, y),
                () -> "RETURN"
        ));

        x = 6;
        y += 1;
        labels.add(new Label(
                new Position(x, y),
                () -> String.valueOf(this.stockyard.getLivestockType().getAnimalBuyPrice())
        ));
        x = 21;
        labels.add(new Label(
                new Position(x, y),
                () -> String.valueOf(this.stockyard.getLivestockType().getAnimalSellPrice())
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
