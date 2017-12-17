/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.zooshell;

import doyenm.zooshell.commandLine.commandImpl.handyman.CreateHandyman;
import doyenm.zooshell.controller.handymancontroller.CreationController;
import doyenm.zooshell.validator.HandymanCreationValidator;
import doyenm.zooshell.validator.name.NameValidator;
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
    
    // Controller
    @Bean
    public CreationController creationController(){
        return new CreationController();
    }
    
    // Validator
     @Bean
    public HandymanCreationValidator handymanCreationValidator(){
        return new HandymanCreationValidator(nameValidator);
    } 
    
    // Command
    @Bean
    public CreateHandyman createHandyman(){
        return new CreateHandyman(handymanCreationValidator(), creationController());
    }
}
