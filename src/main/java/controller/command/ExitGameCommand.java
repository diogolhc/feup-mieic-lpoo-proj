package controller.command;

import controller.GameController;

public class ExitGameCommand implements Command {
    private GameController gameController;

    public ExitGameCommand(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void execute() {
        gameController.endGame();
    }

}
