package doyenm.zooshell.commandLine.commandImpl.paddock;

import doyenm.zooshell.ZooShellPaddockConfig;
import doyenm.zooshell.commandLine.commandImpl.ls.LsBiome;
import doyenm.zooshell.commandLine.commandImpl.ls.LsPaddockType;
import doyenm.zooshell.controller.paddockcontroller.PaddockControllerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author doyenm
 */
@Configuration
@Import({PaddockControllerConfig.class, ZooShellPaddockConfig.class})
public class PaddockCommandsConfig {

    @Autowired
    PaddockControllerConfig paddockControllerConfig;

    @Autowired
    ZooShellPaddockConfig zooShellPaddockConfig;

    @Bean
    public RenamePaddock changePaddockName() {
        return new RenamePaddock(
                zooShellPaddockConfig.paddockChangeNameValidator(),
                paddockControllerConfig.paddockChangeNameController());
    }

    @Bean
    public CreatePaddock createPaddock() {
        return new CreatePaddock(
                zooShellPaddockConfig.paddockCreationValidator(),
                zooShellPaddockConfig.paddockLocationValidator(),
                paddockControllerConfig.paddockCreationController());
    }

    @Bean
    public CreatePaddockEntry createPaddockEntry() {
        return new CreatePaddockEntry(
                zooShellPaddockConfig.paddockEntryCreationValidator(),
                paddockControllerConfig.paddockEntryCreationController());
    }

    @Bean
    public CreatePaddockExtension createPaddockExtension() {
        return new CreatePaddockExtension(
                zooShellPaddockConfig.paddockExtensionCreationValidator(),
                zooShellPaddockConfig.paddockExtensionLocationValidator(),
                paddockControllerConfig.paddockExtensionCreationController());
    }

    @Bean
    public DetailPad detailPad() {
        return new DetailPad(
                zooShellPaddockConfig.paddockValidator(),
                paddockControllerConfig.paddockDetailsController());
    }

    @Bean
    public LsBiome lsBiome() {
        return new LsBiome();
    }

    @Bean
    public LsPaddock lsPaddock() {
        return new LsPaddock();
    }

    @Bean
    public LsPaddockType lsPaddockType() {
        return new LsPaddockType();
    }

    @Bean
    public RemovePaddock removePaddock() {
        return new RemovePaddock(
                zooShellPaddockConfig.paddockRemoveValidator(),
                paddockControllerConfig.paddockRemoveController());
    }

    @Bean
    public UpdateBiome updateBiome() {
        return new UpdateBiome(
                zooShellPaddockConfig.updateBiomeValidator(),
                paddockControllerConfig.updateBiomeController());
    }

    @Bean
    public UpdatePaddockType updatePaddockType() {
        return new UpdatePaddockType(
                zooShellPaddockConfig.updatePaddockTypeValidator(),
                paddockControllerConfig.updatePaddockTypeController());
    }

}
