//package doyenm.zooshell.commandLine.commandManagerImpl;
//
//import doyenm.zooshell.commandLine.general.Command;
//import doyenm.zooshell.commandLine.general.CommandManager;
//import doyenm.zooshell.commandLine.general.ReturnExec;
//import doyenm.zooshell.commandLine.general.SplitDoubleQuotes;
//import doyenm.zooshell.commandLine.general.TypeReturn;
//import java.util.List;
//import doyenm.zooshell.launch.play.Play;
////import doyenm.zooshell.launch.play.Step;
//
///**
// *
// * @author doyenm
// */
//public class TutorialCommandLineManager extends CommandManager {
//
//    List<Step> steps;
//    int i = 0;
//
//    public TutorialCommandLineManager(Play play, List<Step> steps) {
//        super(play, null);
//        this.steps = steps;
//        super.setFirstLine(steps.get(0).getPrevious());
//    }
//
//    @Override
//    public ReturnExec run(String cmd) {
//        if (!steps.isEmpty() && i < steps.size()) {
//            String[] parse = SplitDoubleQuotes.split(cmd);
//            for (Command command : super.getPlayCommands()) {
//                if (command.canExecute(parse)) {
//                    ReturnExec result = command.execute(parse);
//                    // If the player uses the correct command line
//                    // && no execution has been thrown 
//                    if (steps.get(i).check() && command.isSuccess()) {
//                        if (i < steps.size() - 1) {
//                            i += 1;
//                            return new ReturnExec(result + "\n" + steps.get(i).getPrevious(), TypeReturn.SUCCESS);
//                        } else {
//                            return new ReturnExec(result + steps.get(i).getSuccess(), TypeReturn.SUCCESS);
//                        }
//                        // If an exception has been thrown (expected or unexpected command line)
//                    } else {
//                        return new ReturnExec(result + steps.get(i).getFail(), TypeReturn.ERROR);
//                    }
//                }
//            }
//        }
//        return new ReturnExec(steps.get(i).getFail(), TypeReturn.ERROR);
//    }
//
//}
