package doyenm.zooshell.commandLine.commandManagerImpl;

import doyenm.zooshell.commandLine.commandImpl.CreateZoo;
import doyenm.zooshell.commandLine.general.AbstractCommand;
import doyenm.zooshell.commandLine.general.CommandManager;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.SplitDoubleQuotes;
import doyenm.zooshell.commandLine.general.TypeReturn;
import static java.util.Arrays.asList;
import doyenm.zooshell.launch.options.Option;
import doyenm.zooshell.launch.play.Play;

/**
 *
 * @author doyenm
 */
public class FreeCommandManager extends CommandManager {

    private final String LOAD = "load";
    private final String CREATE = "create";

    private final Iterable<AbstractCommand> initialCommands;

    private String currentChangingZooCommand;

    public boolean isChanging = false;

    public boolean isInitiate = false;

    public boolean isSaving = false;

    public FreeCommandManager(Play play, Option option) {
        super(play, option);
        super.setFirstLine("WELCOME");
//        super.setFirstLine(super.getOption().getGeneralCmdBundle().getString("WELCOME"));
        initialCommands = asList(super.getCreateZoo());
    }

    private ReturnExec isNotCurrentlySaving(String[] parse) {
        for (AbstractCommand command : super.getPlayCommands()) {
            if (command.canExecute(parse)) {
                command.setInitiate(isInitiate);
                ReturnExec result = command.execute(parse);
                this.isInitiate |= command.isInitiate();
                return result;
            }
        }
        return new ReturnExec(
                "UNKNOWN_CMD", TypeReturn.ERROR);
    }

    private ReturnExec hasBeenInitiate(String[] parse) {
        return isNotCurrentlySaving(parse);
    }

    private ReturnExec hasNotBeenYetInitiate(String[] parse) {
        for (AbstractCommand command : initialCommands) {
            if (command.canExecute(parse)) {
                ReturnExec result = command.execute(parse);
                this.isInitiate = command.isInitiate();
                return result;
            }
        }
        return new ReturnExec(
                super.getOption().getGeneralCmdBundle().getString("BEGINNING_CMD"), TypeReturn.ERROR);
    }

    @Override
    public ReturnExec run(String cmd) {
        String[] parse = SplitDoubleQuotes.split(cmd);
        if (isInitiate) {
            return hasBeenInitiate(parse);
        }
        return hasNotBeenYetInitiate(parse);
    }
}
