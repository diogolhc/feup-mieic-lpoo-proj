package controller.command;

import model.farm.Farm;
import java.io.*;


public class SaveGameCommand implements Command {
    private final Farm farm;

    public SaveGameCommand(Farm farm) {
        this.farm = farm;
    }

    @Override
    public void execute() {
        try {
            FileOutputStream out = new FileOutputStream("save");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(this.farm);

            // TODO should some message be printed without terminating the game since the user can continue to play?
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
