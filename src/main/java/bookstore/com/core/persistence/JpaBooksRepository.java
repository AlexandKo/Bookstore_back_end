package bookstore.com.core.persistence;

import bookstore.com.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaBooksRepository extends JpaRepository<Book, Long> {
    boolean existsByTitle(String bookTitle);

    @Query(value = "SELECT * FROM books order by id DESC limit ? offset ?", nativeQuery = true)
    List<Book> getBookOnPage(int elementOnPage, int pageNumber);

    Book findByTitle(String title);
}