package doyenm.zooshell.backup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author doyenm
 */
@Configuration
public class BackupConfig {

    @Bean
    public SaveFunction saveFunction() {
        return new SaveFunction();
    }

    @Bean
    public LoadFunction loadFunction() {
        return new LoadFunction();
    }
}
