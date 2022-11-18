package com.capa.libraryapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/books")
    public int add(@RequestBody List<Book> books){
        bookRepo.save(books);
        return 1;
    }

    @PatchMapping("/books/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Book bookUpdate){
        Book book = bookRepo.getOne(id);
        if(book != null){
            if (bookUpdate.getAuthor() != null) book.setAuthor(bookUpdate.getAuthor());
            if(bookUpdate.getTitle() != null) book.setTitle(bookUpdate.getTitle());
            if(bookUpdate.getRating() > 0 && bookUpdate.getRating() <= 10) book.setRating(bookUpdate.getRating());
            if(bookUpdate.getDescription() != null) book.setDescription(bookUpdate.getDescription());
            bookRepo.update(book);
            return 1;
        }else return 0;
    }

    @DeleteMapping("/books/{id}")
    public int delete(@PathVariable("id") int id){
        return bookRepo.delete(id);
    }
}
