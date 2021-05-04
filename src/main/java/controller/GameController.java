package controller;

import controller.farm.FarmController;
import gui.GUI;
import gui.MouseListener;
import model.Position;
import model.farm.Farm;

import java.io.IOException;

public class GameController implements MouseListener {
    private GameControllerState gameControllerState;
    private GUI gui;
    private boolean running;
    private static final int FPS = 50;

    public GameController(GUI gui) {
        this.gui = gui;
        this.gameControllerState = new FarmController(new Farm(40, 20), this);
        this.gui.setMouseListener(this);
        this.running = true;
    }

    public void setGameControllerState(GameControllerState state) {
        this.gameControllerState = state;
    }

    public GameControllerState getGameControllerState() {
        return this.gameControllerState;
    }

    public void endGame() {
        this.running = false;
    }

    public void run() throws IOException {
        int frameTime = 1000/FPS;
        long lastFrameStartTime = System.currentTimeMillis();

        while (running) {
            long startTime = System.currentTimeMillis();
            this.runFrame(startTime, lastFrameStartTime);
            this.sleepRestOfFrame(frameTime, startTime);
            lastFrameStartTime = startTime;
        }

        this.gui.close();
    }

    private void runFrame(long startTime, long lastFrameStartTime) throws IOException {
        long elapsedTimeSinceLastFrame = startTime - lastFrameStartTime;

        this.gameControllerState.getViewer().drawScreen(gui);

        GUI.ACTION action = this.gui.getNextAction();
        if (action == GUI.ACTION.QUIT) {
            this.endGame();
            return;
        }

        this.gameControllerState.reactKeyboard(action);
        this.gameControllerState.reactTimePassed(); // TODO use elapsedTimeSinceLastFrame
    }

    private void sleepRestOfFrame(int frameTime, long startTime) {
        long elapsedTimeThisFrame = System.currentTimeMillis() - startTime;
        long sleepTime = frameTime - elapsedTimeThisFrame;
        if (sleepTime > 0) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) { }
        }
    }

    @Override
    public void onMouseMovement(int x, int y) {
        this.gameControllerState.reactMouseMovement(new Position(x, y));
    }

    @Override
    public void onMouseClick(int x, int y) {
        this.gameControllerState.reactMouseClick(new Position(x, y));
    }
}
