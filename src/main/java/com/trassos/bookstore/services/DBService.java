package com.trassos.bookstore.services;

import com.trassos.bookstore.domain.Book;
import com.trassos.bookstore.domain.Category;
import com.trassos.bookstore.repositories.BookRepository;
import com.trassos.bookstore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void instanciaBaseDeDados() {

        Category cat1 = new Category(null, "Informática", "Livros de TI");
        Category cat2 = new Category(null, "Novela", "Livros Novelísticos");
        Category cat3 = new Category(null, "Clássicos", "Livros Clássicos");



        Book b1 = new Book(null, "Clean Code", "Robert Martin", "Lorem ipsum", cat1);
        Book b2 = new Book(null, "TDD", "Gary", "Lorem ipsum", cat1);
        Book b3 = new Book(null, "Bible", "Jesus", "Nomi", cat1);
        Book b4 = new Book(null, "Senhor dos Anéis", "Tolkien", "Lá vai o frodo", cat2);
        Book b5 = new Book(null, "Odisseia", "Homero", "Mano Ulisses deu a ideia do cavalo", cat3);


        cat1.getBooks().addAll(Arrays.asList(b1, b2, b3));
        cat2.getBooks().add(b4);
        cat3.getBooks().add(b5);


        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        bookRepository.saveAll(Arrays.asList(b1, b2, b3, b4, b5));

    }

}
