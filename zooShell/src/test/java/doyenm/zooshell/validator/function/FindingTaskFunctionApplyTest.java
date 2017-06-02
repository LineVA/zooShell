package doyenm.zooshell.validator.function;

import doyenm.zooshell.model.TaskType;
import doyenm.zooshell.validator.context.FindingTaskContext;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class FindingTaskFunctionApplyTest {

    private FindingTaskContext givenContextWithInput(String input) {
        FindingTaskContext context = Mockito.mock(FindingTaskContext.class);
        Mockito.when(context.getConvertedTask()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(context).setConvertedTask(Mockito.any(TaskType.class));
        Mockito.when(context.getTask()).thenReturn(input);
        return context;
    }

    @Test
    public void shouldSetTheConvertedTaskTypeWhenTheInputIsIntegerCorrespondingToAnExistingTaskType() {
        // Given
        String input = "1";
        FindingTaskContext context = givenContextWithInput(input);
        FindingTaskFunction function = new FindingTaskFunction();
        // When
        FindingTaskContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedTask()).isEqualTo(TaskType.CLEANING);
    }
    
     @Test
    public void shouldSetTheConvertedTaskTypeWhenTheInputIsStringCorrespondingToAnExistingTaskTypeWithoutSpace() {
        // Given
        String input = "cleaning";
        FindingTaskContext context = givenContextWithInput(input);
        FindingTaskFunction function = new FindingTaskFunction();
        // When
        FindingTaskContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedTask()).isEqualTo(TaskType.CLEANING);
    }
    
    @Test
    public void shouldSetTheConvertedTaskTypeWhenTheInputIsStringCorrespondingToAnExistingTaskTypeWithSpace() {
        // Given
        String input = "medical training";
        FindingTaskContext context = givenContextWithInput(input);
        FindingTaskFunction function = new FindingTaskFunction();
        // When
        FindingTaskContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedTask()).isEqualTo(TaskType.MEDICAL_TRAINING);
    }
    
    @Test
    public void shouldSetTheConvertedTaskTypeToNullWhenTheInputIsStringNotCorrespondingToAnExistingTaskType() {
        // Given
        String input = "FALSE_CLEANING";
        FindingTaskContext context = givenContextWithInput(input);
        FindingTaskFunction function = new FindingTaskFunction();
        // When
        FindingTaskContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedTask()).isNull();
    }
    
     @Test
    public void shouldSetTheConvertedTaskTypeToNullWhenTheInputIsIntegerNotCorrespondingToAnExistingTaskType() {
        // Given
        String input = "111";
        FindingTaskContext context = givenContextWithInput(input);
        FindingTaskFunction function = new FindingTaskFunction();
        // When
        FindingTaskContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedTask()).isNull();
    }

}
