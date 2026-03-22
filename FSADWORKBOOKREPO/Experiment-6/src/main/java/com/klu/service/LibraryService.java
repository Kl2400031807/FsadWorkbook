package com.klu.service;

import java.util.List;
import com.klu.model.Book;

public interface LibraryService {

    String welcomeMessage();

    int getBookCount();

    double getSamplePrice();

    List<String> getBookTitles();

    Book getBookById(Integer id);

    List<Book> searchBook(String title);

    List<Book> getBooksByAuthor(String name);

    String addBook(Book book);

    List<Book> viewBooks();
}