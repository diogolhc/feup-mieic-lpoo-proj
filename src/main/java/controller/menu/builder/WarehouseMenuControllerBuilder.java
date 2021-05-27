package controller.menu.builder;

import controller.GameController;
import model.Position;
import model.farm.Farm;
import model.farm.Inventory;
import model.farm.data.item.Item;
import model.menu.label.Label;

import java.util.List;

public class WarehouseMenuControllerBuilder extends PopupMenuControllerBuilder {
    private final Inventory inventory;
    private final List<Item> items;

    public WarehouseMenuControllerBuilder(GameController controller, Farm farm) {
        super(controller);
        this.items = farm.getAllItems();
        this.inventory = farm.getInventory();
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();

        labels.add(new Label(
                new Position(1, 4),
                () -> "USED SPACE: " + inventory.getOccupied() + "/" + inventory.getCapacity()
        ));

        int y = 6;
        for (Item item: items) {
            labels.add(new Label(
                    new Position(1, y),
                    () -> String.format("%1$-7s %2$6s",
                            item.getName(),
                            "x" + this.inventory.getAmount(item)
                    )
            ));
            y += 1;
        }

        return labels;
    }

    @Override
    protected int getHeight() {
        return 7 + items.size();
    }

    @Override
    protected int getWidth() {
        return 21;
    }

    @Override
    protected String getTitle() {
        return "WAREHOUSE";
    }
}
