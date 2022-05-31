package com.trassos.bookstore.resource;

import com.trassos.bookstore.domain.Book;
import com.trassos.bookstore.domain.Category;
import com.trassos.bookstore.dtos.BookDTO;
import com.trassos.bookstore.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/books")
public class BookResource {

    @Autowired
    private BookServices bookServices;

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll(@RequestParam(value = "category", defaultValue ="0") Long id_cat) {
        List<Book> list = bookServices.findAll(id_cat);
        List<BookDTO> listDTO = list.stream().map(obj -> new BookDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        Book book = bookServices.findById(id);
        return ResponseEntity.ok().body(book);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        Book newBook = bookServices.update(id, book);
        return ResponseEntity.ok().body(newBook);
    }



}
