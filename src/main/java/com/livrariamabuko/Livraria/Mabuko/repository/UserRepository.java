package com.livrariamabuko.Livraria.Mabuko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.livrariamabuko.Livraria.Mabuko.model.User;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsernameAndEnabled(String username, boolean enabled);
	public Optional<User> findByUsernameAndPassword(String username, String password);
	public Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Optional<User> findByConfirmationToken(String token);
	
	public List<User> findBynameContainingIgnoreCase(String name);
	

}
