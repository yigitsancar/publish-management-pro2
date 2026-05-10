package com.yigit.publish.controller;

import com.yigit.publish.entity.Author;
import com.yigit.publish.entity.Book;
import com.yigit.publish.entity.Publish;
import com.yigit.publish.repository.AuthorRepository;
import com.yigit.publish.repository.BookRepository;
import com.yigit.publish.repository.PublishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/publishes")
public class PublishController {

    private final PublishRepository publishRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public PublishController(PublishRepository publishRepository,
                             AuthorRepository authorRepository,
                             BookRepository bookRepository) {
        this.publishRepository = publishRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public String listPublishes(Model model) {
        model.addAttribute("publishes", publishRepository.findAll());
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        return "publishes";
    }

    @PostMapping
    public String savePublish(@RequestParam Long authorId,
                              @RequestParam Long bookId,
                              @RequestParam String publishDate,
                              @RequestParam Integer edition) {

        Author author = authorRepository.findById(authorId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        Publish publish = new Publish();
        publish.setAuthor(author);
        publish.setBook(book);
        publish.setPublishDate(LocalDate.parse(publishDate));
        publish.setEdition(edition);

        publishRepository.save(publish);
        return "redirect:/publishes";
    }

    @GetMapping("/delete/{id}")
    public String deletePublish(@PathVariable Long id) {
        publishRepository.deleteById(id);
        return "redirect:/publishes";
    }
}