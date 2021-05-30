package controller.menu.builder.building.stockyard;

import controller.GameController;
import controller.command.*;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.command.farm.stockyard.BuyAnimalCommand;
import controller.command.farm.stockyard.FeedAnimalsCommand;
import controller.command.farm.stockyard.SellAnimalCommand;
import controller.menu.element.ButtonController;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import model.InGameTime;
import model.Position;
import model.farm.Currency;
import model.farm.Farm;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.StockyardAnimals;
import model.farm.data.Livestock;
import model.farm.data.item.Crop;
import model.menu.Button;
import model.menu.label.Label;

import java.util.List;

public class FeedAnimalsMenuControllerBuilder extends PopupMenuControllerBuilder {
    private final Livestock livestockType;
    private final Farm farm;
    private final Stockyard stockyard;
    private final Crop crop;
    private final StockyardAnimals animals;

    public FeedAnimalsMenuControllerBuilder(GameController gameController, Farm farm, Stockyard stockyard, Crop crop) {
        super(gameController);
        this.farm = farm;
        this.stockyard = stockyard;
        this.animals = stockyard.getAnimals();
        this.livestockType = stockyard.getLivestockType();
        this.crop = crop;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        addBuyAnimalButton(buttons);
        addSellAnimalButton(buttons);
        addFeedButton(buttons);

        return buttons;
    }

    private void addBuyAnimalButton(List<ButtonController> buttons) {
        PopupMenuControllerBuilder stockyardFullAlert = new AlertMenuControllerBuilder(this.controller,
                "STOCKYARD IS FULL");

        PopupMenuControllerBuilder notEnoughMoneyAlert = new AlertMenuControllerBuilder(this.controller,
                "NOT ENOUGH MONEY");

        Currency buyPrice = this.livestockType.getAnimalBuyPrice();

        Button buyAnimalButton = new Button(new Position(1, 6), "BUY");
        ConditionalCommand buyAnimalCommand = new ConditionalCommand(this.animals::isFull);
        buyAnimalCommand
                .ifTrue(new OpenPopupMenuCommand(this.controller, stockyardFullAlert))
                .elseIf(getCannotBuyAnimalCondition())
                .ifTrue(new OpenPopupMenuCommand(this.controller, notEnoughMoneyAlert))
                .ifFalse(new BuyAnimalCommand(this.farm.getWallet(), this.stockyard.getAnimals(), buyPrice));

        buttons.add(new ButtonController(buyAnimalButton, buyAnimalCommand));
    }

    private ConditionalCommand.Condition getCannotBuyAnimalCondition() {
        return () -> !this.farm.getWallet().canBuy(this.stockyard.getLivestockType().getAnimalBuyPrice());
    }

    private void addSellAnimalButton(List<ButtonController> buttons) {
        PopupMenuControllerBuilder stockyardEmptyAlert = new AlertMenuControllerBuilder(this.controller,
                "STOCKYARD IS EMPTY");

        Currency sellPrice = this.livestockType.getAnimalSellPrice();

        Button sellAnimalButton = new Button(new Position(16, 6), "SELL");
        ConditionalCommand sellAnimalCommand = new ConditionalCommand(this.animals::isEmpty);
        sellAnimalCommand
                .ifTrue(new OpenPopupMenuCommand(this.controller, stockyardEmptyAlert))
                .ifFalse(new SellAnimalCommand(this.farm.getWallet(), this.animals, sellPrice));

        buttons.add(new ButtonController(sellAnimalButton, sellAnimalCommand));
    }

