package doyenm.zooshell.controller.handymancontroller;

import doyenm.zooshell.controller.handymancontroller.evaluation.HandymanAgeEvaluationController;
import doyenm.zooshell.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author doyenm
 */
@Configuration
public class HandymanControllersConfig {
       
    @Autowired
    Utils utils;

    // Controller
    @Bean
    public CreationController creationController() {
        return new CreationController();
    }
    
    @Bean
    public HandymanDetailsController detailsController(){
        return new HandymanDetailsController(utils);
    }
    
    @Bean
    public RenamingController  renamingController(){
        return new RenamingController();
    }
    
    @Bean
    public UpdateOccupationsController updateOccupationsController(){
        return new UpdateOccupationsController();
    }
    
    @Bean
    public HandymanEvaluationController handymanEvaluationController(){
        return new HandymanEvaluationController(new HandymanAgeEvaluationController());
    }
    
    @Bean
    public RemovingController removingController(){
        return new RemovingController();
    }
}
