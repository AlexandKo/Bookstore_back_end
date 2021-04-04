package bookstore.com.core.response;

import bookstore.com.domain.Book;
import lombok.Getter;

import java.util.List;

public class GetBookResponse {
    @Getter
    private final List<Book> bookList;
    @Getter
    private final int pagesCount;

    public GetBookResponse(List<Book> bookList, int pagesCount) {
        this.bookList = bookList;
        this.pagesCount = pagesCount;
    }
}