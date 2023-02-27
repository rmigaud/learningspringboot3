package com.github.rmigaud.learningspringboot.ch2.contollers;

import com.github.rmigaud.learningspringboot.ch2.models.Book;
import com.github.rmigaud.learningspringboot.ch2.services.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * switches every web method from being template-based to JSON-based
 *
 */
@RestController
public class APIController {

  private final BookService bookService;

  public APIController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/api/books")
  public List<Book> listAllBooks() {
    return bookService.getBooks();
  }

  @PostMapping("/api/books")
  public Book postNewBookFromJSON(@RequestBody Book newBook) {
    return bookService.create(newBook);
  }
}
