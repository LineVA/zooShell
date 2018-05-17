package doyenm.zooshell;

import doyenm.zooshell.actionpoint.ActionPointCommandsConfig;
import doyenm.zooshell.animal.AnimalCommandsConfig;
import doyenm.zooshell.backup.BackupConfig;
import doyenm.zooshell.backup.Load;
import doyenm.zooshell.backup.Save;
import doyenm.zooshell.commandline.general.displayingevent.*;
import doyenm.zooshell.common.CommonConfigs;
import doyenm.zooshell.evaluation.Evaluate;
import doyenm.zooshell.evaluation.EvaluationConfig;
import doyenm.zooshell.handyman.HandymanCommandsConfig;
import doyenm.zooshell.keeper.KeeperCommandsConfig;
import doyenm.zooshell.paddock.PaddockCommandsConfig;
import doyenm.zooshell.specie.SpecieCommandsConfig;
import doyenm.zooshell.zoo.ZooCommandsConfig;
import doyenm.zooshell.zoo.penalty.LsPenalties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.List;

/**
 * @author doyenm
 */
@Configuration
@Import({
        CommonConfigs.class,
        AnimalCommandsConfig.class, HandymanCommandsConfig.class,
        KeeperCommandsConfig.class,
        PaddockCommandsConfig.class,
        EvaluationConfig.class,
        ZooCommandsConfig.class,
        BackupConfig.class,
        SpecieCommandsConfig.class,
        ActionPointCommandsConfig.class})
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
    public EvaluationConfig evaluationConfig;

    @Autowired
    SpecieCommandsConfig specieCommandsConfig;

    @Autowired
    ActionPointCommandsConfig actionPointCommandsConfig;

    @Autowired
    BackupConfig backupConfig;

    @Bean
    public LsPenalties lsPenalties() {
        return new LsPenalties();
    }

//    @Bean
//    public DetailSpecie detailSpecie() {
//        return new DetailSpecie(
//                validatorsConfig.specieDetailsValidator(),
//                specieControllersConfig.specieDetailsController()
//        );
//    }
//
//    @Bean
//    public LsSpecie lsSpecie() {
//        return new LsSpecie();
//    }

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
        return new Evaluate(evaluationConfig.evaluationController(),
                displayingEventsList);
    }

}
