package doyenm.zooshell.commandline.commandImpl;

import doyenm.zooshell.backup.BackupConfig;
import doyenm.zooshell.commandline.commandImpl.animal.AnimalCommandsConfig;
import doyenm.zooshell.commandline.commandImpl.handyman.HandymanCommandsConfig;
import doyenm.zooshell.commandline.commandImpl.keeper.KeeperCommandsConfig;
import doyenm.zooshell.commandline.commandImpl.paddock.PaddockCommandsConfig;
import doyenm.zooshell.commandline.commandImpl.zoo.ZooCommandsConfig;
import doyenm.zooshell.commandline.general.displayingevent.DisplayingBinaryAnimalEvents;
import doyenm.zooshell.commandline.general.displayingevent.DisplayingBinaryPaddockEvents;
import doyenm.zooshell.commandline.general.displayingevent.DisplayingBinaryZooEvents;
import doyenm.zooshell.commandline.general.displayingevent.DisplayingEvents;
import doyenm.zooshell.commandline.general.displayingevent.DisplayingNoneZooEvents;
import doyenm.zooshell.commandline.general.displayingevent.DisplayingUnaryAnimalEvents;
import doyenm.zooshell.commandline.general.displayingevent.DisplayingUnaryKeeperEvents;
import doyenm.zooshell.commandline.general.displayingevent.DisplayingUnaryZooEvents;
import doyenm.zooshell.controller.ControllersConfig;
import doyenm.zooshell.controller.speciecontroller.SpecieControllersConfig;
import doyenm.zooshell.validator.ValidatorsConfig;
import java.util.Arrays;
import java.util.List;
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
