package hr.unizg.fer.is.boore.boore.Bookstore;

import hr.unizg.fer.is.boore.boore.City.City;
import lombok.Data;

@Data
public class BookstoreDTO {
    private Integer id;
    private String city;
    private String name;
    private String adress;
    private String url;
}
