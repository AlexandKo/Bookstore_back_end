package bookstore.com.core.persistence;

import bookstore.com.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBooksRepository extends JpaRepository<Book, Long> {
    boolean existsByTitle(String bookTitle);
}