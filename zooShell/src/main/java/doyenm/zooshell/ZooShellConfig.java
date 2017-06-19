package doyenm.zooshell;

import doyenm.zooshell.commandLine.commandImpl.*;
import doyenm.zooshell.commandLine.commandImpl.zoo.*;
import doyenm.zooshell.commandLine.commandImpl.ls.*;
import doyenm.zooshell.commandLine.commandImpl.paddock.*;
import doyenm.zooshell.commandLine.commandImpl.animal.*;
import doyenm.zooshell.commandLine.commandImpl.keeper.*;
import doyenm.zooshell.commandLine.general.CommandBis;
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
    RemovePaddock RemovePaddock;

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
    CommandManager commandManager() {
        List<CommandBis> commands = Arrays.asList(createZoo, detailZoo,
                lsSpecie, detailSpecie,
                changePaddockName, createPaddock, createPaddockEntry, createPaddockExtension,
                lsPaddock, detailPad, RemovePaddock, updateBiome, updatePaddocktype,
                changeAnimalName, changePaddock, createAnimal, lsAnimal, detailAnimal,
                removeAnimal, resetDiet, updateDiet, updateFastDays, updateFoodQuantity,
                updateCotnraceptionMethod,
                changeKeeperName, createKeeper, lsKeeper, detailKeeper, removeKeeper,
                resetOccupations, updateOccupations,
                lsBiome, lsContraceptionMethod, lsDiet, lsKeeperTask, lsPaddockType, lsSex);
        return new CommandManager(commands);
    }

    @Bean
    Main main() {
        return new Main(mainGUI(), commandManager());
    }
}
