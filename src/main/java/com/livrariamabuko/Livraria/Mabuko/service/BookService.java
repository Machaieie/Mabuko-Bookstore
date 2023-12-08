package com.livrariamabuko.Livraria.Mabuko.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livrariamabuko.Livraria.Mabuko.model.Book;
import com.livrariamabuko.Livraria.Mabuko.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public boolean findBookByTitleGenderEdition(String title, String gender, int edition) {
        Optional<Book> existingBook = bookRepository.findByTitleAndGenderAndEdition(title, gender, edition);
        return existingBook.isPresent();
    }
    


     

    


}
