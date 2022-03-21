package com.example.dao.repository;

import com.example.dao.exception.NotFoundSqlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DAORepository {

    private static final String SQL_SCRIPT = "script.sql";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<String> getProductName(String name) {
        try {
            final String scriptSql = read(SQL_SCRIPT);

            final SqlParameterSource namedParameters = new MapSqlParameterSource("name", name);
            final List<String> result = namedParameterJdbcTemplate.queryForList(scriptSql,
                    namedParameters,
                    String.class);
            return result;
        } catch (DataAccessException e) {
            throw new NotFoundSqlException("Имя отсуствует в базе данных или на одно имя существует несколько записей");
        }
    }

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
