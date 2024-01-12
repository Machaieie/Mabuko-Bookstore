package com.livrariamabuko.Livraria.Mabuko.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livrariamabuko.Livraria.Mabuko.DTOs.BookDTO;
import com.livrariamabuko.Livraria.Mabuko.DTOs.SaleDTO;
import com.livrariamabuko.Livraria.Mabuko.exceptions.DuplicatedEntityException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.EmptyDatabaseException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.ResourceNotFoundException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.UnavailableQuantityException;
import com.livrariamabuko.Livraria.Mabuko.model.Author;
import com.livrariamabuko.Livraria.Mabuko.model.Book;
import com.livrariamabuko.Livraria.Mabuko.model.Publisher;
import com.livrariamabuko.Livraria.Mabuko.model.Sales;
import com.livrariamabuko.Livraria.Mabuko.repository.BookRepository;
import com.livrariamabuko.Livraria.Mabuko.repository.SaleRepository;
import com.livrariamabuko.Livraria.Mabuko.service.SaleService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("api/v1")
public class SalesController {
    

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleService salesService;

    @GetMapping("/sales")
    public List<Sales> getAllSales(){
        List<Sales> sales = saleRepository.findAll();
        if(sales.isEmpty()){
            throw new EmptyDatabaseException("No sales found in the database.");
        }

        return sales;
    }
    @GetMapping("/sale/{id}")
    public ResponseEntity<Sales> getSaleById(@PathVariable(value = "id") long id)throws ResourceNotFoundException{
        Sales sale = saleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sale with ID:: " + id + " not found"));
        return ResponseEntity.ok().body(sale);
    }

    @PostMapping("addSale")
    public ResponseEntity<List<Sales>> makeSales(@Valid @RequestBody List<SaleDTO> saleDTOList) {
        try {
            List<Sales> salesList = salesService.makeSales(saleDTOList);
            return ResponseEntity.status(HttpStatus.CREATED).body(salesList);
        } catch (UnavailableQuantityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            // ou lance uma exceção apropriada e a deixe ser manipulada globalmente
        }
    }

}
