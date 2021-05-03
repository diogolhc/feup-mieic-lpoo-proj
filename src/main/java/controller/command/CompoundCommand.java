package controller.command;

import java.util.ArrayList;
import java.util.List;

public class CompoundCommand implements Command {
    private final List<Command> commands;

    public CompoundCommand() {
        this.commands = new ArrayList<>();
    }

    public CompoundCommand addCommand(Command command) {
        this.commands.add(command);
        return this;
    }

    @Override
    public void execute() {
        for (Command command: this.commands) {
            command.execute();
        }
    }
}
