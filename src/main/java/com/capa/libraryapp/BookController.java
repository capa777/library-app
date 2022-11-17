package com.capa.libraryapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookRepo bookRepo;

    @GetMapping("/books")
        public List<Book> getAll(){
            return bookRepo.getAll();
    }

    @GetMapping("/books/{id}")
    public Book getOne(@PathVariable("id") int id){
        return bookRepo.getOne(id);
    }
}
