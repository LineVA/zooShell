package doyenm.zooshell.commandLine.commandImpl;

import doyenm.zooshell.commandLine.general.AbstractCommand;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.commandLine.utils.FormattingInList;
import doyenm.zooshell.context.LsContext;
import doyenm.zooshell.context.UpdatePaddockTypeContext;
import doyenm.zooshell.context.ZooDetailsContext;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.utils.Constants;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class LsPaddock extends AbstractCommand {
    
    public LsPaddock(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        super.setSuccess(true);
        LsContext context = new LsContext(getPlay().getZooModel());
        return Stream.of(context)
                .map(new Function<LsContext, ReturnExec>() {
                    @Override
                    public ReturnExec apply(LsContext t) {
                        setSuccess(true);
                        FormattingInList formatting = new FormattingInList();
                        return new ReturnExec(formatting.formatList(context.getPaddockNames()), TypeReturn.SUCCESS);
                    }

                })
                .findFirst()
                .get();
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (Arrays.asList(Constants.LS).contains(cmd[0])) {
                if (Arrays.asList(Constants.PAD_OR_PADDOCK).contains(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}
