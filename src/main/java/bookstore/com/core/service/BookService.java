package bookstore.com.core.service;

import bookstore.com.core.persistence.JpaBooksRepository;
import bookstore.com.core.request.AddBookRequest;
import bookstore.com.core.request.GetBookRequest;
import bookstore.com.core.response.AddBookResponse;
import bookstore.com.core.response.GetBookResponse;
import bookstore.com.core.util.PageCalculator;
import bookstore.com.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

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
        pageCalculator.setElementsOnPageQuantity(elementsOnPage);

        int offset = pageCalculator.calculatePageOffset(request.getPageNumber());
        List<Book> booksOnPage = booksRepository.getBookOnPage(elementsOnPage, offset);
        int pagesCount = pageCalculator.getPageCount(booksRepository.count());

        return new GetBookResponse(booksOnPage, pagesCount);
    }
}