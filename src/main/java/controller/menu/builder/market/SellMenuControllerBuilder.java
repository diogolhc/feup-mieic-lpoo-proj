package controller.menu.builder.market;

import controller.GameController;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.PlantCropCommand;
import controller.command.SellItemCommand;
import controller.menu.ButtonController;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;
import model.farm.Farm;
import model.farm.Inventory;
import model.farm.item.Crop;
import model.farm.item.Item;
import model.menu.Button;
import model.menu.label.Label;

import java.util.ArrayList;
import java.util.List;

public class SellMenuControllerBuilder extends PopupMenuControllerBuilder {
    private final Farm farm;
    private final List<Item> items;

    public SellMenuControllerBuilder(GameController controller, Farm farm) {
        super(controller);
        this.items = farm.getItems();
        this.farm = farm;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();


        int y = 6;
        for (Item item: items) {
            int x = 1;

            Button sell1Button = new Button(new Position(x, y), "x1");
            Command sell1Command = new SellItemCommand(this.farm, item, 1);
            buttons.add(new ButtonController(sell1Button, sell1Command));

            x += sell1Button.getWidth() + 1;
            Button sell10Button = new Button(new Position(x, y), "x10");
            Command sell10Command = new SellItemCommand(this.farm, item, 10);
            buttons.add(new ButtonController(sell10Button, sell10Command));

            x += sell10Button.getWidth() + 1;
            Button sell100Button = new Button(new Position(x, y), "x100");
            Command sell100Command = new SellItemCommand(this.farm, item, 100);
            buttons.add(new ButtonController(sell100Button, sell100Command));
            y+=5;
        }

        return buttons;
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();

        int y = 4;
        for (Item item: items) {
            labels.add(new Label(
                    new Position(1, y),
                    () -> String.format("%1$-6s %2$4s %3$5s",
                            item.getName(),
                            "x" + this.farm.getInventory().getAmount(item),
                            item.getSellPrice().toString()
                    )
            ));
            y += 5;
        }

        return labels;
    }

    @Override
    protected int getHeight() {
        return 5 + items.size() * 6;
    }

    @Override
    protected int getWidth() {
        return 21;
    }

    @Override
    protected String getTitle() {
        return "SELL";
    }
}
