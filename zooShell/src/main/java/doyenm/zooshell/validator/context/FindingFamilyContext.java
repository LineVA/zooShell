package doyenm.zooshell.validator.context;

import doyenm.zooshell.model.Family;
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
public class FindingFamilyContext {

    private final String family;
    private Family convertedFamily;
}
