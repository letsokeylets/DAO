package com.example.dao.controller;

import com.example.dao.Product;
import com.example.dao.exception.NotFoundSqlException;
import com.example.dao.service.DAOService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@Data
public class DAOController {
    private final DAOService service;


    @GetMapping("/products/fetch-product")
    public Product getProduct(@RequestParam("name") String name) {
        return service.getProductName(name);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotFoundSqlException.class)
    public String unauthorizedUserHandle(NotFoundSqlException e) {
        return e.getMessage();
    }
}
