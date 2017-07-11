package doyenm.zooshell;

import doyenm.zooshell.commandLine.commandImpl.*;
import doyenm.zooshell.commandLine.commandImpl.zoo.*;
import doyenm.zooshell.commandLine.commandImpl.ls.*;
import doyenm.zooshell.commandLine.commandImpl.paddock.*;
import doyenm.zooshell.commandLine.commandImpl.animal.*;
import doyenm.zooshell.commandLine.commandImpl.keeper.*;
import doyenm.zooshell.commandLine.general.ActionPointCommand;
import doyenm.zooshell.commandLine.general.ActionPointsHandler;
import doyenm.zooshell.commandLine.general.Command;
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
@Import({ZooShellValidatorConfig.class, ZooShellControllerConfig.class, ZooShellCommandConfig.class,
    ZooShellPaddockConfig.class, ZooShellAnimalConfig.class, ZooShellKeeperConfig.class})
public class ZooShellConfig {

    @Bean
    MainGUI mainGUI() {
        return new MainGUI();
    }

    @Autowired
    ChangePaddockName changePaddockName;

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
    LsBiome lsBiome;

    @Autowired
    LsContraceptionMethod lsContraceptionMethod;

    @Autowired
    LsDiet lsDiet;

    @Autowired
    LsKeeperTask lsKeeperTask;

    @Autowired
    LsPaddock lsPaddock;

    @Autowired
    LsPaddockType lsPaddockType;

    @Autowired
    LsSex lsSex;

    @Autowired
    LsSpecie lsSpecie;

    @Autowired
    RemovePaddock removePaddock;

    @Autowired
    UpdateBiome updateBiome;

    @Autowired
    UpdatePaddockType updatePaddocktype;

    @Autowired
    ChangeAnimalName changeAnimalName;

    @Autowired
    ChangePaddock changePaddock;

    @Autowired
    CreateAnimal createAnimal;

    @Autowired
    DetailAnimal detailAnimal;

    @Autowired
    LsAnimal lsAnimal;

    @Autowired
    RemoveAnimal removeAnimal;

    @Autowired
    ResetDiet resetDiet;

    @Autowired
    UpdateContraceptionMethod updateCotnraceptionMethod;

    @Autowired
    UpdateDiet updateDiet;

    @Autowired
    UpdateFastDays updateFastDays;

    @Autowired
    UpdateFoodQuantity updateFoodQuantity;

    @Autowired
    ChangeKeeperName changeKeeperName;

    @Autowired
    CreateKeeper createKeeper;

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
    ActionPointsHandler actionPointsHandler(){
        ActionPointsHandler actionPointsHandler = new ActionPointsHandler();
        actionPointsHandler.initialize(7);
        return actionPointsHandler;
    }
    
    @Bean
    GetActionPoints getActionPoints(){
        return new GetActionPoints();
    } 
    
    @Bean
    Evaluate evaluate(){
        return new Evaluate();
    }

    @Bean
    CommandManager commandManager() {
        List<ActionPointCommand> commands = Arrays.asList(
                new ActionPointCommand(createZoo, 0),
                new ActionPointCommand(detailZoo, 0),
                new ActionPointCommand(lsSpecie, 0),
                new ActionPointCommand(detailSpecie, 0),
                new ActionPointCommand(changePaddockName, 1),
                new ActionPointCommand(createPaddock, 3),
                new ActionPointCommand(createPaddockEntry, 1),
                new ActionPointCommand(createPaddockExtension, 3),
                new ActionPointCommand(lsPaddock, 0),
                new ActionPointCommand(detailPad, 0),
                new ActionPointCommand(removePaddock, 1),
                new ActionPointCommand(updateBiome, 1),
                new ActionPointCommand(updatePaddocktype, 1),
                new ActionPointCommand(changeAnimalName, 1),
                new ActionPointCommand(changePaddock, 1),
                new ActionPointCommand(createAnimal, 3),
                new ActionPointCommand(lsAnimal, 0),
                new ActionPointCommand(detailAnimal, 0),
                new ActionPointCommand(removeAnimal, 1),
                new ActionPointCommand(resetDiet, 0),
                new ActionPointCommand(updateDiet, 1),
                new ActionPointCommand(updateFastDays, 1),
                new ActionPointCommand(updateFoodQuantity, 1),
                new ActionPointCommand(updateCotnraceptionMethod, 1),
                new ActionPointCommand(changeKeeperName, 1),
                new ActionPointCommand(createKeeper, 3),
                new ActionPointCommand(lsKeeper, 0),
                new ActionPointCommand(detailKeeper, 0),
                new ActionPointCommand(removeKeeper, 1),
                new ActionPointCommand(resetOccupations, 0),
                new ActionPointCommand(updateOccupations, 1),
                new ActionPointCommand(lsBiome, 0),
                new ActionPointCommand(lsContraceptionMethod, 0),
                new ActionPointCommand(lsDiet, 0),
                new ActionPointCommand(lsKeeperTask, 0),
                new ActionPointCommand(lsPaddockType, 0),
                new ActionPointCommand(lsSex, 0));
        return new CommandManager(commands, actionPointsHandler(), getActionPoints(), evaluate());
    }

    @Bean
    Main main() {
        return new Main(mainGUI(), commandManager());
    }
}
