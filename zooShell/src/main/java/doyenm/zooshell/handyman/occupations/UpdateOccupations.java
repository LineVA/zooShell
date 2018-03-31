/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.zooshell.handyman.occupations;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.handyman.HandymanContext;
import doyenm.zooshell.handyman.list.LsHandyman;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.handyman.HandymanValidator;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdateOccupations implements Command {

    private final HandymanUpdateOccupationsValidator validator;
    private final UpdateOccupationsController controller;
    private boolean addition;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        LsHandyman.HandymanUpdateOccupationsContext context = new LsHandyman.HandymanUpdateOccupationsContext(zoo,
                cmd[1], cmd[3], addition);
        Optional optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("HANDYMAN_OCCUPATION_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 4
                && Arrays.asList(Constants.HANDYMAN_OCCUPATIONS)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase)) {
            if (Arrays.asList(Constants.ADD)
                    .stream()
                    .anyMatch(cmd[2]::equalsIgnoreCase)) {
                addition = true;
                return true;
            } else if (Arrays.asList(Constants.REMOVE)
                    .stream()
                    .anyMatch(cmd[2]::equalsIgnoreCase)) {
                addition = false;
                return true;
            }
        }
        return false;
    }

    /**
     * @author doyenm
     */
    @RequiredArgsConstructor
    public static class RemoveHandyman implements Command {

        private final HandymanValidator validator;
        private final RemovingController controller;

        @Override
        public ReturnExec execute(String[] cmd, Zoo zoo) {
            HandymanContext context = new HandymanContext(zoo,
                    cmd[2]);
            Optional optional = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst();
            if (optional.isPresent()) {
                return new ReturnExec("HANDYMAN_REMOVE_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
            } else {
                return new ReturnExec("ERROR", TypeReturn.ERROR);
            }
        }

        @Override
        public boolean canExecute(String[] cmd) {
            return cmd.length == 3
                    && Arrays.asList(Constants.HANDYMAN_OR_HD)
                    .stream()
                    .anyMatch(cmd[0]::equalsIgnoreCase)
                    && Arrays.asList(Constants.REMOVE)
                    .stream()
                    .anyMatch(cmd[1]::equalsIgnoreCase);
        }

    }

    /**
     *
     * @author doyenm
     */
    public static class RemovingController
            implements Function<HandymanContext, HandymanContext> {

        @Override
        public HandymanContext apply(HandymanContext t) {
            t.getZoo().getHandymen().remove(t.getHandymanName().toUpperCase());
            return t;
        }

    }
}
