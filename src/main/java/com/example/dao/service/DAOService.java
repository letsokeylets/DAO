package com.example.dao.service;

import com.example.dao.Product;
import com.example.dao.exception.NotFoundSqlException;
import com.example.dao.repository.DAORepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class DAOService {
    private final DAORepository daoRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Product getProductName(String name) {
        try {
            jdbcTemplate.execute(daoRepository.getSQL("schema.sql"));
            String scriptSql = daoRepository.getSQL("script.sql");
            Product product = namedParameterJdbcTemplate.queryForObject(scriptSql,
                    Map.of("name", name),
                    (resultSet, i) -> new Product(resultSet.getString("product_name")));
            return product;
        } catch (DataAccessException e) {
            throw new NotFoundSqlException("Имя отсуствует в базе данных или на одно имя существует несколько записей");
        }
    }
}
