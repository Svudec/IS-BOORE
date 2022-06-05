package hr.unizg.fer.is.boore.boore.Camunda;

import hr.unizg.fer.is.boore.boore.Review.ReviewDTO;
import hr.unizg.fer.is.boore.boore.Review.service.ReviewService;
import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("publishReview")
@AllArgsConstructor
public class PublishReviewDelegate implements JavaDelegate {

    private final ReviewService reviewService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        int personId = (Integer) execution.getVariable("person");
        int bookId = (Integer) execution.getVariable("book");
        int rating = (Integer) execution.getVariable("rating");
        String text = (String) execution.getVariable("text");

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setBook(String.valueOf(bookId));
        reviewDTO.setPerson(String.valueOf(personId));
        reviewDTO.setRating(rating);
        reviewDTO.setText(text);

        reviewService.createOrEdit(reviewDTO);
    }
}
