package bookstore.com;

import bookstore.com.core.request.AddBookRequest;
import bookstore.com.core.response.AddBookResponse;
import bookstore.com.core.service.BookService;
import bookstore.com.domain.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication()
public class StartApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StartApplication.class);
        System.out.println("App started");
        BookService bookService = context.getBean(BookService.class);

        Book book = new Book();
        book.setTitle("Test");
        book.setPrice(new BigDecimal("5.45"));

        AddBookResponse x = bookService.add(new AddBookRequest(book));
        System.out.println("First add response: " + x.isWasSaved());

        AddBookResponse y = bookService.add(new AddBookRequest(book));
        System.out.println("Second add response: " + y.isWasSaved());

        AddBookResponse z = bookService.add(new AddBookRequest(book));
        System.out.println("Third add response: " + z.isWasSaved());
    }
}