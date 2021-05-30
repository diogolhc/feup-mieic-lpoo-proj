package controller.menu.builder.building;

import controller.GameController;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;
import model.farm.Farm;
import model.farm.Inventory;
import model.farm.building.Edifice;
import model.farm.data.item.Item;
import model.menu.label.Label;

import java.util.List;

public class WarehouseMenuControllerBuilder extends PopupMenuControllerBuilder {
    private final Inventory inventory;
    private Edifice warehouse;
    private final List<Item> items;

    public WarehouseMenuControllerBuilder(GameController controller, Farm farm, Edifice warehouse) {
        super(controller);
        this.items = farm.getAllItems();
        this.inventory = farm.getInventory();
        this.warehouse = warehouse;
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();

        addSpaceUsedLabel(labels);
        addAllItemsLabels(labels);

        return labels;
    }

    private void addSpaceUsedLabel(List<Label> labels) {
        labels.add(new Label(new Position(1, 4),
                () -> "USED SPACE: " + inventory.getOccupied() + "/" + inventory.getCapacity()));
    }

    private void addAllItemsLabels(List<Label> labels) {
        int y = 6;
        for (Item item: items) {
            addItemLabel(labels, new Position(1, y), item);
            y += 1;
        }
    }

    private void addItemLabel(List<Label> labels, Position position, Item item) {
        labels.add(new Label(position, () -> String.format("%1$-7s %2$6s",
                item.getName(),
                "x" + this.inventory.getAmount(item)
        )));
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
    public String getTitle() {
        return warehouse.getName();
    }
}
