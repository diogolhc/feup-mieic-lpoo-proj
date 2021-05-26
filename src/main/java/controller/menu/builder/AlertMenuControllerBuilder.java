package controller.menu.builder;

import controller.GameController;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.RemoveCropCommand;
import controller.menu.ButtonController;
import controller.menu.MenuController;
import controller.menu.PopupMenuControllerWithClosingCondition;
import controller.menu.builder.crop_field.HarvestMenuControllerBuilder;
import model.Position;
import model.farm.Farm;
import model.farm.building.CropField;
import model.farm.building.crop_field_state.ReadyToHarvest;
import model.menu.Button;
import model.menu.Menu;
import model.menu.label.Label;
import model.menu.label.LabelText;

import java.util.*;

public class AlertMenuControllerBuilder extends PopupMenuControllerBuilder {
    private final List<String> lines;
    private final int messageHeight;
    private final int messageWidth;

    public AlertMenuControllerBuilder(GameController controller, String message) {
        super(controller);

        this.lines = Arrays.asList(message.split("\n"));
        List<Integer> lineWidths = new ArrayList<>();
        for (String line: this.lines) {
            lineWidths.add(line.length());
        }
        this.messageHeight = lineWidths.size();
        int messageWidth = Collections.max(lineWidths);
        if (messageWidth < 4) messageWidth = 4;
        this.messageWidth = messageWidth;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        int buttonX = this.getWidth()/2 - 2;
        int buttonY = this.getHeight() - 4;
        Button okButton = new Button(new Position(buttonX, buttonY), "OK");
        buttons.add(new ButtonController(okButton, super.getClosePopupMenuCommand()));

        return buttons;
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();

        int y = 4;
        for (String line: this.lines) {
            labels.add(new Label(new Position(1, y), line));
            y += 1;
        }

        return labels;
    }

    @Override
    protected int getHeight() {
        return messageHeight + 9;
    }

    @Override
    protected int getWidth() {
        return messageWidth + 2;
    }

    @Override
    protected String getTitle() {
        return "ALERT";
    }
}
