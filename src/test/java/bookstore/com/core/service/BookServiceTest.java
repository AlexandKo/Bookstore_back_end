package bookstore.com.core.service;

import bookstore.com.core.persistence.JpaBooksRepository;

import bookstore.com.core.request.AddBookRequest;
import bookstore.com.core.request.UpdateBookRequest;
import bookstore.com.core.response.AddBookResponse;
import bookstore.com.core.response.UpdateBookResponse;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class BookServiceTest {
    @Mock
    private JpaBooksRepository booksRepository;
    @InjectMocks
    private BookService bookService;
    private Book book;
    private final List<Book> updateBooksList = new ArrayList<>();

    @BeforeEach
    public void startUp() {
        book = new Book();
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

    @Test
    public void updateBook_BookExists() {
        updateBooksList.add(book);
        Mockito.when(booksRepository.existsByTitle(book.getTitle())).thenReturn(true);
        Mockito.when(booksRepository.findByTitle(book.getTitle())).thenReturn(book);
        UpdateBookResponse response = bookService.update(new UpdateBookRequest(updateBooksList));

        Mockito.verify(booksRepository, Mockito.times(1)).save(book);

        assertEquals(1, response.getUpdateBooksCount());
    }

    @Test
    public void updateBook_BookNotExists() {
        updateBooksList.add(book);
        Mockito.when(booksRepository.existsByTitle(book.getTitle())).thenReturn(false);
        UpdateBookResponse response = bookService.update(new UpdateBookRequest(updateBooksList));

        Mockito.verify(booksRepository, Mockito.times(1)).save(book);

        assertEquals(0, response.getUpdateBooksCount());
    }

    @Test
    public void updateBook_TwoBooks_OneExists_OneAdd() {
        Book newBook = new Book();
        newBook.setTitle("New Book title");
        newBook.setPrice(new BigDecimal("3.05"));

        updateBooksList.add(book);
        updateBooksList.add(newBook);

        Mockito.when(booksRepository.existsByTitle(book.getTitle())).thenReturn(true);
        Mockito.when(booksRepository.existsByTitle(newBook.getTitle())).thenReturn(false);
        Mockito.when(booksRepository.findByTitle(book.getTitle())).thenReturn(book);
        UpdateBookResponse response = bookService.update(new UpdateBookRequest(updateBooksList));

        Mockito.verify(booksRepository, Mockito.times(1)).save(book);
        Mockito.verify(booksRepository, Mockito.times(1)).save(newBook);

        assertEquals(1, response.getUpdateBooksCount());
    }

    @Test
    public void updateBook_TwoBooks_TwoExists() {
        Book newBook = new Book();
        newBook.setTitle("New Book title");
        newBook.setPrice(new BigDecimal("3.05"));

        updateBooksList.add(book);
        updateBooksList.add(newBook);

        Mockito.when(booksRepository.existsByTitle(book.getTitle())).thenReturn(true);
        Mockito.when(booksRepository.existsByTitle(newBook.getTitle())).thenReturn(true);
        Mockito.when(booksRepository.findByTitle(book.getTitle())).thenReturn(book);
        Mockito.when(booksRepository.findByTitle(newBook.getTitle())).thenReturn(newBook);
        UpdateBookResponse response = bookService.update(new UpdateBookRequest(updateBooksList));

        Mockito.verify(booksRepository, Mockito.times(1)).save(book);
        Mockito.verify(booksRepository, Mockito.times(1)).save(newBook);

        assertEquals(2, response.getUpdateBooksCount());
    }

    @Test
    public void updateBook_TwoBooks_TwoAdd() {
        Book newBook = new Book();
        newBook.setTitle("New Book title");
        newBook.setPrice(new BigDecimal("3.05"));

        updateBooksList.add(book);
        updateBooksList.add(newBook);

        Mockito.when(booksRepository.existsByTitle(book.getTitle())).thenReturn(false);
        Mockito.when(booksRepository.existsByTitle(newBook.getTitle())).thenReturn(false);
        UpdateBookResponse response = bookService.update(new UpdateBookRequest(updateBooksList));

        Mockito.verify(booksRepository, Mockito.times(1)).save(book);
        Mockito.verify(booksRepository, Mockito.times(1)).save(newBook);

        assertEquals(0, response.getUpdateBooksCount());
    }
}