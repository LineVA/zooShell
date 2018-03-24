/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.zooshell.commandline.commandimpl.handyman;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.HandymanUpdateOccupationsContext;
import doyenm.zooshell.controller.handymancontroller.UpdateOccupationsController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.HandymanUpdateOccupationsValidator;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdateOccupations implements Command {

    private final HandymanUpdateOccupationsValidator validator;
    private final UpdateOccupationsController controller;
    private boolean addition;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        HandymanUpdateOccupationsContext context = new HandymanUpdateOccupationsContext(zoo,
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
        if (cmd.length == 4) {
            if (Arrays.asList(Constants.HANDYMAN_OCCUPATIONS).contains(cmd[0])) {
                if (Constants.ADD.equalsIgnoreCase(cmd[2])) {
                    addition = true;
                    return true;
                } else if (Constants.REMOVE.equalsIgnoreCase(cmd[2])) {
                    addition = false;
                    return true;
                }
            }
        }
        return false;
    }

}
