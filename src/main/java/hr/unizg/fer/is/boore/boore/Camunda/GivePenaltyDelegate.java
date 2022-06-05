package hr.unizg.fer.is.boore.boore.Camunda;

import hr.unizg.fer.is.boore.boore.Person.service.PersonService;
import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("givePenalty")
@AllArgsConstructor
public class GivePenaltyDelegate implements JavaDelegate {

    private final PersonService personService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        int personId = (Integer) execution.getVariable("person");
        personService.increaseDeclinedReviewsCount(personId);
    }
}
