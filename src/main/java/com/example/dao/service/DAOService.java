package com.example.dao.service;

import com.example.dao.repository.DAORepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DAOService {
    private final DAORepository daoRepository;

    public String getProductName(String name) {
        return daoRepository.getSQL(name);
    }
}
