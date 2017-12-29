package doyenm.zooshell.context;

import doyenm.zooshell.model.Handyman;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class HandymanUpdateOccupationsContext {
    private final Zoo zoo;
    private final String handymanName;
    private final String paddockName;
    private Handyman handyman;
    private Paddock paddock;
}
