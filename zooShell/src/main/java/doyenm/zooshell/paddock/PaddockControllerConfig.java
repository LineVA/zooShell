package doyenm.zooshell.paddock;

import doyenm.zooshell.evaluation.PaddockEvaluationController;
import doyenm.zooshell.evaluation.paddock.*;
import doyenm.zooshell.model.Handyman;
import doyenm.zooshell.model.PaddockState;
import doyenm.zooshell.paddock.biomes.UpdateBiomeController;
import doyenm.zooshell.paddock.creation.PaddockCreationController;
import doyenm.zooshell.paddock.details.PaddockDetailsController;
import doyenm.zooshell.paddock.entry.PaddockEntryCreationController;
import doyenm.zooshell.paddock.extension.PaddockExtensionCreationController;
import doyenm.zooshell.paddock.facilities.ResetPaddockFacilitiesController;
import doyenm.zooshell.paddock.facilities.UpdatePaddockArrangementController;
import doyenm.zooshell.paddock.remove.PaddockRemoveController;
import doyenm.zooshell.paddock.rename.PaddockChangeNameController;
import doyenm.zooshell.paddock.types.UpdatePaddockTypeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
@Configuration
public class PaddockControllerConfig {
    
    @Autowired
    Environment environment;

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
    
    @Bean
    public UpdatePaddockArrangementController updatePaddockArrangementController() {
        return new UpdatePaddockArrangementController();
    }

    @Bean
    public ResetPaddockFacilitiesController resetPaddockFacilitiesController(){
        return new ResetPaddockFacilitiesController();
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
                computeObsolescenceErasedByHandymen(),
                obsolescenceToStateFunction()
        );
    }
    
    @Bean
    PaddockEvacuationController paddockEvacuationController(){
        return new PaddockEvacuationController(
        environment.getProperty("paddock.obsolescence.max_number_of_turn_when_unusable", Integer.class),
        environment.getProperty("paddock.obsolescence.penality_duration", Integer.class),
        environment.getProperty("paddock.obsolescence.penality_duration", Double.class));
    }

    @Bean
    public PaddockEvaluationController paddockEvaluationController() {
        return new PaddockEvaluationController(
                paddockAgeEvaluationController(),
                obsolescenceEvaluationController(),
                new UpdateUnusableState(obsolescenceToStateFunction()),
                paddockEvacuationController()
        );
    }

    Function<Double, PaddockState> obsolescenceToStateFunction() {
        return new Function<Double, PaddockState>() {
            @Override
            public PaddockState apply(Double t) {
                if (t < environment.getProperty("paddock.obsolescence.new_limit", Double.class)) {
                    return PaddockState.NEW;
                } else if (t < environment.getProperty("paddock.obsolescence.excellent_limit", Double.class)) {
                    return PaddockState.EXCELLENT;
                } else if (t < environment.getProperty("paddock.obsolescence.good_limit", Double.class)) {
                    return PaddockState.GOOD;
                } else if (t < environment.getProperty("paddock.obsolescence.fair_limit", Double.class)) {
                    return PaddockState.FAIR;
                } else if (t < environment.getProperty("paddock.obsolescence.damaged_limit", Double.class)) {
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

    Function<LightZooDto, Double> computeObsolescenceErasedByHandymen() {
        return new Function<LightZooDto, Double>() {
            @Override
            public Double apply(LightZooDto dto) {
                double sum = 0.0;
                for (Handyman handyman : dto.getHandymen()) {
                    sum += 0.05 / handyman.getAffectations().size();
                }
                return sum;
            }
        };
    }
}
