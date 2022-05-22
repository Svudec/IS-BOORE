package hr.unizg.fer.is.boore.boore.Person.service;

import hr.unizg.fer.is.boore.boore.Auth.dto.RegistrationDTO;
import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Book.BookDTO;
import hr.unizg.fer.is.boore.boore.Book.service.BookService;
import hr.unizg.fer.is.boore.boore.Genre.Genre;
import hr.unizg.fer.is.boore.boore.Person.Person;
import hr.unizg.fer.is.boore.boore.Person.PersonRepository;
import hr.unizg.fer.is.boore.boore.Person.dto.UserProfileDTO;
import hr.unizg.fer.is.boore.boore.Review.ReviewDTO;
import hr.unizg.fer.is.boore.boore.Review.service.ReviewService;
import hr.unizg.fer.is.boore.boore.User.User;
import hr.unizg.fer.is.boore.boore.Wishlist.service.WishlistService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final BookService bookService;

    @Override
    @Transactional
    public Person getById(int id) {
        return personRepository.getById(id);
    }

    @Override
    public Person getById(String id) {
        int personId;
        try {
            personId = Integer.parseInt(id);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Person id is wrong format");
        }
        return getById(personId);
    }

    @Override
    public Person getLoggedInUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getById(principal.getId());
    }

    @Override
    @Transactional
    public Person getByUsername(String username) {
        return personRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    @Override
    @Transactional
    public Boolean existsByUsername(String username) {
        return personRepository.existsByUsername(username);
    }

    @Override
    @Transactional
    public Boolean existsByEmail(String email) {
        return personRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public Person createUser(RegistrationDTO registrationDTO) {
        Person newPerson = mapper.map(registrationDTO, Person.class);
        newPerson.setId(null);
        newPerson.setIsUser(true);
        newPerson.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        return personRepository.save(newPerson);
    }

    @Override
    public List<BookDTO> getRecommendations() {
        return bookService.getRecommendations(getLoggedInUser()).stream()
                .map(book -> {
                    book.setReviews(null);
                    return mapper.map(book, BookDTO.class);
                }).collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getRecommendations(Genre genre) {
        return bookService.getRecommendations(genre, getLoggedInUser()).stream()
                .map(book -> {
                    book.setReviews(null);
                    return mapper.map(book, BookDTO.class);
                }).collect(Collectors.toList());
    }

    @Override
    public UserProfileDTO getUserProfile() {
        return getUserProfile(getLoggedInUser());
    }

    @Override
    public UserProfileDTO getUserProfile(String userId) {
        int id;
        try {
            id = Integer.parseInt(userId);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("User id is wrong format");
        }
        return getUserProfile(personRepository.getById(id));
    }

    @Override
    public UserProfileDTO getUserProfile(Person person) {
        UserProfileDTO res = mapper.map(person, UserProfileDTO.class);

        List<BookDTO> hasRead = new ArrayList<>();
        List<BookDTO> wantsToRead = new ArrayList<>();

        person.getBooksInWishlist()
                .forEach(wishlist -> {
                    BookDTO dto = mapper.map(wishlist.getBook(), BookDTO.class);
                    dto.setReviews(null);
                    if(wishlist.getWasRead()){
                        hasRead.add(dto);
                    } else {
                        wantsToRead.add(dto);
                    }
                });
        res.setHasRead(hasRead);
        res.setWantsToRead(wantsToRead);

        res.setReviews(person.getReviews()
                .stream().map(review -> mapper.map(review, ReviewDTO.class)).collect(Collectors.toList()));
        return res;
    }
}
