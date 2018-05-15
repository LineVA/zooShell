package doyenm.zooshell;

import doyenm.zooshell.backup.BackupConfig;
import doyenm.zooshell.animal.AnimalCommandsConfig;
import doyenm.zooshell.backup.Load;
import doyenm.zooshell.backup.Save;
import doyenm.zooshell.evaluation.Evaluate;
import doyenm.zooshell.handyman.HandymanCommandsConfig;
import doyenm.zooshell.keeper.KeeperCommandsConfig;
import doyenm.zooshell.paddock.PaddockCommandsConfig;
import doyenm.zooshell.specie.details.DetailSpecie;
import doyenm.zooshell.specie.list.LsSpecie;
import doyenm.zooshell.zoo.penalty.LsPenalties;
import doyenm.zooshell.zoo.ZooCommandsConfig;
import doyenm.zooshell.commandline.general.displayingevent.*;
import doyenm.zooshell.specie.SpecieControllersConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.List;

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
    ControllersConfig.class,
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
    ControllersConfig controllersConfig;

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

    private final List<DisplayingEvents> displayingEventsList = Arrays.asList(
            new DisplayingBinaryAnimalEvents(),
            new DisplayingBinaryPaddockEvents(),
            new DisplayingBinaryZooEvents(),
            new DisplayingNoneZooEvents(),
            new DisplayingUnaryAnimalEvents(),
            new DisplayingUnaryZooEvents(),
            new DisplayingUnaryKeeperEvents()
    );

    @Bean
    public Evaluate evaluate() {
        return new Evaluate(controllersConfig.evaluationController(),
                displayingEventsList);
    }

}
