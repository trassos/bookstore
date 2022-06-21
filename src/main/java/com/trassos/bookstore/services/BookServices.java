package com.trassos.bookstore.services;

import com.trassos.bookstore.domain.Book;
import com.trassos.bookstore.domain.Category;
import com.trassos.bookstore.dtos.BookDTO;
import com.trassos.bookstore.exceptions.ObjectNotFoundException;
import com.trassos.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServices {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryServices categoryServices;
    public List<Book> findAll() {
    return bookRepository.findAll();
    }

    public List<Book> findAllbyCategory(Long id_cat) {
        return bookRepository.findAll(); }

    public Book findById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado id: "+id+ ", Tipo: "+ Book.class.getName()));
    }

    public void delete(Long id) {
        Book book = findById(id);
        bookRepository.delete(book);
    }

    public List<Book> findAll(Long id_cat) {
        categoryServices.findById(id_cat);
        return bookRepository.findAllByCategory(id_cat);
    }

    public Book update(Long id, Book book) {
        Book newBook = findById(id);
        updateData(newBook, book);
        return bookRepository.save(newBook);
    }
    public Book updatePatch(Long id, Book book) {
        Book newBook = findById(id);
        updateData(newBook, book);
        return bookRepository.save(newBook);
    }

    private void updateData(Book newBook, Book book) {
        newBook.setTitle(book.getTitle());
        newBook.setAuthor_name(book.getAuthor_name());
        newBook.setText(book.getText());
    }

    public Book create(Long id_cat, Book book) {
        book.setId(null);
        Category cat = categoryServices.findById(id_cat);
        book.setCategory(cat);
        return bookRepository.save(book);
    }
}
