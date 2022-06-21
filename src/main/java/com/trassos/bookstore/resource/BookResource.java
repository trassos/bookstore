package com.trassos.bookstore.resource;

import com.trassos.bookstore.domain.Book;
import com.trassos.bookstore.domain.Category;
import com.trassos.bookstore.dtos.BookDTO;
import com.trassos.bookstore.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/books")
public class BookResource {

    @Autowired
    private BookServices bookServices;

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAllByCategory(@RequestParam(value = "category", defaultValue ="0") Long id_cat) {
        List<Book> list = bookServices.findAllByCategory(id_cat);
        List<BookDTO> listDTO = list.stream().map(obj -> new BookDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/books")
    public ResponseEntity<List<BookDTO>> findAll() {
        List<Book> list = bookServices.findAll();
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
    public ResponseEntity<Book> update(@PathVariable Long id, @Valid @RequestBody Book book) {
        Book newBook = bookServices.update(id, book);
        return ResponseEntity.ok().body(newBook);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Book> updatePatch(@PathVariable Long id, @Valid @RequestBody Book book) {
        Book newBook = bookServices.update(id, book);
        return ResponseEntity.ok().body(newBook);
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestParam(value = "category", defaultValue = "0") Long id_cat,
                                       @Valid @RequestBody Book book) {
    Book newBook = bookServices.create(id_cat, book);
    URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/books/{id}").buildAndExpand(newBook.getId()).toUri();
    return ResponseEntity.created(uri).build();
    }

}
