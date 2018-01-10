package doyenm.zooshell.context;

import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.model.Handyman;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class HandymanEvaluationContext {

    private final Zoo zoo;
    private Handyman handyman;

    public HandymanEvaluationContext(Zoo zoo, Handyman handyman) {
        this.zoo = zoo;
        this.handyman = handyman;
    }
}
