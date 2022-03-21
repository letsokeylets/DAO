package com.example.dao.controller;

import com.example.dao.exception.NotFoundSqlException;
import com.example.dao.repository.DAORepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@Data
public class DAOController {
    private final DAORepository repository;


    @GetMapping("/products/fetch-product")
    public List<String> getProduct(@RequestParam("name") String name) {
        return repository.getProductName(name);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotFoundSqlException.class)
    public String unauthorizedUserHandle(NotFoundSqlException e) {
        return e.getMessage();
    }
}
