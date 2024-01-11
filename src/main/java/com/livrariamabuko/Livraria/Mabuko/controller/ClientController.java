package com.livrariamabuko.Livraria.Mabuko.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.livrariamabuko.Livraria.Mabuko.exceptions.EmptyDatabaseException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.ResourceNotFoundException;
import com.livrariamabuko.Livraria.Mabuko.model.Client;
import com.livrariamabuko.Livraria.Mabuko.repository.ClientRepository;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        List<Client> clients = clientRepository.findAll();

        if (clients.isEmpty()) {
            throw new EmptyDatabaseException("No clients found in the database.");
        }

        return clients;
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client with ID:: " + id + " not found"));
        return ResponseEntity.ok().body(client);
    }

    @GetMapping("/clients/byName/{name}")
    public ResponseEntity<List<Client>> getClientByName(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        List<Client> clients = clientRepository.findByName(name);

        if (clients.isEmpty()) {
            throw new ResourceNotFoundException("No clients found with the name: " + name);
        }

        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }

    @PostMapping("/clients")
    public ResponseEntity addNewClient(@Valid @RequestBody Client client) {
        Client savedClient = clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") long id,
            @Valid @RequestBody Client client) throws ResourceNotFoundException {
        Client foundClient = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client with ID:: " + id + " not found"));
        BeanUtils.copyProperties(client, foundClient);
        final Client updatedClient = clientRepository.save(foundClient);
        return ResponseEntity.ok().body(updatedClient);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Object> deleteClientEntity(@PathVariable(value = "id") long id)
            throws ResourceNotFoundException {
        Client foundClient = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client with ID:: " + id + " not found"));
        clientRepository.delete(foundClient);
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully");
    }
}
