package doyenm.zooshell.handyman.list;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.commandline.utils.FormattingInList;
import doyenm.zooshell.context.LsContext;
import doyenm.zooshell.model.Handyman;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

/**
 *
 * @author doyenm
 */
public class LsHandyman implements Command{

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        LsContext context = new LsContext(zoo);
        FormattingInList formatting = new FormattingInList();
        return new ReturnExec(formatting.formatList(context.getHandymenNames()), TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 1
                && Arrays.asList(Constants.HANDYMEN)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase);
    }

    /**
     *
     * @author doyenm
     */
    @Setter
    @Getter
    @RequiredArgsConstructor
    public static class HandymanUpdateOccupationsContext {
        private final Zoo zoo;
        private final String handymanName;
        private final String paddockName;
        private final boolean addition;
        private Handyman handyman;
        private Paddock paddock;
    }
}
