package controller.menu.builder.info;

import controller.GameController;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.NoOperationCommand;
import controller.menu.element.ButtonController;
import model.Position;
import model.menu.Button;

import java.util.List;

public class ConfirmationMenuControllerBuilder extends InfoMenuControllerBuilder {
    private Command yesCommand;
    private Command noCommand;

    public ConfirmationMenuControllerBuilder(GameController controller, String title, String message) {
        super(controller, title, message);
        yesCommand = new NoOperationCommand();
        noCommand = new NoOperationCommand();
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        int buttonX = this.getWidth()/2 - 5;
        int buttonY = this.getHeight() - 4;
        Button yesButton = new Button(new Position(buttonX, buttonY), "YES");
        Command yesCommand = new CompoundCommand()
                .addCommand(this.getClosePopupMenuCommand())
                .addCommand(this.yesCommand);
        buttons.add(new ButtonController(yesButton, yesCommand));

        buttonX += 6;
        Button noButton = new Button(new Position(buttonX, buttonY), "NO");
        Command noCommand = new CompoundCommand()
                .addCommand(this.getClosePopupMenuCommand())
                .addCommand(this.noCommand);
        buttons.add(new ButtonController(noButton, noCommand));

        return buttons;
    }

    @Override
    protected int getHeight() {
        return super.getHeight() + 4;
    }

    @Override
    protected int getWidth() {
        if (super.getWidth() >= 12) {
            return super.getWidth();
        } else {
            return 12;
        }
    }

    public ConfirmationMenuControllerBuilder setYesCommand(Command yesCommand) {
        this.yesCommand = yesCommand;
        return this;
    }

    public ConfirmationMenuControllerBuilder setNoCommand(Command noCommand) {
        this.noCommand = noCommand;
        return this;
    }
}
