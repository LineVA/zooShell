package doyenm.zooshell.launch.play;

import doyenm.zooshell.launch.options.Option;
import doyenm.zooshell.commandLine.general.CommandManager;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
abstract public class Play {

    @Getter
    @Setter
    private Option option;

//    @Setter
//    private IZoo zoo;
//
//    public IZoo getZoo() {
//        System.out.println(zooModel.getName());
//        return zoo;
//    }
    
    @Getter
    @Setter
    private doyenm.zooshell.model.Zoo zooModel;
    
    @Getter
    @Setter
    private CommandManager manager;

//    public Play(Option opt) {
//        this.zoo = new Zoo();
//        this.zoo.setOption(opt);
//        Diet.NONE.setOption(opt);
//        Sex.UNKNOWN.setOption(opt);
//        Ecoregion.UNKNOWN.setOption(opt);
//        ConservationStatus.UNKNOWN.setOption(opt);
//        Family.UNKNOWN.setOption(opt);
//        Biome.NONE.setOption(opt);
//        Size.UNKNOWN.setOption(opt);
//        Continent.UNKNOWN.setOption(opt);
//        PaddockTypes.UNKNOWN.setOption(opt);
//        Task.UNKNOWN.setOption(opt);
//        BreedingProgramme.NONE.setOption(opt);
//        ContraceptionMethods.NONE.setOption(opt);
//        this.option = opt;
//    }
//
//    public void updateOption() {
//        Diet.NONE.setOption(this.option);
//        Sex.UNKNOWN.setOption(this.option);
//        Ecoregion.UNKNOWN.setOption(this.option);
//        ConservationStatus.UNKNOWN.setOption(this.option);
//        Family.UNKNOWN.setOption(this.option);
//        Biome.NONE.setOption(this.option);
//        Size.UNKNOWN.setOption(this.option);
//        Continent.UNKNOWN.setOption(this.option);
//        PaddockTypes.UNKNOWN.setOption(this.option);
//        Task.UNKNOWN.setOption(this.option);
//        ContraceptionMethods.NONE.setOption(this.option);
//    }
}
