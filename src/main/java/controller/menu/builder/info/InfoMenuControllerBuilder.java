package controller.menu.builder.info;

import controller.GameController;
import controller.menu.builder.PopupMenuControllerBuilder;
import gui.Color;
import model.Position;
import model.menu.label.Label;

import java.util.Arrays;
import java.util.List;

public class InfoMenuControllerBuilder extends PopupMenuControllerBuilder {
    public static final Color INFO_MENU_COLOR = new Color("#2C2C2C");

    private final String title;
    private final List<String> lines;
    private final int messageHeight;
    private final int messageWidth;

    public InfoMenuControllerBuilder(GameController controller, String title, String message) {
        super(controller);
        this.title = title;

        this.lines = Arrays.asList(message.split("\n"));
        int longestLine = 0;
        for (String line: this.lines) {
            longestLine = Math.max(longestLine, line.length());
        }
        this.messageHeight = this.lines.size();
        this.messageWidth = longestLine;
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
    public String getTitle() {
        return this.title;
    }

    @Override
    protected Color getColor() {
        return INFO_MENU_COLOR;
    }
}
