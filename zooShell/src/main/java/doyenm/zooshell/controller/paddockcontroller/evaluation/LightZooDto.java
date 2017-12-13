package doyenm.zooshell.controller.paddockcontroller.evaluation;

import doyenm.zooshell.model.Paddock;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
@AllArgsConstructor
@Getter
public class LightZooDto {
    Paddock paddock;
    int speed;
    int animalsOfThePaddock;
}
