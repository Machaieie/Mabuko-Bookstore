package com.livrariamabuko.Livraria.Mabuko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livrariamabuko.Livraria.Mabuko.model.Publisher;
import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>{

    List<Publisher> findByName(String name);
    
    
}
