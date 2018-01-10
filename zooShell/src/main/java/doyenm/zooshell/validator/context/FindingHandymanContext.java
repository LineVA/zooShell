package doyenm.zooshell.validator.context;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Handyman;
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
public class FindingHandymanContext {
    private final Map<String, Handyman> handymen;
    private final String handymanName;
    @Setter
    private Handyman handyman;
}
