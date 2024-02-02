package com.livrariamabuko.Livraria.Mabuko.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.livrariamabuko.Livraria.Mabuko.DTOs.StockDTO;
import com.livrariamabuko.Livraria.Mabuko.exceptions.EmptyDatabaseException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.ResourceNotFoundException;
import com.livrariamabuko.Livraria.Mabuko.model.Book;
import com.livrariamabuko.Livraria.Mabuko.model.StockBook;
import com.livrariamabuko.Livraria.Mabuko.repository.BookRepository;
import com.livrariamabuko.Livraria.Mabuko.repository.StockRepository;
import com.livrariamabuko.Livraria.Mabuko.service.StockService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class StockController {

    @Autowired
    private StockRepository stockBookRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StockService stockService;

    @GetMapping("/stock")
    public List<StockBook> getAllStock() {
        List<StockBook> stockBooks = stockBookRepository.findAll();

        if (stockBooks.isEmpty()) {
            throw new EmptyDatabaseException("Nenhuma informação de estoque encontrada no banco de dados.");
        }

        return stockBooks;
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<StockBook> getStockById(@PathVariable(value = "id") long id)
            throws ResourceNotFoundException {
        StockBook stockBook = stockBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Informação de estoque com ID:: " + id + " não encontrada"));
        return ResponseEntity.ok().body(stockBook);
    }

    @GetMapping("/countStockBook")
    public long countNumberOfBooks(){
        return stockService.countBooksInStock();
    }

    @PostMapping("/stock")
    public ResponseEntity adicionarEstoque(@Valid @RequestBody StockDTO stockDTO) {
        StockBook stockBook = new StockBook();
        BeanUtils.copyProperties(stockDTO, stockBook);

        Book book = bookRepository.findById(stockDTO.idBook())
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));

        stockBook.setBook(book);

        StockBook estoqueSalvo = stockBookRepository.save(stockBook);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/stock/{id}")
    public ResponseEntity<Object> atualizarEstoque(@PathVariable(value = "id") long id,
            @Valid @RequestBody StockDTO stockDTO) throws ResourceNotFoundException {
        StockBook estoqueEncontrado = stockBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Informação de estoque com ID:: " + id + " não encontrada"));
        BeanUtils.copyProperties(stockDTO, estoqueEncontrado);

        Book book = bookRepository.findById(stockDTO.idBook())
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));

        estoqueEncontrado.setBook(book);

        final StockBook estoqueAtualizado = stockBookRepository.save(estoqueEncontrado);
        return ResponseEntity.ok().body(estoqueAtualizado);
    }

    @DeleteMapping("/stock/{id}")
    public ResponseEntity<Object> excluirEstoque(@PathVariable(value = "id") long id)
            throws ResourceNotFoundException {
        StockBook estoqueEncontrado = stockBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Informação de estoque com ID:: " + id + " não encontrada"));
        stockBookRepository.delete(estoqueEncontrado);
        return ResponseEntity.status(HttpStatus.OK).body("Informação de estoque excluída com sucesso");
    }
}
