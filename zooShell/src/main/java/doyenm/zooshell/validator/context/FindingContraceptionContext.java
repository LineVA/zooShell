package doyenm.zooshell.validator.context;

import doyenm.zooshell.model.ContraceptionMethod;
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
public class FindingContraceptionContext {

    private final String contraception;
    private ContraceptionMethod convertedContraception;
}
