package doyenm.zooshell.commandLine.commandImpl.keeper;

import doyenm.zooshell.ZooShellKeeperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author doyenm
 */
@Configuration
@Import({ZooShellKeeperConfig.class})
public class KeeperCommandsConfig {
    
    @Autowired
    ZooShellKeeperConfig zooShellKeeperConfig;

    @Bean
    public ChangeKeeperName changeKeeperName() {
        return new ChangeKeeperName(
                zooShellKeeperConfig.keeperRenameValidator(), 
                 zooShellKeeperConfig.keeperRenameController());
    }

    @Bean
    public CreateKeeper createKeeper() {
        return new CreateKeeper(
                 zooShellKeeperConfig.keeperCreationValidator(),
                 zooShellKeeperConfig.keeperCreationController());
    }

    @Bean
    public DetailKeeper detailKeeper() {
        return new DetailKeeper(
                 zooShellKeeperConfig.keeperValidator(),
                 zooShellKeeperConfig.keeperDetailsController());
    }

    @Bean
    public LsKeeper lsKeeper() {
        return new LsKeeper();
    }

    @Bean
    public RemoveKeeper removeKeeper() {
        return new RemoveKeeper(
                 zooShellKeeperConfig.keeperValidator(), 
                 zooShellKeeperConfig.keeperDeletionController());
    }

    @Bean
    public ResetOccupations resetOccupations() {
        return new ResetOccupations(
                 zooShellKeeperConfig.keeperValidator(), 
                 zooShellKeeperConfig.keeperResetOccupationsController());
    }

    @Bean
    public UpdateOccupations updateOccupations() {
        return new UpdateOccupations(
                 zooShellKeeperConfig.keeperUpdateOccupationsValidator(), 
                 zooShellKeeperConfig.keeperUpdateOccupationsController());
    }
}
