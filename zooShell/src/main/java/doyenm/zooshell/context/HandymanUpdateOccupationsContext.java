package doyenm.zooshell.context;

import doyenm.zooshell.model.Handyman;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Setter
@Getter
@RequiredArgsConstructor
public class HandymanUpdateOccupationsContext {
    private final Zoo zoo;
    private final String handymanName;
    private final String paddockName;
    private final boolean addition;
    private Handyman handyman;
    private Paddock paddock;
}
