package bookstore.com.core.service;

import bookstore.com.core.persistence.JpaBooksRepository;
import bookstore.com.core.request.AddBookRequest;
import bookstore.com.core.response.AddBookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private JpaBooksRepository booksRepository;

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
}