package doyenm.zooshell;

import doyenm.zooshell.commandLine.commandImpl.*;
import doyenm.zooshell.commandLine.general.ActionPointCommand;
import doyenm.zooshell.commandLine.general.ActionPointsHandler;
import doyenm.zooshell.commandLine.general.CommandManager;
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
@Import({
    CommandsConfig.class})
public class ZooShellConfig {

    @Autowired
    CommandsConfig commandsConfig;

    @Bean
    MainGUI mainGUI() {
        return new MainGUI();
    }

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
                new ActionPointCommand(commandsConfig.zooCommandsConfig.renameZoo(), 1),
                new ActionPointCommand(commandsConfig.zooCommandsConfig.detailZoo(), 0),
                // Specie
                new ActionPointCommand(commandsConfig.lsSpecie(), 0),
                new ActionPointCommand(commandsConfig.detailSpecie(), 0),
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
                new ActionPointCommand(commandsConfig.paddockCommandsConfig.updatePaddockArrangements(), 1),
                new ActionPointCommand(commandsConfig.paddockCommandsConfig.LsPaddockType(), 0),
                new ActionPointCommand(commandsConfig.paddockCommandsConfig.lsPaddockArrangements(), 0),
                new ActionPointCommand(commandsConfig.paddockCommandsConfig.lsBiome(), 0),
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
                new ActionPointCommand(commandsConfig.keeperCommandsConfig.addTraining(), 0),
                // Handyman
                new ActionPointCommand(commandsConfig.handymanCommandsConfig.createHandyman(), 3),
                new ActionPointCommand(commandsConfig.handymanCommandsConfig.detailsHandyman(), 0),
                new ActionPointCommand(commandsConfig.handymanCommandsConfig.renameHandyman(), 1),
                new ActionPointCommand(commandsConfig.handymanCommandsConfig.lsHandyman(), 0),
                new ActionPointCommand(commandsConfig.handymanCommandsConfig.updateHandymanOccupations(), 1),
                new ActionPointCommand(commandsConfig.handymanCommandsConfig.removeHandyman(), 1),
                // Ls
                new ActionPointCommand(commandsConfig.animalCommandsConfig.lsContraceptionMethod(), 0),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.lsDiet(), 0),
                new ActionPointCommand(commandsConfig.keeperCommandsConfig.lsKeeperTask(), 0),
                new ActionPointCommand(commandsConfig.animalCommandsConfig.lsSex(), 0),
                new ActionPointCommand(commandsConfig.lsPenalties(), 0),
                // General
                new ActionPointCommand(commandsConfig.load(), 0),
                new ActionPointCommand(commandsConfig.save(), 0));
        return new CommandManager(commands, actionPointsHandler(), getActionPoints(), commandsConfig.evaluate());
    }

    @Bean
    Main main() {
        return new Main(mainGUI(), commandManager());
    }
}
