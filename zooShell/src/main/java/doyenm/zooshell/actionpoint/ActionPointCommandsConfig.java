package doyenm.zooshell.actionpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActionPointCommandsConfig {

    @Bean
    public GetActionPoints getActionPoints() {
        return new GetActionPoints();
    }

    @Bean
    public ActionPointsHandler actionPointsHandler() {
        ActionPointsHandler actionPointsHandler = new ActionPointsHandler();
        actionPointsHandler.initialize(25);
        return actionPointsHandler;
    }

}
