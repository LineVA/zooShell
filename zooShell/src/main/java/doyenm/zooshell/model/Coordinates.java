package doyenm.zooshell.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class Coordinates {

    private final Position position;
    private final int width;
    private final int height;

    @Override
    public String toString() {
        return "Coordinates{" + "position=" + position + ", width=" + width + ", height=" + height + '}';
    }

}
