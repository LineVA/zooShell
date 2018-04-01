package doyenm.zooshell.keeper;

import doyenm.zooshell.evaluation.keeper.*;
import doyenm.zooshell.keeper.creation.KeeperCreationController;
import doyenm.zooshell.keeper.details.KeeperDetailsController;
import doyenm.zooshell.keeper.remove.KeeperDeletionController;
import doyenm.zooshell.keeper.rename.KeeperRenameController;
import doyenm.zooshell.keeper.tasks.KeeperResetOccupationsController;
import doyenm.zooshell.keeper.tasks.KeeperUpdateOccupationsController;
import doyenm.zooshell.keeper.training.KeeperAddTrainingController;
import doyenm.zooshell.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author doyenm
 */
@Configuration
@PropertySource("classpath:/doyenm/zooshell/zooshell.properties")
public class KeeperControllersConfig {

    @Autowired
    Environment environment;

    @Bean
    Utils utils() {
        return new Utils();
    }

    @Bean
    public KeeperRenameController keeperRenameController() {
        return new KeeperRenameController();
    }

    @Bean
    public KeeperCreationController keeperCreationController() {
        return new KeeperCreationController();
    }

    @Bean
    public KeeperDetailsController keeperDetailsController() {
        return new KeeperDetailsController(utils());
    }

    @Bean
    public KeeperDeletionController keeperDeletionController() {
        return new KeeperDeletionController();
    }

    @Bean
    public KeeperResetOccupationsController keeperResetOccupationsController() {
        return new KeeperResetOccupationsController();
    }

    @Bean
    public KeeperUpdateOccupationsController keeperUpdateOccupationsController() {
        return new KeeperUpdateOccupationsController();
    }

    @Bean
    KeeperEvaluationAgeingController keeperAgeingController() {
        return new KeeperEvaluationAgeingController();
    }

    @Bean
    KeeperEvaluationTaskController taskController() {
        return new KeeperEvaluationTaskController();
    }

    @Bean
    KeeperEvaluationFamilyController familyController() {
        return new KeeperEvaluationFamilyController();
    }

    @Bean
    KeeperEvaluationTrainingController trainingController() {
        return new KeeperEvaluationTrainingController();
    }

    @Bean
    public KeeperEvaluationController keeperEvaluationController() {
        return new KeeperEvaluationController(
                keeperAgeingController(),
                taskController(),
                familyController(),
                trainingController()
        );
    }

    @Bean
    public KeeperAddTrainingController keeperAddTrainingController() {
        return new KeeperAddTrainingController(
                environment.getProperty("keeper.training.duration", Integer.class),
                environment.getProperty("keeper.training.bonus", Double.class)
        );
    }

}
