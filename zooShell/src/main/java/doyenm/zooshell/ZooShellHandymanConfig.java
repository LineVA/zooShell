/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.zooshell;

import doyenm.zooshell.commandLine.commandImpl.handyman.CreateHandyman;
import doyenm.zooshell.commandLine.commandImpl.handyman.DetailsHandyman;
import doyenm.zooshell.controller.handymancontroller.CreationController;
import doyenm.zooshell.controller.handymancontroller.HandymanDetailsController;
import doyenm.zooshell.utils.Utils;
import doyenm.zooshell.validator.HandymanCreationValidator;
import doyenm.zooshell.validator.HandymanValidator;
import doyenm.zooshell.validator.name.NameValidator;
import doyenm.zooshell.validator.predicates.HandymenNumberPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author doyenm
 */
@Configuration
public class ZooShellHandymanConfig {
    
    @Autowired
    NameValidator nameValidator;
    
    @Autowired
    Utils utils;

    // Controller
    @Bean
    public CreationController creationController() {
        return new CreationController();
    }
    
    @Bean
    public HandymanDetailsController handymanDetailsController(){
        return new HandymanDetailsController(utils);
    }

    // Validator
    @Bean
    public HandymanCreationValidator handymanCreationValidator() {
        return new HandymanCreationValidator(nameValidator,
                new HandymenNumberPredicate(0.2));
    }
    
    @Bean
    public HandymanValidator handymanValidator(){
        return new HandymanValidator();
    }

    // Command
    @Bean
    public CreateHandyman createHandyman() {
        return new CreateHandyman(handymanCreationValidator(), creationController());
    }
    
    @Bean
    public DetailsHandyman detailsHandyman(){
        return new DetailsHandyman(handymanValidator(), handymanDetailsController());
    }
}
