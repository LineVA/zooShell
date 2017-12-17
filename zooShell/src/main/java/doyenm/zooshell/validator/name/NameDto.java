package doyenm.zooshell.validator.name;

import java.util.Set;
import lombok.Builder;
import lombok.Getter;

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
