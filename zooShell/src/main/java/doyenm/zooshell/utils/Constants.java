package doyenm.zooshell.utils;

/**
 *
 * @author doyenm
 */
public class Constants {

    // Value for undefined in an enum
    public static final int UNDEFIND_ENUM = -1;
    // Max number of turns befrore drowning
    public static final int MAX_DROWNING = 3;
    // Max number of turns befrore starving
    public static final int MAX_STARVING = 3;

    // action_points
    public static final String ACTION_POINTS = "action_points";
    // actualFeedingAttributes
    public static final String ACTUALFEEDING_ATT = "actualFeedingAttributes";
    // actualLifeSpanAttributes
    public static final String ACTUALLIFESPAN_ATT = "actualLifeSpanAttributes";
    // actualReproductionAttributes
    public static final String ACTUALREPRODUCTION_ATT = "actualReproductionAttributes";
    // add
    public static final String ADD = "add";
// additionalEnglishNames
    public static final String ADDITIONALENGLISHNAMES = "additionalEnglishNames";
    // additionalFrenchNames
    public static final String ADDITIONALFRENCHNAMES = "additionalFrenchNames";
    // age
    public static final String AGE = "age";
    // animal
    public static final String ANIMAL = "animal";
    // animals
    public static final String ANIMALS = "animals";
    // animalDiversity
    public static final String ANIMALDIVERSITY = "animalDiversity";
    // animal-contraceptionMethod
    public static final String ANIMAL_CONTRACEPTION = "animal-contraceptionMethod";
    // animal-diet
    public static final String ANIMAL_DIET = "animal-diet";
    // animal-fastDays || animal-fd
    public static final String[] ANIMAL_FAST_DAYS = {"animal-fastDays", "animal-fd"};
    // animal-foodQuantity || animal-fq
    public static final String[] ANIMAL_FOOD_QUANTITY = {"animal-foodQuantity", "animal-fq"};
    // animal-paddock || animal-pad
    public static final String[] ANIMAL_PADDOCK = {"animal-paddock", "animal-pad"};
    // animalKeeper
    public static final String ANIMALKEEPER = "animalKeeper";
    // animalKeepers
    public static final String ANIMALKEEPERS = "animalKeepers";
    // animalKeeper || ak
    public static final String[] AK_OR_ANIMALKEEPER = {"animalKeeper", "ak"};
    // animalKeepers || keepers
    public static final String[] KEEPERS_OR_ANIMALKEEPERS = {"animalKeepers", "keepers"};
    // animalKeeper-occ || ak-occ
    public static final String[] AK_OR_ANIMALKEEPER_OCCUPATIONS = {"animalKeeper-occupations",
        "ak-occupations", "animalKeeper-occ", "ak-occ"};
    // biome
    public static final String BIOME = "biome";
    // biomes
    public static final String BIOMES = "biomes";
    public static final String[] BIOME_ARG = {"--biome", "-b"};
    // bravery
    public static final String BRAVERY = "bravery";
    // breedingProgramme
    public static final String BREEDING = "breedingProgramme";
    // --breedingProgramme || -bP as argument
    public static final String[] BREEDING_ARG = {"--breedingProgramme", "-bP"};
    // cmd || command
    public static final String[] CMD_OR_COMMAND = {"command", "cmd"};
    // conservation
    public static final String CONSERVATION = "conservation";
    // --conservation || -cs as argument
    public static final String[] CONSERVATION_ARG = {"--conservation", "-cs"};
    // continents
    public static final String CONTINENTS = "continents";
    // continent
    public static final String CONTINENT = "continent";
    // --continent || -ct as argument
    public static final String[] CONTINENT_ARG = {"--continent", "-ct"};
    // contraception method
    public static final String CONTRACEPTIONMETHOD = "contraceptionMethod";
    // contraception methods
    public static final String CONTRACEPTIONMETHODS = "contraceptionMethods";
    // --contraceptionMethod || -cm  as argument
    public static final String[] CONTRACEPTIONMETHOD_ARG = {"--contraceptionMethod", "-cm"};
    // create
    public static final String CREATE = "create";
    // curiosity
    public static final String CURIOSITY = "curiosity";
    // currentlyGestationDuration
    public static final String CURRENTLYGESTATIONDURATION = "currentlyGestationDuration";
    // detailed
    public static final String DETAILED = "detailed";
    // diets
    public static final String DIETS = "diets";
    // diet
    public static final String DIET = "diet";
    // --diet || -d as argument
    public static final String[] DIET_ARG = {"--diet", "-d"};
    // dimensions
    public static final String DIMENSIONS = "dimensions";
    // documentation
    public static final String DOCUMENTATION = "documentation";
    // documentation || doc
    public static final String[] DOC_OR_DOCUMENTATION = {"documentation", "doc"};
    // drowning
    public static final String DROWNING = "drowning";
    // --drowning || -dw
    public static final String[] DROWNING_ARG = {"--drowning", "-dw"};
    // ecoregion
    public static final String ECOREGION = "ecoregion";
    // --ecoregion || -e as argument
    public static final String[] ECOREGION_ARG = {"--ecoregion", "-e"};
    // en
    public static final String EN = "en";
    // EnglishWikipedia
    public static final String ENGLISHWIKI = "englishWikipedia";
    // englishTags
    public static final String ENGLISHTAGS = "englishTags";
    // evaluate
    public static final String EVALUATE = "evaluate";
    // family
    public static final String FAMILY = "family";
    // --family || -f as argument
    public static final String[] FAMILY_ARG = {"--family", "-f"};
    // fastDays
    public static final String FASTDAYS = "fastDays";
    // --fastDay || -fd 
    public static final String[] FASTDAY_ARG = {"--fastDay", "-fd"};
    // father
    public static final String FATHER = "father";
    // femaleLifespan
    public static final String FEMALELIFESPAN = "femaleLifespan";
    // femaleMaturityAge
    public static final String FEMALEMATURITYAGE = "femaleMaturity";
    // feeding
    public static final String FEEDING = "feeding";
    // --foodQuantity || -fg as argument
    public static final String[] FOODQUANTITY_ARG = {"--foodQuantity", "-fq"};
    // fr
    public static final String FR = "fr";
    // frenchTags
    public static final String FRENCHTAGS = "frenchTags";
    // frenchWikipedia
    public static final String FRENCHWIKI = "frenchWikipedia";
    // general
    public static final String GENERAL = "general";
    // gestationDuration
    public static final String GESTATIONDURATION = "gestationDuration";
    // gestationFrequency
    public static final String GESTATIONFREQUENCY = "gestationFrequency";
    // greed
    public static final String GREED = "greed";
    // groupSize
    public static final String GROUPSIZE = "groupSize";
    // handyman or hd
    public static final String[] HANDYMAN_OR_HD = {"handyman", "hd"};
    // handyma-occupations
    public static final String[] HANDYMAN_OCCUPATIONS = {"handyman-occupations", "hd-occupations",
        "handyman-occ", "hd-occ"};
// handymen
    public static final String HANDYMEN = "handymen";
    // height
    public static final String HEIGHT = "height";
    // horizon
    public static final String HORIZON = "horizon";
    // --horizon |- -h as argument
    public static final String[] HORIZON_ARG = {"--horizon", "-h"};
    // intelligence
    public static final String INTELLIGENCE = "intelligence";
    // language
    public static final String LANGUAGE = "language";
    // --language || -l as argument
    public static final String[] LANGUAGE_ARG = {"--language", "-l"};
    // lifeSpan
    public static final String LIFESPAN = "lifespan";
    // litterSize
    public static final String LITTERSIZE = "litterSize";
    // load
    public static final String LOAD = "load";
    // ls
    public static final String LS = "ls";
    // maleLifespan
    public static final String MALELIFESPAN = "maleLifespan";
    // maleMaturityAge
    public static final String MALEMATURITYAGE = "maleMaturity";
    // man
    public static final String MAN = "man";
    // managedFamilies
    public static final String MANAGEDFAMILIES = "managedFamilies";
    // managedFamily
    public static final String MANAGEDFAMILY = "managedFamily";
    // managedTask
    public static final String MANAGEDTASK = "managedTask";
    // managedTasks
    public static final String MANAGEDTASKS = "managedTasks";
    // map
    public static final String MAP = "map";
    // meticulousness
    public static final String METICULOUSNESS = "meticulousness";
    // monthsPerEvaluation
    public static final String MONTHSPEREVALUATION = "monthsPerEvaluation";
    // mother
    public static final String MOTHER = "mother";
    // name
    public static final String NAME = "name";
    // names
    public static final String NAMES = "names";
    // optimalFeedingAttributes
    public static final String OPTIMALFEEDING_ATT = "optimalFeedingAttributes";
    // optimalSocialAttributes
    public static final String OPTIMALSOCIAL_ATT = "optimalSocialAttributes";
    // optimalTerritoryAttributes
    public static final String OPTIMALTERRITORY_ATT = "optimalTerritoryAttributes";
    // option
    public static final String OPTION = "option";
    // paddock
    public static final String PADDOCK = "paddock";
    // paddocks
    public static final String PADDOCKS = "paddocks";
    // --paddock || -p as argument
    public static final String[] PADDOCK_ARG = {"--paddock", "-p"};
    // paddock || pad
    public static final String[] PAD_OR_PADDOCK = {"paddock", "pad"};
    // paddocks || pads
    public static final String[] PADS_OR_PADDOCKS = {"paddocks", "pads"};
    // paddock-biome || pad-biome
    public static final String[] PAD_OR_PADDOCK_BIOME = {"paddock-biome", "pad-biome"};
    // paddock-entry || pad-entry
    public static final String[] PAD_OR_PADDOCK_ENTRY = {"paddock-entry", "pad-entry"};
    // paddock-extension || pad-extension
    public static final String[] PAD_OR_PADDOCK_EXTENSION = {"paddock-extension", "pad-extension"};
    // paddock-type || pad-type
    public static final String[] PAD_OR_PADDOCK_TYPE = {"paddock-type", "pad-type"};
    // paddockType
    public static final String PADDOCKTYPE = "paddockType";
    // paddockType || padType
    public static final String[] PADDOCKTYPE_OR_PADTYPE = {"paddockType", "padType"};
    // paddockTypes || padTypes
    public static final String[] PADDOCKTYPES_OR_PADTYPES = {"paddockTypes", "padTypes"};
    // --paddockType || --padType || -pT
    public static final String[] PADDOCKTYPE_ARG = {"--paddockType", "--padType", "-pT"};
    // penalties
    public static final String PENALTIES = "penalties";
    // personality
    public static final String PERSONALITY = "personality";
    // quantity
    public static final String QUANTITY = "quantity";
    // remove
    public static final String REMOVE = "remove";
    // rename
    public static final String RENAME = "rename";
    // reproduction
    public static final String REPRODUCTION = "reproduction";
    // reset
    public static final String RESET = "reset";
    // save
    public static final String SAVE = "save";
    // scientific
    public static final String SCIENTIFIC = "scientific";
    // sex
    public static final String SEX = "sex";
    // sexes
    public static final String SEXES = "sexes";
    // --sex || -sx as argument
    public static final String[] SEX_ARG = {"--sex", "-sx"};
    // size
    public static final String SIZE = "size";
    // --size || -sz as argument
    public static final String[] SIZE_ARG = {"--size", "-sz"};
    // social
    public static final String SOCIAL = "social";
    // specie
    public static final String SPECIE = "specie";
    // species
    public static final String SPECIES = "species";
    // specie || spec
    public static final String[] SPEC_OR_SPECIE = {"specie", "spec"};
    // --specie, -s as arguments
    public static final String[] SPECIE_ARG = {"--specie", "-s"};
    // speed
    public static final String SPEED = "speed";
    // --speed || -s as argument
    public static final String[] SPEED_ARG = {"--speed", "-s"};
    // starvation
    public static final String STARVATION = "starvation";
    // --starvation || -sv
    public static final String[] STARVATION_ARG = {"--starvation", "-sv"};
    // tag
    public static final String TAG = "tag";
    // tags
    public static final String TAGS = "tags";
    // --tag || -t as argument
    public static final String[] TAG_ARG = {"--tag", "-t"};
    // task
    public static final String TASK = "task";
    // tasks
    public static final String TASKS = "tasks";
    // territory
    public static final String TERRITORY = "territory";
    // territorySize
    public static final String TERRITORYSIZE = "territorySize";
    // time
    public static final String TIME = "time";
    // timedPaddock
    public static final String TIMEDPADDOCK = "timedPaddock";
    // timedPaddocks
    public static final String TIMEDPADDOCKS = "timedPaddocks";
    // --timedPaddock, -tP as arguments
    public static final String[] TIMEDPADDOCK_ARG = {"--timedPaddock", "-tP"};
    // timedTaskPerPaddock
    public static final String TIMEDTASK = "timedTaskPerPaddock";
    // timedTasksPerPaddock
    public static final String TIMEDTASKS = "timedTasksPerPaddock";
    // --timedTaskPerPaddock, -tT, -tTP as arguments
    public static final String[] TIMEDTASK_ARG = {"--timedTaskPerPaddock", "-tT", "-tTP"};
    // time
    public static final String TRAINING = "training";
    // uicn
    public static final String UICN = "uicn";
    // update
    public static final String UPDATE = "update";
    //wellBeing
    public static final String WELLBEING = "wellBeing";
    // width 
    public static final String WIDTH = "width";
    // wikipedia || wiki
    public static final String[] WIKI_OR_WIKIPEDIA = {"wikipedia", "wiki"};
    // x
    public static final String X = "x";
    // y
    public static final String Y = "y";
    // yes || y
    public static final String[] YES_OR_Y = {"yes", "y"};
    // zoo
    public static final String ZOO = "zoo";

}
