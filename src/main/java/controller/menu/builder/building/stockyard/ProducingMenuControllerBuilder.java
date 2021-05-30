package controller.menu.builder.building.stockyard;

import controller.GameController;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.ConditionalCommand;
import controller.command.controller_state.SetControllerStateCommand;
import controller.command.farm.stockyard.StopProducingStockyardCommand;
import controller.menu.element.ButtonController;
import controller.menu.MenuController;
import controller.menu.PopupMenuControllerWithTimePassedReaction;
import controller.menu.builder.MenuControllerBuilder;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;
import model.farm.Farm;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.ReadyToCollect;
import model.menu.Button;
import model.menu.Menu;
import model.menu.label.Label;

import java.util.List;

public class ProducingMenuControllerBuilder extends PopupMenuControllerBuilder {
    private Farm farm;
    private Stockyard stockyard;

    public ProducingMenuControllerBuilder(GameController controller, Farm farm, Stockyard stockyard) {
        super(controller);
        this.farm = farm;
        this.stockyard = stockyard;
    }

    @Override
    protected MenuController getMenuController(Menu menu) {
        MenuControllerBuilder collectMenuControllerBuilder = new CollectMenuControllerBuilder(
                this.controller, this.farm.getInventory(), this.stockyard);

        MenuController collectMenuController = collectMenuControllerBuilder.buildMenuCentered(
                this.controller.getWindowWidth(), this.controller.getWindowHeight());

        Command closingCondition = new ConditionalCommand(() -> this.stockyard.getState() instanceof ReadyToCollect)
            .ifTrue(new SetControllerStateCommand(this.controller, collectMenuController));

        return new PopupMenuControllerWithTimePassedReaction(menu, this.controller,
                this.controller.getGameControllerState(), closingCondition);
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        addStopProducingButton(buttons);

        return buttons;
    }

    private void addStopProducingButton(List<ButtonController> buttons) {
        Button stopProducingButton = new Button(new Position(1, 8), "STOP PRODUCING");

        Command stopProducingButtonCommand = new CompoundCommand()
                .addCommand(new StopProducingStockyardCommand(this.stockyard))
                .addCommand(super.getClosePopupMenuCommand());

        buttons.add(new ButtonController(stopProducingButton, stopProducingButtonCommand));
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();

        addItemBeingProducedLabel(labels);
        addRemainingTimeLabel(labels);
        addQuantityLabel(labels);

        return labels;
    }

    private void addItemBeingProducedLabel(List<Label> labels) {
        labels.add(new Label(
                new Position(1, 4),
                () -> "PRODUCT: " + this.stockyard.getLivestockType().getProducedItem().getName()
        ));
    }

    private void addRemainingTimeLabel(List<Label> labels) {
        labels.add(new Label(
                new Position(1, 5),
                () -> "REMAINING TIME: " + this.stockyard.getRemainingTime().getTimerString()
        ));
    }

    private void addQuantityLabel(List<Label> labels) {
        labels.add( new Label(
                new Position(1, 6),
                () -> "QUANTITY: " + this.stockyard.getState().getCollectAmount()
        ));
    }

    @Override
    protected int getHeight() {
        return 12;
    }

    @Override
    protected int getWidth() {
        return 23;
    }

    @Override
    public String getTitle() {
        return "PRODUCING";
    }
}
