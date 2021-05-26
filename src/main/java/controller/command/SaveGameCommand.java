package controller.command;

import model.farm.Farm;
import java.io.*;


public class SaveGameCommand implements Command {
    private final Farm farm;
    private String saveFileName;

    public SaveGameCommand(Farm farm, String saveFileName) {
        this.farm = farm;
        this.saveFileName = saveFileName;
    }

    @Override
    public void execute() {
        try {
            FileOutputStream out = new FileOutputStream(this.saveFileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(this.farm);

            // TODO should some message be printed without terminating the game since the user can continue to play?
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
