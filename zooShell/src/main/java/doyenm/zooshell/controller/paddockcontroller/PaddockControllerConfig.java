package doyenm.zooshell.controller.paddockcontroller;

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
    
     @Bean
    PaddockAgeEvaluationController paddockAgeEvaluationController(){
        return new PaddockAgeEvaluationController();
    }
    
    @Bean
    public PaddockEvaluationController paddockEvaluationController(){
        return new PaddockEvaluationController(paddockAgeEvaluationController());
    }

    public Function<Double, PaddockState> obsolescenceToStateFunction() {
        return new Function<Double, PaddockState>() {
            @Override
            public PaddockState apply(Double t) {
                if(t<0.1){
                    return PaddockState.NEW;
                } else if(t<0.2){
                    return PaddockState.EXCELLENT;
                } else if(t<0.6){
                    return PaddockState.GOOD;
                } else if(t<0.8){
                    return PaddockState.FAIR;
                } else if(t<0.95) {
                    return PaddockState.DAMAGED;
                } else {
                    return PaddockState.UNSUABLE;
                }
            }
        };
    }
}
