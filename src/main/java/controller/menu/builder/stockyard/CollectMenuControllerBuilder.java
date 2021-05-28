package controller.menu.builder.stockyard;

import controller.GameController;
import controller.command.farm.stockyard.CollectStockyardCommand;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.menu.element.ButtonController;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;
import model.farm.Inventory;
import model.farm.building.Stockyard;
import model.menu.Button;
import model.menu.label.Label;

import java.util.List;

public class CollectMenuControllerBuilder extends PopupMenuControllerBuilder {
    private Inventory inventory;
    private Stockyard stockyard;

    public CollectMenuControllerBuilder(GameController controller, Inventory inventory, Stockyard stockyard) {
        super(controller);
        this.inventory = inventory;
        this.stockyard = stockyard;
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();

        labels.add(new Label(
                new Position(1, 4),
                () -> "PRODUCT: " + stockyard.getLivestockType().getProducedItem().getName()
        ));

        labels.add( new Label(
                new Position(1, 5),
                () -> "QUANTITY: " + stockyard.getState().getCollectAmount()
        ));

        return labels;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        Button collectButton = new Button(new Position(1, 7), "COLLECT");

        Command collectButtonCommand = new CompoundCommand()
                .addCommand(new CollectStockyardCommand(inventory, stockyard))
                .addCommand(super.getClosePopupMenuCommand());

        buttons.add(new ButtonController(collectButton, collectButtonCommand));

        return buttons;
    }

    @Override
    protected int getHeight() {
        return 11;
    }

    @Override
    protected int getWidth() {
        return 22;
    }

    @Override
    protected String getTitle() {
        return "READY TO COLLECT";
    }
}
