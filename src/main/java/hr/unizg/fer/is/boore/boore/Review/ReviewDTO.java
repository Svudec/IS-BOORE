package hr.unizg.fer.is.boore.boore.Review;

import lombok.Data;

@Data
public class ReviewDTO {
    private ReviewId id;
    private String book;
    private String Person;
    private String text;
    private Integer rating;
}
