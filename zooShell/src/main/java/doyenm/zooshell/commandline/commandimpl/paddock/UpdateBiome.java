package doyenm.zooshell.commandline.commandimpl.paddock;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.UpdateBiomeContext;
import doyenm.zooshell.controller.paddockcontroller.UpdateBiomeController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.UpdateBiomeValidator;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdateBiome implements Command {

    private final UpdateBiomeValidator validator;
    private final UpdateBiomeController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        UpdateBiomeContext context = new UpdateBiomeContext(zoo,
                cmd[2], cmd[3]);
        Optional<UpdateBiomeContext> optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("UPDATE_BIOME_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 4
                && Arrays.asList(Constants.PAD_OR_PADDOCK_BIOME).contains(cmd[0])
                && Arrays.asList(Constants.UPDATE).contains(cmd[1]);
    }
}
