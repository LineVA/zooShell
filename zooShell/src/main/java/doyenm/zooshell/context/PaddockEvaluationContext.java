package doyenm.zooshell.context;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class PaddockEvaluationContext {

    private final Zoo zoo;
    private Paddock paddock;

    public PaddockEvaluationContext(Zoo zoo, Paddock paddock) {
        this.zoo = zoo;
        this.paddock = paddock;
    }
}
