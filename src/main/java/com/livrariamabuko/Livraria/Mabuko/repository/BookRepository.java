package com.livrariamabuko.Livraria.Mabuko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livrariamabuko.Livraria.Mabuko.model.Author;
import com.livrariamabuko.Livraria.Mabuko.model.Book;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{
    
   
    List<Book> findByTitle(String title);
   
    List<Book> findByGender(String gender);

    Optional<Book> findByTitleAndEditionAndAuthor(String title, int edition, Author author);}
