//package doyenm.zooshell.main;
//
//import doyenm.zooshell.OldZooShellConfiguration;
//import doyenm.zooshell.gui.MainGUI;
//import doyenm.zooshell.launch.play.OldFreePlayImpl;
//import doyenm.zooshell.launch.play.OldPlay;
//import doyenm.zooshell.launch.options.Option;
//import doyenm.zooshell.model.Zoo;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
///**
// * The Main class
// *
// * @author doyenm
// */
//@RequiredArgsConstructor
//@Slf4j
//public class OldMain {
//
////    private final Play play;
//    private final MainGUI mainGUI;
//    
//    /**
//     * The main method :
//     *
//     * @param args the args of the cmd line used to launch the application
//     */
//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        ctx.register(OldZooShellConfiguration.class);
//        ctx.refresh();
//
//        OldMain main = (OldMain) ctx.getBean("main");
//        main.start();
////        Option options = new Option();
////        Play play = new FreePlayImpl(options);
////        Play play = new FreePlayImpl();
////        MainGUI mainGUI = new MainGUI(play);
//    }
//
//    public void start() {
//        log.info("Start");
////        Play play = new FreePlayImpl();
////        MainGUI mainGUI = new MainGUI(play);
//    }
//}
