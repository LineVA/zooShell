package doyenm.zooshell.main;

import doyenm.zooshell.ZooShellConfig;
import doyenm.zooshell.commandline.general.CommandManager;
import doyenm.zooshell.gui.MainGUI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 *
 * @author doyenm
 */
@Slf4j
@RequiredArgsConstructor
public class Start {
    
    private final MainGUI mainGUI;
    private final CommandManager manager;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ZooShellConfig.class);
        ctx.refresh();

        Start main = (Start) ctx.getBean("main");
        main.start();
    }
    
    private void start(){
        log.info("Start");
        mainGUI.construct(manager);
    }
}
