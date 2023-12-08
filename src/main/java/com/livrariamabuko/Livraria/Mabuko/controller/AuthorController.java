package com.livrariamabuko.Livraria.Mabuko.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livrariamabuko.Livraria.Mabuko.DTOs.AuthorDTO;
import com.livrariamabuko.Livraria.Mabuko.exceptions.EmptyDatabaseException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.ResourceNotFoundException;
import com.livrariamabuko.Livraria.Mabuko.model.Author;
import com.livrariamabuko.Livraria.Mabuko.repository.AuthorRepository;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/v1")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            throw new EmptyDatabaseException("No authors found in the database.");
        }

        return authors;
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with ID:: " + id + " not found"));
        return ResponseEntity.ok().body(author);
    }

    @GetMapping("/authors/byName/{name}")
    public ResponseEntity<List<Author>> getAuthorByName(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        List<Author> authors = authorRepository.findByName(name);

        if (authors.isEmpty()) {
            throw new ResourceNotFoundException("No authors found with the name: " + name);
        }

        return ResponseEntity.status(HttpStatus.OK).body(authors);
    }

    @PostMapping("/author")
    public ResponseEntity addNewAuthor(@Valid @RequestBody AuthorDTO authorDTO) {
        Author author = new Author();

        BeanUtils.copyProperties(authorDTO, author);
        Author saveAuthor = authorRepository.save(author);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("author/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable(value = "id") long id,
            @Valid @RequestBody AuthorDTO authorDTO) throws ResourceNotFoundException {
        Author foundedAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with ID:: " + id + " not found"));
        foundedAuthor.setName(authorDTO.name());
        foundedAuthor.setNationairy(authorDTO.nationairy());
        foundedAuthor.setBibliography(authorDTO.bibliography());
        final Author updateAuthor = authorRepository.save(foundedAuthor);
        return ResponseEntity.ok().body(updateAuthor);
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<Object> deleteAuthorEntity(@Valid @PathVariable(value = "id") long id)
            throws ResourceNotFoundException {
        Author foundedAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with ID:: " + id + " not found"));
        authorRepository.delete(foundedAuthor);
        return ResponseEntity.status(HttpStatus.OK).body("author deleted succesfully");
    }

}
