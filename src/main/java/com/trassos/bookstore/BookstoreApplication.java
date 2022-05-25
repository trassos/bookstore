package com.trassos.bookstore;

import com.trassos.bookstore.domain.Book;
import com.trassos.bookstore.domain.Category;
import com.trassos.bookstore.repositories.BookRepository;
import com.trassos.bookstore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner{
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Informática", "Livros de TI");

		Book b1 = new Book(null, "Clean Code", "Robert Martin", "Lorem ipsum", cat1);
		Book b2 = new Book(null, "TDD", "Gary", "Lorem ipsum", cat1);
		Book b3 = new Book(null, "Bible", "Jesus", "Nomi", cat1);


		cat1.getBooks().addAll(Arrays.asList(b1, b2, b3));

		categoryRepository.save(cat1);
		bookRepository.saveAll(Arrays.asList(b1, b2, b3));
	}
}
