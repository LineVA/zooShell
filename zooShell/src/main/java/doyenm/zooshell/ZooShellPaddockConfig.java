//package doyenm.zooshell;
//
//import doyenm.zooshell.commandLine.commandImpl.paddock.LsBiome;
//import doyenm.zooshell.commandLine.commandImpl.paddock.LsPaddockType;
//import doyenm.zooshell.commandLine.commandImpl.paddock.*;
//import doyenm.zooshell.controller.paddockcontroller.*;
//import doyenm.zooshell.validator.FindPaddock;
//import doyenm.zooshell.validator.PaddockChangeNameValidator;
//import doyenm.zooshell.validator.PaddockCreationValidator;
//import doyenm.zooshell.validator.PaddockEntryCreationValidator;
//import doyenm.zooshell.validator.PaddockExtensionCreationValidator;
//import doyenm.zooshell.validator.PaddockExtensionLocationValidator;
//import doyenm.zooshell.validator.PaddockLocationValidator;
//import doyenm.zooshell.validator.PaddockRemoveValidator;
//import doyenm.zooshell.validator.PaddockValidator;
//import doyenm.zooshell.validator.PaddockValidatorsConfig;
//import doyenm.zooshell.validator.UpdateBiomeValidator;
//import doyenm.zooshell.validator.UpdatePaddockTypeValidator;
//import doyenm.zooshell.validator.function.FindingBiomeFunction;
//import doyenm.zooshell.validator.function.FindingPaddockTypeFunction;
//import doyenm.zooshell.validator.name.NameValidator;
//import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
//import doyenm.zooshell.validator.predicates.StringLengthPredicates;
//import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//
///**
// *
// * @author doyenm
// */
//@Configuration
//@Import({ZooShellPredicatesConfig.class, PaddockControllerConfig.class, PaddockValidatorsConfig.class})
//@PropertySource("classpath:/doyenm/zooshell/zooshell.properties")
//public class ZooShellPaddockConfig {
//
////    @Autowired
////    Environment environment;
////
////    @Autowired
////    PaddockControllerConfig paddockControllerConfig;
////
////    // Predicates
////    @Autowired
////    FindPaddock findPaddock;
////
////    @Autowired
////    FindingBiomeFunction findingBiomeFunction;
////
////    @Autowired
////    FindingPaddockTypeFunction findingPaddockTypeFunction;
////
////    @Autowired
////    StringLengthPredicates stringLengthPredicates;
////
////    @Autowired
////    UniquenessNamesBiPredicates uniquenessNamesBiPredicates;
////
////    @Autowired
////    IntegerValuePredicates integerValuePredicates;
////    
////    @Autowired
////    NameValidator nameValidator; 
//
//    // Validators
////    @Bean
////    PaddockChangeNameValidator paddockChangeNameValidator() {
////        return new PaddockChangeNameValidator(
////                findPaddock,
////                nameValidator);
////    }
////
////    @Bean
////    PaddockCreationValidator paddockCreationValidator() {
////        return new PaddockCreationValidator(
////                nameValidator,
////                integerValuePredicates,
////                Integer.parseInt(environment.getProperty("paddock.height.min")),
////                Integer.parseInt(environment.getProperty("paddock.width.min"))
////        );
////    }
//
////    @Bean
////    PaddockEntryCreationValidator paddockEntryCreationValidator() {
////        return new PaddockEntryCreationValidator(findPaddock);
////    }
////
////    @Bean
////    PaddockExtensionCreationValidator paddockExtensionCreationValidator() {
////        return new PaddockExtensionCreationValidator(findPaddock);
////    }
////
////    @Bean
////    PaddockExtensionLocationValidator paddockExtensionLocationValidator() {
////        return new PaddockExtensionLocationValidator();
////    }
//
////    @Bean
////    PaddockLocationValidator paddockLocationValidator() {
////        return new PaddockLocationValidator();
////    }
////
////    @Bean
////    PaddockRemoveValidator paddockRemoveValidator() {
////        return new PaddockRemoveValidator(findPaddock);
////    }
////
////    @Bean
////    PaddockValidator paddockValidator() {
////        return new PaddockValidator(findPaddock);
////    }
////
////    @Bean
////    UpdateBiomeValidator updateBiomeValidator() {
////        return new UpdateBiomeValidator(findPaddock, findingBiomeFunction);
////    }
////
////    @Bean
////    UpdatePaddockTypeValidator updatePaddockTypeValidator() {
////        return new UpdatePaddockTypeValidator(findPaddock, findingPaddockTypeFunction);
////    }
//
//    // Commands
////    @Bean
////    RenamePaddock changePaddockName() {
////        return new RenamePaddock(
////                paddockChangeNameValidator(),
////                paddockControllerConfig.paddockChangeNameController());
////    }
////
////    @Bean
////    CreatePaddock createPaddock() {
////        return new CreatePaddock(
////                paddockCreationValidator(),
////                paddockLocationValidator(),
////                paddockControllerConfig.paddockCreationController());
////    }
////
////    @Bean
////    CreatePaddockEntry createPaddockEntry() {
////        return new CreatePaddockEntry(
////                paddockEntryCreationValidator(),
////                paddockControllerConfig.paddockEntryCreationController());
////    }
////
////    @Bean
////    CreatePaddockExtension createPaddockExtension() {
////        return new CreatePaddockExtension(
////                paddockExtensionCreationValidator(),
////                paddockExtensionLocationValidator(),
////                paddockControllerConfig.paddockExtensionCreationController());
////    }
////
////    @Bean
////    DetailPad detailPad() {
////        return new DetailPad(
////                paddockValidator(),
////                paddockControllerConfig.paddockDetailsController());
////    }
//
////    @Bean
////    LsBiome lsBiome() {
////        return new LsBiome();
////    }
//
////    @Bean
////    LsPaddock lsPaddock() {
////        return new LsPaddock();
////    }
//
////    @Bean
////    LsPaddockType LsPaddockType() {
////        return new LsPaddockType();
////    }
//
////    @Bean
////    RemovePaddock removePaddock() {
////        return new RemovePaddock(
////                paddockRemoveValidator(),
////                paddockControllerConfig.paddockRemoveController());
////    }
////
////    @Bean
////    UpdateBiome updateBiome() {
////        return new UpdateBiome(
////                updateBiomeValidator(),
////                paddockControllerConfig.updateBiomeController());
////    }
////
////    @Bean
////    UpdatePaddockType updatePaddockType() {
////        return new UpdatePaddockType(
////                updatePaddockTypeValidator(),
////                paddockControllerConfig.updatePaddockTypeController());
////    }
//
//}
