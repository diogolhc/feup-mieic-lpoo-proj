package controller.menu;

import model.menu.Menu;

public class MenuControllerTest {
    private Menu menu;
    private MenuController controller;
/*
    @BeforeEach
    public void setUp() {
        menu = new Menu("TEST", new Position(0, 0), 10, 10);
        menu.addButton(Mockito.mock(Button.class));
        menu.addButton(Mockito.mock(Button.class));
        menu.addButton(Mockito.mock(Button.class));
        controller = new MenuController(menu);
    }

    @Test
    public void reactMouseMovement() {
        Position position = new Position(5, 7);
        controller.reactMouseMovement(position);
        for (Button button: menu.getButtons()) {
            Mockito.verify(button, Mockito.times(1)).contains(position);
        }
    }

    @Test
    public void reactMouseClick() {
        Position position = new Position(5, 7);
        controller.reactMouseClick(position);
        for (Button button: menu.getButtons()) {
            Mockito.verify(button, Mockito.times(1)).contains(position);
        }
    }

 */
}
