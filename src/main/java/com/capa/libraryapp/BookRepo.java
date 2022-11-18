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
        return jdbcTemplate.query("SELECT id,title,author,rating,description FROM book",
                BeanPropertyRowMapper.newInstance(Book.class));
    }

    public Book getOne(int id){
        return jdbcTemplate.queryForObject("SELECT id,title,author,rating,description FROM book WHERE id = ?", BeanPropertyRowMapper.newInstance(Book.class),id);
    }

    public int save(List<Book> books){
        for (Book book : books) {
            jdbcTemplate.update("INSERT INTO book(title,author,rating,description) VALUES (?,?,?,?)",
                    book.getTitle(),book.getAuthor(),book.getRating(),book.getDescription());
        }
        return 1;
    }

    public int update(Book book){
        return jdbcTemplate.update("UPDATE book SET title=?,author=?,rating=?,description=? WHERE id = ?",
                book.getTitle(),book.getAuthor(),book.getRating(),book.getDescription(),book.getId());
    }

    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM book WHERE id = ?",id);
    }
}
