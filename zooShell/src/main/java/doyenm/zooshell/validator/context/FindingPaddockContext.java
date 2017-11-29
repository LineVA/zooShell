package doyenm.zooshell.validator.context;

import doyenm.zooshell.model.Paddock;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@RequiredArgsConstructor
public class FindingPaddockContext {

    private final Map<String, Paddock> paddocks;
    private final String paddockName;
    @Setter
    private Paddock paddock;
}
