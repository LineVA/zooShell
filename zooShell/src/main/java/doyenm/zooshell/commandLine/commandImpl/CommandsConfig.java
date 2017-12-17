package doyenm.zooshell.commandLine.commandImpl;

import doyenm.zooshell.commandLine.commandImpl.animal.AnimalCommandsConfig;
import doyenm.zooshell.commandLine.commandImpl.keeper.KeeperCommandsConfig;
import doyenm.zooshell.commandLine.commandImpl.paddock.PaddockCommandsConfig;
import doyenm.zooshell.commandLine.commandImpl.zoo.ZooCommandsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author doyenm
 */
@Configuration
@Import({AnimalCommandsConfig.class,
    KeeperCommandsConfig.class,
    PaddockCommandsConfig.class,
    ZooCommandsConfig.class})
public class CommandsConfig {

    @Autowired
    public AnimalCommandsConfig animalCommandsConfig;

    @Autowired
    public KeeperCommandsConfig keeperCommandsConfig;
    
    @Autowired
    public PaddockCommandsConfig paddockCommandsConfig;

    @Autowired
    public ZooCommandsConfig zooCommandsConfig;
}
