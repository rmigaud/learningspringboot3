package com.github.rmigaud.learningspringboot.ch2.contollers;

import com.github.rmigaud.learningspringboot.ch2.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.github.rmigaud.learningspringboot.ch2.models.Book;

@Controller // this class is a web controller detected when component scanning
public class IndexController {

  private final BookService bookService;

  public IndexController(BookService bookService) {
    this.bookService = bookService;
  }

  /**
   * map HTTP GET / calls to this method
   *
   * @return resources/templates/index.mustache
   */
  @GetMapping("/")
  String index(Model model) {
    model.addAttribute("Books", bookService.getBooks());
    return "index";
  }

  @PostMapping("/new-book")
  public String newBook(@ModelAttribute Book newBook) {
    bookService.create(newBook);
    return "redirect:/";
  }
}
