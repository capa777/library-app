package com.capa.libraryapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Book> getAll(){
        return jdbcTemplate.query("Select id,title,author,rating,description from book",
                BeanPropertyRowMapper.newInstance(Book.class));
    }

    public Book getOne(int id){
        return jdbcTemplate.queryForObject("Select id,title,author,rating,description from book where id = ?", BeanPropertyRowMapper.newInstance(Book.class),id);
    }
}
