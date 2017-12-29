package doyenm.zooshell.commandLine.commandImpl;

import doyenm.zooshell.commandLine.commandImpl.animal.AnimalCommandsConfig;
import doyenm.zooshell.commandLine.commandImpl.handyman.HandymanCommandsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author doyenm
 */
@Configuration
@Import({AnimalCommandsConfig.class, HandymanCommandsConfig.class})
public class CommandsConfig {
    @Autowired
    public AnimalCommandsConfig animalCommandsConfig;
    
    @Autowired
    public HandymanCommandsConfig handymanCommandsConfig;
}
