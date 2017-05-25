package doyenm.zooshell.commandLine.general;

import doyenm.zooshell.commandLine.commandImpl.*;
import static java.util.Arrays.asList;
import doyenm.zooshell.launch.options.Option;
import doyenm.zooshell.launch.play.Play;
import lombok.Getter;
import lombok.Setter;

/**
 * The abstract command manager : determine how the command lines are checked
 *
 * @author doyenm
 */
public abstract class CommandManager {

    Play play;
    @Getter
    Iterable<AbstractCommand> playCommands;
    @Getter
    @Setter
    private String firstLine;
    @Getter
    private Option option;
    @Getter
    private CreateZoo createZoo;

    public CommandManager(Play play, Option option) {
        this.option = option;
        this.play = play;
        this.createZoo = new CreateZoo(play);
            // For Paddock, Animal, Specie and Animal Keeper : Ls must be before Detail
        // and Create & remove before Ls
        playCommands = asList(this.createZoo, new DetailZoo(play),
                new LsPaddock(play), new LsSpecie(play), new LsAnimal(play),
                new CreatePaddock(play),
                new DetailPad(play), new CreatePaddockEntry(play),
                new CreatePaddockExtension(play),
                new UpdateBiome(play), new UpdatePaddockType(play),
                new CreateAnimal(play), new DetailAnimal(play),
                new UpdateFoodQuantity(play), new UpdateDiet(play), new UpdateFastDays(play), new ResetDiet(play),
                new DetailSpecie(play)
        );
    }

    public abstract ReturnExec run(String cmd);

    private void CreateAnimal(Play play) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
