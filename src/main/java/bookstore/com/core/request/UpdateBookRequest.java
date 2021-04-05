package bookstore.com.core.request;

import bookstore.com.domain.Book;
import lombok.Getter;

import java.util.List;

public class UpdateBookRequest {
    @Getter
    private final List<Book> books;

    public UpdateBookRequest(List<Book> book) {
        this.books = book;
    }
}