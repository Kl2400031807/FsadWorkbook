package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klu.model.Book;
import com.klu.service.LibraryService;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/welcome")
    public String welcome() {
        return libraryService.welcomeMessage();
    }

    @GetMapping("/count")
    public int count() {
        return libraryService.getBookCount();
    }

    @GetMapping("/price")
    public double averagePrice() {
        return libraryService.getSamplePrice();
    }

    @GetMapping("/books")
    public List<String> books() {
        return libraryService.getBookTitles();
    }

    @GetMapping("/books/{id}")
    public Book bookById(@PathVariable Integer id) {
        return libraryService.getBookById(id);
    }

    @GetMapping("/search")
    public List<Book> search(@RequestParam String title) {
        return libraryService.searchBook(title);
    }

    @GetMapping("/author/{name}")
    public List<Book> author(@PathVariable String name) {
        return libraryService.getBooksByAuthor(name);
    }

    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        return libraryService.addBook(book);
    }

    @GetMapping("/viewbooks")
    public List<Book> viewBooks() {
        return libraryService.viewBooks();
    }
}