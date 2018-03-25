package doyenm.zooshell.validator.context;

import doyenm.zooshell.model.Handyman;
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
public class FindingHandymanContext {
    private final Map<String, Handyman> handymen;
    private final String handymanName;
    @Setter
    private Handyman handyman;
}
