package controller;

import gui.GUI;
import gui.MouseListener;
import model.Position;

import java.io.IOException;

public class GameController implements MouseListener {
    public static final int FPS = 50;
    private GameControllerState gameControllerState;
    private GUI gui;
    private boolean running;

    public GameController(GUI gui) {
        this.gui = gui;
        this.gui.setMouseListener(this);
        this.running = false;
    }

    public GameController(GUI gui, GameControllerState initialState) {
        this(gui);
        this.gameControllerState = initialState;
    }

    public void setGameControllerState(GameControllerState state) {
        if (this.running) {
            this.gameControllerState.reactChangeState();
        }
        this.gameControllerState = state;
        if (this.running) {
            // Update mouse position in new state
            this.gameControllerState.reactMouseMovement(new Position(this.gui.getMouseX(), this.gui.getMouseY()));
        }
    }

    public GameControllerState getGameControllerState() {
        return this.gameControllerState;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void run() throws IOException {
        this.running = true;

        int frameTime = 1000/FPS;
        long lastFrameStartTime = System.currentTimeMillis();

        while (this.running) {
            long startTime = System.currentTimeMillis();
            this.runFrame(startTime - lastFrameStartTime);
            this.sleepRestOfFrame(frameTime, startTime);
            lastFrameStartTime = startTime;
        }

        this.gui.close();
    }

    public void runFrame(long elapsedTimeSinceLastFrame) throws IOException {
        this.gameControllerState.getViewer().drawScreen(this.gui);

        GUI.KEYBOARD_ACTION action = this.gui.getNextKeyboardAction();
        if (action == GUI.KEYBOARD_ACTION.QUIT) {
            this.setRunning(false);
            return;
        }

        this.gameControllerState.reactKeyboard(action);
        this.gameControllerState.reactTimePassed(elapsedTimeSinceLastFrame);
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
        if (!this.running) return;
        this.gameControllerState.reactMouseMovement(new Position(x, y));
    }

    @Override
    public void onMouseClick(int x, int y) {
        if (!this.running) return;
        this.gameControllerState.reactMouseClick(new Position(x, y));
    }

    public int getWindowWidth() {
        return this.gui.getWindowWidth();
    }

    public int getWindowHeight() {
        return this.gui.getWindowHeight();
    }
}
