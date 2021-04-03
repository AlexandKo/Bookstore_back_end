package bookstore.com.core.service;

import bookstore.com.core.persistence.JpaBooksRepository;

import bookstore.com.core.request.AddBookRequest;
import bookstore.com.core.response.AddBookResponse;
import bookstore.com.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class BookServiceTest {
    @Mock
    private JpaBooksRepository booksRepository;
    @InjectMocks
    private BookService bookService;
    private Book book;

    @BeforeEach
    public void startUp() {
        book = new Book();
        book.setId(1L);
        book.setTitle("Book title");
        book.setPrice(new BigDecimal("5.45"));
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void bookNotExists_save() {
        Mockito.when(booksRepository.existsByTitle(book.getTitle())).thenReturn(false);
        AddBookResponse response = bookService.add(new AddBookRequest(book));

        Mockito.verify(booksRepository, Mockito.times(1)).save(book);

        assertTrue(response.isWasSaved());

    }

    @Test
    public void bookExists_notSave() {
        Mockito.when(booksRepository.existsByTitle(book.getTitle())).thenReturn(true);
        AddBookResponse response = bookService.add(new AddBookRequest(book));

        Mockito.verify(booksRepository, Mockito.times(0)).save(book);

        assertFalse(response.isWasSaved());

    }

}