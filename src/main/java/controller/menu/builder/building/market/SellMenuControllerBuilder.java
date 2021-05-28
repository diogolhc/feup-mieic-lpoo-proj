package controller.menu.builder.building.market;

import controller.GameController;
import controller.command.Command;
import controller.command.farm.SellItemCommand;
import controller.menu.element.ButtonController;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;
import model.farm.Farm;
import model.farm.data.item.Item;
import model.menu.Button;
import model.menu.label.Label;

import java.util.List;

public class SellMenuControllerBuilder extends PopupMenuControllerBuilder {
    private final Farm farm;
    private final List<Item> items;

    public SellMenuControllerBuilder(GameController controller, Farm farm) {
        super(controller);
        this.items = farm.getAllItems();
        this.farm = farm;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        addAllItemsSellButtons(buttons);

        return buttons;
    }

    private void addAllItemsSellButtons(List<ButtonController> buttons) {
        int x = 1;
        int y = 5;
        for (Item item: items) {
            addItemSellButtons(buttons, new Position(x, y), item);

            y += 5;
            if (y >= 20) {
                x += 19;
                y = 5;
            }
        }
    }

    private void addItemSellButtons(List<ButtonController> buttons, Position position, Item item) {
        Button lastButton;

        lastButton = addSellItemButton(buttons, position, item, 1);

        position = position.getTranslated(new Position(lastButton.getWidth() + 1, 0));
        lastButton = addSellItemButton(buttons, position, item, 10);

        position = position.getTranslated(new Position(lastButton.getWidth() + 1, 0));
        addSellItemButton(buttons, position, item, 100);
    }

    private Button addSellItemButton(List<ButtonController> buttons, Position position, Item item, int amount) {
        Button sellButton = new Button(position, "x" + amount);
        Command sellCommand = new SellItemCommand(this.farm.getWallet(), this.farm.getInventory(), item, amount);
        buttons.add(new ButtonController(sellButton, sellCommand));

        return sellButton;
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();

        addAllItemsInfoLabels(labels);

        return labels;
    }

    private void addAllItemsInfoLabels(List<Label> labels) {
        int x = 1;
        int y = 4;
        for (Item item: items) {
            addItemInfoLabel(labels, new Position(x, y), item);

            y += 5;
            if (y >= 19) {
                x += 19;
                y = 4;
            }
        }
    }

    private void addItemInfoLabel(List<Label> labels, Position position, Item item) {
        labels.add(new Label(position,
                () -> String.format("%1$-7s %2$4s %3$4s",
                        item.getName(),
                        "x" + this.farm.getInventory().getAmount(item),
                        item.getSellPrice().toString()
                )
        ));
    }

    @Override
    protected int getHeight() {
        return 19;
    }

    @Override
    protected int getWidth() {
        return 38;
    }

    @Override
    protected String getTitle() {
        return "SELL";
    }
}
