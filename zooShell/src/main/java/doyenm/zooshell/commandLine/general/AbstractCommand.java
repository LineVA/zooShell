package doyenm.zooshell.commandLine.general;

import doyenm.zooshell.launch.play.Play;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
public abstract class AbstractCommand implements Command {

    @Getter
    @Setter
    private boolean isSaving = false;
    
    @Getter
    @Setter
    private boolean isChangingZoo = false;

    @Getter
    private Play play;
    @Getter
    @Setter
    private boolean success = false;
    @Getter
    @Setter
    private boolean initiate = false;

    public AbstractCommand(Play play){
        this.play = play;
    }

}
