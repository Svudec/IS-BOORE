package hr.unizg.fer.is.boore.boore.Review;

import hr.unizg.fer.is.boore.boore.Person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
    List<Review> findAllByPerson(Person user);
}
