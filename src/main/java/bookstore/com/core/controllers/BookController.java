package bookstore.com.core.controllers;

import bookstore.com.core.request.AddBookRequest;
import bookstore.com.core.request.GetBookRequest;
import bookstore.com.core.response.AddBookResponse;
import bookstore.com.core.response.GetBookResponse;
import bookstore.com.core.service.BookService;
import bookstore.com.domain.Book;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping(value = "addBook")
    public String addBook(@RequestBody Book book) {
        AddBookResponse response = bookService.add(new AddBookRequest(book));

        JSONObject object = new JSONObject();
        object.put("isWasSaved", response.isWasSaved());

        return object.toString();
    }

    @GetMapping("getBook")
    public String getCourses(@RequestParam Integer pageNumber) {
        GetBookResponse response = bookService.getBooks(new GetBookRequest(pageNumber));

        JSONObject object = new JSONObject();
        object.put("load", response.getBookList());
        object.put("pageCount", response.getPagesCount());

        return object.toString();
    }
}