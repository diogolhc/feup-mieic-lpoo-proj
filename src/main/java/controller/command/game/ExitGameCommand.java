package controller.command.game;

import controller.GameController;
import controller.command.Command;

public class ExitGameCommand implements Command {
    private final GameController gameController;

    public ExitGameCommand(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void execute() {
        gameController.endGame();
    }
}
