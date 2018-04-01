package doyenm.zooshell.common.context;

import doyenm.zooshell.model.Paddock;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

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
