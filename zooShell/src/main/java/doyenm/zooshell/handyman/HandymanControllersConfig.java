package doyenm.zooshell.handyman;

import doyenm.zooshell.evaluation.HandymanEvaluationController;
import doyenm.zooshell.evaluation.handyman.HandymanAgeEvaluationController;
import doyenm.zooshell.handyman.create.CreationController;
import doyenm.zooshell.handyman.details.HandymanDetailsController;
import doyenm.zooshell.handyman.occupations.UpdateOccupations;
import doyenm.zooshell.handyman.occupations.UpdateOccupationsController;
import doyenm.zooshell.handyman.rename.RenamingController;
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
    public RenamingController renamingController(){
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
    public UpdateOccupations.RemovingController removingController(){
        return new UpdateOccupations.RemovingController();
    }
}
