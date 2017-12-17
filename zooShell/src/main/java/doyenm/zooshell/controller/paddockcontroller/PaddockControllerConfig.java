package doyenm.zooshell.controller.paddockcontroller;

import doyenm.zooshell.controller.paddockcontroller.evaluation.LightZooDto;
import doyenm.zooshell.controller.paddockcontroller.evaluation.ObsolescenceEvaluationController;
import doyenm.zooshell.controller.paddockcontroller.evaluation.PaddockAgeEvaluationController;
import doyenm.zooshell.model.PaddockState;
import java.util.function.Function;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author doyenm
 */
public class PaddockControllerConfig {

    @Bean
    public PaddockChangeNameController paddockChangeNameController() {
        return new PaddockChangeNameController();
    }

    @Bean
    public PaddockCreationController paddockCreationController() {
        return new PaddockCreationController();
    }

    @Bean
    public PaddockDetailsController paddockDetailsController() {
        return new PaddockDetailsController(obsolescenceToStateFunction());
    }

    @Bean
    public PaddockEntryCreationController paddockEntryCreationController() {
        return new PaddockEntryCreationController();
    }

    @Bean
    public PaddockExtensionCreationController paddockExtensionCreationController() {
        return new PaddockExtensionCreationController();
    }

    @Bean
    public PaddockRemoveController paddockRemoveController() {
        return new PaddockRemoveController();
    }

    @Bean
    public UpdateBiomeController updateBiomeController() {
        return new UpdateBiomeController();
    }

    @Bean
    public UpdatePaddockTypeController updatePaddockTypeController() {
        return new UpdatePaddockTypeController();
    }

    // Evaluation
    @Bean
    PaddockAgeEvaluationController paddockAgeEvaluationController() {
        return new PaddockAgeEvaluationController();
    }

    @Bean
    ObsolescenceEvaluationController obsolescenceEvaluationController() {
        return new ObsolescenceEvaluationController(
                computeObsolescenceAddedByAge(),
                obsolescenceToStateFunction()
        );
    }

    @Bean
    public PaddockEvaluationController paddockEvaluationController() {
        return new PaddockEvaluationController(
                paddockAgeEvaluationController(),
                obsolescenceEvaluationController());
    }

    Function<Double, PaddockState> obsolescenceToStateFunction() {
        return new Function<Double, PaddockState>() {
            @Override
            public PaddockState apply(Double t) {
                if (t < 0.1) {
                    return PaddockState.NEW;
                } else if (t < 0.2) {
                    return PaddockState.EXCELLENT;
                } else if (t < 0.6) {
                    return PaddockState.GOOD;
                } else if (t < 0.8) {
                    return PaddockState.FAIR;
                } else if (t < 0.95) {
                    return PaddockState.DAMAGED;
                } else {
                    return PaddockState.UNSUABLE;
                }
            }
        };
    }

    Function<LightZooDto, Double> computeObsolescenceAddedByAge() {
        return new Function<LightZooDto, Double>() {
            @Override
            public Double apply(LightZooDto dto) {
                return 0.001 * (1 + dto.getSpeed() * dto.getAnimalsOfThePaddock());
            }
        };
    }
}
