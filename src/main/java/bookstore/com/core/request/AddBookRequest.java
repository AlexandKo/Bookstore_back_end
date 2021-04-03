package bookstore.com.core.request;

import bookstore.com.domain.Book;
import lombok.Getter;

public class AddBookRequest {
    @Getter
    private final Book book;

    public AddBookRequest(Book book) {
        this.book = book;
    }
}