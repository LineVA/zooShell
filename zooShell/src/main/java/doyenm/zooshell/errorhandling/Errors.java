package doyenm.zooshell.errorhandling;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Errors {
    private List<BusinessError> errorsList = new ArrayList<>();
}
