package mate.academy.bookstoretest;

import java.math.BigDecimal;
import mate.academy.bookstoretest.model.Book;
import mate.academy.bookstoretest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreTestApplication {

    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreTestApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book simpleBook = new Book();
            simpleBook.setTitle("Simple Book");
            simpleBook.setAuthor("Admin");
            simpleBook.setIsbn("ISBN-10: 0-596-52068-5");
            simpleBook.setPrice(BigDecimal.valueOf(250));
            simpleBook.setDescription("This is a sample book description.");
            simpleBook.setCoverImage("https://admin@ukr.com/cover1.jpg");
            bookService.save(simpleBook);

            System.out.println(bookService.findAll());
        };
    }
}
