package doyenm.zooshell.commandLine.commandImpl;

import doyenm.zooshell.backup.LoadFunction;
import doyenm.zooshell.backup.SaveFunction;
import doyenm.zooshell.commandLine.general.Command;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.ZooContext;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
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
        if (cmd.length == 2) {
            return Arrays.asList(Constants.LOAD).contains(cmd[0]);
        }
        return false;
    }
}