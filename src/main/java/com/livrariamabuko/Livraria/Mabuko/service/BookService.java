package com.livrariamabuko.Livraria.Mabuko.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livrariamabuko.Livraria.Mabuko.DTOs.BookDTO;
import com.livrariamabuko.Livraria.Mabuko.exceptions.DuplicatedEntityException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.EmptyDatabaseException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.ResourceNotFoundException;
import com.livrariamabuko.Livraria.Mabuko.model.Book;
import com.livrariamabuko.Livraria.Mabuko.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


     public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
         if (books.isEmpty()) {
            throw new EmptyDatabaseException("No books found in the database.");
        }
        return books;
    }
    

    public Optional<Book> getBookById(long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isEmpty()) {
            throw new ResourceNotFoundException("Book  with ID: " + id+" not found");
        }

        return optionalBook;
    }

    public List<Book> getBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);

        if (books.isEmpty()) {
            throw new ResourceNotFoundException(" books with title: " + title+"  not found");
        }

        return books;
    }

    public List<Book> getBooksByGender(String gender) {
        List<Book> books = bookRepository.findByGender(gender);

        if (books.isEmpty()) {
            throw new ResourceNotFoundException(" books  with gender: " + gender+"  not found");
        }

        return books;
    }

     

    


}
