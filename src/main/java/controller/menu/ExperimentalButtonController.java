package controller.menu;

import model.menu.Button;

// TODO won't be around in the final version
public class ExperimentalButtonController extends ButtonController {
    public ExperimentalButtonController(Button button) {
        super(button);
    }

    @Override
    public void doButtonAction() {
        System.out.printf("BUTTON PRESSED\n");
    }
}
