package com.github.rmigaud.learningspringboot.ch2.services;

import com.github.rmigaud.learningspringboot.ch2.models.Book;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

  private List<Book> books = List.of(
      new Book("Learning Spring Boot 3.0 Greg Turnquist"),
      new Book("Oracle Certified Professional Java SE 17 Oracle Press 2023"),
      new Book("Docker for Java and Spring Boot Developers"),
      new Book("Domain-driven design Eric Evans"));

  public List<Book> getBooks() {
    return books;
  }

  public Book create(Book newBook) {
      List<Book> newBooks = new ArrayList<>(books);
      newBooks.add(newBook);
      books = List.copyOf(newBooks);
      return newBook;
  }
}
