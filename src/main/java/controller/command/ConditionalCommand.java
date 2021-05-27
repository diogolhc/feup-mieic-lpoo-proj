package controller.command;

public class ConditionalCommand implements Command {
    private ConditionalCommandCondition condition;
    private Command ifTrueCommand;
    private Command ifFalseCommand;

    public interface ConditionalCommandCondition {
        boolean evaluate();
    }

    public ConditionalCommand(ConditionalCommandCondition condition) {
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

    public ConditionalCommand elseIf(ConditionalCommandCondition condition) {
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
