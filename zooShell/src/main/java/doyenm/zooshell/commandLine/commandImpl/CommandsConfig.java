package doyenm.zooshell.commandLine.commandImpl;

import doyenm.zooshell.commandLine.commandImpl.animal.AnimalCommandsConfig;
import doyenm.zooshell.commandLine.commandImpl.handyman.HandymanCommandsConfig;
import doyenm.zooshell.commandLine.commandImpl.paddock.PaddockCommandsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author doyenm
 */
@Configuration
@Import({AnimalCommandsConfig.class, HandymanCommandsConfig.class,
    PaddockCommandsConfig.class})
public class CommandsConfig {

    @Autowired
    public AnimalCommandsConfig animalCommandsConfig;

    @Autowired
    public HandymanCommandsConfig handymanCommandsConfig;
    
    @Autowired
    public PaddockCommandsConfig paddockCommandsConfig;

    @Bean
    public LsPenalties lsPenalties() {
        return new LsPenalties();
    }
}
