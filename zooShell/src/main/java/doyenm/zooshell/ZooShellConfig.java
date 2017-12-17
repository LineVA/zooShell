package doyenm.zooshell;

import doyenm.zooshell.commandLine.commandImpl.*;
import doyenm.zooshell.commandLine.commandImpl.zoo.*;
import doyenm.zooshell.commandLine.commandImpl.ls.*;
import doyenm.zooshell.commandLine.commandImpl.paddock.*;
import doyenm.zooshell.commandLine.commandImpl.animal.*;
import doyenm.zooshell.commandLine.commandImpl.keeper.*;
import doyenm.zooshell.commandLine.general.ActionPointCommand;
import doyenm.zooshell.commandLine.general.ActionPointsHandler;
import doyenm.zooshell.commandLine.general.CommandManager;
import doyenm.zooshell.controller.eventhandling.zoo.ZooEventsConfig;
import doyenm.zooshell.gui.MainGUI;
import doyenm.zooshell.main.Main;
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
@Import({ZooShellValidatorConfig.class, ZooShellControllerConfig.class, ZooShellCommandConfig.class,
    ZooShellPaddockConfig.class, ZooShellAnimalConfig.class, ZooShellKeeperConfig.class,
    ZooShellZooConfig.class, ZooEventsConfig.class,
    CommandsConfig.class})
public class ZooShellConfig {
    
    @Autowired
    CommandsConfig commandsConfig;

    @Bean
    MainGUI mainGUI() {
        return new MainGUI();
    }

    @Autowired
    CreateZoo createZoo;

    @Autowired
    DetailSpecie detailSpecie;

    @Autowired
    DetailZoo detailZoo;

    @Autowired
    Evaluate evaluate;

    @Autowired
    Load load;

    @Autowired
    Save save;

    @Autowired
    LsKeeperTask lsKeeperTask;


    @Autowired
    LsSpecie lsSpecie;

    @Autowired
    LsAnimalsWithCriteria lsAnimalsWithCriteria;

    @Bean
    ActionPointsHandler actionPointsHandler() {
        ActionPointsHandler actionPointsHandler = new ActionPointsHandler();
        actionPointsHandler.initialize(25);
        return actionPointsHandler;
    }

    @Bean
    GetActionPoints getActionPoints() {
        return new GetActionPoints();
    }

    @Bean
    CommandManager commandManager() {
        List<ActionPointCommand> commands = Arrays.asList(
                // Zoo
                new ActionPointCommand(commandsConfig.zooCommandsConfig.createZoo(), 0),
                new ActionPointCommand(commandsConfig.zooCommandsConfig.renameZoo(), 0),
                new ActionPointCommand(commandsConfig.zooCommandsConfig.detailZoo(), 0),
                // Specie
                new ActionPointCommand(lsSpecie, 0),
                new ActionPointCommand(detailSpecie, 0),
                // Paddock
                new ActionPointCommand(commandsConfig.paddockCommandsConfig.changePaddockName(), 1),
                new ActionPointCommand(commandsConfig.paddockCommandsConfig.createPaddock(), 3),
                new ActionPointCommand(commandsConfig.paddockCommandsConfig.createPaddockEntry(), 1),
                new ActionPointCommand(commandsConfig.paddockCommandsConfig.createPaddockExtension(), 3),
                new ActionPointCommand(commandsConfig.paddockCommandsConfig.lsPaddock(), 0),
                new ActionPointCommand(commandsConfig.paddockCommandsConfig.detailPad(), 0),
                new ActionPointCommand(commandsConfig.paddockCommandsConfig.removePaddock(), 1),
                new ActionPointCommand(commandsConfig.paddockCommandsConfig.updateBiome(), 1),
                new ActionPointCommand(commandsConfig.paddockCommandsConfig.updatePaddockType(), 1),
                // Animal
                new ActionPointCommand(commandsConfig.animalCommandsConfig.changeAnimalName(), 1),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.changePaddock(), 1),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.createAnimal(), 3),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.lsAnimal(), 0),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.lsAnimalsWithCriteria(), 0),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.detailAnimal(), 0),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.removeAnimal(), 1),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.resetDiet(), 0),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.updateDiet(), 1),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.updateFastDays(), 1),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.updateFoodQuantity(), 1),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.updateContraceptionMethod(), 1),
                // Keeper
                new ActionPointCommand(commandsConfig.keeperCommandsConfig.changeKeeperName(), 1),
                new ActionPointCommand(commandsConfig.keeperCommandsConfig.createKeeper(), 3),
                new ActionPointCommand(commandsConfig.keeperCommandsConfig.lsKeeper(), 0),
                new ActionPointCommand(commandsConfig.keeperCommandsConfig.detailKeeper(), 0),
                new ActionPointCommand(commandsConfig.keeperCommandsConfig.removeKeeper(), 1),
                new ActionPointCommand(commandsConfig.keeperCommandsConfig.resetOccupations(), 0),
                new ActionPointCommand(commandsConfig.keeperCommandsConfig.updateOccupations(), 1),
                //                new ActionPointCommand(addTraining, 1),
                // Ls
                new ActionPointCommand(commandsConfig.paddockCommandsConfig.lsBiome(), 0),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.lsContraceptionMethod(), 0),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.lsDiet(), 0),
                new ActionPointCommand(lsKeeperTask, 0),
                new ActionPointCommand(commandsConfig.paddockCommandsConfig.lsPaddockType(), 0),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.lsSex(), 0),
                // General
                new ActionPointCommand(load, 0),
                new ActionPointCommand(save, 0));
        return new CommandManager(commands, actionPointsHandler(), getActionPoints(), commandsConfig.zooCommandsConfig.evaluate());
    }

    @Bean
    Main main() {
        return new Main(mainGUI(), commandManager());
    }
}