    private void addFeedButton(List<ButtonController> buttons) {
        PopupMenuControllerBuilder stockyardEmptyAlert = new AlertMenuControllerBuilder(this.controller,
                "STOCKYARD IS EMPTY");

        PopupMenuControllerBuilder notEnoughFoodAlert = new AlertMenuControllerBuilder(this.controller,
                "NOT ENOUGH " + this.stockyard.getLivestockType().getFoodCrop().getName());

        Button feedAnimalsButton = new Button(new Position(1, 12), "FEED");
        ConditionalCommand feedAnimalsCommand = new ConditionalCommand(this.animals::isEmpty);
        feedAnimalsCommand
                .ifTrue(new OpenPopupMenuCommand(this.controller, stockyardEmptyAlert))
                .elseIf(getNotEnoughFoodCondition())
                .ifTrue(new OpenPopupMenuCommand(this.controller, notEnoughFoodAlert))
                .ifFalse(new CompoundCommand()
                        .addCommand(new FeedAnimalsCommand(this.stockyard, this.farm.getInventory()))
                        .addCommand(super.getClosePopupMenuCommand()));

        buttons.add(new ButtonController(feedAnimalsButton, feedAnimalsCommand));
    }

    private ConditionalCommand.Condition getNotEnoughFoodCondition() {
        return () -> {
            int amount = this.farm.getInventory().getAmount(this.stockyard.getLivestockType().getFoodCrop());
            return amount < this.stockyard.getRequiredFood();
        };
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();

        addNumAnimalsLabel(labels);
        addBuyAnimalCostLabel(labels);
        addSellAnimalReturnLabel(labels);
        addFoodInInventoryLabel(labels);
        addProductionRequirementLabel(labels);
        addProductionInfoLabel(labels);

        return labels;
    }

    private void addNumAnimalsLabel(List<Label> labels) {
        labels.add(new Label(
                new Position(1, 4),
                () -> String.format("%1$sS: %2$d/%3$d",
                        this.livestockType.getAnimalName(),
                        this.animals.getSize(),
                        this.livestockType.getMaxNumAnimals())
        ));
    }

    private void addBuyAnimalCostLabel(List<Label> labels) {
        labels.add(new Label(new Position(7, 6), "COSTS"));
        labels.add(new Label(
                new Position(7, 7),
                () -> String.format("%1$5s", this.livestockType.getAnimalBuyPrice())
        ));
    }

    private void addSellAnimalReturnLabel(List<Label> labels) {
        labels.add(new Label(new Position(23, 6), "RETURN"));
        labels.add(new Label(
                new Position(23, 7),
                () -> String.format("%1$6s", this.livestockType.getAnimalSellPrice())
        ));
    }

    private void addFoodInInventoryLabel(List<Label> labels) {
        labels.add(new Label(
                new Position(1, 10),
                () -> {
                    String foodName = this.livestockType.getFoodCrop().getName();
                    int amount = this.farm.getInventory().getAmount(this.livestockType.getFoodCrop());

                    return String.format("%1$s: %2$s", foodName, "x" + amount);
                }
        ));
    }

    private void addProductionRequirementLabel(List<Label> labels) {
        labels.add(new Label(new Position(8, 12), "NEEDS"));
        labels.add(new Label(
                new Position(8, 13),
                () -> {
                    if (this.animals.isEmpty()) {
                        return this.livestockType.getAnimalName() + "S";
                    } else {
                        String foodName = this.livestockType.getFoodCrop().getName();
                        int foodRequirement = this.stockyard.getRequiredFood();

                        return foodName + " x" + foodRequirement;
                    }
                }
        ));
    }

    private void addProductionInfoLabel(List<Label> labels) {
        labels.add(new Label(
                new Position(1, 16),
                () -> {
                    if (this.animals.isEmpty()) {
                        return "STOCKYARD IS EMPTY";
                    } else {
                        String itemName = this.livestockType.getProducedItem().getName();
                        int itemAmount = this.stockyard.getBaseProducedAmount();
                        InGameTime productionTime = this.livestockType.getProducedItem().getProductionTime();

                        return String.format("PRODUCES: %1$s %2$s IN %3$s",
                                itemName,
                                "x" + itemAmount,
                                productionTime.getTimerString());
                    }
                }
        ));
    }

    @Override
    protected int getHeight() {
        return 18;
    }

    @Override
    protected int getWidth() {
        return 30;
    }

    @Override
    public String getTitle() {
        return "STOCKYARD";
    }
}
