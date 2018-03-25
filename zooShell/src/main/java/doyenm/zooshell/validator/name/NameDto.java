package doyenm.zooshell.validator.name;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

/**
 *
 * @author doyenm
 */
@Getter
@Builder
public class NameDto {
    private final String testing;
    private final Set<String> existingNames;
}
