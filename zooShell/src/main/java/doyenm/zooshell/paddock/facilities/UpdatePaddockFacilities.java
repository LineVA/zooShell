package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.utils.DisplayingErrorsList;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdatePaddockFacilities implements Command {

    private final UpdatePaddocFacilityExistenceValidator existenceValidator;
    private final UpdatePaddockFacilityCoherenceValidator coherenceValidator;
    private final UpdatePaddockFacilityController controller;

    private boolean isAdding;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        UpdatePaddockFacilityContext context = new UpdatePaddockFacilityContext(zoo,
                cmd[2], generateFacilitiesList(cmd), isAdding);
        Optional<UpdatePaddockFacilityContext> optional = Stream.of(context)
                .map(existenceValidator)
                .filter(t -> t.getErrors().isEmpty())
                .map(coherenceValidator)
                .filter(t -> t.getErrors().isEmpty())
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("UPDATE_PADDOCK_ARRANGEMENT_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return DisplayingErrorsList.displayErrorList(context.getErrors());
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        boolean isCommandCorrect = cmd.length >= 4
                && Arrays.asList(Constants.PAD_OR_PADDOCK_FACILITY)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase)
                && Arrays.asList(Constants.ADD_OR_REMOVE)
                .stream()
                .anyMatch(cmd[1]::equalsIgnoreCase);
        if (isCommandCorrect) {
            if (Arrays.asList(Constants.ADD)
                    .stream()
                    .anyMatch(cmd[1]::equalsIgnoreCase)) {
                isAdding = true;
            } else {
                isAdding = false;
            }
        }
        return isCommandCorrect;
    }

    private List<String> generateFacilitiesList(String[] cmd) {
        List<String> list = new ArrayList<>();
        for (int i = 3; i < cmd.length; i++) {
            list.add(cmd[i]);
        }
        return list;
    }
}
