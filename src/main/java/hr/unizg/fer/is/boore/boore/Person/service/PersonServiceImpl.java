package hr.unizg.fer.is.boore.boore.Person.service;

import hr.unizg.fer.is.boore.boore.Auth.dto.RegistrationDTO;
import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Book.BookDTO;
import hr.unizg.fer.is.boore.boore.Book.service.BookService;
import hr.unizg.fer.is.boore.boore.Genre.Genre;
import hr.unizg.fer.is.boore.boore.Person.Person;
import hr.unizg.fer.is.boore.boore.Person.PersonRepository;
import hr.unizg.fer.is.boore.boore.Person.dto.UserProfileDTO;
import hr.unizg.fer.is.boore.boore.User.User;
import hr.unizg.fer.is.boore.boore.Wishlist.service.WishlistService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final BookService bookService;
    private final WishlistService wishlistService;

    @Override
    @Transactional
    public Person getById(int id) {
        return personRepository.getById(id);
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
                .map(book -> mapper.map(book, BookDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getRecommendations(Genre genre) {
        return bookService.getRecommendations(genre, getLoggedInUser()).stream()
                .map(book -> mapper.map(book, BookDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserProfileDTO getUserProfile() {
        Person loggedInUser = getLoggedInUser();
        UserProfileDTO res = mapper.map(loggedInUser, UserProfileDTO.class);

        res.setHasRead(wishlistService.getAllBooksForUser(loggedInUser, true)
                .stream().map(book -> mapper.map(book, BookDTO.class)).collect(Collectors.toList()));

        res.setWantsToRead(wishlistService.getAllBooksForUser(loggedInUser, false)
                .stream().map(book -> mapper.map(book, BookDTO.class)).collect(Collectors.toList()));
        return res;
    }
}
