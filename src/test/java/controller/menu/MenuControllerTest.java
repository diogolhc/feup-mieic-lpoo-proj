package controller.menu;

import controller.GameController;
import controller.command.Command;
import controller.menu.element.ButtonController;
import model.Position;
import model.menu.Button;
import model.menu.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import viewer.GameViewer;

import java.util.ArrayList;
import java.util.List;

public class MenuControllerTest {
    private Menu menu;
    private GameController gameController;
    private MenuController controller;

    @BeforeEach
    public void setUp() {
        this.menu = new Menu();
        this.gameController = Mockito.mock(GameController.class);
        this.controller = new MenuController(this.menu, this.gameController) {
            @Override
            public void reactTimePassed(long elapsedTimeSinceLastFrame) {}

            @Override
            public GameViewer getViewer() {
                return null;
            }
        };
    }

    @Test
    public void addButton() {
        Button button = Mockito.mock(Button.class);
        ButtonController buttonController = new ButtonController(button, Mockito.mock(Command.class));
        this.controller.addButton(buttonController);
        Assertions.assertTrue(this.menu.getButtons().contains(button));
    }

    @Test
    public void reactMouseMovement() {
        this.menu.setTopLeftPosition(new Position(4, 6));
        List<ButtonController> buttonControllers = new ArrayList<>();
        buttonControllers.add(Mockito.mock(ButtonController.class));
        buttonControllers.add(Mockito.mock(ButtonController.class));
        buttonControllers.add(Mockito.mock(ButtonController.class));

        for (ButtonController buttonController: buttonControllers) {
            this.controller.addButton(buttonController);
        }

        Position position = new Position(6, 7);
        this.controller.reactMouseMovement(position);
        for (ButtonController buttonController: buttonControllers) {
            Mockito.verify(buttonController, Mockito.times(1))
                    .reactMouseMovement(Mockito.eq(new Position(2, 1)));
        }
    }

    @Test
    public void reactMouseClick() {
        this.menu.setTopLeftPosition(new Position(7, 4));
        List<ButtonController> buttonControllers = new ArrayList<>();
        buttonControllers.add(Mockito.mock(ButtonController.class));
        buttonControllers.add(Mockito.mock(ButtonController.class));
        buttonControllers.add(Mockito.mock(ButtonController.class));

        for (ButtonController buttonController: buttonControllers) {
            this.controller.addButton(buttonController);
        }

        Position position = new Position(5, 5);
        this.controller.reactMouseClick(position);
        for (ButtonController buttonController: buttonControllers) {
            Mockito.verify(buttonController, Mockito.times(1))
                    .reactMouseClick(Mockito.eq(new Position(-2, 1)));
        }
    }

    @Test
    public void reactChangeState() {
        List<Button> buttons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Button button = new Button(new Position(0, 0), "");
            if (i % 3 == 1) button.select();
            ButtonController buttonController = new ButtonController(button, Mockito.mock(Command.class));
            buttons.add(button);
            this.controller.addButton(buttonController);
        }

        this.controller.reactChangeState();
        for (Button button: buttons) {
            Assertions.assertFalse(button.isSelected());
        }
    }
}
