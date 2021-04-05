package bookstore.com.core.service;

import bookstore.com.core.persistence.JpaBooksRepository;
import bookstore.com.core.request.AddBookRequest;
import bookstore.com.core.request.GetBookRequest;
import bookstore.com.core.request.UpdateBookRequest;
import bookstore.com.core.response.AddBookResponse;
import bookstore.com.core.response.GetBookResponse;
import bookstore.com.core.response.UpdateBookResponse;
import bookstore.com.core.util.PageCalculator;
import bookstore.com.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BookService {
    @Autowired
    private JpaBooksRepository booksRepository;
    @Autowired
    private PageCalculator pageCalculator;
    @Value("${element.on.page.quantity}")
    private int elementsOnPage;

    public AddBookResponse add(AddBookRequest request) {
        String bookTitle = request.getBook().getTitle();
        boolean isBookExists = booksRepository.existsByTitle(bookTitle);
        if (isBookExists) {
            return new AddBookResponse(false);
        } else {
            booksRepository.save(request.getBook());
        }
        return new AddBookResponse(true);
    }

    public GetBookResponse getBooks(GetBookRequest request) {
        int offset = pageCalculator.calculatePageOffset(request.getPageNumber());
        List<Book> booksOnPage = booksRepository.getBookOnPage(elementsOnPage, offset);
        int pagesCount = pageCalculator.getPageCount(booksRepository.count());

        return new GetBookResponse(booksOnPage, pagesCount);
    }

    public UpdateBookResponse update(UpdateBookRequest request) {
        AtomicInteger updateBooksCount = new AtomicInteger();

        List<Book> updateBooksList = request.getBooks();
        updateBooksList.forEach(book -> {
            boolean isBookExists = booksRepository.existsByTitle(book.getTitle());
            if (isBookExists) {
                Book updateBook = booksRepository.findByTitle(book.getTitle());

                updateBooksCount.getAndIncrement();
                updateBook.setPrice(book.getPrice());

                booksRepository.save(updateBook);
            } else {
                booksRepository.save(book);
            }
        });
        return new UpdateBookResponse(updateBooksCount.get());
    }
}