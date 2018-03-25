package doyenm.zooshell.commandline.general;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@Getter
@RequiredArgsConstructor
public class ActionPointCommand {

    private final Command command;
    private final int actionPointsNumber;
}
