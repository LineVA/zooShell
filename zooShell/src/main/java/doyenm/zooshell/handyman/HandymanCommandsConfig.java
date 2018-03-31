package doyenm.zooshell.handyman;

import doyenm.zooshell.handyman.create.CreateHandyman;
import doyenm.zooshell.handyman.details.DetailsHandyman;
import doyenm.zooshell.handyman.list.LsHandyman;
import doyenm.zooshell.handyman.occupations.UpdateOccupations;
import doyenm.zooshell.handyman.rename.RenameHandyman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author doyenm
 */
@Configuration
@Import({HandymanControllersConfig.class, HandymanValidatorsConfig.class})
public class HandymanCommandsConfig {

    @Autowired
    HandymanControllersConfig controllers;

    @Autowired
    HandymanValidatorsConfig validators;

    @Bean
    public CreateHandyman createHandyman() {
        return new CreateHandyman(validators.handymanCreationValidator(), controllers.creationController());
    }

    @Bean
    public DetailsHandyman detailsHandyman() {
        return new DetailsHandyman(validators.handymanValidator(), controllers.detailsController());
    }

    @Bean
    public RenameHandyman renameHandyman() {
        return new RenameHandyman(validators.renamingValidator(), controllers.renamingController());
    }

    @Bean
    public LsHandyman lsHandyman() {
        return new LsHandyman();
    }

    @Bean
    public UpdateOccupations updateHandymanOccupations() {
        return new UpdateOccupations(validators.handymanUpdateOccupationsValidator(),
                controllers.updateOccupationsController());
    }
    
    @Bean
    public UpdateOccupations.RemoveHandyman removeHandyman(){
        return new UpdateOccupations.RemoveHandyman(validators.handymanValidator(), controllers.removingController());
    }
}
