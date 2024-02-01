package com.livrariamabuko.Livraria.Mabuko.controller;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livrariamabuko.Livraria.Mabuko.DTOs.BookDTO;
import com.livrariamabuko.Livraria.Mabuko.exceptions.DuplicatedEntityException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.EmptyDatabaseException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.ResourceNotFoundException;
import com.livrariamabuko.Livraria.Mabuko.model.Author;
import com.livrariamabuko.Livraria.Mabuko.model.Book;
import com.livrariamabuko.Livraria.Mabuko.model.Publisher;
import com.livrariamabuko.Livraria.Mabuko.repository.AuthorRepository;
import com.livrariamabuko.Livraria.Mabuko.repository.BookRepository;
import com.livrariamabuko.Livraria.Mabuko.repository.PublisherRepository;
import com.livrariamabuko.Livraria.Mabuko.service.BookService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired 
    private BookService bookService;

     
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        if (books.isEmpty()) {
            throw new EmptyDatabaseException("No books found in the database");
        }
        return books;
    }

    @GetMapping("/book/byTitle/{title}")
    public ResponseEntity<List<Book>> getBooksByTitle(@Valid @PathVariable(value = "title") String title)
            throws ResourceNotFoundException {
        List<Book> books = bookRepository.findByTitle(title);
        if (books.isEmpty()) {
            throw new EmptyDatabaseException("No books found with title ::" + title);
        }

        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @PostMapping("/addBook")
    public ResponseEntity addNewBook(@Valid @RequestBody BookDTO bookDTO) throws ResourceNotFoundException {
    
        Author author = authorRepository.findById(bookDTO.author_id())
        .orElseThrow(
                () -> new ResourceNotFoundException("Author with ID:: " + bookDTO.author_id() + " not found"));
Publisher publisher = publisherRepository.findById(bookDTO.publisher_id()).orElseThrow(
        () -> new ResourceNotFoundException("Publisher with ID:: " + bookDTO.publisher_id() + " not found"));
    
        boolean livroExistente = bookService.findBookByTitleGenderEdition(
                bookDTO.title(), bookDTO.gender(), bookDTO.edition());
    
        if (livroExistente) {
            throw new DuplicatedEntityException("The book with credentials title: " + bookDTO.title() + " Genre: " + bookDTO.gender() + " Edition: " + bookDTO.edition() + " already exists in the database", "/api/v1/addBook");

        }
    
    
        Book saveBook = new Book();
        saveBook.setAuthor(author);
        saveBook.setPublisher(publisher);
        BeanUtils.copyProperties(bookDTO, saveBook);
    
        Book book = bookRepository.save(saveBook);
    
        return ResponseEntity.status(HttpStatus.CREATED).body("Book successfully registered!");
    }
    

    

}
