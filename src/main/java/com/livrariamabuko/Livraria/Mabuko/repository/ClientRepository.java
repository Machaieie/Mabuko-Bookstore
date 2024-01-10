package com.livrariamabuko.Livraria.Mabuko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livrariamabuko.Livraria.Mabuko.model.Client;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{

    Optional<Client> findByName(String name);
    Optional<Client> findByEmail(String email);
    
}
