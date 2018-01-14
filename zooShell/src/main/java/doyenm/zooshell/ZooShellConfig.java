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
    CommandsConfig.class,
    ZooShellHandymanConfig.class})
public class ZooShellConfig {

    @Autowired
    CommandsConfig commandsConfig;
    
    @Autowired
    ZooShellHandymanConfig zooShellHandymanConfig;

    @Bean
    MainGUI mainGUI() {
        return new MainGUI();
    }

    @Autowired
    RenamePaddock changePaddockName;

    @Autowired
    CreatePaddock createPaddock;

    @Autowired
    CreatePaddockEntry createPaddockEntry;

    @Autowired
    CreatePaddockExtension createPaddockExtension;

    @Autowired
    CreateZoo createZoo;

    @Autowired
    DetailPad detailPad;

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
    LsBiome lsBiome;

    @Autowired
    LsKeeperTask lsKeeperTask;

    @Autowired
    LsPaddock lsPaddock;

    @Autowired
    LsPaddockType lsPaddockType;

    @Autowired
    LsSpecie lsSpecie;

    @Autowired
    RemovePaddock removePaddock;

    @Autowired
    UpdateBiome updateBiome;

    @Autowired
    UpdatePaddockType updatePaddocktype;

    @Autowired
    LsAnimalsWithCriteria lsAnimalsWithCriteria;

    @Autowired
    ChangeKeeperName changeKeeperName;

    @Autowired
    CreateKeeper createKeeper;

//    @Autowired
//    AddTraining addTraining;
    @Autowired
    DetailKeeper detailKeeper;

    @Autowired
    LsKeeper lsKeeper;

    @Autowired
    RemoveKeeper removeKeeper;

    @Autowired
    ResetOccupations resetOccupations;

    @Autowired
    UpdateOccupations updateOccupations;

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
                new ActionPointCommand(createZoo, 0),
                new ActionPointCommand(ZooShellZooConfig.renameZoo(), 0),
                new ActionPointCommand(detailZoo, 0),
                // Specie
                new ActionPointCommand(lsSpecie, 0),
                new ActionPointCommand(detailSpecie, 0),
                // Paddock
                new ActionPointCommand(changePaddockName, 1),
                new ActionPointCommand(createPaddock, 3),
                new ActionPointCommand(createPaddockEntry, 1),
                new ActionPointCommand(createPaddockExtension, 3),
                new ActionPointCommand(lsPaddock, 0),
                new ActionPointCommand(detailPad, 0),
                new ActionPointCommand(removePaddock, 1),
                new ActionPointCommand(updateBiome, 1),
                new ActionPointCommand(updatePaddocktype, 1),
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
                new ActionPointCommand(changeKeeperName, 1),
                new ActionPointCommand(createKeeper, 3),
                new ActionPointCommand(lsKeeper, 0),
                new ActionPointCommand(detailKeeper, 0),
                new ActionPointCommand(removeKeeper, 1),
                new ActionPointCommand(resetOccupations, 0),
                new ActionPointCommand(updateOccupations, 1),
                //                new ActionPointCommand(addTraining, 1),
                // Handyman
                new ActionPointCommand(commandsConfig.handymanCommandsConfig.createHandyman(), 3),
                new ActionPointCommand(commandsConfig.handymanCommandsConfig.detailsHandyman(), 0),
                new ActionPointCommand(commandsConfig.handymanCommandsConfig.renameHandyman(), 1),
                new ActionPointCommand(commandsConfig.handymanCommandsConfig.lsHandyman(), 0),
                new ActionPointCommand(commandsConfig.handymanCommandsConfig.updateHandymanOccupations(), 1),
                new ActionPointCommand(commandsConfig.handymanCommandsConfig.removeHandyman(), 1),
                // Ls
                new ActionPointCommand(lsBiome, 0),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.lsContraceptionMethod(), 0),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.lsDiet(), 0),
                new ActionPointCommand(lsKeeperTask, 0),
                new ActionPointCommand(lsPaddockType, 0),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.lsSex(), 0),
                new ActionPointCommand(commandsConfig.lsPenalties(), 0),
                // General
                new ActionPointCommand(load, 0),
                new ActionPointCommand(save, 0));
        return new CommandManager(commands, actionPointsHandler(), getActionPoints(), evaluate);
    }

    @Bean
    Main main() {
        return new Main(mainGUI(), commandManager());
    }
}
