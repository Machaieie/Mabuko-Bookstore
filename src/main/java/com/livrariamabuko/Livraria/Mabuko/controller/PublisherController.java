package com.livrariamabuko.Livraria.Mabuko.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.livrariamabuko.Livraria.Mabuko.DTOs.PublisherDTO;
import com.livrariamabuko.Livraria.Mabuko.exceptions.EmptyDatabaseException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.ResourceNotFoundException;
import com.livrariamabuko.Livraria.Mabuko.model.Publisher;
import com.livrariamabuko.Livraria.Mabuko.repository.PublisherRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PublisherController {

    @Autowired
    private PublisherRepository publisherRepository;

    @GetMapping("/publishers")
    public List<Publisher> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        if (publishers.isEmpty()) {
            throw new EmptyDatabaseException("No publishers found in the database.");
        }

        return publishers;
    }

    @GetMapping("/publisher/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable(value = "id") long id)
            throws ResourceNotFoundException {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher with ID " + id + " not found"));
        return ResponseEntity.ok().body(publisher);
    }

    @GetMapping("/publishers/byName/{name}")
    public ResponseEntity<List<Publisher>> getPublisherByName(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        List<Publisher> publishers = publisherRepository.findByName(name);

        if (publishers.isEmpty()) {
            throw new ResourceNotFoundException("No publishers found with the name: " + name);
        }

        return ResponseEntity.status(HttpStatus.OK).body(publishers);
    }

    @PostMapping("/publisher")
    public ResponseEntity addNewPublisher(@Valid @RequestBody PublisherDTO publisherDTO) {
        Publisher publisher = new Publisher();
        BeanUtils.copyProperties(publisherDTO, publisher);
        Publisher savedPublisher = publisherRepository.save(publisher);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPublisher);
    }

    @PutMapping("/publisher/{id}")
    public ResponseEntity<Object> updatePublisher(@PathVariable(value = "id") long id,
            @Valid @RequestBody PublisherDTO publisherDTO) throws ResourceNotFoundException {
        Publisher foundedPublisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher with ID " + id + " not found"));

        foundedPublisher.setName(publisherDTO.name());
        foundedPublisher.setLocation(publisherDTO.location());
        foundedPublisher.setPhone(publisherDTO.phone());
        foundedPublisher.setNuit(publisherDTO.nuit());

        final Publisher updatedPublisher = publisherRepository.save(foundedPublisher);
        return ResponseEntity.ok().body(updatedPublisher);
    }

    @DeleteMapping("/publisher/{id}")
    public ResponseEntity<Object> deletePublisherEntity(@PathVariable(value = "id") long id)
            throws ResourceNotFoundException {
        Publisher foundedPublisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher with ID " + id + " not found"));

        publisherRepository.delete(foundedPublisher);
        return ResponseEntity.status(HttpStatus.OK).body("Publisher deleted successfully");
    }
}
