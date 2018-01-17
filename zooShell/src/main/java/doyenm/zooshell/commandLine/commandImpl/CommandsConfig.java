package doyenm.zooshell.commandLine.commandImpl;

import doyenm.zooshell.backup.BackupConfig;
import doyenm.zooshell.commandLine.commandImpl.animal.AnimalCommandsConfig;
import doyenm.zooshell.commandLine.commandImpl.handyman.HandymanCommandsConfig;
import doyenm.zooshell.commandLine.commandImpl.keeper.KeeperCommandsConfig;
import doyenm.zooshell.commandLine.commandImpl.paddock.PaddockCommandsConfig;
import doyenm.zooshell.commandLine.commandImpl.zoo.ZooCommandsConfig;
import doyenm.zooshell.controller.speciecontroller.SpecieControllersConfig;
import doyenm.zooshell.validator.ValidatorsConfig;
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
    KeeperCommandsConfig.class,
    PaddockCommandsConfig.class,
    ZooCommandsConfig.class,
    BackupConfig.class,
    ValidatorsConfig.class,
    SpecieControllersConfig.class})
public class CommandsConfig {

    @Autowired
    public AnimalCommandsConfig animalCommandsConfig;

    @Autowired
    public HandymanCommandsConfig handymanCommandsConfig;

    @Autowired
    public PaddockCommandsConfig paddockCommandsConfig;

    @Autowired
    public KeeperCommandsConfig keeperCommandsConfig;

    @Autowired
    public ZooCommandsConfig zooCommandsConfig;

    @Autowired
    ValidatorsConfig validatorsConfig;

    @Autowired
    SpecieControllersConfig specieControllersConfig;
    
    @Autowired
    BackupConfig backupConfig;

    @Bean
    public LsPenalties lsPenalties() {
        return new LsPenalties();
    }

    @Bean
    public DetailSpecie detailSpecie() {
        return new DetailSpecie(
                validatorsConfig.specieDetailsValidator(),
                specieControllersConfig.specieDetailsController()
        );
    }

    @Bean
    public LsSpecie lsSpecie() {
        return new LsSpecie();
    }

    @Bean
    public Save save() {
        return new Save(backupConfig.saveFunction());
    }

    @Bean
    public Load load() {
        return new Load(backupConfig.loadFunction());
    }

}
