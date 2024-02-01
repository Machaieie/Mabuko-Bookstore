package com.livrariamabuko.Livraria.Mabuko.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.livrariamabuko.Livraria.Mabuko.DTOs.SaleDTO;
import com.livrariamabuko.Livraria.Mabuko.exceptions.ResourceNotFoundException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.UnavailableQuantityException;
import com.livrariamabuko.Livraria.Mabuko.model.Book;
import com.livrariamabuko.Livraria.Mabuko.model.Sales;
import com.livrariamabuko.Livraria.Mabuko.model.StockBook;
import com.livrariamabuko.Livraria.Mabuko.repository.BookRepository;
import com.livrariamabuko.Livraria.Mabuko.repository.SaleRepository;
import com.livrariamabuko.Livraria.Mabuko.repository.StockRepository;

import jakarta.transaction.Transactional;

@Service
public class SaleService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private StockRepository stockRepository;

    @Transactional
    public List<Sales> makeSales(List<SaleDTO> saleItems) throws UnavailableQuantityException {
        List<Sales> sales = new ArrayList<>();
        for (SaleDTO saleDTO : saleItems) {
            Book book = bookRepository.findById(saleDTO.book_id()).orElseThrow(
                    () -> new ResourceNotFoundException("Book with ID: " + saleDTO.book_id() + " not found!"));

            StockBook stock = stockRepository.findByBookId(saleDTO.book_id());

            if (stock.getAmount() < saleDTO.amount()) {
                throw new UnavailableQuantityException("Insufficient quantity in stock for sale.");
            }

            stock.setAmount(stock.getAmount() - saleDTO.amount());
            stockRepository.save(stock);

            Sales sale = new Sales();
            sale.setBook(book);
            sale.setAmount(saleDTO.amount());
            sale.setSaleDate(saleDTO.saleDate());

            BigDecimal total = BigDecimal.valueOf(saleDTO.amount())
                    .multiply(BigDecimal.valueOf(book.getPrice()))
                    .setScale(2, RoundingMode.UP);

            sale.setTotal(total.doubleValue());

            sales.add(sale);
        }

        return saleRepository.saveAll(sales);
    }

    public long countSales(){
        return saleRepository.count();
    }

}
