package doyenm.zooshell.launch.options;

import java.util.Locale;
import java.util.ResourceBundle;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class Option {

    /**
     * The current Locale
     */
    @Getter
    private Locale locale;

    /**
     * General messages about the command lines
     */
    @Getter
    private ResourceBundle generalCmdBundle;
    /**
     * Messages about the zoo
     */
    @Getter
    private ResourceBundle zooBundle;
    /**
     * Messages about a paddock
     */
    @Getter
    private ResourceBundle paddockBundle;
    /**
     * Messages about an animal
     */
    @Getter
    private ResourceBundle animalBundle;
    /**
     * Messages about a specie
     */
    @Getter
    private ResourceBundle specieBundle;
    /**
     * Messages about an animal keeper
     */
    @Getter
    private ResourceBundle keeperBundle;

    public Option() {
        locale = Locale.getDefault();
        this.updateBundles();
    }

    /**
     * Change the value of locale
     *
     * @param str the value of the new locale
     */
    public Option(String str) {
        if (str.equals("")) {
            str = "fr";
        }
        locale = new Locale(str);
        updateBundles();
    }

    /**
     * Update all the bundle with the locale
     */
    public void updateBundles() {
//        this.generalCmdBundle = ResourceBundle.getBundle("doyenm.zooshell.i18n.info", locale);
//        this.zooBundle = ResourceBundle.getBundle("doyenm.zooshell.i18n.zoo.info", locale);
//        this.paddockBundle = ResourceBundle.getBundle("doyenm.zooshell.i18n.paddock.paddock", locale);
//        this.animalBundle = ResourceBundle.getBundle("doyenm.zooshell.i18n.animal.animal", locale);
//        this.specieBundle = ResourceBundle.getBundle("doyenm.zooshell.i18n.specie.specie", locale);
//        this.keeperBundle = ResourceBundle.getBundle("doyenm.zooshell.i18n.animalKeeper.keeper", locale);
    }

    /**
     * Change the language
     *
     * @param lang the parameter to update the Locale
     */
    public void setLanguage(String lang) {
        switch (lang) {
            case "en":
            case "":
                locale = new Locale("");
                break;
            case "fr":
                locale = new Locale("fr");
                break;
        }
        updateBundles();
    }

}
