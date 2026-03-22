package com.klu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.klu.model.Book;
import com.klu.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {

    private List<Book> bookList = new ArrayList<>();

    @Override
    public String welcomeMessage() {
        return "Welcome to the Online Library System!";
    }

    @Override
    public int getBookCount() {
        return bookList.size();
    }

    @Override
    public double getSamplePrice() {
        if (bookList.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (Book b : bookList) 
        {
            sum += b.getPrice();
        }
        return sum / bookList.size();
    }

    @Override
    public List<String> getBookTitles() {

        List<String> titles = new ArrayList<>();

        for (Book b : bookList) {
            titles.add(b.getTitle());
        }

        return titles;
    }
    
    @Override
    public Book getBookById(Integer id) {
        for (Book b : bookList) {
            if (b.getId().equals(id)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public List<Book> searchBook(String title) {

        List<Book> result = new ArrayList<>();

        for (Book b : bookList) {
            if (b.getTitle() != null && b.getTitle().equalsIgnoreCase(title)) {
                result.add(b);
            }
        }

        return result;
    }

    @Override
    public List<Book> getBooksByAuthor(String name) {

        List<Book> result = new ArrayList<>();

        for (Book b : bookList) {
            if (b.getAuthor() != null && b.getAuthor().equalsIgnoreCase(name)) {
                result.add(b);
            }
        }

        return result;
    }

    @Override
    public String addBook(Book book) {
        bookList.add(book);
        return "Book added successfully!";
    }

    @Override
    public List<Book> viewBooks() {
        return bookList;
    }
}