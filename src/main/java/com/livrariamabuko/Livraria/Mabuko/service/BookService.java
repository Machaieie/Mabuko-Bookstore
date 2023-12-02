package com.livrariamabuko.Livraria.Mabuko.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

      public Book saveBook(Book book) {
        if (bookRepository.findByTitleAndEditionAndAuthor(book.getTitle(), book.getEdition(), book.getAuthor()).isPresent()) {
            throw new DuplicatedEntityException("The book with the title '" + book.getTitle() +
                    "', edition '" + book.getEdition() +
                    "', and author '" + book.getAuthor().getName() + "' already exists.");
        }

        return bookRepository.save(book);
    }

    public Book updateBook(long id, Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
        
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setPublisherDate(updatedBook.getPublisherDate());
            existingBook.setEdition(updatedBook.getEdition());
            existingBook.setGender(updatedBook.getGender());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setPublisher(updatedBook.getPublisher());
            existingBook.setAvailability(updatedBook.isAvailability());

            return bookRepository.save(existingBook);
        } else {
            throw new ResourceNotFoundException("Book  with ID: " + id+" not found");
        }
    }

    public void deleteBook(long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Book  with ID: " + id+" not found");
        }
    }


}
