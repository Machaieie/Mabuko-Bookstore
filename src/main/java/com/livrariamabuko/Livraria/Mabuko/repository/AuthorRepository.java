package com.livrariamabuko.Livraria.Mabuko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livrariamabuko.Livraria.Mabuko.model.Author;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long>{
    List<Author> findByName(String name);
    
    
}
