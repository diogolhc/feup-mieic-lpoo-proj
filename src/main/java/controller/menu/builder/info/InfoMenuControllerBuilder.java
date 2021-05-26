package controller.menu.builder.info;

import controller.GameController;
import controller.menu.ButtonController;
import controller.menu.builder.PopupMenuControllerBuilder;
import gui.Color;
import model.Position;
import model.menu.Button;
import model.menu.label.Label;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InfoMenuControllerBuilder extends PopupMenuControllerBuilder {
    private String title;
    private final List<String> lines;
    private final int messageHeight;
    private final int messageWidth;

    public InfoMenuControllerBuilder(GameController controller, String title, String message) {
        super(controller);
        this.title = title;

        this.lines = Arrays.asList(message.split("\n"));
        List<Integer> lineWidths = new ArrayList<>();
        for (String line: this.lines) {
            lineWidths.add(line.length());
        }
        this.messageHeight = lineWidths.size();
        this.messageWidth = Collections.max(lineWidths);
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
        return messageHeight + 5;
    }

    @Override
    protected int getWidth() {
        return messageWidth + 2;
    }

    @Override
    protected String getTitle() {
        return this.title;
    }

    @Override
    protected Color getColor() {
        return new Color("#2C2C2C");
    }
}
