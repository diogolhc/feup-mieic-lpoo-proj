package controller.menu.builder.stockyard;

import controller.GameController;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.FeedAnimalsCommand;
import controller.menu.ButtonController;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;
import model.farm.building.Stockyard;
import model.farm.item.AnimalProduct;
import model.farm.item.Crop;
import model.menu.Button;

import java.util.List;

public class FeedAnimalsMenuControllerBuilder extends PopupMenuControllerBuilder {
    private Stockyard stockyard;
    private Crop crop;

    public FeedAnimalsMenuControllerBuilder(GameController gameController, Stockyard stockyard, Crop crop) {
        super(gameController);
        this.stockyard = stockyard;
        this.crop = crop;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();
        int x = 1;
        int y = 5;

        Button feedAnimalsButton = new Button(new Position(x, y), stockyard.getLivestockType().getFoodCrop().getName());
        Command feedAnimalsCommand = new CompoundCommand()
                .addCommand(new FeedAnimalsCommand(this.stockyard, crop))
                .addCommand(super.getClosePopupMenuCommand());

        buttons.add(new ButtonController(feedAnimalsButton, feedAnimalsCommand));

        return buttons;
    }

    @Override
    protected int getHeight() {
        return 10;
    }

    @Override
    protected int getWidth() {
        return 20;
    }

    @Override
    protected String getTitle() {
        return "STOCKYARD";
    }
}
