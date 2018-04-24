package doyenm.zooshell.utils;

import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.commandline.utils.FormattingInList;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;

import java.util.List;
import java.util.stream.Collectors;

public class DisplayingErrorsList {
    public static ReturnExec displayErrorList(List<BusinessError> input){
        List<String> errors = input
                .stream()
                .map(BusinessError::getType)
                .map(ErrorType::toString)
                .collect(Collectors.toList());
        return new ReturnExec(FormattingInList.formatList(errors),
                TypeReturn.ERROR, null);
    }
}
