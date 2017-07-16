package doyenm.zooshell.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@ToString
public class Coordinates {

    private final Position position;
    private final int width;
    private final int height;
}
