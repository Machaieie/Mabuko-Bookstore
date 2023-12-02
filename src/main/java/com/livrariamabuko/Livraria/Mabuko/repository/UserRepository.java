package com.livrariamabuko.Livraria.Mabuko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livrariamabuko.Livraria.Mabuko.model.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findByName(String name);
    List<User> findByUsername(String username);
    
    
}
