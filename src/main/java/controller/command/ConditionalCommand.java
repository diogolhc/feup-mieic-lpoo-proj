package controller.command;

public class ConditionalCommand implements Command {
    private final Condition condition;
    private Command ifTrueCommand;
    private Command ifFalseCommand;

    public interface Condition {
        boolean evaluate();
    }

    public ConditionalCommand(Condition condition) {
        this.condition = condition;
        this.ifTrueCommand = new NoOperationCommand();
        this.ifFalseCommand = new NoOperationCommand();
    }

    public ConditionalCommand ifTrue(Command ifTrue) {
        this.ifTrueCommand = ifTrue;
        return this;
    }

    public ConditionalCommand ifFalse(Command ifFalse) {
        this.ifFalseCommand = ifFalse;
        return this;
    }

    public ConditionalCommand elseIf(Condition condition) {
        ConditionalCommand command = new ConditionalCommand(condition);
        this.ifFalseCommand = command;
        return command;
    }

    @Override
    public void execute() {
        if (this.condition.evaluate()) {
            this.ifTrueCommand.execute();
        } else {
            this.ifFalseCommand.execute();
        }
    }
}
