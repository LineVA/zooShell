package doyenm.zooshell.commandline.commandimpl;

import doyenm.zooshell.backup.LoadFunction;
import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.ZooContext;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class Load implements Command {

    private final LoadFunction controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        ZooContext context = new ZooContext(cmd[1]);
        Optional optional = Stream.of(context)
                .map(controller)
                .filter(Objects::nonNull)
                .findFirst();
        if (optional.isPresent()) {
            ZooContext zooContext = (ZooContext) optional.get();
            return new ReturnExec("SUCCESSFUL_LOADING", TypeReturn.SUCCESS, zooContext.getZoo());
        }
        return new ReturnExec("FAILED_LOADING", TypeReturn.ERROR);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2
                && Arrays.asList(Constants.LOAD).contains(cmd[0]);
    }
}