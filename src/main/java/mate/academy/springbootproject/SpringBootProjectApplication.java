package mate.academy.springbootproject;

import java.math.BigDecimal;
import mate.academy.springbootproject.model.Book;
import mate.academy.springbootproject.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootProjectApplication {
    private final BookService bookService;

    public SpringBootProjectApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Book book = new Book();
                book.setTitle("Hobbit");
                book.setAuthor("John Ronald Reuel Tolkien");
                book.setIsbn("9780261103283");
                book.setPrice(BigDecimal.valueOf(19.99));
                book.setDescription("""
                    Tolkien describes hobbits as between two and four feet (0.6–1.2 m) tall, 
                    with the average height being three feet six inches (1.1 m). They dress in 
                    bright colours, favouring yellow and green. They are usually shy, but are 
                    nevertheless capable of great courage and amazing feats under the proper 
                    circumstances.""");
                book.setCoverImage("https://bookriot.com/wp-content/uploads/2017/02/hobbit-original-cover-1280x720.jpg");

                bookService.save(book);
                System.out.println(bookService.findAll());
            }
        };
    }
}
