package doyenm.zooshell.commandLine.commandImpl.paddock;

import doyenm.zooshell.commandLine.general.AbstractCommand;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.PaddockEntryCreationContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockEntryCreationController;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.PaddockEntryCreationValidator;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class CreatePaddockEntry extends AbstractCommand {

    private PaddockEntryCreationValidator validator = new PaddockEntryCreationValidator();
    private PaddockEntryCreationController controller = new PaddockEntryCreationController();

    public CreatePaddockEntry(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            PaddockEntryCreationContext context = new PaddockEntryCreationContext(this.getPlay().getZooModel(),
                    cmd[2], cmd[3], cmd[4]);
            return Stream.of(context)
                    .map(validator)
                    .map(controller)
                    .map(new Function<PaddockEntryCreationContext, PaddockEntryCreationContext>() {
                        @Override
                        public PaddockEntryCreationContext apply(PaddockEntryCreationContext t) {
                            getPlay().setZooModel(t.getZoo());
                            return t;
                        }
                    })
                    .map(new Function<PaddockEntryCreationContext, ReturnExec>() {
                        @Override
                        public ReturnExec apply(PaddockEntryCreationContext t) {
                            setSuccess(true);
                            return new ReturnExec("PADDOCK_ENTRY_CREATION_SUCCESS", TypeReturn.SUCCESS);
                        }
                    })
                    .findFirst()
                    .get();
//        } catch (NameException | IncorrectDimensionsException ex) {
//            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        } catch (java.lang.NumberFormatException ex) {
            return new ReturnExec("NUMBER_FORMAT_EXCEPTION", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 5) {
            if (Arrays.asList(Constants.PAD_OR_PADDOCK_ENTRY).contains(cmd[0])) {
                if (Constants.CREATE.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }
}
