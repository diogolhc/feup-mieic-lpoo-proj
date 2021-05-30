package controller.menu;

import controller.GameController;
import controller.command.Command;
import controller.command.NoOperationCommand;
import controller.menu.element.ButtonController;
import model.Position;
import model.menu.Button;
import model.menu.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
        menu = new Menu();
        gameController = Mockito.mock(GameController.class);
        controller = new MenuController(menu, gameController) {
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
        controller.addButton(buttonController);
        Assertions.assertTrue(menu.getButtons().contains(button));
    }

    @Test
    public void reactMouseMovement() {
        menu.setTopLeftPosition(new Position(4, 6));
        List<ButtonController> buttonControllers = new ArrayList<>();
        buttonControllers.add(Mockito.mock(ButtonController.class));
        buttonControllers.add(Mockito.mock(ButtonController.class));
        buttonControllers.add(Mockito.mock(ButtonController.class));

        for (ButtonController buttonController: buttonControllers) {
            controller.addButton(buttonController);
        }

        Position position = new Position(6, 7);
        controller.reactMouseMovement(position);
        for (ButtonController buttonController: buttonControllers) {
            Mockito.verify(buttonController, Mockito.times(1))
                    .reactMouseMovement(Mockito.eq(new Position(2, 1)));
        }
    }

    @Test
    public void reactMouseClick() {
        menu.setTopLeftPosition(new Position(7, 4));
        List<ButtonController> buttonControllers = new ArrayList<>();
        buttonControllers.add(Mockito.mock(ButtonController.class));
        buttonControllers.add(Mockito.mock(ButtonController.class));
        buttonControllers.add(Mockito.mock(ButtonController.class));

        for (ButtonController buttonController: buttonControllers) {
            controller.addButton(buttonController);
        }

        Position position = new Position(5, 5);
        controller.reactMouseClick(position);
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
            controller.addButton(buttonController);
        }

        controller.reactChangeState();
        for (Button button: buttons) {
            Assertions.assertFalse(button.isSelected());
        }
    }
}
