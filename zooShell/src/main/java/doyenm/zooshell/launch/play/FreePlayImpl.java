package doyenm.zooshell.launch.play;

import doyenm.zooshell.launch.options.Option;
import doyenm.zooshell.commandLine.commandManagerImpl.FreeCommandManager;

/**
 *
 * @author doyenm
 */
public class FreePlayImpl extends Play {

    public FreePlayImpl(Option opt) {
//        super(opt);
        super.setManager(new FreeCommandManager(this, opt));
    }
}
