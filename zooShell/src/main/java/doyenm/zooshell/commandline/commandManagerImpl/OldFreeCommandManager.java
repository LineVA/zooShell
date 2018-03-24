//package doyenm.zooshell.commandLine.commandManagerImpl;
//
//import doyenm.zooshell.commandline.commandimpl.zoo.CreateZoo;
//import doyenm.zooshell.commandline.general.AbstractCommand;
//import doyenm.zooshell.commandline.general.Command;
//import doyenm.zooshell.commandline.general.OldCommandManager;
//import doyenm.zooshell.commandline.general.ReturnExec;
//import doyenm.zooshell.commandline.general.SplitDoubleQuotes;
//import doyenm.zooshell.commandline.general.TypeReturn;
//import static java.util.Arrays.asList;
//import doyenm.zooshell.launch.options.Option;
//import doyenm.zooshell.launch.play.OldPlay;
//import doyenm.zooshell.model.Zoo;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// *
// * @author doyenm
// */
//public class OldFreeCommandManager implements OldCommandManager {
//
//    private final String LOAD = "load";
//    private final String CREATE = "create";
//
//    private final Iterable<Command> initialCommands;
//
//    private String currentChangingZooCommand;
//
//    public boolean isChanging = false;
//
//    public boolean isInitiate = false;
//
//    public boolean isSaving = false;
//
////    public FreeCommandManager(Play play) {
//////        super(play, option);
////        super(play);
////        super.setFirstLine("WELCOME");
//////        super.setFirstLine(super.getOption().getGeneralCmdBundle().getString("WELCOME"));
////        initialCommands = asList(super.getCreateZoo());
////    }
//    public OldFreeCommandManager() {
////        super(play, option);
////        super(play);
//        this.setFirstLine("WELCOME");
////        super.setFirstLine(super.getOption().getGeneralCmdBundle().getString("WELCOME"));
//        initialCommands = asList(this.getCreateZoo());
//    }
//
////    private ReturnExec isNotCurrentlySaving(String[] parse) {
////        for (AbstractCommand command : super.getPlayCommands()) {
////            if (command.canExecute(parse)) {
////                command.setInitiate(isInitiate);
////                ReturnExec result = command.execute(parse);
////                this.isInitiate |= command.isInitiate();
////                return result;
////            }
////        }
////        return new ReturnExec(
////                "UNKNOWN_CMD", TypeReturn.ERROR);
////    }
////    private ReturnExec hasBeenInitiate(String[] parse) {
////        return isNotCurrentlySaving(parse);
////    }
//    private ReturnExec hasNotBeenYetInitiate(String[] parse) {
//        for (Command command : initialCommands) {
//            if (command.canExecute(parse)) {
//                ReturnExec result = command.execute(parse);
//                this.isInitiate = command.isInitiate();
//                return result;
//            }
//        }
//        return new ReturnExec(
//               "BEGINNING_CMD", TypeReturn.ERROR);
//    }
//
//    @Override
//    public ReturnExec run(String cmd) {
//        String[] parse = SplitDoubleQuotes.split(cmd);
////        if (isInitiate) {
////            return hasBeenInitiate(parse);
////        }
//        return hasNotBeenYetInitiate(parse);
//    }
//
//    public void save() {
//
//    }
//
//    @Getter
//    Iterable<Command> playCommands;
//    @Getter
//    @Setter
//    private String firstLine;
//    @Getter
//    private Option option;
//    @Getter
//    @Autowired
//    private CreateZoo createZoo;
//
//    private Zoo zoo;
//
//}
