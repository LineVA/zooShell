package doyenm.zooshell.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@Builder
public class AnimalKeeper {

    private String name;
    private List<TimedOccupation> occupations;
         
}
